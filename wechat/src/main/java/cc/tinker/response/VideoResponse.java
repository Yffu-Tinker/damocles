package cc.tinker.response;

import java.util.Map;

public class VideoResponse extends BaseResponse {

	private Video Video;

	public VideoResponse() {
		this.setMsgType(Response.VIDEO);
	}

	public VideoResponse(Map<String, String> map) {
		super(map);
		this.setMsgType(Response.VIDEO);
	}

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}

}
