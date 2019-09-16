package com.indusfo.spc.exception;


/**
 * 
 * @ClassName: ParamsErrorException
 * @Description: 输入参数异常类
 * @author xuzhe
 * @date 2018年9月29日
 *
 */
public class ParamsErrorException extends GlobalException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4588366011794320865L;

	public ParamsErrorException() {
		super();
	}

	public ParamsErrorException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ParamsErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParamsErrorException(String message) {
		super(message);
	}

	public ParamsErrorException(Throwable cause) {
		super(cause);
	}

	
}
