package cc.tinker.response;

import java.util.Map;

public class TextResponse extends BaseResponse {

	private String Content;

	public TextResponse() {
		this.setMsgType(Response.TEXT);
	}

	public TextResponse(Map<String, String> map) {
		super(map);
		this.setMsgType(Response.TEXT);
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
