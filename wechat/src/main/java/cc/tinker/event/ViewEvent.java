package cc.tinker.event;

import java.util.Map;

public class ViewEvent extends ClickEvent {

	private String menuID;

	public ViewEvent() {
	}

	public ViewEvent(Map<String, String> map) {
		super(map);
		setMenuID(map.get("MenuID"));
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

}
