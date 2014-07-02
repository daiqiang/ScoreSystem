package com.daiq.aop;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;

//@Aspect
//@Component
public class LogInterceptor implements ThrowsAdvice{
	//@Pointcut("execution(public * com.bjsxt.service..*.add(..))")
	public void myMethod(){};
	/*
	public void afterThrowing(Method method, Object[] args, Object target,Exception ex) throws Throwable{
		System.out.println("------------------------");
		System.out.println(ex.toString());
	}
	*/
	
	
	   /** *//**
     * 对NullPointerException异常的处理
     * @param method
     * @param args
     * @param target
     * @param ex
     * @throws Throwable
     * @author <a href="mailto:xiexingxing1121@126.com">AmigoXie</a>
     * Creation date: 2007-7-24 - 下午01:17:35
     */
	/*
    public void afterThrowing(Method method, Object[] args, Object target,
            NullPointerException ex) throws Throwable {
        System.out.println("*************************************");
        System.out.println("Error happened in class: " + target.getClass().getName());
        System.out.println("Error happened in method: " + method.getName());
        
        for (int i = 0; i < args.length; i++) {
            System.out.println("args[" + i + "]: " + args[i]);
        }
        
        System.out.println("Exception class: " + ex.getClass().getName());
        System.out.println("*************************************");
    }
*/
	
	
	
	//@Before("myMethod()")
	public void before() {
		System.out.println("method before");
	}
	
	//@Around("myMethod()")
	public void aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("method around start");
		joinPoint.proceed();
		System.out.println("method around end");
		/*
		StringBuffer sb = new StringBuffer();   
        try{     
            Object result = joinPoint.proceed();     
            return result;     
        }catch(RuntimeException e){     
            sb.append("开始方法："+joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName()+ "()  ");   
            sb.append("错误信息如下：["+e+"]");   
            System.out.println(sb.toString());    
        }   
        System.out.println("------------------------");
        return false;
        */
	}
	
	public void afterThrowing(JoinPoint point,Exception ex){
		
		System.out.println("------------------------");
		Object[] args = point.getArgs();

        System.out.println("目标参数列表：");

        if (args != null) {
            for (Object obj : args) {
                System.out.println(obj + ",");
            }
            System.out.println(point.getTarget().getClass());
            System.out.println(point.getSignature().getName());
            System.out.println(ex);
        }
	}
	

	       
	
	
	/*
	public void before(Method method, Object[] args, Object o)
			throws Throwable {
		// TODO Auto-generated method stub
		System. out .println("系统日志："+(new Date())+ ":"+"调用了"+method.getName()+ ":使用了参数"+(Arrays.toString(args))); 

	}*/
	
}
