package cc.tinker.request;

import java.util.Map;

public class LinkRequest extends BaseRequest {

	private String title;
	private String description;
	private String url;

	public LinkRequest() {
	}

	public LinkRequest(Map<String, String> map) {
		super(map);
		setTitle(map.get("Title"));
		setDescription(map.get("Description"));
		setUrl(map.get("Url"));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
