package cc.tinker.event;

import java.util.Map;

public class SubscribeEvent extends BaseEvent {

	public SubscribeEvent() {
	}

	public SubscribeEvent(Map<String, String> map) {
		super(map);
	}

}
