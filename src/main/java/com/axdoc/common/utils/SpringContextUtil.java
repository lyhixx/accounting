package com.axdoc.common.utils;

import java.io.IOException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext = null;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
		try {
			System.out.println("系统 注入了spring 上下文" + applicationContext.getResource("").getFile().getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

}
