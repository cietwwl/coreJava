package com.edu.codis.protocol.exception;
import java.io.IOException;

public class UnknowSignalException extends IOException{
	private static final long serialVersionUID = -7844776109254208895L;

	public UnknowSignalException(int type, int signal) {
		super("代理类型[" + type + "]无法识别的值标记[" + signal + "]");
	}
}
