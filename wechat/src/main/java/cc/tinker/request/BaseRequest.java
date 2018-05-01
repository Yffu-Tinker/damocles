package cc.tinker.request;

import java.util.Map;

public class BaseRequest {

	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private Long msgId;

	public BaseRequest() {
	}

	public BaseRequest(Map<String, String> map) {
		setToUserName(map.get("ToUserName"));
		setFromUserName(map.get("FromUserName"));
		setCreateTime(Long.valueOf(map.get("CreateTime")));
		setMsgType(map.get("MsgType"));
		setMsgId(Long.valueOf(map.get("MsgId")));
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

}
