package com.edu.game.resource;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.edu.game.resource.other.ResourceDefinition;

/**
 * 资源管理器工厂
 * @author frank
 */
public class StorageManagerFactory extends DefaultListableBeanFactory implements FactoryBean<StorageManager>,
ApplicationContextAware {
	
	/** 资源定义列表 */
	private List<ResourceDefinition> definitions;
	
	public void setDefinitions(List<ResourceDefinition> definitions) {
		this.definitions = definitions;
	}
	
	private StorageManager storageManager;
	
	@PostConstruct
	protected void initialize() {
		// 属性文件读取支持
		try {
			PropertyPlaceholderConfigurer propertyHolder = applicationContext
					.getBean(PropertyPlaceholderConfigurer.class);
			propertyHolder.postProcessBeanFactory(this);
		} catch (BeansException e) {
			logger.error("No qualifying bean of type [org.springframework.beans.factory.config.PropertyPlaceholderConfigurer] is defined");
		}
		storageManager = this.applicationContext.getAutowireCapableBeanFactory().createBean(StorageManager.class);
		
		long start = System.currentTimeMillis();
		for (ResourceDefinition definition : definitions) {
			// 替代资源路径
			String location = resolveEmbeddedValue(definition.getLocation());
			definition.resolveLocation(location);
			storageManager.initialize(definition);
		}
		String info = String.format("加载数值表数量: %s , 耗时: %.2f 秒", definitions.size(), (System.currentTimeMillis() - start) * 0.001);
		logger.info(info);
		for (ResourceDefinition definition : definitions) {
			storageManager.validate(definition);
		}
	}

	@Override
	public StorageManager getObject() throws Exception {
		return storageManager;
	}
	
	// 实现接口的方法

	@Override
	public Class<StorageManager> getObjectType() {
		return StorageManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	private ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
