from sklearn.feature_extraction.text import CountVectorizer
from sklearn.model_selection import train_test_split
import pymysql as db
import numpy as np
from sklearn.naive_bayes import BernoulliNB, MultinomialNB
import pandas as pd
import sys
import math

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



# province = 'jiangxi'
# path = 'E:/yeal/JXResult/' + province + '_'
# conn = db.connect(host='172.16.135.6', user='root', passwd='10086', db='jiangxi_before170619', port=3306, charset='utf8')
#province = 'jiangxi'
path = 'E:/' + province + '_'
conn = db.connect(host='172.16.135.6', user='root', passwd='10086', db='jiangxi_before170619', port=3306, charset='utf8')

cur = conn.cursor()
cur.execute("select obj_id,name,CHANNEL_TYPE from t_channel_base WHERE (CHANNEL_TYPE is not  null AND CHANNEL_TYPE<>'')")
result = cur.fetchall()
data = np.array(result)

# word_list = ['E_2M', 'E_155M', 'E_45M',#type=1
#              '光路', '光纤通道', '主用传输段',#type=2
#              'FE', 'MSTP通道']#type=6
# # 初始化特征
# x = np.zeros((data.shape[0], len(word_list)), dtype=np.float)
# 获得类别
y_tmp = data[:, -1]
y = []
for i in range(y_tmp.shape[0]):
    if y_tmp[i] == '':
        print("null")
        y.append(0)
    else:
        y.append(int(y_tmp[i]))
# 量化
count_vec = CountVectorizer()
x = count_vec.fit_transform(data[:,1])
print(x.shape)
# for i in range(data.shape[0]):
#     for j in range(len(word_list)):
#         if data[i, 1].find(word_list[j]) >= 0:
#             x[i, j] = 1
# 交叉验证
x_train, x_test, y_train, y_test = train_test_split(x, y, random_state=33)
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
# print(probablity.shape,type(probablity),type(probablity[1]))
# #####取分子e指数
# for i in range(probablity.shape[0]):
#     probablity[i]=np.exp(probablity[i])
# print(probablity[1])
# print(probablity[1,1])
# for i in range(probablity.shape[0]):
#     probablity[i]=probablity[i]/(probablity[i,0]+probablity[i,1]+probablity[i,2])

# print("************")
# print(x[1])
# p=np.zeros(probablity.shape)
# pp=np.zeros(clf.feature_count_.shape)
# for i in range(clf.feature_count_.shape[0]):
#     pp[i]=np.log(clf.feature_count_[i]/clf.class_count_[i])
# p=np.zeros(clf.feature_count_.shape)
# for i in range(clf.feature_count_.shape[0]):
#     p[i]=np.exp(clf.feature_log_prob_[i])



fl=np.float64(1.0)
list_pro = []
for i in range(probablity.shape[0]):
    pro = max(list(probablity[i]))

    if  abs(pro-1.0)<1e-6:
        pro=0.99
    list_pro.append(pro)



# 输出结果
length = data.shape[0]
result = pd.DataFrame(np.column_stack((data[:, 0:2], data[:, -1], np.array(h).reshape((-1, 1))[0:length, 0:1],np.array(list_pro).reshape((-1,1)))),
                      columns=['obj_id','NAMW', 'CHANNEL_TYPE', 'predict', 'probablity'])


result.to_csv(path+"channel_type_all.csv", index=True, header=True, encoding='gbk')
# 找出错误通道类型
boarr = []
for i in range(result.shape[0]):
    boarr.append(result['CHANNEL_TYPE'][i] != result['predict'][i])
result_diff = result[boarr].sort_values(by='probablity', ascending=False)  # 排序
result_diff.to_csv(path + "channel_type_diff.csv", index=False, header=True, encoding='gbk')

# result[boarr].to_csv(path+"channel_type_diff.csv", index=True, header=True, encoding='gbk')

#空值处理
cur.execute("select obj_id,name,CHANNEL_TYPE from t_channel_base WHERE (CHANNEL_TYPE is null or CHANNEL_TYPE='')")
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

    result_null = pd.DataFrame(np.column_stack((data__null[:, 0:2], data__null[:, -1], np.array(h_null).reshape((-1, 1))[0:length, 0:1],np.array(list_pro_null).reshape((-1,1)))),
                          columns=['obj_id','NAMW', 'CHANNEL_TYPE', 'predict', 'probablity'])
    result_null.to_csv(path+"channel_type_null.csv", index=True, header=True, encoding='gbk')


#########输出到数据库中########################################################################################################
connect=db.connect(host="172.16.135.19",user="root",passwd="hadoop",db="jiangxi_power",port=3306,charset='utf8')
cursor=connect.cursor()
sql="create table if not exists channel_type_diff" \
    "(province varchar(255) DEFAULT NULL,obj_id varchar(255) DEFAULT NULL,channel_type varchar(255) DEFAULT NULL," \
    "predict varchar(255) DEFAULT NULL,probablity varchar(255) DEFAULT NULL);"
cursor.execute(sql)

h=np.array(h).reshape((-1, 1))
list_pro=np.array(list_pro).reshape((-1, 1))
data4=[]###############predicate
data5=[]################probablity
values=[]
values1=[]
for i in h:
    data4.append(str(i).replace('[','').replace("]","").replace("'","").replace("'",""))
for j in list_pro:
    data5.append(str(j).replace('[','').replace("]",""))

data1=list(data[:,0])##############odj_id
data2=[]###############province
data3=list(data[:,-1])###########Type

for i in range(len(data1)):
    data2.append(sys.argv[1])
############异常的值（）diff
for i in range(len(data1)):
    if data3[i]!=data4[i] and data3[i]!='':
        values.append([data2[i],data1[i],data3[i],data4[i],data5[i]])

# cursor.execute('delete from buztype_diff ')
cursor.execute('delete from channel_type_diff WHERE province=%s',sys.argv[1])
for i in range(len(values)):
    cursor.execute('insert into channel_type_diff VALUES(%s,%s,%s,%s,%s)',values[i])

##空值################3
for i in range(len(data1)):
    if data3[i] != data4[i] and data3[i] == '':
        values1.append([data2[i],data1[i],data4[i],data5[i]])
cursor.execute('delete from channel_type_null WHERE province=%s',sys.argv[1])
for i in range(len(values1)):
    cursor.execute('insert into channel_type_null VALUES(%s,%s,%s,%s)', values1[i])


connect.commit()
cursor.close()
connect.close()
