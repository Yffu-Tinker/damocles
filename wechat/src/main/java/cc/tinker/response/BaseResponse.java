package cc.tinker.response;

import java.util.Date;
import java.util.Map;

public class BaseResponse {

	private String ToUserName;
	private String FromUserName;
	private Long CreateTime = new Date().getTime();
	private String MsgType;

	public BaseResponse() {
	}

	public BaseResponse(Map<String, String> map) {
		this.setFromUserName(map.get("ToUserName"));
		this.setToUserName(map.get("FromUserName"));
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
