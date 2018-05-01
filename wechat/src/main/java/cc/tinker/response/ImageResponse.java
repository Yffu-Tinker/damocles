package cc.tinker.response;

import java.util.Map;

public class ImageResponse extends BaseResponse {

	private Image Image;

	public ImageResponse() {
		this.setMsgType(Response.IMAGE);
	}

	public ImageResponse(Map<String, String> map) {
		super(map);
		this.setMsgType(Response.IMAGE);
	}

	public Image getImage() {
		return Image;
	}

	public void setImage(Image image) {
		Image = image;
	}

}
