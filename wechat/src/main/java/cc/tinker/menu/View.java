package cc.tinker.menu;

public class View extends Button implements Subable {

	private String type = Button.VIEW;
	private String url;

	public String getType() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
