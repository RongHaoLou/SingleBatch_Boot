package com.indusfo.spc.exception;


/**
 * 
 * @ClassName: QueryFailedException
 * @Description: 查询失败异常类
 * @author xuzhe
 * @date 2018年9月29日
 *
 */
public class QueryFailedException extends GlobalException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2110658694693754789L;

	public QueryFailedException() {
		super();
	}

	public QueryFailedException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public QueryFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryFailedException(String message) {
		super(message);
	}

	public QueryFailedException(Throwable cause) {
		super(cause);
	}
	
}
