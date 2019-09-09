package com.wipro.jfs.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Logging {
	
	/**
	 * Following is the definition for a pointcut to select all the methods
	 * available. So advice will be called for all the methods.
	 */
	@Pointcut("execution(* *(..)) &&" + 
			"(" + 
			"    within(com.wipro.jfs.controller..*) ||" + 
			"    within(com.wipro.jfs.service..*) ||" + 
			"    within(com.wipro.jfs.dao..*)" + 
			")")
	private void selectAll() {
	}

	/**
	 * This is the method which I would like to execute before a selected method
	 * execution.
	 */
	@Before("selectAll()")
	public void beforeAdvice(JoinPoint jp) {
		System.out.println("Before Execution classname : " + jp.getClass().getCanonicalName() + "," + jp.getSignature().getName());
	}

	/**
	 * This is the method which I would like to execute after a selected method
	 * execution.
	 */
	@After("selectAll()")
	public void afterAdvice(JoinPoint jp) {
		System.out.println("After Execution classname : " + jp.getClass().getCanonicalName() + "," + jp.getSignature().getName());
	}

	/**
	 * This is the method which I would like to execute when any method returns.
	 */
	@AfterReturning(pointcut = "selectAll()", returning = "retVal")
	public void afterReturningAdvice(Object retVal, JoinPoint jp) {
		System.out.println("Returning:" + retVal.toString() + "After Execution classname : " + jp.getClass().getCanonicalName() + "," + jp.getSignature().getName());
	}

	/**
	 * This is the method which I would like to execute if there is an exception
	 * raised by any method.
	 */
	@AfterThrowing(pointcut = "selectAll()", throwing = "ex")
	public void AfterThrowingAdvice(IllegalArgumentException ex, JoinPoint jp) {
		System.out.println("There has been an exception: classname : " + jp.getClass().getCanonicalName() + "," + jp.getSignature().getName());
		System.out.println(ex.toString());
	}
	
}