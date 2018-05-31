package com.five.common.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.five.common.util.JsonResult;



@ControllerAdvice
public class ExceptionController {

	  @ExceptionHandler(ServiceException.class)
	  @ResponseBody
	  public JsonResult handleServiceException(
			  RuntimeException e){
		  e.printStackTrace();
		  //封装错误信息
		  return new JsonResult(e);
	  }
	  @ExceptionHandler(AuthorizationException.class)
	  @ResponseBody
	  public JsonResult handleAuthorizationeException(
			  AuthorizationException e){
		  System.out.println("handleAuthorizationeException");
		  e.printStackTrace();
		  //封装错误信息
		  return new JsonResult(new ServiceException("没有此权限"));
	  }
}
