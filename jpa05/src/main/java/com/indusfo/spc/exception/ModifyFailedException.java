package com.indusfo.spc.exception;


/**
 * 
 * @ClassName: ModifyFailedException
 * @Description: 修改操作异常类
 * @author xuzhe
 * @date 2018年9月29日
 *
 */
public class ModifyFailedException extends GlobalException {

	
	/**
	 *  
	 */
	private static final long serialVersionUID = -1963596477883667304L;

	public ModifyFailedException() {
		super();
	}

	public ModifyFailedException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ModifyFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ModifyFailedException(String message) {
		super(message);
	}

	public ModifyFailedException(Throwable cause) {
		super(cause);
	}
	
}
