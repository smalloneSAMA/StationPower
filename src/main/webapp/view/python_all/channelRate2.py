from sklearn.model_selection import train_test_split
import pymysql as db
import numpy as np
from sklearn.naive_bayes import BernoulliNB,MultinomialNB
import pandas as pd
from sklearn.feature_extraction.text import CountVectorizer
import sys
# province = 'jiangxi'
# path = 'E:/yeal/JXResult/' + province + '_'
# conn = db.connect(host='172.16.135.6', user='root', passwd='10086', db='jiangxi_before170619', port=3306, charset='utf8')

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

path = 'E:/yeal/JXResult/' + province + '_'
conn = db.connect(host='172.16.135.8', user='jiangxi', passwd='456123', db=province, port=3306, charset='utf8')
cur = conn.cursor()
conn1 = db.connect(host='172.16.135.19', user='root', passwd='hadoop', db='jiangxi_power', port=3306, charset='utf8')
cursor = conn1.cursor()
cur.execute("select obj_id,name,RATE from t_channel_base WHERE (RATE is not  null AND RATE<>'')")
result = cur.fetchall()
data = np.array(result)

#词特征
# word_list = ['E_2M', 'E_155M', 'E_45M',#type=1
#              '光路', '光纤通道', '主用传输段',#type=2
#              'FE', 'MSTP通道']#type=6
# 初始化特征
# x = np.zeros((data.shape[0], len(word_list)), dtype=np.float)
# 获得类别
y_tmp = data[:, -1]
y = []
#y值处理
for i in range(y_tmp.shape[0]):
    if y_tmp[i] == '':
        y.append(0)
    else:
        y.append(int(y_tmp[i]))
# 量化
count_vec = CountVectorizer()
x = count_vec.fit_transform(data[:,1])

# for i in range(data.shape[0]):
#     for j in range(len(word_list)):
#         if data[i, 1].find(word_list[j]) >= 0:
#             x[i, j] = 1
# 交叉验证
x_train, x_test, y_train, y_test = train_test_split(x, y, random_state=1)
# 朴素贝叶斯分类
# clf = BernoulliNB()
clf = MultinomialNB()
# 训练
print(province)
print(x_train.shape)
clf.fit(x_train, y_train)
print(clf.score(x_test, y_test))
# 预测
h = clf.predict(x)
# 可能性
probablity = clf.predict_proba(x)
list_pro = []
for i in range(probablity.shape[0]):
    pro = max(list(probablity[i]))
    if abs(pro - 1.0) < 1e-6:
        pro = 0.99
    list_pro.append(pro)

# 输出结果
# length = data.shape[0]
# result = pd.DataFrame(np.column_stack((data[:, 0:2], data[:, -1], np.array(h).reshape((-1, 1))[0:length, 0:1],np.array(list_pro).reshape((-1,1)))),
#                       columns=['obj_id','NAMW', 'CHANNEL_TYPE', 'predict', 'probablity'])
# result.to_csv(path+"channel_rate_all.csv", index=True, header=True, encoding='gbk')

h = np.array(h).reshape((-1, 1))
list_pro = np.array(list_pro).reshape((-1, 1))
data1 = []  ###############province
data3 = list(data[:, -1])  ###########CHANNEL_TYPE
data2 = list(data[:, 0])  ##############odj_id
data4 = []  ###################predict
data5 = []  ###################probablity
values = []
values1 = []
for i in h:
    data4.append(str(i).replace('[', '').replace("]", "").replace("'", "").replace("'", ""))
for j in list_pro:
    data5.append(str(j).replace('[', '').replace("]", ""))
for i in range(len(data2)):
    data1.append(sys.argv[1])
############异常的值（）diff
for i in range(len(data2)):
    if data3[i] != data4[i]:
        values.append([data1[i], data2[i], data3[i], data4[i], data5[i]])
cursor.execute('delete from channel_rate_diff WHERE province=%s', sys.argv[1])
for i in range(len(values)):
    cursor.execute('insert into channel_rate_diff VALUES(%s,%s,%s,%s,%s)', values[i])

# 找出错误通道类型
# boarr = []
# for i in range(result.shape[0]):
#     boarr.append(result['CHANNEL_TYPE'][i] != result['predict'][i] and result['predict'][i] != '0')
#
# result_diff = result[boarr].sort_values(by='probablity', ascending=False)  # 排序
# result_diff.to_csv(path + "channel_rate_diff.csv", index=True, header=True, encoding='gbk')
# # result[boarr].to_csv(path+"channel_rate_diff.csv", index=True, header=True, encoding='gbk')

#空值处理

cur.execute("select obj_id,name,RATE from t_channel_base WHERE (RATE is null or RATE='')")
result_null = cur.fetchall()
data__null = np.array(result_null)

if len(data__null) > 0:
    x_null = count_vec.transform(data__null[:,1])
    h_null = clf.predict(x_null)
    print("y空值：" + str(x_null.shape))
    probablity_null = clf.predict_proba(x_null)


    list_pro_null = []
    for i in range(probablity_null.shape[0]):
        pro = max(list(probablity_null[i]))
        if abs(pro - 1.0) < 1e-6:
            pro = 0.99
        list_pro_null.append(pro)

    h_null = np.array(h_null).reshape((-1, 1))
    list_pro_null = np.array(list_pro_null).reshape((-1, 1))
    data1 = []  ###############province
    data2 = list(data__null[:, 0])  ##############odj_id
    data4 = []  ###################predict
    data5 = []  ###################probablity
    values = []
    values1 = []
    for i in h_null:
        data4.append(str(i).replace('[', '').replace("]", "").replace("'", "").replace("'", ""))
    for j in list_pro_null:
        data5.append(str(j).replace('[', '').replace("]", ""))
    for i in range(len(data2)):
        data1.append(sys.argv[1])
    ############空的值（）null
    for i in range(len(data2)):
            values1.append([data1[i], data2[i], data4[i], data5[i]])
    cursor.execute('delete from channel_rate_null WHERE province=%s', sys.argv[1])
    for i in range(len(values1)):
        cursor.execute('insert into channel_rate_null VALUES(%s,%s,%s,%s)', values1[i])

conn1.commit()
cursor.close()
conn1.close()

            # result_null = pd.DataFrame(np.column_stack((data__null[:, 0:2], data__null[:, -1], np.array(h_null).reshape((-1, 1))[0:length, 0:1],np.array(list_pro_null).reshape((-1,1)))),
    #                       columns=['obj_id','NAMW', 'CHANNEL_TYPE', 'predict', 'probablity'])
    # result_null.to_csv(path+"channel_rate_null.csv", index=True, header=True, encoding='gbk')

