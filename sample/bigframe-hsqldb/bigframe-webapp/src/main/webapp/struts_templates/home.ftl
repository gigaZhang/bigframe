<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk18030">
	<title>freemarker+struts2+spring3+mybatis+hsqldb整合</title>
	<link  rel="stylesheet" type="text/css" href="${staticFilePrefix}/css/base.css"/>
</head>
<body>
	<div class="d">
		<div style="font-size: 25px;text-align:center" >
			freemarker + struts2 + spring3 + mybatis + hsqldb 整合：
			<br>
			<br>
		</div>
		<div>
			1、已完成的功能：
			<ul>
				<li>1）、异常处理</li>
				<li>2）、spring声明式事务处理</li>
				<li>3）、DAO基类实现基本的CRUD功能，其他的DAO类继承BaseDao</li>
				<li>4）、分页组件封装</li>
				<li>5）、maven项目管理</li>
				<li>6）、静态文件分离解决，比如静态文件放在CDN上，静态文件的前缀配在bigframe-webapp的constants.properties</li>
				<li>7）、gbk编码时ajax乱码的解决</li>
				<li>8）、单元测试基类的封装</li>
				<li>9）、struts2集成jsonResult</li>
				<li>10）、使用jetty应用容器，内存数据库hsqldb测试例子代码</li>
			</ul>
		<div>
		<div>
			2、计划的功能：
			<ul>
				<li>1）、maven插件生成框架(使用freemarker实现)，支持设置代码的编码，目前所有文件的编码为gb18030</li>
			</ul>
		<div>
		<div>
			<a href="sample/list.htm" target="_blank" >例子</a>
		</div>
	</div>
</body>
</html>