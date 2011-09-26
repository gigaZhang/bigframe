<#if (pageModel.totalPage >1) >
    <div class="pagination">
		<div class="page-bottom" >
			<#if !pageModel.isFirstPage><a class="page-prev" href="javascript:turnPage('${pageModel.prevPage}');"><span>上一页</span></a></#if>
			<#list pageModel.pageNumList as pageNum >
				<#if pageModel.toPage == pageNum >
					<span class="page-cur" >${pageNum}</span>
				<#else>
					<a href='javascript:void(0)' onclick="javascript:turnPage(${pageNum})" >${pageNum}</a>
				</#if>
			</#list>			
			<#if !pageModel.isLastPage><a class="page-next" href="javascript:turnPage('${pageModel.nextPage}');"><span>下一页</span></a></#if>
			共${pageModel.totalPage}页 到第<input type="text" class="input-text" id="skipPageNum" value="${pageModel.toPage}" onkeypress="javascript:pageKeyPress(event,this)"/>页 <input type="button"  class="input-button" value="确定" onclick="javascript:turnPage(document.getElementById('skipPageNum').value);"/>
        	<input type="hidden" id="toPage" name="pageModel.toPage" value="${pageModel.toPage}"/>
        	<input type="hidden" name="pageModel.totalItem" value="${pageModel.totalItem}"/>
        </div>
    </div>
</#if>