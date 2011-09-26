package com.ldl.bigframe.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.ldl.bigframe.domain.common.PageModel;
import com.ldl.bigframe.util.Constants;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	protected PageModel pageModel = new PageModel();
	
	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public String urlEncode(String name) throws UnsupportedEncodingException{
		return urlEncode(name, "utf-8");
	}
    
    public String urlEncode(String name,String charset) throws UnsupportedEncodingException {
		return URLEncoder.encode(name, charset);
	}
    
    public String urlDecode(String name) throws UnsupportedEncodingException {
    	return urlDecode(name, "utf-8");
    }
    
    public String urlDecode(String name,String charset) throws UnsupportedEncodingException {
		return URLDecoder.decode(name, charset);
    }
    
    /**
     * 静态文件url前缀,页面静态文件使用
     * 比如<script type="text/javascript" src="${staticFilePrefix}/js/jquery-1.6.1.js" ></script>
     * 好处：静态文件分离，以便使用CDN
     * @return
     */
	public String getStaticFilePrefix() {
		return Constants.STATIC_FILE_URL_PREFIX;
	}    
}
