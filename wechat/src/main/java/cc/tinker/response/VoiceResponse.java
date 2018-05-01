package cc.tinker.response;

import java.util.Map;

public class VoiceResponse extends BaseResponse {

	private Voice Voice;

	public VoiceResponse() {
		this.setMsgType(Response.VOICE);
	}

	public VoiceResponse(Map<String, String> map) {
		super(map);
		this.setMsgType(Response.VOICE);
	}

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
