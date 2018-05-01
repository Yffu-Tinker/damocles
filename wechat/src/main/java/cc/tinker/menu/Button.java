package cc.tinker.menu;

public abstract class Button {

	public static final String CLICK = "click";
	public static final String VIEW = "view";
	public static final String SCANCODE_PUSH = "scancode_push";
	public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
	public static final String PIC_SYSPHOTO = "pic_sysphoto";
	public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
	public static final String PIC_WEIXIN = "pic_weixin";
	public static final String LOCATION_SELECT = "location_select";

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
