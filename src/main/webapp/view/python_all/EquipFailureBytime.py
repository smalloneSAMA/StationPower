import pandas as pd
import numpy as np
import datetime
import calendar
from dateutil import rrule

path = "E:\360Downloads\StationPower\src\main\webapp\view\CSV"  #找文件路径
province = "jiangxi"
class FailureselectByMonth(object):

    def __init__(self):
        self.df = pd.read_csv(path + province + "_electric_alarm_clean.csv", encoding='gbk', sep=',')
        self.k = self.df.shape[0]
        self.dict3 = {}
        for i in range(self.k):
            id = self.df.loc[i,'NE_OBJ_ID']
            # self.dict3[id] = self.df.loc[i, 'ALARM_TIME_NEW']
            self.dict3[id] = self.df.loc[i, 'ALARM_TIME']
        df1 = pd.read_csv(path + province + "_electric_ne_work_year.csv", encoding='gbk', sep=',')
        h = df1.shape[0]
        self.dict1 = {}
        self.dict2 = {}
        for i in range(h):
            id = df1.loc[i, 'OBJ_ID']
            self.dict1[id] = df1.iloc[i, 2]
            self.dict2[id] = df1.iloc[i, 3]
        self.df.loc[:, 'SUM'] = ""
        self.df.loc[:, 'COUNT'] = ""
        for i in range(self.k):
            if (type(self.df.loc[i, 'ALARM_TIME']) == type('a')):
            # if (type(self.df.loc[i, 'ALARM_TIME_NEW']) == type('a')):
                self.df.iat[i, 9] = np.array(eval(self.df.loc[i, 'ALARM_TIME']))
                # self.df.iat[i, 11] = np.array(eval(self.df.loc[i, 'ALARM_TIME_NEW']))
                self.df.loc[i, 'SUM'] = 'True'
        self.groups = self.df.groupby(["PRODUCER_ID", "WORK_YEAR"])["COUNT"]
        self.groups1 = self.df.groupby(["WORK_YEAR"])["COUNT"]
        self.groups2 = self.df.groupby(["PRODUCER_ID"])["COUNT"]
        self.groups3 = self.df.groupby(["SUM"])["COUNT"]
        self.pieces = dict(list(self.groups))
        self.pieces1 = dict(list(self.groups1))
        self.pieces2 = dict(list(self.groups2))
        self.pieces3 = dict(list(self.groups3))


    def failure (self,equipment_id, month):
        start_time = datetime.datetime(2016, 1, 1)
        until_time = datetime.datetime.strptime(month, '%Y-%m')
        months = rrule.rrule(rrule.MONTHLY, dtstart=start_time, until=until_time).count() - 1
        # if (equipment_id in self.dict3) & (self.dict3[equipment_id] != '[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]'):
        if equipment_id in self.dict3:
            alarm_num = self.dict3[equipment_id].split("[")[1].split("]")[0].split(",")
        elif equipment_id in self.dict1 and equipment_id in self.dict2:
            if (self.dict1[equipment_id], self.dict2[equipment_id]) in self.pieces:
                alarm_num = self.pieces[self.dict1[equipment_id], self.dict2[equipment_id]].mean()
            elif (self.dict2[equipment_id] in self.pieces1):
                alarm_num = self.pieces1[self.dict2[equipment_id]].mean()
            elif (self.dict1[equipment_id] in self.pieces2):
                alarm_num = self.pieces2[self.dict1[equipment_id]].mean()
        else:
            alarm_num = self.pieces3['True'].mean()
        return (float)(alarm_num[months])




# s = Failureselect()
#     s = FailureselectByMonth()
    monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07', '2016-08',
                     '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02', '2017-03']
# for month in monthlist:
#     days = calendar.monthrange(int(month.split('-')[0]), int(month.split('-')[1]))[1]
#     seconds = days * 24 * 3600
#     print(s.failure('FA01F55D-6121-499C-9C0E-40E5E24BB16A-00002', '2016-04') / seconds)

# days = monthRange = calendar.monthrange(2016, 4)[1]
# seconds = days*24*3600
#
# print(str(s.failure('FA01F55D-6121-499C-9C0E-40E5E24BB16A-00002', '2016-04')/seconds))