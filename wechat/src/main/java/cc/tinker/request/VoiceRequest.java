package cc.tinker.request;

import java.util.Map;

public class VoiceRequest extends BaseRequest {

	private String mediaID;
	private String format;
	private String recognition;

	public VoiceRequest() {
	}

	public VoiceRequest(Map<String, String> map) {
		super(map);
		setMediaID(map.get("MediaId"));
		setFormat(map.get("Format"));
		setRecognition(map.get("Recongnition"));
	}

	public String getMediaID() {
		return mediaID;
	}

	public void setMediaID(String mediaID) {
		this.mediaID = mediaID;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}

}
