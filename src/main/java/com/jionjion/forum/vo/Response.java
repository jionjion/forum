package com.jionjion.forum.vo;
/**
 * 	统一返回的对象
 * @author JionJion
 */
public class Response {

	/**返回状态*/
	private Boolean status;
	
	/**返回信息*/
	private String message;
	
	/**返回主体内容*/
	private Object body;

	public Response() {
		super();
	}

	public Response(Boolean status) {
		super();
		this.status = status;
	}

	public Response(Boolean status, String message, Object body) {
		super();
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}
