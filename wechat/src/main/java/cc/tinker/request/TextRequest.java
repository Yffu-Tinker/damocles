package cc.tinker.request;

import java.util.Map;

public class TextRequest extends BaseRequest {

	private String content;

	public TextRequest() {
	}

	public TextRequest(Map<String, String> map) {
		super(map);
		setContent(map.get("Content"));
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
