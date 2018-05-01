package cc.tinker.request;

import java.util.Map;

public class ShortVideoRequest extends BaseRequest {

	private String mediaId;
	private String thumbMediaId;

	public ShortVideoRequest() {
	}

	public ShortVideoRequest(Map<String, String> map) {
		super(map);
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
