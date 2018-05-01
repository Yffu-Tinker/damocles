package cc.tinker.event;

import java.util.Map;

public class BaseEvent {

	private String toUserName;
	private String fromUserName;
	private Long createTime;
	private String msgType;
	private String event;

	public BaseEvent() {
	}

	public BaseEvent(Map<String, String> map) {
		setToUserName(map.get("ToUserName"));
		setFromUserName(map.get("FromUserName"));
		setCreateTime(Long.valueOf(map.get("CreateTime")));
		setMsgType(map.get("MsgType"));
		setEvent(map.get("Event"));
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
