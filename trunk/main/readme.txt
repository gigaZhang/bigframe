
1、已完成的功能：

 1）、maven项目管理
 2）、spring声明式事务处理
 3）、DAO基类实现基本的CRUD功能，其他的DAO类继承BaseDao
 4）、分页组件封装
 5）、异常处理
 6）、静态文件分离解决，比如静态文件放在CDN上，静态文件的前缀配在bigframe-webapp的constants.properties
 7）、gbk编码时ajax乱码的解决
 8）、单元测试基类的封装
 9）、struts2集成jsonResult
10）、使用jetty应用容器，内存数据库hsqldb测试例子代码
				
2、计划的功能：

  1）、maven插件生成框架(使用freemarker实现)，支持设置代码的编码，目前所有文件的编码为gb18030

使用jetty应用容器，内存数据库hsqldb测试例子代码在trunk/sample/bigframe-hsqldb/下。