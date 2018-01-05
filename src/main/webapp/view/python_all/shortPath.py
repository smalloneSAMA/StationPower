import networkx as nx
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import pymysql as db
import numpy as np
import random
import matplotlib.cm as cm
import pandas as pd
import operator
import math
from itertools import islice
from sqlalchemy import create_engine
import sys


#    @description 射线法判断点是否在多边形内部
#    @param {Object} p 待判断的点，格式：{ x: X坐标, y: Y坐标 }
#    @param {Array} poly 多边形顶点，数组成员的格式同 p
#    @return {String} 点 p 和多边形 poly 的几何关系


# 将第一个点复制到结尾
# 得到计算上下左右极限，得到最小矩形
# 得到最小矩形高度，设置分割份数，得到每份高度
# 按每份高度
# 射线判断函数

def ray_casting(p, poly):

    px = p[0]
    py = p[1]
    flag = False

    i = 0
    l = len(poly)
    j = l-1

    while i < l:
        sx = float(poly[i][0])
        sy = float(poly[i][1])
        tx = float(poly[j][0])
        ty = float(poly[j][1])

        # 点与多边形顶点重合
        if (sx == px and sy == py) or (tx == px and ty == py):
            return True
        # 判断线段两端点是否在射线两侧
        if sy < py and ty >= py or sy >= py and ty < py:

            # 线段上与射线 Y 坐标相同的点的 X 坐标
            x = sx + (py - sy) * (tx - sx) / (ty - sy)

            # 点在多边形的边上
            if x == px:
                return True
            # 射线穿过多边形的边界
            if x > px:
                flag = not flag
                
        j = i
        i += 1
    # 射线穿过多边形边界的次数为奇数时点在多边形内
    if flag:
        return True
    else:
        return False


#  获取需要查询的省份 start
# 中文
# province_gbk = sys.argv[1]
province_gbk = '江西'
# 中文省份对应数据库名
dict_provinces = {
                '江西': 'jiangxi_before170619', '安徽': 'anhui', '重庆': 'chongqing', '甘肃': 'gansu', '河南': 'henan',
                '湖北': 'hubei', '湖南': 'hunan', '江苏': 'jiangsu', '辽宁': 'liaoning', '宁夏': 'ningxia',
                '山西': 'shan1xi', '山东': 'shandong', '四川': 'sicuan', '新疆': 'xinjiang', '北京': 'beijing',
                '成都': 'chengdu', '福建': 'fujian', '河北': 'hebei', '黑龙江': 'heilongjiang', '吉林': 'jilin',
                '内蒙古': 'mengdong', '青海': 'qinghai', '陕西': 'shan3xi', '天津': 'tianjin', '西藏': 'xizang',
                '浙江': 'zhejiang'
            }
# 中文省份对应告警文件名
dict_alarmData = {
                '江西': 'jiangxi', '安徽': 'anhui', '重庆': 'chongqing', '甘肃': 'gansu', '河南': 'henan',
                '湖北': 'hubei', '湖南': 'hunan', '江苏': 'jiangsu', '辽宁': 'liaoning', '宁夏': 'ningxia',
                '山西': 'shan1xi', '山东': 'shandong', '四川': 'sicuan', '新疆': 'xinjiang', '北京': 'beijing',
                '成都': 'chengdu', '福建': 'fujian', '河北': 'hebei', '黑龙江': 'heilongjiang', '吉林': 'jilin',
                '内蒙古': 'mengdong', '青海': 'qinghai', '陕西': 'shan3xi', '天津': 'tianjin', '西藏': 'xizang',
                '浙江': 'zhejiang'
            }

# 英文
province = dict_alarmData[province_gbk]

# 数据库名
database = dict_provinces[province_gbk]

#  获取需要查询的省份 end

"""
import matplotlib as mpl
mpl.rcParams['font.sans-serif'] = [u'SimHei']
mpl.rcParams['axes.unicode_minus'] = False
"""

# 连接数据库
conn = db.connect(host='172.16.135.6', user='root', passwd='10086', db=database, port=3306, charset='utf8')
# conn = db.connect(host='172.16.135.8', user='jiangxi', passwd='456123', db=province, port=3306, charset='utf8')


# 获取k条最短路径
def k_shortest_paths(G, source, target, k, weight=None):
    return list(islice(nx.shortest_simple_paths(G, source, target, weight=weight), k))

# 是否寻找最短路径
is_find_path = False
"""
# 计算每条业务的权值
cur_buz = conn.cursor()
cur_buz.execute("select OBJ_ID,BUZ_TYPE,DISPATCH_LEVEL from t_buz")
result_buz = cur_buz.fetchall()
data_buz = np.array(result_buz)
buz_weight_dic = {}  # 字典存放每条业务的权值（分数）
buz_type_point = {1: 10, 2: 10, 3: 10, 4: 9, 6: 8, 7: 7.5, 8: 7.0, 9: 6.0, 10: 5.0, 11: 5.0, 12: 4.0, 14: 2.0, 16: 3.0, 17: 7.0, 18: 4.5}  # 每个业务类型对应的分数
buz_dispatch_point = {1: 10, 2: 9.0, 3: 8.0, 4: 7.0, 5: 6.0}  # 每个调度等级对应的分数
for i in range(data_buz.shape[0]):
    wt = buz_type_point[int(data_buz[i][1])]
    wd = buz_dispatch_point[int(data_buz[i][2])]
    buz_weight_dic[data_buz[i][0]] = 0.7 * wt + 0.3 * wd
"""

# 读取告警数据,计算设备故障率


# path = 'E:/electricDataNew/' + province + '_electric_alarm_clean.csv'
save_path = 'E:/project/S_net_data/nework/' + province + '_'
path = 'E:/project/S_net_data/alarmData/' + province + '_electric_alarm_clean.csv'
df = pd.read_csv(path, encoding='gbk', sep=',')
# 告警数据存入字典
alarmDic = {}
for i in range(df.shape[0]):
    eqp_id = df.loc[i, 'NE_OBJ_ID']
    alarm_sum = df.loc[i, 'ALARM_TIME'].replace('[', '(')
    alarm_sum = alarm_sum.replace(']', ')')
    # 每台设备的故障率
    if sum(list(eval(alarm_sum))) != 0:
        eqp_fault = sum(list(eval(alarm_sum))) / (16 * 30 * 24 * 3600)
        if eqp_fault > 1:
            alarmDic[eqp_id] = 1.0
        else:
            alarmDic[eqp_id] = eqp_fault

# 设备的平均故障率
eqp_avg_fault = sum(alarmDic.values()) / len(alarmDic)

print("每台设备的故障率", alarmDic)
print("设备的平均故障率", eqp_avg_fault)
# max_alarm = max(zip(alarmDic.values(), alarmDic.keys()))[0]
# min_alarm = min(zip(alarmDic.values(), alarmDic.keys()))[0]

"""
# 读取光缆告警数据
path_fiber_fault = 'E:/electricData/' + province + '_fiber_fault.csv'
df_fiber_fault = pd.read_csv(path_fiber_fault,encoding='gbk',sep=',')

# 告警数据存入字典
fiber_fault_dic = {}
for i in range(df_fiber_fault.shape[0]):
    fiber_id = df_fiber_fault.loc[i, 'OBJ_ID']
    if type(df_fiber_fault.loc[i, 'ALARM_TIME']) == float:
        fiber_fault = 0
    else:
        alarm_sum = df_fiber_fault.loc[i, 'ALARM_TIME'].replace('[', '(')
        alarm_sum = alarm_sum.replace(']', ')')
        pre_list = list(eval(alarm_sum))
        for j in range(len(pre_list)):
            if pre_list[j] < 0:
                pre_list[j] = 0
        # 每条光缆的故障率
        fiber_fault = sum(pre_list) / (16 * 30 * 24 * 3600)
    if fiber_fault > 1:
        fiber_fault_dic[fiber_id] = 1.0
    else:
        fiber_fault_dic[fiber_id] = fiber_fault

# 光缆的平均故障率
fiber_avg_fault = sum(fiber_fault_dic.values()) / len(fiber_fault_dic)
for fiid in fiber_fault_dic:
    if fiber_fault_dic[fiid] == 0:
        fiber_fault_dic[fiid] = fiber_avg_fault

print("每条光缆的故障率", fiber_fault_dic)
print("光缆的平均故障率", fiber_avg_fault)
"""
# 每种光缆类型的单位长度（每公里）的可靠性
fiber_type_1 = 0.999973  # OPGW类型
fiber_type_2 = 0.999959  # ADSS类型
fiber_type_3 = 0.999280  # 普通光缆
fiber_type_4 = 0.99      # OPPC
fiber_type_5 = 0.99      # 跳纤
fiber_type_other = 0.99  # 类型为空的光缆

# 10101-站点 10102-机房，线段
cur = conn.cursor()
cur.execute("select OBJ_ID,A_RESTYPE,Z_RESTYPE,A_RESOBJID,Z_RESOBJID,LINE_NUMBER,LINE_NUMBER_USED,LINE_NUMBER_FREE,1,1,FIBER_TYPE,LENGTH from t_fiber_seg")
result = cur.fetchall()
data = np.array(result)
# 机房和站点的关系
cur2 = conn.cursor()
cur2.execute("select OBJ_ID,PAR_SITE from t_spc_room")
result2 = cur2.fetchall()
data2 = np.array(result2)
# 站点承载的所有重要业务数量
cur3 = conn.cursor()
cur3.execute("select OBJ_ID,name,buznum from sitebuznum_all")
result3 = cur3.fetchall()
data3 = np.array(result3)

# 站点15年承载的业务数量
cur9 = conn.cursor()
cur9.execute("select OBJ_ID,name,buzNum from sitebuznum_15")
result9 = cur9.fetchall()
data9 = np.array(result9)

station_buz_num_15_dic = {}
for i in range(data9.shape[0]):
    station_buz_num_15_dic[data9[i][0]] = int(data9[i][2])
"""
max_buz_num_15 = max(zip(station_buz_num_15_dic.values(), station_buz_num_15_dic.keys()))[0]
min_buz_num_15 = min(zip(station_buz_num_15_dic.values(), station_buz_num_15_dic.keys()))[0]
"""
# 站点16年承载的业务数量
cur6 = conn.cursor()
cur6.execute("select OBJ_ID,name,buzNum from sitebuznum_16")
result6 = cur6.fetchall()
data6 = np.array(result6)

station_buz_num_16_dic = {}
for i in range(data6.shape[0]):
    station_buz_num_16_dic[data6[i][0]] = int(data6[i][2])
"""
max_buz_num_16 = max(zip(station_buz_num_16_dic.values(), station_buz_num_16_dic.keys()))[0]
min_buz_num_16 = min(zip(station_buz_num_16_dic.values(), station_buz_num_16_dic.keys()))[0]
"""
# 每个站点的业务增长率，只比较了15年和16年
station_buz_rate = {}
for station_id in station_buz_num_15_dic:
    if station_buz_num_16_dic.__contains__(station_id):
        station_buz_rate[station_id] = int(station_buz_num_16_dic[station_id]) - int(station_buz_num_15_dic[station_id])
max_buz_rate = max(station_buz_rate.values())
min_buz_rate = min(station_buz_rate.values())
# 设备端口使用情况
cur7 = conn.cursor()
cur7.execute("select neid, used, allPort from ne_occ")
result7 = cur7.fetchall()
data7 = np.array(result7)

port_occ = {}  # 设备端口使用情况
eqp_port_occ = 0
for i in range(data7.shape[0]):
    if type(data7[i][1]) != int:
        data7[i][1] = 0
    port_occ[data7[i][0]] = [int(data7[i][1]), int(data7[i][2])]
    eqp_port_occ += int(data7[i][1]) / int(data7[i][2])
avg_eqp_port_occ = eqp_port_occ / len(port_occ)
# max_free_port = max(zip(port_occ.values(), port_occ.keys()))[0]

# 字典存储机房与站点的关系
dic = {}
for i in range(data2.shape[0]):
    dic[data2[i][0]] = data2[i][1]
# 字典存储站点与业务数量的关系
dic2 = {}
for i in range(data3.shape[0]):
    dic2[data3[i][0]] = data3[i][1]
# 在图中添加边
G = nx.Graph()
miss = 0
for i in range(data.shape[0]):
    a = False
    z = False
    # a端若是站点
    if data[i][1] == '10101':
        data[i][8] = data[i][3]
        a = True
    # a端若是机房，寻找机房对应的站点
    if data[i][1] == '10102':
        if dic.__contains__(data[i][3]):
            data[i][8] = dic[data[i][3]]
            a = True
        else:
            miss += 1
    # 同样，对b端做同样的处理
    if data[i][2] == '10101':
        data[i][9] = data[i][4]
        z=True
    if data[i][2] == '10102':
        if dic.__contains__(data[i][4]):
            data[i][9] = dic[data[i][4]]
            z = True
        else:
            miss += 1
    # 均是站点，在图中添加边
    # if a and z and dic2.__contains__(data[i][8]) and dic2.__contains__(data[i][9]) and not '110kV' in dic2[data[i][8]] and not '35kV' in dic2[data[i][8]] and not '110kV' in dic2[data[i][9]] and not '35kV' in dic2[data[i][9]]:
    if a and z and dic2.__contains__(data[i][8]) and dic2.__contains__(data[i][9]):
        if data[i][11] == '':
            squ = 1
        else:
            squ = float(data[i][11])
        if data[i][10] == 1:
            fiber_good = fiber_type_1 ** math.ceil(squ)
        elif data[i][10] == 2:
            fiber_good = fiber_type_2 ** math.ceil(squ)
        elif data[i][10] == 3:
            fiber_good = fiber_type_3 ** math.ceil(squ)
        elif data[i][10] == 4:
            fiber_good = fiber_type_4 ** math.ceil(squ)
        elif data[i][10] == 5:
            fiber_good = fiber_type_5 ** math.ceil(squ)
        else:
            fiber_good = fiber_type_other ** math.ceil(squ)
        if data[i][5] == '' or data[i][6] == '':
            fiber_occ = 0
        else:
            fiber_occ = int(data[i][6]) / int(data[i][5])
        G.add_edge(data[i][8], data[i][9], weight=0.0, used=data[i][6], total=data[i][5], occ=fiber_occ, fiber_good=fiber_good)
print('miss='+str(miss))
# 每个站点承载的业务数量
buznumdic = {}
for i in range(data3.shape[0]):
    if data3[i][0] in G.nodes():
        buznumdic[data3[i][0]] = int(data3[i][2])
# 计算每个站点承载的业务数
buzNum = []
for node in G.nodes():
    # 该站点承载的业务数
    if buznumdic.__contains__(node):
        num = buznumdic[node]
    else:
        num = 0
    buzNum.append(num)
max_buz_num = max(buzNum)
min_buz_num = min(buzNum)

# 计算每个站点的设备故障率和端口占用率
station_property = {}
node_color = []  # 站点显示的颜色，颜色代表扩容渐变
node_color_dic = {}  # 每个站点对应的扩容紧迫性

# 修改start
a_list = []  # 扩容迫切性的第一个因素，端口占用率
b_list = []  # 扩容迫切性的第二个因素，站点承载的重要业务数
c_list = []  # 扩容迫切性的第三个因素，站点业务数增长率
# 修改end

for node in G.nodes():
    # 该站点下的所有设备
    cur5 = conn.cursor()
    cur5.execute("select OBJ_ID from t_ne WHERE par_station='" + node + "'")
    result5 = cur5.fetchall()
    data5 = np.array(result5)
    # 该站点下的所有设备的告警时间
    stationAlarm = {}
    # 该站点下设备端口已使用数目列表
    used_port = []
    # 该站点下设备端口总数目列表
    all_port = []
    station_fault = 1
    for j in range(data5.shape[0]):
        # 设备故障率
        if alarmDic.__contains__(data5[j][0]):
            stationAlarm[data5[j][0]] = alarmDic[data5[j][0]]
        else:
            stationAlarm[data5[j][0]] = eqp_avg_fault
        # 设备端口占用率
        if port_occ.__contains__(data5[j][0]):
            used_port.append(port_occ[data5[j][0]][0])
            all_port.append(port_occ[data5[j][0]][1])
    # 该站点下所有设备故障率排序
    sorted_station_alarm = sorted(stationAlarm.items(), key=operator.itemgetter(1))
    # 取故障率最低的设备代表站点的可靠性
    station_reliability = 1 - sorted_station_alarm[0][1]
    # 该站点最可靠设备的端口占用率
    if port_occ.__contains__(sorted_station_alarm[0][0]):
        station_best_port_occ = port_occ[sorted_station_alarm[0][0]][0] / port_occ[sorted_station_alarm[0][0]][1]
    else:
        station_best_port_occ = avg_eqp_port_occ
    # 该站点的端口占用率
    if sum(all_port) == 0:
        station_port_occ = 0
    else:
        station_port_occ = sum(used_port) / sum(all_port)
    # 设置该站点相关属性
    station_property[node] = (station_reliability, station_best_port_occ)
    # 该站点承载的业务数
    if buznumdic.__contains__(node):
        buz_num = buznumdic[node]
    else:
        buz_num = 0
    buz_num_rate = (buz_num - min_buz_num) / (max_buz_num - min_buz_num)

    # 每个站点显示的颜色，颜色代表该站点扩容的迫切性
    if station_buz_rate.__contains__(node):
        increase_rate = (station_buz_rate[node] - min_buz_rate) / (max_buz_rate - min_buz_rate)
    else:
        increase_rate = 0
    node_color.append(0.1 * station_port_occ + 0.5 * buz_num_rate + 0.4 * increase_rate)
    node_color_dic[node] = 0.1 * station_port_occ + 0.5 * buz_num_rate + 0.4 * increase_rate
    # 修改start
    f1 = station_port_occ
    f2 = buz_num_rate
    f3 = increase_rate
    if f1 == 1:
        f1 = 0.99
    if f2 == 1:
        f2 = 0.99
    if f3 == 1:
        f3 = 0.99

    if f1 == 0:
        f1 = 0.01
    if f2 == 0:
        f2 = 0.01
    if f3 == 0:
        f3 = 0.01

    a_list.append(f1)
    b_list.append(f2)
    c_list.append(f3)
    # 修改end
print("站点属性", station_property)
# 预测扩容的站点
pre_kuorong_list = []
for node in node_color_dic:
    if node_color_dic[node] >= 0.3:
        pre_kuorong_list.append(node)
print("预测扩容的站点", pre_kuorong_list)
"""
# 寻找
cur8 = conn.cursor()
cur8.execute("select DISTINCT PAR_ROOM from t_ne WHERE substr(BEG_TIME,4,1)>7 AND substr(BEG_TIME,8,2)=16")
result8 = cur8.fetchall()
data8 = np.array(result8)
# 保存扩容的站点
kuorong_station_list = []
for i in range(data8.shape[0]):
    if dic.__contains__(data8[i][0]) and not kuorong_station_list.__contains__(dic[data8[i][0]]):
        kuorong_station_list.append(dic[data8[i][0]])
print("已经扩容的站点", kuorong_station_list)

for station in kuorong_station_list:
    if node_color_dic.__contains__(station):
        print(node_color_dic[station])
pre_set = set(pre_kuorong_list)
done_set = set(kuorong_station_list)
print(pre_set & done_set)
"""
# 设置边的颜色，边的权重
occ = []
for edge in G.edges():
    # 设置边的颜色
    occ.append(G[edge[0]][edge[1]]['occ'])
    # 设置边的权重
    if station_property[edge[0]][0] == 0 or station_property[edge[1]][0] == 0 or G[edge[0]][edge[1]]['fiber_good'] == 0:
        G[edge[0]][edge[1]]['weight'] = 1000
    else:
        G[edge[0]][edge[1]]['weight'] = -(math.log(station_property[edge[0]][0]) + math.log(station_property[edge[1]][0]) + 2 * math.log(G[edge[0]][edge[1]]['fiber_good']))

# 输出通信网拓扑结构信息到csv start

# # 江西
# zone_dic = {'7660966C-F160-424F-B68E-DF16D06A564E-00035': [115.85, 28.68],
#             '83EBB176-6C17-43B4-B4EF-9E7D3A9BE432-00001': [115.85, 28.68],
#             '83EBB176-6C17-43B4-B4EF-9E7D3A9BE432-00003': [115.85, 28.68],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00012': [115.85, 28.68],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00013': [115.77, 29.33],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00014': [116.35, 27.5],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00015': [114.93, 25.83],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00016': [113.85, 27.63],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00017': [117.07, 28.27],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00018': [117.97, 28.45],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00019': [117.20, 29.27],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00020': [114.78, 28.40],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00021': [114.98, 27.12],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00022': [114.98, 27.12],
#             '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00023': [117.97, 28.45],
#             '7FE2FB56-1C56-453F-914F-CDDE8F9C7435-00001': [115.85, 28.68],
#             '7FE2FB56-1C56-453F-914F-CDDE8F9C7435-00002': [115.85, 28.68],
#             '7E93D91B-2DC1-4DD5-B730-80C447E704DC-47529': [115.85, 28.68]
# 
#             }


# 查询每个省的所以分区id  边界   分区中心点 start
conn_zone_dict = db.connect(host='172.16.135.19', user='root', passwd='hadoop', db="jiangxi_power", port=3306, charset='utf8')

cur_zone_dict = conn_zone_dict.cursor()

sql_zone = "select DISTINCT Province,Station,Xpoint,Ypoint from zone_roly where Province = %s"
cur_zone_dict.execute(sql_zone, province_gbk)
result_zone = cur_zone_dict.fetchall()
data_zone = np.array(result_zone)
zone_dic = {}
list_provence_id = []
for i in range(data_zone.shape[0]):
    zone_dic[data_zone[i][1]] = [float(data_zone[i][2]), float(data_zone[i][3])]
    # 获得去重区域id
    list_provence_id.append(data_zone[i][1])

# 获得所有区域边界
zone_border = {}
for item in list_provence_id:
    sql_border = "select  Roly_x, Roly_y from zone_roly where Station = %s"
    cur_zone_dict.execute(sql_border, item)
    result_border = cur_zone_dict.fetchall()
    data_border = np.array(result_border)
    data_border = data_border.tolist()
    zone_border[item] = data_border

conn_zone_dict.commit()
conn_zone_dict.close()

# 查询每个省的所以分区id  边界   分区中心点 end

"""
# 安徽
zone_dic = {
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00001': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00002': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00003': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00004': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00005': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00006': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00007': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00008': [116.75, 30.68],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00009': [117.38, 33.11],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00010': [117.38, 33.11],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00011': [117.38, 33.11],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00012': [117.38, 33.11],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00013': [116.35, 33.27],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00014': [116.35, 33.27],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00015': [116.35, 33.27],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00016': [118.12, 32.42],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00017': [118.12, 32.42],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00018': [118.12, 32.42],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00019': [118.12, 32.42],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00020': [117.48, 33.63],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00021': [117.48, 33.63],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00022': [117.48, 33.63],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00023': [117.48, 33.63],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00024': [117.48, 33.63],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00025': [118.12, 32.42],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00026': [118.12, 32.42],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00027': [115.55, 32.9],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00028': [115.55, 32.9],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00029': [115.55, 32.9],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00030': [115.55, 32.9],
    'AAAABDE4-45F1-4526-9541-C634F5517F6A-00031': [115.55, 32.9],
    'FA0DE7F7-E746-4B8C-8A2B-7D532C74FBBA-00001': [117.31, 31.81],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00001': [117.31, 31.81],f
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00002': [118.24, 31.23],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00003': [118.03, 29.92],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00004': [116.7, 33.75],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00005': [117.48, 30.29],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00006': [116.25, 31.73],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00007': [115.55, 32.9],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00008': [116.75, 30.68],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00009': [117.0, 32.63],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00010': [118.75, 30.57],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00011': [117.38, 33.11],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00012': [117.31, 31.81],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00013': [118.2, 31.7],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00014': [118.12, 32.42],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00015': [117.48, 33.63],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00016': [117.88, 30.93],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00017': [116.35, 33.27],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00018': [117.31, 31.81],
    '68FC8FCA-A97A-4133-8EC3-FB7DFDFF4779-00019': [117.31, 31.81],
    'D69F6B70-4FEA-458A-8119-78DFC8614940-00001': [117.31, 31.81],
    '54464139-3C39-44DD-BAB1-C882B0E27743-00001': [117.31, 31.81],
    '54464139-3C39-44DD-BAB1-C882B0E27743-00002': [117.31, 31.81],
    '584C616D-79AF-449D-90DF-D4EB67F514B9-00001': [118.99, 32.69],
    '584C616D-79AF-449D-90DF-D4EB67F514B9-00002': [118.99, 32.69],
    '19C28F9B-D2DC-4DC6-86E6-FA1C32DC7E84-81893': [117.48, 33.63],
    'B0AA125B-9DB4-438F-B550-3909D14AEEB1-01069': [118.03, 29.92],
    '0EB718BA-221E-492D-99A7-F15AE418D39C-19476': [118.03, 29.92],
    '31B7BCF7-1289-4038-BD07-559182ADBD93-07892': [118.2, 31.7],
    '31B7BCF7-1289-4038-BD07-559182ADBD93-07894': [118.2, 31.7],
    '31B7BCF7-1289-4038-BD07-559182ADBD93-07896': [118.2, 31.7],
    'A5F0BECF-469B-465C-A4CC-D850706A440E-00040': [117.31, 31.81],
    '99F079F1-1CFE-480E-9C42-B73EE8AF7746-00421': [118.75, 30.57],
    'A9360503-BEE4-49A3-9C6E-660DB6C6FD1E-00001': [117.0, 32.63],
    'A9360503-BEE4-49A3-9C6E-660DB6C6FD1E-00002': [116.25, 31.73],
    'A9360503-BEE4-49A3-9C6E-660DB6C6FD1E-00003': [116.25, 31.73],
    'B031E81C-EA10-4655-A1E9-7FBFC0181D65-96273': [117.0, 32.63],
    'B031E81C-EA10-4655-A1E9-7FBFC0181D65-96275': [117.0, 32.63],
    'A9360503-BEE4-49A3-9C6E-660DB6C6FD1E-00004': [116.25, 31.73],
    'A9360503-BEE4-49A3-9C6E-660DB6C6FD1E-00005': [116.25, 31.73],
    'A9360503-BEE4-49A3-9C6E-660DB6C6FD1E-00006': [116.25, 31.73],
    'F1587EF3-696B-42ED-A234-81246F219C25-02157': [117.31, 31.81],
    '1F0BCE2E-D12C-4B85-B131-03D70CBBFE40-02978': [117.88, 30.93],
    'B031E81C-EA10-4655-A1E9-7FBFC0181D65-95728': [118.03, 29.92],
    '1F0BCE2E-D12C-4B85-B131-03D70CBBFE40-10783': [118.24, 31.23],
    '1F0BCE2E-D12C-4B85-B131-03D70CBBFE40-10780': [118.24, 31.23],
    'B031E81C-EA10-4655-A1E9-7FBFC0181D65-96277': [117.0, 32.63],
    'F1587EF3-696B-42ED-A234-81246F219C25-02149': [117.31, 31.81],
    'F1587EF3-696B-42ED-A234-81246F219C25-02153': [117.31, 31.81],
    'F1587EF3-696B-42ED-A234-81246F219C25-02155': [117.31, 31.81],
    'B031E81C-EA10-4655-A1E9-7FBFC0181D65-95730': [118.03, 29.92],
    'B031E81C-EA10-4655-A1E9-7FBFC0181D65-95724': [118.03, 29.92],
    '3867163D-1449-4A98-99D5-C667218B8A4B-00139': [118.75, 30.57],
    'FFCB038B-58DE-4434-8357-5E76A1F0BFB3-02145': [117.48, 30.29],
    '99F079F1-1CFE-480E-9C42-B73EE8AF7746-06238': [118.24, 31.23],
    '99F079F1-1CFE-480E-9C42-B73EE8AF7746-09971': [118.24, 31.23],
    'A17A729F-4747-42EE-A0E9-017BA1604478-01216': [118.75, 30.57],
    'FFCB038B-58DE-4434-8357-5E76A1F0BFB3-02147': [117.48, 30.29],
    'FFCB038B-58DE-4434-8357-5E76A1F0BFB3-02143': [117.48, 30.29],
    '3715BF69-B3F1-443F-B072-13AD97BA8D53-10345': [116.25, 31.73],
    'A17A729F-4747-42EE-A0E9-017BA1604478-01214': [118.75, 30.57],
    'A17A729F-4747-42EE-A0E9-017BA1604478-01215': [118.75, 30.57],
    '3867163D-1449-4A98-99D5-C667218B8A4B-00129': [118.75, 30.57],
    'FFCB038B-58DE-4434-8357-5E76A1F0BFB3-02149': [117.48, 30.29],
    '99F079F1-1CFE-480E-9C42-B73EE8AF7746-02807': [116.7, 33.75],
    '396B0760-CD4C-403A-ADC7-3BDEED733A05-48253': [117.31, 31.81],
    '0BEF48DB-626D-4647-8836-68C0BF1B88B3-25819': [115.55, 32.9]
}
"""

# t_spc_site
cur_site = conn.cursor()
cur_site.execute("select OBJ_ID,name,par_zone from t_spc_site")
result_site = cur_site.fetchall()
data_site = np.array(result_site)
site_dic = {}
for i in range(data_site.shape[0]):
    site_dic[data_site[i][0]] = [data_site[i][1], data_site[i][2]]

print('图点：', G.nodes())
name_list = []  # 站点名称list
x_list = []  # 站点经度
y_list = []  # 站点纬度
buzNum_list = []  # 站点业务数量list
kurong_list = []  # 站点扩容迫切性
for node in G.nodes():
    # name
    if site_dic.__contains__(node):
        name_list.append(site_dic[node][0])
    else:
        name_list.append('none')
    # Xaxis,Yaxis
    if site_dic.__contains__(node):
        if zone_dic.__contains__(site_dic[node][1]):
            # 随机分布版
            # x_list.append(zone_dic[site_dic[node][1]][0] + random.uniform(-0.1, 0.1))
            # y_list.append(zone_dic[site_dic[node][1]][1] + random.uniform(-0.2, 0.2))
            # 正态分布版
            # x_list.append(zone_dic[site_dic[node][1]][0] + np.random.randn() * 0.06)
            # y_list.append(zone_dic[site_dic[node][1]][1] + np.random.randn() * 0.06)

            p_tem = [round(zone_dic[site_dic[node][1]][0] + np.random.randn() * 0.5, 2), round(zone_dic[site_dic[node][1]][1] + np.random.randn() * 0.5, 2)]
            while not ray_casting(p_tem, zone_border[site_dic[node][1]]):
                p_tem = [round(zone_dic[site_dic[node][1]][0] + np.random.randn() * 0.5, 2),
                         round(zone_dic[site_dic[node][1]][1] + np.random.randn() * 0.5, 2)]
            x_list.append(p_tem[0])
            y_list.append(p_tem[1])
        else:
            x_list.append(117.97)
            y_list.append(29.5)
    else:
        x_list.append(117.97)
        y_list.append(29.5)
    # businessNum
    buzNum_list.append(buznumdic[node])
    # kurong

# 加入省份
list_provence = []
for i in enumerate(y_list):
    list_provence.append(province_gbk)


# 修改start
result = pd.DataFrame(np.column_stack((np.array(list_provence).reshape((-1, 1)), np.array(G.nodes()).reshape((-1, 1)), np.array(name_list).reshape((-1, 1)),
                                       np.array(x_list).reshape((-1, 1)), np.array(y_list).reshape((-1, 1)),
                                       np.array(buzNum_list).reshape((-1, 1)), np.array(node_color).reshape((-1, 1)),
                                       np.array(a_list).reshape((-1, 1)), np.array(b_list).reshape((-1, 1)),
                                       np.array(c_list).reshape((-1, 1)))),
                      columns=['Province', 'obj_id', 'name', 'Xaxis', 'Yaxis', 'businessNum', 'kuorong', 'portOcc', 'buzNumRate', 'increaseRate'])
# result.to_csv(save_path+"station_property.csv", index=False, header=True, encoding='gbk')
# 修改end


# 将station_property写入数据库对应的表 start
try:
    engine_property = create_engine("mysql+pymysql://root:hadoop@172.16.135.19:3306/jiangxi_power?charset=utf8", max_overflow=5)
    pd.io.sql.to_sql(result, name='StationProperty', con=engine_property, if_exists='append', schema='jiangxi_power', index=False, index_label=False, chunksize=10000)

finally:
    print('station_property insert successfully!')

# 将station_property写入数据库对应的表 end


# 机房和站点的关系

edge_id1_list = []  # 头站点id
edge_name1_list = []  # 头站点name
edge_id2_list = []  # 尾站点id
edge_name2_list = []  # 尾站点name
edge_occ_list = []  # 铅芯占用率
for edge in G.edges():
    edge_id1_list.append(edge[0])
    edge_name1_list.append(site_dic[edge[0]][0])
    edge_id2_list.append(edge[1])
    edge_name2_list.append(site_dic[edge[1]][0])
    edge_occ_list.append(G[edge[0]][edge[1]]['occ'])

# 加入省份
list_provence = []
for i in enumerate(edge_id1_list):
    list_provence.append(province_gbk)

result = pd.DataFrame(np.column_stack((np.array(list_provence).reshape((-1, 1)), np.array(edge_id1_list).reshape((-1, 1)), np.array(edge_name1_list).reshape((-1, 1)),
                                       np.array(edge_id2_list).reshape((-1, 1)), np.array(edge_name2_list).reshape((-1, 1)),
                                       np.array(edge_occ_list).reshape((-1, 1)))),
                      columns=['Province', 'obj_id1', 'name1', 'obj_id2', 'name2', 'fiberOcc'])
# result.to_csv(save_path+"station_link.csv", index=False, header=True, encoding='gbk')


# 将station_link写入数据库对应的表 start
try:
    engine_link = create_engine("mysql+pymysql://root:hadoop@172.16.135.19:3306/jiangxi_power?charset=utf8", max_overflow=5)
    pd.io.sql.to_sql(result, name='StationLink', con=engine_link, if_exists='append', schema='jiangxi_power', index=False, index_label=False, chunksize=10000)
finally:
    print('station_link insert successfully!')

# 将station_link写入数据库对应的表 end

# 输出通信网拓扑结构信息到csv end

# 为每个站点布局
position = {}
for node in G.nodes():
    cur4 = conn.cursor()
    cur4.execute("select PAR_ZONE from t_spc_site where OBJ_id='"+node+"'")
    result4 = cur4.fetchall()
    data4 = np.array(result4)
    # 江西
    if data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00012':  # 南昌
        position[node] = [random.uniform(550, 700), random.uniform(400, 620)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00013':  # 九江
        position[node] = [random.uniform(400, 650), random.uniform(250, 450)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00014':  # 抚州
        position[node] = [random.uniform(740, 900), random.uniform(640, 950)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00015':  # 赣州
        position[node] = [random.uniform(640, 900), random.uniform(1200, 1700)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00016':  # 萍乡
        position[node] = [random.uniform(150, 270), random.uniform(930, 1050)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00017':  # 鹰潭
        position[node] = [random.uniform(910, 1020), random.uniform(465, 600)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00018':  # 上饶
        position[node] = [random.uniform(1050, 1270), random.uniform(260, 550)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00019':  # 景德镇
        position[node] = [random.uniform(900, 970), random.uniform(80, 350)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00020':  # 宜春
        position[node] = [random.uniform(250, 680), random.uniform(600, 730)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00021':  # 吉安
        position[node] = [random.uniform(300, 670), random.uniform(930, 1190)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00022':  # 赣西
        position[node] = [random.uniform(360, 500), random.uniform(788, 900)]
    elif data4[0][0] == '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00023':  # 赣东北
        position[node] = [random.uniform(750, 880), random.uniform(200, 450)]
    elif data4[0][0] == '7660966C-F160-424F-B68E-DF16D06A564E-00035':  # 江西检修分公司
        position[node] = [684, 509]
    elif data4[0][0] == '83EBB176-6C17-43B4-B4EF-9E7D3A9BE432-00001':  # 江西
        position[node] = [674, 1054]
    else:
        position[node] = [random.uniform(0, 1), random.uniform(0, 1)]
    """
    # 北京
    if data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00007':
        position[node] = [random.uniform(0.34, 0.46), random.uniform(0.36, 0.44)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00002':
        position[node] = [random.uniform(0.53, 0.58), random.uniform(0.25, 0.36)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00006':
        position[node] = [random.uniform(0.33, 0.49), random.uniform(0.22, 0.26)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00008':
        position[node] = [random.uniform(0.5, 0.6), random.uniform(0.51, 0.9)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00009':
        position[node] = [random.uniform(0.05, 0.31), random.uniform(0.3, 0.4)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00010':
        position[node] = [random.uniform(0.61, 0.86), random.uniform(0.6, 0.78)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00011':
        position[node] = [random.uniform(0.75, 0.9), random.uniform(0.44, 0.52)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00012':
        position[node] = [random.uniform(0.34, 0.39), random.uniform(0.29, 0.35)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00013':
        position[node] = [random.uniform(0.54, 0.72), random.uniform(0.39, 0.5)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00001':
        position[node] = [random.uniform(0.25, 0.5), random.uniform(0.46, 0.55)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00003':
        position[node] = [random.uniform(0.41, 0.51), random.uniform(0.26, 0.32)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00004':
        position[node] = [random.uniform(0.4, 0.55), random.uniform(0.08, 0.2)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00005':
        position[node] = [random.uniform(0.09, 0.39), random.uniform(0.1, 0.24)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00014':
        position[node] = [random.uniform(0.58, 0.72), random.uniform(0.15, 0.3)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00015':
        position[node] = [random.uniform(0.2, 0.5), random.uniform(0.6, 0.73)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00016':
        position[node] = [random.uniform(0.6, 0.7), random.uniform(0.27, 0.39)]
    elif data4[0][0] == '9B70D934-5FFE-4D98-A788-54195A553566-00002':
        position[node] = [0.53, 0.3]
    elif data4[0][0] == '04433C56-0D35-4FDD-BDB5-5061EAAAD2C0-00001':
        position[node] = [random.uniform(0, 1), random.uniform(0, 1)]
    elif data4[0][0] == '8F98220F-CFFF-41EB-8DE4-5B40BD128BFB-00001':
        position[node] = [random.uniform(0, 1), random.uniform(0, 1)]
    elif data4[0][0] == '84F4575E-49E4-4F91-9E61-F5789722BBF9-00039':
        position[node] = [0.5, 0.35]
    else:
        position[node] = [random.uniform(0.98, 1), random.uniform(0.98, 1)]
    
    # 北京
    if data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00007':  # 海淀区
        position[node] = [random.uniform(450, 650), random.uniform(800, 950)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00002':  # 朝阳区
        position[node] = [random.uniform(700, 800), random.uniform(890, 1000)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00006':  # 丰台区
        position[node] = [random.uniform(450, 650), random.uniform(990, 1060)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00008':  # 怀柔区
        position[node] = [random.uniform(800, 860), random.uniform(200, 600)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00009':  # 门头沟区
        position[node] = [random.uniform(100, 400), random.uniform(800, 950)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00010':  # 密云区
        position[node] = [random.uniform(950, 1150), random.uniform(300, 600)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00011':  # 平谷区
        position[node] = [random.uniform(1100, 1250), random.uniform(600, 800)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00012':  # 石景山区
        position[node] = [random.uniform(460, 480), random.uniform(900, 960)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00013':  # 顺义区
        position[node] = [random.uniform(700, 1000), random.uniform(660, 840)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00001':  # 昌平区
        position[node] = [random.uniform(400, 700), random.uniform(600, 750)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00003':  # 城区
        position[node] = [random.uniform(610, 700), random.uniform(940, 1000)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00004':  # 大兴区
        position[node] = [random.uniform(580, 700), random.uniform(1050, 1200)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00005':  # 房山区
        position[node] = [random.uniform(200, 400), random.uniform(1000, 1200)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00014':  # 通州区
        position[node] = [random.uniform(800, 980), random.uniform(1000, 1100)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00015':  # 延庆区
        position[node] = [random.uniform(300, 600), random.uniform(400, 600)]
    elif data4[0][0] == 'FCE7529D-8F73-4677-A557-DAABB25E5ACD-00016':  # 亦庄区，属于大兴区
        position[node] = [random.uniform(580, 700), random.uniform(1050, 1200)]
    elif data4[0][0] == '9B70D934-5FFE-4D98-A788-54195A553566-00002':  # 地址：北京市西城区广安门内大街482号
        position[node] = [650, 950]
    elif data4[0][0] == '04433C56-0D35-4FDD-BDB5-5061EAAAD2C0-00001':  # 不确定区域
        position[node] = [random.uniform(100, 1200), random.uniform(100, 1200)]
    elif data4[0][0] == '8F98220F-CFFF-41EB-8DE4-5B40BD128BFB-00001':  # 不确定区域（一级骨干网）
        position[node] = [random.uniform(100, 1200), random.uniform(100, 1200)]
    elif data4[0][0] == '84F4575E-49E4-4F91-9E61-F5789722BBF9-00039':  # 朝阳区/物资仓库
        position[node] = [700, 900]
    else:
        position[node] = [random.uniform(1150, 1200), random.uniform(1150, 1200)]
    """
# 为每个业务分配路由，导出csv start
cur_buz = conn.cursor()
cur_buz.execute("select DISTINCT OBJ_ID,name,a_site_id,z_site_id from t_buz")
result_buz = cur_buz.fetchall()
data_buz = np.array(result_buz)
buz_list = []  # 业务id list
buz_name_list = []  # 业务名称list
route_list1 = []  # 路由分配list,最优路径
route_list2 = []  # 路由分配list，备选路径
for i in range(data_buz.shape[0]):
    source_node_1 = data_buz[i][2]
    target_node_1 = data_buz[i][3]

    if source_node_1 in G.nodes() and target_node_1 in G.nodes():
        # result_list = []
        # try:
        #     result_list = nx.shortest_path(G, source_node_1, target_node_1, weight='weight')
        # except:
        #     continue
        # else:
        #     if len(result_list) != 0 and len(result_list) <= 7:
        #         buz_list.append(data_buz[i][0])
        #         buz_name_list.append(data_buz[i][1])
        #         tmp = str(result_list).replace('[', '').replace(']', '')
        #         route_list.append(tmp)
        try:
            result_list_tmp = k_shortest_paths(G, source_node_1, target_node_1, 2, weight=None)  # 找寻前两条最优路径
        except:
            continue
        else:
            result_list1 = []  # 第一条最优路径
            result_list2 = []  # 第二条最优路径
            route_length = len(result_list_tmp)  # 路径条数
            if route_length == 2:
                result_list1 = result_list_tmp[0]
                result_list2 = result_list_tmp[1]
            elif route_length == 1:
                result_list1 = result_list_tmp[0]

            flag_route = False  # 判断是否添加该业务的路由分配
            flag_route1 = False  # 判断第一条路径是否添加
            flag_route2 = False  # 判断第二条路径是否添加
            if len(result_list1) != 0 and len(result_list1) <= 7:
                flag_route = True
                flag_route1 = True
                tmp1 = str(result_list1).replace('[', '').replace(']', '')

            if len(result_list2) != 0 and len(result_list2) <= 7:
                flag_route = True
                flag_route2 = True
                tmp2 = str(result_list2).replace('[', '').replace(']', '')

            if flag_route:
                buz_list.append(data_buz[i][0])
                buz_name_list.append(data_buz[i][1])
                if flag_route1:
                    route_list1.append(tmp1)
                else:
                    route_list1.append("none")
                if flag_route2:
                    route_list2.append(tmp2)
                else:
                    route_list2.append("none")
# 加入省份
list_provence = []
for i in enumerate(buz_list):
    list_provence.append(province_gbk)

result = pd.DataFrame(np.column_stack((np.array(list_provence).reshape((-1, 1)), np.array(buz_list).reshape((-1, 1)), np.array(buz_name_list).reshape((-1, 1)),
                                       np.array(route_list1).reshape((-1, 1)), np.array(route_list2).reshape((-1, 1)))),
                      columns=['Province', 'buz_id', 'name', 'route', 'route_2'])
# result.to_csv(save_path+"station_route.csv", index=False, header=True, encoding='gbk')

# 将station_route写入数据库对应的表 start
try:
    engine_route = create_engine("mysql+pymysql://root:hadoop@172.16.135.19:3306/jiangxi_power?charset=utf8", max_overflow=5)
    pd.io.sql.to_sql(result, name='StationRoute', con=engine_route, if_exists='append', schema='jiangxi_power', index=False, index_label=False, chunksize=10000)
finally:
    print('station_route insert successfully!')

# 将station_route写入数据库对应的表 end


# 为每个业务分配路由，导出csv end

if is_find_path:
    # 指定两个顶点，获得最短路径经过的顶点
    source_node = '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00392'
    target_node = '0CF3663A-EB41-4DF9-9061-D27EDA049BFD-00774'

    all_path_list = list(k_shortest_paths(G, source=source_node, target=target_node, k=10))

    path_weight = {}
    for i in range(len(all_path_list)):
        if len(all_path_list[i]) == 2:
            continue
        a = 1  # 设备可靠性
        b = 1  # 设备端口占用率
        c = 1  # 铅芯占用率
        d = 1  # 光缆故障率

        e = 0  # 路径可靠性
        m = []  # 经过站点
        for j in range(len(all_path_list[i])):
            m.append(dic2[all_path_list[i][j]])
            a = a * station_property[all_path_list[i][j]][0]
            b = b * (1 - station_property[all_path_list[i][j]][1])
            if j < len(all_path_list[i]) - 1:
                c = c * (1 - G.edge[all_path_list[i][j]][all_path_list[i][j+1]]['occ'])
                d = d * G.edge[all_path_list[i][j]][all_path_list[i][j+1]]['fiber_good']
                e = e + G.edge[all_path_list[i][j]][all_path_list[i][j+1]]['weight']
        print("第", i, "条路径dis长度为", e)

        f = 0.7 * a * d + 0.1 * b + 0.1 * c
        path_weight[i] = f
        print("第", i, "条路径可靠性考虑端口为", f)
        print("第", i, "条路径经过站点", m)
    sorted_path_weight = sorted(path_weight.items(), key=operator.itemgetter(1), reverse=True)
    print(sorted_path_weight)
    shortNodeList = all_path_list[sorted_path_weight[0][0]]
    shortNodeList1 = all_path_list[sorted_path_weight[1][0]]
    print("第一条路径", shortNodeList)
    print("第二条路径", shortNodeList1)


# 设置边的宽度
edge_width = []
for edge in G.edges():
    flag = True
    flag1 = True
    if is_find_path:
        for i in range(len(shortNodeList)):
            if i < len(shortNodeList)-1 and ((shortNodeList[i] == edge[0] and shortNodeList[i+1] == edge[1]) or (shortNodeList[i] == edge[1] and shortNodeList[i+1] == edge[0])):
                edge_width.append(2)
                flag = False
                break
        for j in range(len(shortNodeList1)):
            if flag and j < len(shortNodeList1)-1 and ((shortNodeList1[j] == edge[0] and shortNodeList1[j+1] == edge[1]) or (shortNodeList1[j] == edge[1] and shortNodeList1[j+1] == edge[0])):
                edge_width.append(1)
                flag1 = False
                break

    if flag and flag1:
        if is_find_path:
            edge_width.append(0)
        else:
            #if G.edge[edge[0]][edge[1]]['occ'] > 0.8:
               # edge_width.append(2)
            #else:
                #edge_width.append(0.2)
            edge_width.append(0.5)
# 设置顶点说明
dicNew = {}
if is_find_path:

    #path_list = shortNodeList + shortNodeList1
    for node in G.nodes():
        #if node in dic2 and node in path_list and not dicNew.__contains__(node):
            #dicNew[node] = dic2[node] + "(" + str(buznumdic[node]) + ")"
        if node == source_node:
            dicNew[node] = '起点'
        elif node == target_node:
            dicNew[node] = '终点'
# 设置顶点的size
node_size = []
for node in G.nodes():
    if is_find_path:
        path_list = shortNodeList + shortNodeList1
        if node in path_list:
            node_size.append(10)
        else:
            node_size.append(0.5)
    else:
        #if node_color_dic[node] > 0.5:
            #node_size.append(4)
        #else:
            #node_size.append(1)
        node_size.append(2)
print(G.adj)

if max(node_color) > max(occ):
    vmax = max(node_color)
else:
    vmax = max(occ)

if min(node_color) < min(occ):
    vmin = min(node_color)
else:
    vmin = min(occ)
vmax = 0.8

print(node_color)
nodecmap = cm.jet
nx.draw_networkx(G, position, nodelist=G.nodes(), node_size=node_size, labels=dicNew, style='solid', width=edge_width, cmap=nodecmap, vmin=vmin, vmax=vmax, edge_cmap=nodecmap,edge_vmin=vmin,edge_vmax=vmax,with_labels=is_find_path,font_color='R',font_size=8,node_color=node_color,edge_color=occ,label_pos=0.5)
Z = [[0, 0], [0, 0]]
# levels = range(nodevmin,nodevmax+1,1)
levels = np.arange(vmin, vmax+0.1, 0.1)
bar = plt.contourf(Z, levels, cmap=nodecmap, fontcolor='w')
# plt.colorbar(bar)


# img = mpimg.imread(province + ".png")
# #img_1 = img[:, :, 0]
# #plt.imshow(img_1, cmap='Greys_r')
# plt.imshow(img)
#
# fig = plt.gcf()
#
# axes = plt.subplot(111)
# axes.set_xticks([])
# axes.set_yticks([])
# # axes.set_title(u'北京站点分布图', fontproperties=myfont, color='w')
# axes.spines['left'].set_color('none')
# axes.spines['bottom'].set_color('none')
# axes.spines['right'].set_color('none')
# axes.spines['top'].set_color('none')
#
# fig.set_size_inches(8, 5)
# plt.tight_layout()
# """
# if is_find_path:
#     plt.savefig('E:/electricData/' + province + '_topology_short.png', dpi=240, transparent=True)
# else:
#     plt.savefig('E:/electricData/' + province + '_topology.png', dpi=480, transparent=True)
# """
# plt.show()




