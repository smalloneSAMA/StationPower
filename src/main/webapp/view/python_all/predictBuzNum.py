from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder
from sklearn.preprocessing import scale
from sklearn.model_selection import GridSearchCV
from sklearn import linear_model
import pymysql as db
import numpy as np
from sklearn import tree
from sklearn import svm
import matplotlib.pyplot as plt
import pandas as pd
import matplotlib as mpl


if __name__ == "__main__":
    def predictCorrect(num,predict):
        if(predict-20<num<predict+20):
            return True
        if(predict/2<num<predict*2):
            return True
        return False


    #####################Matplotlib 是一个 Python 的 2D绘图库，mpl.rcParams对参数设置
    mpl.rcParams['font.sans-serif'] = [u'SimHei']#指定默认字体

    mpl.rcParams['axes.unicode_minus'] = False#解决保存图像是负号'-'显示为方块的问题

    #connJiangxi = db.connect(host='127.0.0.1', user='root', passwd='liujie', db='StateGrid', port=3306, charset='utf8')
    connJiangxi = db.connect(host='172.16.135.6', user='root', passwd='10086', db='jiangxi_before', port=3306, charset='utf8')
    #connJiangxi = db.connect(host='localhost', user='root', passwd='', db='233', port=3306,charset='utf8')
    curJiangxi = connJiangxi.cursor()

    #connBeijing = db.connect(host='127.0.0.1', user='root', passwd='liujie', db='StateGrid', port=3306, charset='utf8')
    connBeijing = db.connect(host='172.16.135.8', user='jiangxi', passwd='456123', db='beijing', port=3306, charset='utf8')
    #connBeijing = db.connect(host='localhost', user='root', passwd='', db='233', port=3306,charset='utf8')
    curBeijing = connBeijing.cursor()

    #connHebei = db.connect(host='127.0.0.1', user='root', passwd='liujie', db='StateGrid', port=3306, charset='utf8')
    connHebei = db.connect(host='172.16.135.8', user='jiangxi', passwd='456123', db='hebei', port=3306,charset='utf8')
    #connHebei = db.connect(host='localhost', user='root', passwd='', db='233', port=3306, charset='utf8')
    curHebei = connHebei.cursor()

    #cur.execute("select RES_OBJ_ID,STATION_ID,SYS_OBJ_ID,PRODUCER_ID,DEV_TYPE_ID,ALARM_EMS_TIME,ALARM_EMS_TIME from restype_NE")
    curJiangxi.execute("select name,stationType,(2017-year) as years,degree_centrality*100,neNum,0,0,1,buzNum FROM sitebuznum_all where degree_centrality is not NULL")
    resultJiangxi = curJiangxi.fetchall()
    curBeijing.execute(
        "select name,stationType,(2017-year) as years,degree_centrality*100,neNum,0,1,0,buzNum FROM sitebuznum where degree_centrality is not NULL")
    resultBeijing = curBeijing.fetchall()
    curHebei.execute(
        "select name,stationType,(2017-year) as years,degree_centrality*100,neNum,1,0,0,buzNum FROM sitebuznum where degree_centrality is not NULL")
    resultHebei = curHebei.fetchall()
    # conn.close()
    dataJiangxi=np.array(resultJiangxi)##############查询得到的二维数组
    dataBeijing = np.array(resultBeijing)
    dataHebei = np.array(resultHebei)
    print(dataJiangxi.shape[0])#dataJiangxi的行数
    print(dataBeijing.shape[0])
    print(dataHebei.shape[0])
##################取查询结果的第一列，即name属性
    names=[]
    for i in range(dataJiangxi.shape[0]):
        names.append(dataJiangxi[i,0])
    for i in range(dataBeijing.shape[0]):
        names.append(dataBeijing[i,0])
    for i in range(dataHebei.shape[0]):
        names.append(dataHebei[i, 0])
#########data是从三个数据库中查询到的结果二维数组以行为组合成一个数组
    data = np.row_stack((dataJiangxi,dataBeijing,dataHebei))
    x_train, y_train = data[:, :-1], data[:, -1]###############x_train是data中不包含最后一列的数据，y_train中是data中最后一列数据即buzNum
    y = []
    for i in range(y_train.shape[0]):###############
        y.append(int(y_train[i]))       ##############y[]为buzNum，作为类别号，分类标签
    x = np.zeros((x_train.shape[0], 8), dtype=np.float)  #x行数为查询到的二位数据data的行数即记录条数，列数为8

    for i in range(x_train.shape[0]):      #从name属性中查找，有‘35kV’的在x中第一列对应行记录为1，有。。。
        if (x_train[i, 0].find('35kV') >= 0):
            x[i, 0] = 1

        elif (x_train[i, 0].find('110kV') >= 0):
            x[i, 0] = 2

        elif (x_train[i, 0].find('220kV') >= 0):
            x[i, 0] = 3

        elif (x_train[i, 0].find('500kV') >= 0):
            x[i, 0] = 4
        elif (x_train[i, 1] == '1'): #中心站
            x[i, 0] = 5
        elif (x_train[i, 1] == '6'): #供电所
            x[i, 0] = 6
        elif (x_train[i, 1] == '12'): #县供电企业
            x[i, 0] = 7
        elif (x_train[i, 1] == '13'):  # 乡镇供电所
            x[i, 0] = 8
        elif (x_train[i, 1] == '4'):  # 电厂
            x[i, 0] = 9
        else:
            x[i, 0] = 10
                      #x[:,0]即x第一列，x从第三列开始到最后列的数据，axis=1即按列组合
    x = np.concatenate((x[:, 0:1], x[:,2:]), axis=1)#x去掉了第二列，现有7列
    for i in range(x_train.shape[0]):
        for j in [1,2,3,4,5,6]:
            x[i,j]=float(x_train[i,j])#将查询到的stationType,(2017-year) as years,degree_centrality*100,neNum,0,0六列复制给x
    #x=x[:,]

    enc = OneHotEncoder(categorical_features=[0])#对第一列进行独热编码
    z = enc.fit_transform(x)#对x的第一列即name属性    对于名词性（Norminal）的类别，计算距离
    z = scale(z.todense())


    #z = z[:,:-1]
    clf = svm.NuSVR(C=20000,nu=0.1)
    #clf = svm.SVR(C=10000)
    #clf = linear_model.Ridge(alpha=10)
    #clf=tree.DecisionTreeRegressor(min_samples_leaf=10,max_depth=5)
    # alpha_can = np.logspace(-1, 2, 5)
    # cv_model = GridSearchCV(model, param_grid={'alpha': alpha_can}, cv=5)
    print('begin train...')

    #z_train, z_test, y_train, y_test = z[index:,:],z[:index,:],y[index:],y[:index]
    z_train, z_test, y_train, y_test = train_test_split(z,y,random_state=1)
    clf.fit(z_train, y_train)
    #model.fit(x_train, y_train)
    # print('验证参数：\n', model.get_params())
    #print(clf.coef_,clf.intercept_)
    print(clf.score(z_test, y_test))##############score方法评分
    print(clf.support_.__len__())#############_len_()返回字符串长度或列表元素个数
    Y = clf.predict(z)
    # for i in range(dataJiangxi.shape[0]):
    #
    #     print(i,names[i],y[i],format(Y[i],'.2f'))
    #print(count/float(z.__len__()))
    length = dataJiangxi.shape[0]
    for i in range(Y.__len__()):
        Y[i] = round(Y[i],2)##############round() 方法返回浮点数x的四舍五入值。，保留n位小数
        ##################y[]为buzNum，作为类别号，分类标签
    #################result利用pandas中DataFrame二维数据结构，存储了江西查询出来的结果的第一列即name属性，buzNum，预测的结果，列名分别为name,inSys,predict
    ###########3也就是说，先前我们不知道z的shape属性是多少，但是想让z变成只有一列，行数不知道多少，通过`z.reshape(-1,1)`
    result = pd.DataFrame(np.column_stack((dataJiangxi[:,0:1],np.array(y).reshape((-1,1))[0:length,0:1]
                                           ,np.array(Y).reshape((-1,1))[0:length,0:1])) , columns=['name','inSys','predict'])

    result['predict'][pd.Series(result['predict'], dtype=np.float32) < 1] = 1.0
    delta = (pd.Series(result['inSys'], dtype=np.float32) - pd.Series(result['predict'], dtype=np.float32)).abs()

    ratio = (pd.Series(result['inSys'], dtype=np.float32) / pd.Series(result['predict'], dtype=np.float32))
    ratio[ratio<1] = 1/(ratio[ratio<1])
    result['ratio'] = ratio
    ratio = ((pd.Series(result['inSys'], dtype=np.float32)+10) / (pd.Series(result['predict'], dtype=np.float32) + 10))
    ratio[ratio<1] = 1/(ratio[ratio<1])
    score = delta ** (ratio )
    result['delta'] = delta
    result['score'] = score
    result = result.sort_values(by='score',ascending=True)##############依据SCORE字段排序升序
    result = result.reset_index(drop=True)
    # result.to_csv("buz_num.csv", index=False, header=True, encoding='gbk')
    boarr=[]
    for i in range(result.shape[0]):
        boarr.append(result['name'][i].find('供电公司')>=0)##############找到结果result中名称name包含供电公司的数据
   # print(result[boarr])
   # print(x[boarr])





    # print("\n********************************\n")
    # num=result.iloc[:,0].size
    # result1=result.ix[:9,:'predict']
    # result2=result.ix[num-10:num,:'predict']
    # result1.to_csv("buz_num1.csv", index=False, header=True, encoding='gbk')
    # result2.to_csv("buz_num2.csv", index=False, header=True, encoding='gbk')
    resultfull=np.array(result)
    # result1n=np.array(result1)
    # result1n=result1n.tolist()
    # result2n = np.array(result2)
    # result2n = result2n.tolist()
    # print(result1n[1][0])


 ########## 输出到数据库中########################################################################################################
    connect = db.connect(host="172.16.135.19", user="root", passwd="hadoop", db="jiangxi_power", port=3306, charset='utf8')
    cursor = connect.cursor()
    sql = "create table if not exists Station_top10" \
          "(ID INTEGER NOT NULL AUTO_INCREMENT,TransformSubstation varchar(255) DEFAULT NULL,BusinessNumRecords varchar(255) DEFAULT NULL,BusinessNumForecast varchar(255) DEFAULT NULL,Score VARCHAR (255) DEFAULT NULL," \
          "PRIMARY KEY (`ID`));"
    cursor.execute(sql)
    # sql1 = "create table if not exists AccuratePrediction_top10" \
    #       "(ID INTEGER NOT NULL AUTO_INCREMENT,TransformSubstation varchar(255) DEFAULT NULL,BusinessNumRecords varchar(255) DEFAULT NULL,BusinessNumForecast varchar(255) DEFAULT NULL," \
    #        "PRIMARY KEY (`ID`));"
    # cursor.execute(sql1)

    values=[]
    values1=[]
    ############所有值

    for i in range(resultfull.shape[0]):
        values.append([i+1,resultfull[i][0],resultfull[i][1],resultfull[i][2],resultfull[i][5]])
        #values.extend(result1n[i])

    cursor.execute('delete from Station_top10 ')
    #cursor.execute('delete from buz_rate_diff WHERE province=%s', sys.argv[1])
    for i in range(len(values)):
        cursor.execute('insert into Station_top10 VALUES(%s,%s,%s,%s,%s)', values[i])
    # ##差的值
    # for i in range(10):
    #     values1.append([i+1, result2n[9-i][0], result2n[9-i][1], result2n[9-i][2]])
    # cursor.execute('delete from AbnormalStation_top10 ')
    # for i in range(len(values)):
    #     cursor.execute('insert into AbnormalStation_top10 VALUES(%s,%s,%s,%s)', values1[i])

    connect.commit()
    cursor.close()
    connect.close()

