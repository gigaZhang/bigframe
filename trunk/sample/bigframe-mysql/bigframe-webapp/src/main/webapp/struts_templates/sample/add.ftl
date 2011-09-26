<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk18030">
	<title>sample-add</title>
	<link  rel="stylesheet" type="text/css" href="${staticFilePrefix}/css/base.css"/>
</head>
<body>

	<#setting number_format="#">
	<form action="doAdd.htm" method="post" >	
		<div class="p">
			<table width="500px" border="1" class="t2">
				<tr>
					<td width="20%" >Ãû³Æ£º</td><td><input type="text" name="sampleDO.name" maxLength="90"></td>
				</tr>
				<tr>
					<td width="20%" >ÄÚÈÝ£º</td><td><input style="width:300px" type="text" name="sampleDO.content" maxLength="250" ></td>
				</tr>
			</table>
			<input type="submit" value="Ìí¼Ó">
		</div>
	</form>
</body>
</html>