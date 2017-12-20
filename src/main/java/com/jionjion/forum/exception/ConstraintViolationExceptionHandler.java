package com.jionjion.forum.exception;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;

/**
 * 	异常处理的工具类
 * @author JionJion
 */
public class ConstraintViolationExceptionHandler {

	/**
	 * 	获取验证信息的返回异常.
	 * @param e 验证失败的异常
	 * @return 处理后的验证信息
	 */
	public static String getMessage(ConstraintViolationException e) {
		List<String> msgList = new ArrayList<String>();
		for(ConstraintViolation<?> constraintViolation : e.getConstraintViolations())
		msgList.add(constraintViolation.getMessage());	
		String messages = StringUtils.join(msgList.toArray() , ';');
		return messages;
	}
}
