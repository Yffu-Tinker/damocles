package cc.tinker.response;

import java.util.Map;

public class MusicResponse extends BaseResponse {

	private Music Music;

	public MusicResponse() {
		this.setMsgType(Response.MUSIC);
	}

	public MusicResponse(Map<String, String> map) {
		super(map);
		this.setMsgType(Response.MUSIC);
	}

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
