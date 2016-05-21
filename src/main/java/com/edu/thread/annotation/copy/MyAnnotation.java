package com.edu.thread.annotation.copy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.CONSTRUCTOR,ElementType.TYPE} ) //annotation�������� �࣬������������
@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
	String name() ; //���巽��
	
	Student student(); //
}