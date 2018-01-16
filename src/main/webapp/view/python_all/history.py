import pandas as pd
import pymysql.cursors
import matplotlib
import matplotlib.pyplot as plt
import numpy as np
import networkx as nx
import matplotlib.cm as cm
from EquipFailureBytime import *
from FiberSegFailureBytime import *
from pandas import Series
import sys


config = {
    'host':"172.16.135.6",
    'port':3306,
    'user':'root',
    'password':'10086',
    'db':'electric',
    'charset':'utf8',
    'cursorclass':pymysql.cursors.DictCursor,
}

connection = pymysql.connect(**config)

myfont = matplotlib.font_manager.FontProperties(fname=r"c:\windows\fonts\simhei.ttf", size=16)


province = 'jiangxi'
buz_type = '2'
graphPath = 'E:/new_graph/'
path = graphPath + 'history/'

def resolvebusz(buz_id,buz_lst):
    r = getReliability()
    businessid = buz_id  # 业务id
    print("b_id:" + businessid)
    if (len(buz_lst) <= 1):
        return
    index = 0
    G = nx.Graph()
    firstnode = ''
    lastnode = ''
    fiber_dict = {}
    node_dic = {}
    nodes = []
    nodevmax = float(0.0)
    nodevmin = float(1.0)
    paths = []
    fiberLsts = []
    while (index < len(buz_lst)):
        vals = buz_lst[index]
        equips = []
        fibersegs = []
        prevnode = ''
        if (len(vals) > 1):
            for equip in vals:
                if (firstnode == ''):
                    firstnode = equip
                equips.append(equip)
                nodes.append(equip)
                # 获取设备可靠性getEqpAvgRel
                eReliability = getEqpAvgRel(equip)
                er = round(eReliability, 3)
                node_dic[equip] = er
                print(er)
                if (er > nodevmax): nodevmax = er
                if (er < nodevmin): nodevmin = er

                if (prevnode != ''):
                    # 获取光缆ID及平均可靠性
                    fsID, w = r.fiberFailureByType(prevnode, equip)
                    print(fsID)
                    print(w)
                    fiber_dict[fsID] = w
                    fibersegs.append(fsID)
                    G.add_edge(prevnode, equip, weight=w)
                prevnode = equip
                if (lastnode == ''): lastnode = vals[-1]
            paths.append(equips)
            fiberLsts.append(fibersegs)
        index += 1
    print(paths)
    print(fiberLsts)
    if (lastnode != ""):
        fig = plt.figure(figsize=(16, 8))  # 新图 0
        # fig = plt.figure(figsize=(30, 15))
        # fig.suptitle(u'(500kV赣州变～500kV罗坊变)安全自动装置业务' + '\n', fontproperties=myfont, fontsize=60, color='w')
        fig.subplots_adjust(wspace=0.4)
        ax1 = plt.subplot2grid((1, 2), (0, 0))
        pos = nx.spectral_layout(G)  # positions for all nodes
        lables = {}
        m = 1
        for e in G.nodes():
            if e == firstnode:
                lables[e] = r"$S$"
            elif e == lastnode:
                lables[e] = r"$E$"
            else:
                lables[e] = str(m)
                m += 1
        widthlst = [float(d['weight'] * 5) for (u, v, d) in G.edges(data=True)]
        if (nodevmax != nodevmin):
            levels = np.arange(nodevmin, nodevmax, (nodevmax - nodevmin) / 10)
        else:
            nodevmin = 0.0
            nodevmax = 1.0
            levels = [0.10, 0.20, 0.30, 0.40, 0.50, 0.60, 0.70, 0.80, 0.90, 1.00]

        nx.draw_networkx_nodes(G, pos, node_color=list(node_dic[node] for node in G.nodes()), node_size=1000,
                               cmap=cm.jet, vmin=nodevmin, vmax=nodevmax, alpha=0.7, label=None)
        nx.draw_networkx_edges(G, pos, width=widthlst, alpha=0.7)
        nx.draw_networkx_labels(G, pos, lables, font_size=16, font_weight='bold', font_color='w')

        nodecmap = cm.jet
        Z = [[0, 0], [0, 0]]
        bar = plt.contourf(Z, levels, cmap=nodecmap, alpha=0.7)
        ax1=plt.colorbar(bar)
        # ax1.set_ticks([0.10, 0.20, 0.30, 0.40, 0.50, 0.60, 0.70, 0.80, 0.90, 1.00])
        ax1.set_label('equipment average reliability',  color='black')
        # plt.xlabel(str(businessid))
        plt.savefig(path + businessid + ".png", transparent=True, format='png', dpi=80)  # save as png

        G.clear()
        plt.axis('off')
        # 计算业务可靠性
        monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07', '2016-08',
                     '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02', '2017-03'];
        x = range(15)
        buzreliablelist = []
        for month in monthlist:
            timem = datetime.datetime.strptime(month, '%Y-%m')
            days = calendar.monthrange(int(month.split('-')[0]), int(month.split('-')[1]))[1]
            seconds = days * 24 * 3600
            buzreliable = float(1)
            commonreliable = float(1);
            psreliable_0 = float(1);
            psreliable_1 = float(1);
            if (len(paths) == 1):
                for eq in paths[0]:
                    ereliable = s.failure(eq, month) / seconds
                    buzreliable = buzreliable * (1 - ereliable);
                buzreliablelist.append(buzreliable)
            if (len(paths) == 2):
                for eq in paths[0]:
                    ereliable = s.failure(eq, month) / seconds
                    if (eq in paths[1]):
                        commonreliable = commonreliable * (1 - ereliable);
                    else:
                        psreliable_0 = psreliable_0 * (1 - ereliable)
                for eq in paths[1]:
                    ereliable = s.failure(eq, month) / seconds
                    if (eq in paths[0]):
                        pass
                    else:
                        psreliable_1 = psreliable_1 * (1 - ereliable)

                buzreliable = commonreliable * (1 - (1 - psreliable_0) * (1 - psreliable_1))
                buzreliablelist.append(buzreliable)
        plt.subplot2grid((1, 2), (0, 1))
        plt.plot(x, buzreliablelist, "b--", linewidth=1)
        # plt.axis('off')
        plt.xlabel("month(s)")  # X轴标签
        plt.ylabel("reliable")  # Y轴标签

        # plt.show()
        plt.savefig(path + businessid + ".png")  # save as png
    plt.close('all')  # 关闭图s

def getEqpAvgRel(equip):
    monthlist = ['2016-01', '2016-02', '2016-03', '2016-04', '2016-05', '2016-06', '2016-07', '2016-08',
                 '2016-09', '2016-10', '2016-11', '2016-12', '2017-01', '2017-02', '2017-03']
    efailure = float(0)
    ereliable = float(1)
    for month in monthlist:
        days = calendar.monthrange(int(month.split('-')[0]), int(month.split('-')[1]))[1]
        seconds = days * 24 * 3600
        efailure += (s.failure(equip, month) / seconds)
    ereliable = 1 - efailure / len(monthlist)
    return ereliable


def getbuz_base(buz_id, buz_type):
    sql2 = "select b.obj_id as business_id, b.buz_type, bc.channel_id from " \
    "t_business_channel bc, t_buz b where (b.OBJ_ID = '%s' and  bc.BUSINESS_ID = '%s' and b.BUZ_TYPE = '%s' AND b.service_state='1')" %(buz_id, buz_id, buz_type)
    df_buz_channel = pd.read_sql(sql2, connection)

    print(len(df_buz_channel))

    if(len(df_buz_channel) > 1):
        clist = list()
        for index in df_buz_channel['channel_id']:
            if index not in clist:
                clist.append(index)
            else:
                continue
        ctuple = str(tuple(clist))
        sql3 = "select obj_id AS channel_id, channel_type, service_state, a_res_type, z_res_type, a_res_id," \
            "z_res_id, a_ne, z_ne, delete_flag from t_channel_base WHERE (delete_flag IS NULL OR delete_flag ='0') AND obj_id IN %s" %ctuple
    else:
        cid = df_buz_channel['channel_id'][0]
        print(cid)
        sql3 = "select obj_id AS channel_id, channel_type, service_state, a_res_type, z_res_type, a_res_id," \
            "z_res_id, a_ne, z_ne, delete_flag from t_channel_base WHERE (delete_flag IS NULL OR delete_flag ='0') AND obj_id = '%s'"  %cid
    df_cbase = pd.read_sql(sql3, connection)
    df_buzbase = pd.merge(df_buz_channel, df_cbase, how='left', on='channel_id')
    return df_buzbase


def getne(buz_id, buz_type):
    global df_buzbase
    global df_cc
    global df_top

    with connection.cursor() as cursor:
        df_buzbase = getbuz_base(buz_id, buz_type)

        sql1 = "select a_ptp,a_ctp, z_ptp ,z_ctp ,delete_flag from t_sdh_cc WHERE DELETE_FLAG IS NULL OR delete_flag ='0'"
        df_cc = pd.read_sql(sql1, connection)
        df_cc['flag'] = 0

        sql2 = "select obj_id as topo_id, a_ne, a_port, z_ne, z_port from t_topology where DELETE_FLAG IS NULL OR delete_flag ='0'"
        df_top = pd.read_sql(sql2, connection)
        df_top['flag'] = 0


    print('buz_id:' + df_buzbase['business_id'][0])
    index_lst = df_buzbase.index
    AllList = []
    for j in index_lst:
        base = df_buzbase.ix[j]
        print('c_id' + base.channel_id + "\n")
        a_res_id = base.a_res_id
        z_res_id = base.z_res_id
        if(a_res_id == ''):
            print("该通道a_res_id为空")
            continue
        if(z_res_id == ''):
            print("该通道z_res_id为空")
            continue
        z_ne = base.z_ne

        df_cc_a = df_cc[(df_cc['a_ptp'] == a_res_id) & (df_cc['flag'] != 1)]
        df_cc_z = df_cc[(df_cc['z_ptp'] == a_res_id) & (df_cc['flag'] != 1)]
        alst = df_cc_a.index
        print(len(alst))
        print('--------------------------------------------------------------------------------------------------------------------------------------')
        zlst = df_cc_z.index
        print(len(zlst))
        if (len(alst) > 0):
            for k in alst:
                cc = df_cc.ix[k]
                ptp_id = cc.z_ptp
                ctp = cc.z_ctp
                OneList = []
                list=[]
                OneList = cc_topo(z_res_id, ptp_id, ctp, z_ne,list)
                print('---------------------------------------onelistz:', OneList)
                AllList.append(OneList)
                cc.flag = 1
        if (len(zlst) > 0):
            for j in zlst:
                cc = df_cc.ix[j]
                ptp_id = cc.a_ptp
                ctp = cc.a_ctp
                OneList = []
                list = []
                OneList = cc_topo(z_res_id, ptp_id, ctp, z_ne,list)
                print('---------------------------------------onelistz:', OneList)
                AllList.append(OneList)
                cc.flag = 1
                print("结束分支")

        print("CHANNEL END\n")
    print("BUSINESS END\n")
    return AllList

def cc_topo(res_id, ptp_id, ctp,z_ne,list):
    len_top = len(df_top)
    for k in range(0, len_top):
        top = df_top.ix[k]
        if (top.a_port == ptp_id):
            list.append(top.a_ne)
            port = top.z_port
            ne = topo_sdh(res_id, port, ctp, z_ne,list)
            if ne != '':
                list.append(ne)
        elif (top.z_port == ptp_id):
            list.append(top.z_ne)
            port = top.a_port
            ne = topo_sdh(res_id, port, ctp, z_ne,list)
            if ne != '':
                list.append(ne)
    return list


def topo_sdh(res_id, port, ctp,z_ne,list):
    df_cc_a = df_cc[(df_cc['a_ptp'] == port) & (df_cc['a_ctp'] == ctp) & (df_cc['flag'] != 1)]
    df_cc_z = df_cc[(df_cc['z_ptp'] == port) & (df_cc['z_ctp'] == ctp) & (df_cc['flag'] != 1)]
    ccindex_alst = df_cc_a.index
    ccindex_zlst = df_cc_z.index
    ne=''
    if (len(ccindex_alst) > 0):
        for i in ccindex_alst:
            cc = df_cc_a.ix[i]
            ptp_id = cc.z_ptp
            ctp = cc.z_ctp
            cc.flag = 1
            if (ptp_id == res_id):
                ne = z_ne
            else:
                cc_topo(res_id, ptp_id, ctp, z_ne,list)
            continue

    if (len(ccindex_zlst) > 0):
        for i in ccindex_zlst:
            cc = df_cc_z.ix[i]
            ptp_id = cc.a_ptp
            ctp = cc.a_ctp
            cc.flag = 1
            if (ptp_id == res_id):
                ne = z_ne
            else:
                cc_topo(res_id, ptp_id, ctp, z_ne,list)
            continue
    return ne


if __name__ == '__main__':
    s = FailureselectByMonth()
    list_buz_id = []
    # buz_id = "A7464B0A-B0FB-41C5-951A-FE17E41B7263-00250"
    # buz_type='2'
    buz_id = sys.argv[1]
    buz_type = sys.argv[2]
    lists = getne(buz_id,buz_type)

    if (len(lists) > 1):
        resolvebusz(buz_id, lists)
    connection.close()
