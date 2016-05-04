package com.edu.game.resource.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 静态资源注入注释
 * @author frank
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Static {

	/**
	 * 标识值
	 */
	String value() default "";

	/**
	 * 类型
	 */
	Class<?> type() default Void.class;

	/**
	 * 属性名
	 */
	String field() default "content";

	/**
	 * 注入值是否必须
	 */
	boolean required() default true;

	/**
	 * 标识值是否唯一值
	 */
	boolean unique() default false;
}
