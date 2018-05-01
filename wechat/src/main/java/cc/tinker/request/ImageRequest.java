package cc.tinker.request;

import java.util.Map;

public class ImageRequest extends BaseRequest {

	private String picUrl;
	private String mediaId;

	public ImageRequest() {
	}

	public ImageRequest(Map<String, String> map) {
		super(map);
		setPicUrl(map.get("PicUrl"));
		setMediaId(map.get("MediaId"));
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
