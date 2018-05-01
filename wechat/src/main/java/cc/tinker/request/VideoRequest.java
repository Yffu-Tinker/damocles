package cc.tinker.request;

import java.util.Map;

public class VideoRequest extends BaseRequest {

	private String mediaId;
	private String thumbMediaId;

	public VideoRequest() {
	}

	public VideoRequest(Map<String, String> map) {
		super();
		setMediaId(map.get("MediaId"));
		setThumbMediaId(map.get("ThumbMediaId"));
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

}
