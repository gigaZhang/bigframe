package com.ldl.bigframe.web.interceptor;


import org.apache.log4j.Logger;

import com.ldl.bigframe.web.exception.BOException;
import com.ldl.bigframe.web.exception.InfoException;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class ExceptionInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getRootLogger();
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        }catch(BOException be){
        	log.error(be.getMessage(),be);
        	return Action.ERROR;
        }catch (InfoException e) {
        	invocation.getInvocationContext().put("info", e.getMessage());
        	log.info("fail in action:" + invocation.getProxy().getActionName(), e);
        	return "info";
        }catch (Exception e) {
            log.error("fail in action:" + invocation.getProxy().getActionName(), e);
            return Action.ERROR;
        }
    }
}
