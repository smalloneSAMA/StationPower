import pandas as pd
import pymysql.cursors
import math
import datetime
import calendar
from dateutil import rrule

province = "jiangxi"
path = 'E:/'

config = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'password': 'admin',
    'db': 'jiangxi_power',
    'charset':'utf8',
    'cursorclass':pymysql.cursors.DictCursor,
}

# class getReliability(object):
    # 各类型光缆百公里可靠性
def AvgRGroupByType(fibertype):
    # fibertype = 1
    monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07',
                 '2016-08', '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02',
                 '2017-03']
    df_fiberfaultWithType = pd.read_csv(path + province + '_fiberfaultWithType.csv', encoding='gbk', sep=',')
    df_OPGW = df_fiberfaultWithType[(df_fiberfaultWithType['FIBER_TYPE'] == fibertype)]
    df_OPGW1 = df_fiberfaultWithType[
        (df_fiberfaultWithType['FIBER_TYPE'] == fibertype) & (df_fiberfaultWithType['LENGTH'] > 0)]

    index_opgw = df_OPGW1.index
    totalFiberLen_opgw = df_OPGW1['LENGTH'].sum()
    total_opgwFWithWeight = 0.0
    total_opgwF = 0.0
    # 统计每种类型光缆单位长度平均故障率
    avg_reliability = 1.0
    avg_failure = 0.0
    for i in index_opgw:
        fiberBase = df_fiberfaultWithType.ix[i]
        sigle_len = fiberBase.LENGTH
        fibersegID = fiberBase.OBJ_ID
        alarm_time_new = fiberBase.ALARM_TIME_NEW
        if (type(alarm_time_new) == type('a')):
            alarm_timelst = alarm_time_new.split("[")[1].split("]")[0].split(",")
            f_failure_time = 0
            totalseconds = 0
            # len_month = len(monthlist)
            # totalseconds = len_month * 30 * 24 * 3600
            for month in monthlist:
                days = calendar.monthrange(int(month.split('-')[0]), int(month.split('-')[1]))[1]
                seconds = days * 24 * 3600
                totalseconds += seconds
            for f in range(0, len(alarm_timelst) - 1):
                f_failure_time += int(alarm_timelst[f])
            avg_failure = float(f_failure_time / totalseconds)
            # avg_reliability = 1.0 - avg_failure
            # 每条光缆段上单位长度光缆故障率
            sigleavg_opgrF = pow(avg_failure, sigle_len)
            # sigleavg_opgrF = pow(avg_failure, 100 / sigle_len)
        else:
            sigleavg_opgrF = 0.0
            # total_opgwFWithWeight += sigleavg_opgrF*sigle_len
            # opgwAvgF = total_opgwFWithWeight / totalFiberLen_opgw
        total_opgwF += sigleavg_opgrF
    opgwAvgF = total_opgwF / len(index_opgw)
    if fibertype == 1:
        fibertype = 'OPGW'
    if fibertype == 2:
        fibertype = 'ADSS'
    if fibertype == 3:
        fibertype = '普通光缆'

    reliability = 1-opgwAvgF
    fibertype = str(fibertype)
    totalFiberLen_opgw = str(totalFiberLen_opgw)
    reliability = str(reliability)
    print(totalFiberLen_opgw)
    print(reliability)
    print(fibertype)

    connection = pymysql.connect(**config)
    try:
        with connection.cursor() as cur:
            # sql = "SELECT * from t_fiber_re WHERE type='%s'" %fibertype
            # cur.execute(sql)
            # results = cur.fetchall()
            # print(results)

            sql = "UPDATE t_fiber_re SET length='%s',reliability='%s' WHERE type='%s'" %(totalFiberLen_opgw, reliability, fibertype)
            cur.execute(sql)

            connection.commit()
    finally:
        connection.close()



if __name__ == '__main__':
    AvgRGroupByType(1)
    AvgRGroupByType(2)
    AvgRGroupByType(3)