package cc.tinker.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private List<Button> button = new ArrayList<>();

	public List<Button> getButton() {
		return button;
	}

	public void setButton(List<Button> button) {
		this.button = button;
	}

	public void addButton(Button button) {
		this.button.add(button);
	}

}
