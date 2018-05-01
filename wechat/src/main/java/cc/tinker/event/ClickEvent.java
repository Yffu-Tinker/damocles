package cc.tinker.event;

import java.util.Map;

public class ClickEvent extends BaseEvent {

	private String eventKey;

	public ClickEvent() {
	}

	public ClickEvent(Map<String, String> map) {
		super(map);
		setEventKey(map.get("EventKey"));
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
