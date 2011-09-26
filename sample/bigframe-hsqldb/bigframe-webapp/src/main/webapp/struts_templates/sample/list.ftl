<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gbk18030">
	<title>sample-list</title>
	<link  rel="stylesheet" type="text/css" href="${staticFilePrefix}/css/base.css"/>
	<script type="text/javascript" src="${staticFilePrefix}/js/jquery-1.6.1.js" ></script>
	<script type="text/javascript" src="${staticFilePrefix}/js/page_model.js" ></script>
	<script type="text/javascript">
		function update(sid){
			$.ajax({
				  type: 'POST',
				  url: "update.htm",
				  data: {id:sid},
				  success: success,
				  dataType: "json"
			});	
		}
	
		function success(data){
			$("#divid").show();
			$("#sid").val(data.s.id);
			$("#sname").val(data.s.name);
			$("#content").val(data.s.content);
		}
	
		function doUpdate(){
			$("#myForm").attr("action","doUpdate.htm");
			$("#myForm").submit();
		}
	</script>	
</head>
<body>

	<#setting number_format="#">
	<#setting datetime_format="yyyy-MM-dd HH:mm:ss">
	</br>
	<form action="list.htm" method="post" id="myForm" >	
		<div class="p">
			<div><a href="add.htm" target="_blank" >���</a></div>
			<table width="800px" border="1" class="t2">
			  <thead>
			    <th width="5%">���</th>
			    <th width="15%">����</th>
			    <th width="45%">����</th>
			    <th width="20%">����ʱ��</th>
			    <th width="20%">����ʱ��</th>
			    <th width="10%">����</th>
			  </thead>
				<#if pageModel?? >
					<#list pageModel.list as sample >
						<tr <#if sample_index%2==0 >class="a1"</#if>>
							<td>${sample.id}</td>
							<td>${sample.name}</td>
							<td>${sample.content}</td>
							<td>${sample.createTime?datetime}</td>
							<td>${sample.updateTime?datetime}</td>
							<td>
								<div><a href="delete.htm?id=${sample.id}" >ɾ��</a></div>
								<div><a href="javascript:update(${sample.id})" >�޸�</a></div>
							</td>
						</tr>
					</#list>
				</#if>			  
			</table>
			<#include "../common/pageModel.ftl" />
			</br>
			<div id="divid" style="display:none" >
			    <fieldset>
			    	<legend>�޸�sample:</legend>
			    	<input type="hidden" name="sampleDO.id" id="sid">
					���ƣ�<input type="text" value="" name="sampleDO.name" id="sname"  maxLength="90" >
					</br>
					</br>
					���ݣ�<input type="text" value="" style="width:300px" maxLength="250" name="sampleDO.content" id="content" >	
					</br>
					</br>
					<input type="button" value="�޸�" onclick="doUpdate();">
			    </fieldset>				
			</div>		
		</div>
	</form>
</body>
</html>