import sys
from sklearn.model_selection import train_test_split
import pymysql as db
import numpy as np
import pandas as pd
from sklearn import tree

def predict_buz_rate(province):
    # jiangxi_before170619
    word_type = 'buz_rate'
    #path = 'C:/dykang/electricData/predict/' + word_type + '/' + province + '_'
    path = 'F:/' + province + '_'
    # conn = db.connect(host='172.16.135.6', user='root', passwd='10086', db=province, port=3306, charset='utf8')
    conn = db.connect(host='172.16.135.8', user='jiangxi', passwd='456123', db=province, port=3306, charset='utf8')
    # 业务带宽不为空
    cur = conn.cursor()
    cur.execute("select distinct name,buz_type,dispatch_level,a_site_id,z_site_id,0,0,buz_rate from t_buz where buz_rate is not null and buz_rate <> ''")
    result = cur.fetchall()
    data = np.array(result)
    # 所有数据
    cur2 = conn.cursor()
    cur2.execute("select distinct obj_id,name,buz_type,dispatch_level,a_site_id,z_site_id,0,0,buz_rate from t_buz ")
    result2 = cur2.fetchall()
    data2 = np.array(result2)
    # 站点表
    cur1 = conn.cursor()
    cur1.execute("select obj_id,OBJ_DISPIDX from t_spc_site")
    result1 = cur1.fetchall()
    data1 = np.array(result1)
    # 站点id和显示序号字典
    site_dic = {}
    for i in range(data1.shape[0]):
        site_dic[data1[i][0]] = data1[i][1]
    # 处理特征数据
    for i in range(data.shape[0]):
        if site_dic.__contains__(data[i][3]):
            data[i][5] = site_dic[data[i][3]]
        if site_dic.__contains__(data[i][4]):
            data[i][6] = site_dic[data[i][4]]
        for j in [1, 2, 5, 6]:
            if data[i][j] == '':
                data[i][j] = 0
    # 特征
    x = np.concatenate((data[:, 1:3], data[:, 5:7]), axis=1)
    # 类别
    y = data[:, -1]
    # 交叉验证
    x_train, x_test, y_train, y_test = train_test_split(x, y, random_state=1)
    # 决策树模型
    clf = tree.DecisionTreeClassifier()
    # 训练
    clf = clf.fit(x_train, y_train)
    # 模型得分
    print(clf.score(x_test, y_test))
    # 预测
    y_all = data2[:, -1]
    for i in range(data2.shape[0]):
        if site_dic.__contains__(data2[i][4]):
            data2[i][6] = site_dic[data2[i][4]]
        if site_dic.__contains__(data2[i][5]):
            data2[i][7] = site_dic[data2[i][5]]
        for j in [2, 3, 6, 7]:
            if data2[i][j] == '':
                data2[i][j] = 0
    # 特征
    x_all = np.concatenate((data2[:, 2:4], data2[:, 6:8]), axis=1)
    h = clf.predict(x_all)
    # 可能性
    probablity = clf.predict_proba(x_all)
    list_pro = []
    for i in range(probablity.shape[0]):
        pro = max(list(probablity[i]))
        if abs(pro - 1.0) < 1e-6:
            pro = 0.99
        list_pro.append(pro)
    # 输出结果
    length = data.shape[0]
    result = pd.DataFrame(np.column_stack((data2[:, 0:2], y_all, np.array(h).reshape((-1, 1)), np.array(list_pro).reshape((-1, 1)))),
                          columns=['obj_id', 'name', 'buz_rate', 'predict', 'probablity'])
    result.to_csv(path+"buz_rate_all.csv", index=False, header=True, encoding='gbk')

    # 找出错误的业务速率,包括空值和错误值
    boarr_diff = []
    boarr_null = []
    for i in range(result.shape[0]):
        boarr_diff.append(result['buz_rate'][i] != result['predict'][i] and result['buz_rate'][i] != '')
        boarr_null.append(result['buz_rate'][i] != result['predict'][i] and result['buz_rate'][i] == '')
    # 错误值导出
    result_diff = result[boarr_diff].sort_values(by='probablity', ascending=False)  # 排序
    result_diff.to_csv(path+"buz_rate_diff.csv", index=False, header=True, encoding='gbk')
    # 空值导出
    result_null = result[boarr_null].sort_values(by='probablity', ascending=False)  # 排序
    result_null.to_csv(path+"buz_rate_null.csv", index=False, header=True, encoding='gbk')

    ########## 输出到数据库中########################################################################################################
    connect = db.connect(host="172.16.135.19", user="root", passwd="hadoop", db="jiangxi_power", port=3306, charset='utf8')
    cursor = connect.cursor()
    sql = "create table if not exists buz_rate_diff" \
          "(Province varchar(255) DEFAULT NULL,obj_id varchar(255) DEFAULT NULL,name varchar(255) DEFAULT NULL,buz_rate varchar(255) DEFAULT NULL," \
          "predict varchar(255) DEFAULT NULL,probablity varchar(255) DEFAULT NULL);"
    cursor.execute(sql)

    h = np.array(h).reshape((-1, 1))
    list_pro = np.array(list_pro).reshape((-1, 1))
    data4 = []  ###############predicate
    data5 = []  ################probablity
    values = []
    for i in h:
        data4.append(str(i).replace('[', '').replace("]", "").replace("'", "").replace("'", ""))
    for j in list_pro:
        data5.append(str(j).replace('[', '').replace("]", ""))

    data1 = list(data2[:,0])  ##############odj_id
    datap = []  ###############province
    data3 = list(data2[:, -1])  ###########buzType
    data6 = list(data2[:,1])

    for i in range(len(data1)):
        datap.append(sys.argv[1])
    ############异常的值（）diff
    for i in range(len(data1)):
        if data3[i] != data4[i] and data3[i]!= '':
            values.append([datap[i], data1[i],data6[i], data3[i], data4[i], data5[i]])

    #cursor.execute('delete from BuzRate_diff ')
    cursor.execute('delete from buz_rate_diff WHERE province=%s', sys.argv[1])
    for i in range(len(values)):
        cursor.execute('insert into buz_rate_diff VALUES(%s,%s,%s,%s,%s,%s)', values[i])
    ##空值
    values=[]
    for i in range(len(data1)):
        if data3[i] != data4[i] and data3[i] == '':
            values.append([datap[i], data1[i], data6[i], data4[i], data5[i]])
    cursor.execute('delete from buz_rate_null WHERE province=%s', sys.argv[1])
    for i in range(len(values)):
        cursor.execute('insert into buz_rate_null VALUES(%s,%s,%s,%s,%s)', values[i])

    connect.commit()
    cursor.close()
    connect.close()


#province_list = ['anhui', 'chongqing', 'gansu', 'henan', 'hubei', 'hunan', 'jiangsu', 'liaoning', 'ningxia', 'shan1xi',
#                 'shandong', 'sicuan', 'xinjiang', 'jiangxi_before170619']

# province_list = ['beijing', 'chengdu', 'fujian', 'hebei', 'heilongjiang', 'jibei', 'jilin', 'mengdong', 'qinghai',
#                  'shan3xi', 'xizang', 'zhejiang']  # 'tianjin'
# for i in range(len(province_list)):
#     predict_buz_rate(province_list[i])




##命令行参数##############################################################################
print("**************")
print(sys.argv)
print(sys.argv[1])
dict={'安徽':'anhui','北京':'beijing','成都':'chengdu','重庆':'chongqing','福建':'fujian','甘肃':'gansu','河北':'hebei','黑龙江':'heilongjang',
      '河南':'henan','湖北':'hubei','湖南':'hunan','江苏':'jiangsu','江西':'jiangxi','冀北':'jibei','吉林':'jilin','辽宁':'liaoning','蒙东':'mengdong',
      '宁夏':'ningxia','青海':'qinghai','山东':'shandong','山西':'shanxi','四川':'sichuan','新疆':'xinjiang','西藏':'xizang','浙江':'zhejiang'}
name=dict[sys.argv[1]]
print(name)

database =name
province =name
predict_buz_rate(province)
