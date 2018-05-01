package cc.tinker.event;

import java.util.Map;

public class QRCodeEvent extends BaseEvent {

	private String eventKey;
	private String ticket;
	
	public QRCodeEvent() {
	}
	
	public QRCodeEvent(Map<String, String> map) {
		super(map);
		setEventKey(map.get("EventKey"));
		setTicket(map.get("Ticket"));
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

}
