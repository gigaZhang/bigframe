### 1、已完成的功能： ###
```
 1、使用jetty应用容器
 2、内存数据库hsqldb
 3、异常处理
 4、struts2集成jsonResult
 5、单元测试基类的封装
 6、spring声明式事务处理
 7、DAO基类实现基本的CRUD功能，其他的DAO类继承BaseDao
 8、分页组件封装
 9、maven项目管理
 10、静态文件分离解决，比如静态文件放在CDN上
 11、gbk编码时ajax乱码的解决
计划的功能：
 1.maven插件生成框架(使用freemarker实现)，支持设置代码的编码，目前所有文件的编码为gb18030。
```

### 2、更新列表--2011.11.04 ###
<p>1、修复jetty与hsqldb例子代码工程的jetty启动报错异常。</p>

### 3、新功能--代码生成器--2011.11.22 ###
<p>基于主干开发了代码生成器。</p>
```
1、在代码生成器codeGenerator项目根目录下运行：
   mvn clean compile
   mvn install -Dmaven.test.skip=true;
  将插件安装到本地。
 
2、在frame（为主干项目）项目的pom.xml中写入

<plugin>  
    <groupId>com.ldl.code</groupId>  
    <artifactId>code-maven-plugin</artifactId>  
    <version>1.0.1</version>  
</plugin>  
 
 3、运行插件：
  插件共有四个参数：
  1）templateDirectory：模板文件夹的位置，默认在“用户帐号根目录/codeTemplate”（模板文件在codeGenerator项目的template目录下）。
  2）pdmFile：powerdesinger文件的位置，默认为“用户帐号根目录/code.pdm”。
  3）outputDirectory：生成的代码的输出文件夹（尽量为frame工程的根目录，这样生成的代码就不用再拷到frame里了）。
  4）module：模块名称，有些项目都是分模块开发的（可以为null，则为不分模块）。
   在frame项目下运行(文件夹的位置根据自己的实际位置改写)：
 
   mvn code:generate -DtemplateDirectory=d:/code -DoutputDirectory=d:/frame -DpdmFile=d:/code.pdm
 
4、如果outputDirectory文件夹不是指定在frame工程下，则把生成的代码拷贝到frame工程里。然后在frame工程struts.xml文件中加入<include file="struts-xx.xml"></include>，这个数量看生成的struts-xx.xml文件数量。
 
5、代码生成器的实现原理：
    1）、使用dom4j解析pdm文件生成javabean。
    2）、使用freemarker作为模板。
    3）、目前只支持mysql的数据类型到java数据类型的转换。

```
