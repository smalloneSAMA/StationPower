import pandas as pd
import pymysql.cursors
import math
import datetime
import calendar
from dateutil import rrule

province = "jiangxi"
path = 'E:/'

'''
config = {
    'host':"172.16.135.8",
    'port':3306,
    'user':'jiangxi',
    'password':'456123',
    'db':province,
    'charset':'utf8',
    'cursorclass':pymysql.cursors.DictCursor,
}
'''
'''
config = {
    'host':"172.16.135.47",
    'port':3306,
    'user':'root',
    'password':'10086',
    'db':'electric',
    'charset':'utf8',
    'cursorclass':pymysql.cursors.DictCursor,
}
'''

config = {
    'host': '172.16.135.19',
    'port': 3306,
    'user': 'root',
    'password': 'hadoop',
    'db': 'jiangxi_power',
    'charset':'utf8',
    'cursorclass':pymysql.cursors.DictCursor,
}


class getReliability(object):

    def __init__(self):
        self.df_fiberfault = pd.read_csv(path + province + '_fiber_fault.csv', encoding='utf-8', sep=',')
        self.k = self.df_fiberfault.shape[0]
        self.dict3 = {}
        for i in range(self.k):
            if (type(self.df_fiberfault.ix[i].ALARM_TIME_NEW) == type('a')):
                id = self.df_fiberfault.loc[i, 'OBJ_ID']
                self.dict3[id] = self.df_fiberfault.loc[i, 'ALARM_TIME_NEW']
                # print(self.dict3[id])
            else:
                continue

        self.monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07',
                     '2016-08', '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02',
                     '2017-03']

# ******************另一个方法是fiberFailureByType
    def getFiberSegReliability(self, ane, zne):
        connection = pymysql.connect(**config)
        try:
            with connection.cursor() as cursor:
                sql1 = "select PAR_STATION from t_ne where obj_id= '%s'" % ane
                # cursor.execute(sql1)
                # parstation1 = cursor.fetchone()
                PAR_STATION1 = pd.read_sql(sql1, connection)
                sql2 = "select PAR_STATION from t_ne where obj_id= '%s'" % zne
                # cursor.execute(sql2)
                # parstation2 = cursor.fetchone()
                PAR_STATION2 = pd.read_sql(sql2, connection)
                fibersegID = 0
                len_fibr = 0
                if (len(PAR_STATION2) > 0) & (len(PAR_STATION1) > 0) :
                    parstation1 = PAR_STATION1.ix[0].PAR_STATION
                    parstation2 = PAR_STATION2.ix[0].PAR_STATION
                    # print(parstation1)
                    # print(ane)
                    # print(zne)
                    # print(parstation2)
                    df_fiberseg = self.df_fiberfault[(self.df_fiberfault["A_RESOBJID"] == parstation1) & (self.df_fiberfault["Z_RESOBJID"] == parstation2)|
                    (self.df_fiberfault["A_RESOBJID"] == parstation2) & (self.df_fiberfault["Z_RESOBJID"] == parstation1)]
                    # print(df_fiberseg)
                    len_fibr = len(df_fiberseg)
                avg_reliability = float(1.0)
                if(len_fibr != 0):
                    index = df_fiberseg.index
                    for i in index:
                        seg = df_fiberseg.ix[i]
                        fibersegID = seg.OBJ_ID
                        alarm_time_new = seg.ALARM_TIME_NEW
                        if (type(alarm_time_new) == type('a')):
                            alarm_timelst = alarm_time_new.split("[")[1].split("]")[0].split(",")
                            # print(alarm_timelst)
                            f_failure_time = 0
                            len_month = len(self.monthlist)
                            totalseconds = len_month * 30 * 24 * 3600
                            for f in range(0, len(alarm_timelst) - 1):
                                f_failure_time += int(alarm_timelst[f])
                            avg_reliability = 1 - round(float(f_failure_time/totalseconds), 3)
                            if avg_reliability > 1.0:
                                avg_reliability = 1.0
                                break

        finally:
            connection.close()
        # print(avg_reliability)
        return fibersegID, avg_reliability

    # 月故障率
    def fs_failure(self, fiberseg_id, month):
        start_time = datetime.datetime(2016, 1, 1)
        until_time = datetime.datetime.strptime(month, '%Y-%m')
        months = rrule.rrule(rrule.MONTHLY, dtstart=start_time, until=until_time).count() - 1
        if fiberseg_id in self.dict3:
            alarm_timelst = self.dict3[fiberseg_id].split("[")[1].split("]")[0].split(",")
            return float(alarm_timelst[months])
        else:
            return float(0)

    def getfiberWithType(self):
        connection = pymysql.connect(**config)
        df_fiberfault = pd.read_csv(path + province + '_fiber_fault.csv', encoding='utf-8', sep=',')
        try:
            with connection.cursor() as cursor:
                sql_getfibertype = "select OBJ_ID, `NAME`, FIBER_TYPE, LINE_NUMBER_USED from t_fiber_seg "
                df_fibertype = pd.read_sql(sql_getfibertype, connection)
                df_mergeFiber =  pd.merge(df_fiberfault, df_fibertype, how='left', on='OBJ_ID')
                # print(df_mergeFiber.head(5))
                df_mergeFiber.to_csv(path + province + '_fiberfaultWithType.csv', index=False, header=True)
        finally:
            connection.close()

    # 各类型光缆百公里可靠性  马克1
    def AvgRGroupByType(self,fibertype):
        # fibertype = 1
        monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07',
                     '2016-08', '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02',
                     '2017-03']
        df_fiberfaultWithType = pd.read_csv('E:\jiangxi_fiberfaultWithType.csv', encoding='gbk', sep=',')
        df_OPGW = df_fiberfaultWithType[(df_fiberfaultWithType['FIBER_TYPE'] == fibertype)]
        df_OPGW1 = df_fiberfaultWithType[(df_fiberfaultWithType['FIBER_TYPE'] == fibertype) & (df_fiberfaultWithType['LENGTH'] > 0)]

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
        print(fibertype)
        print(1.0 - opgwAvgF)
        # print(totalFiberLen_opgw)



    def mstpByGroup(self,fibertype):
        monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07',
                     '2016-08', '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02',
                     '2017-03']
        totalseconds = 0
        for month in monthlist:
            days = calendar.monthrange(int(month.split('-')[0]), int(month.split('-')[1]))[1]
            seconds = days * 24 * 3600
            totalseconds += seconds
        df_fiberfaultWithType = pd.read_csv(path + province + '_fiberfaultWithType.csv', encoding='gbk', sep=',')
        # df_OPGW = df_fiberfaultWithType[(df_fiberfaultWithType['FIBER_TYPE'] == fibertype) & (df_fiberfaultWithType['LENGTH'] > 0)]
        df_OPGW = df_fiberfaultWithType[(df_fiberfaultWithType['FIBER_TYPE'] == fibertype)]

        index_opgw = df_OPGW.index
        totalFiberLen_opgw = df_OPGW['LENGTH'].sum()
        total_opgwR = 0
        # 计算每种类型光缆MSTP
        avg_reliability = 1.0
        for i in index_opgw:
            fiberBase = df_fiberfaultWithType.ix[i]
            sigle_len = fiberBase.LENGTH
            alarm_num = fiberBase.ALARM_NUM
            fibersegID = fiberBase.OBJ_ID
            alarm_time = fiberBase.ALARM_TIME
            f_failure_time = 0
            f_failure_num = 0
            if (type(alarm_num) == type('a')):
                alarm_timelst = alarm_time.split("[")[1].split("]")[0].split(",")
                alarm_numlst = alarm_num.split("[")[1].split("]")[0].split(",")
                for f in range(0, len(alarm_timelst) - 1):
                    f_failure_time += int(alarm_timelst[f])
                    f_failure_num += int(alarm_numlst[f])
                f_normal_time = totalseconds - f_failure_time
            amstp = f_normal_time/f_failure_num


    def fiberRByType2(fiberseg_id):
        df_fiberfaultByType = pd.read_csv(path + province + '_fiberfaultWithType.csv', encoding='utf-8', sep=',')
        df_fiber = df_fiberfaultByType[df_fiberfaultByType['OBJ_ID'] == fiberseg_id]
        fbase = df_fiber.ix[0]
        type = fbase.FIBER_TYPE
        length = fbase.LENGTH
        fiberR = 1
        if(type == 1):
            fiberR = 0.860*length
        elif type==2:
            fiberR = 0.949*length
        else:
            fiberR = 0.36*length
        return fiberR


    def fiberFailureByType(self, ane, zne):
        df_fiberfaultByType = pd.read_csv(path + province + '_fiberfaultWithType.csv', encoding='gbk', sep=',')
        connection = pymysql.connect(**config)
        try:
            with connection.cursor() as cursor:
                sql1 = "select PAR_STATION from t_ne where obj_id= '%s'" % ane
                # cursor.execute(sql1)
                # parstation1 = cursor.fetchone()
                PAR_STATION1 = pd.read_sql(sql1, connection)
                sql2 = "select PAR_STATION from t_ne where obj_id= '%s'" % zne
                # cursor.execute(sql2)
                # parstation2 = cursor.fetchone()
                PAR_STATION2 = pd.read_sql(sql2, connection)
                fibersegID = 0
                len_fibr = 0
                if (len(PAR_STATION2) > 0) & (len(PAR_STATION1) > 0):
                    parstation1 = PAR_STATION1.ix[0].PAR_STATION
                    parstation2 = PAR_STATION2.ix[0].PAR_STATION
                    # print(parstation1)
                    # print(ane)
                    # print(zne)
                    # print(parstation2)
                    df_fiberseg = df_fiberfaultByType[(df_fiberfaultByType["A_RESOBJID"] == parstation1) & (df_fiberfaultByType["Z_RESOBJID"] == parstation2) |
                                                      (df_fiberfaultByType["A_RESOBJID"] == parstation2) & (df_fiberfaultByType["Z_RESOBJID"] == parstation1)]
                    # print(df_fiberseg)
                    len_fibr = len(df_fiberseg)
                # avg_reliability = float(1.0)
                fibersegID = '101010'
                fiberR = float(1.0)
                if (len_fibr != 0):
                    index = df_fiberseg.index
                    for i in index:
                        fbase = df_fiberseg.ix[i]
                        fibersegID = fbase.OBJ_ID
                        type = fbase.FIBER_TYPE
                        length = fbase.LENGTH
                        AvgFiberR = 1.0
                        if(type == 1):
                            AvgFiberR = 0.999973
                            # print('1')
                        elif type==2:
                            AvgFiberR = 0.999959
                        else:
                            AvgFiberR = 0.999280
                            # print('3')

                        # print(length)
                        fiberR = pow(AvgFiberR, int(length))
                        # print(fibersegID)
                        # print(fiberR)
                        return fibersegID, fiberR
                else: return fibersegID, 1.0



        finally:
            connection.close()
        # print(fibersegID)
        # print(fiberR)
        # return fibersegID, fiberR

g=getReliability()
print('------------------------------------------------')
g.AvgRGroupByType(1)
print('------------------------------------------------')
g.AvgRGroupByType(2)
print('------------------------------------------------')
g.AvgRGroupByType(3)
print('------------------------------------------------')
g.fiberFailureByType('227DF4CE-7EDD-4C17-B72F-17E5C8C3F5C5-56892', '227DF4CE-7EDD-4C17-B72F-17E5C8C3F5C5-57839')

    # zne = 'DCC58927-7D2A-42D8-9B8C-22CBED38199F-02914'
    # ane = 'DB5D5A6E-7332-4606-B98A-1E2ED9D20115-00027'
    # print(str(fiberFailureByType('self', ane, zne)))



