package cc.tinker.menu;

import java.util.ArrayList;
import java.util.List;

public class Second extends Button {

	private List<Subable> sub_button = new ArrayList<>();

	public List<Subable> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<Subable> sub_button) {
		this.sub_button = sub_button;
	}

	public void addSub_button(Subable Subable) {
		this.sub_button.add(Subable);
	}

}
