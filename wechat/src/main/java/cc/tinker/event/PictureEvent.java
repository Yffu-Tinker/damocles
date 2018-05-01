package cc.tinker.event;

public class PictureEvent extends ClickEvent {

	private String sendPicsInfo;
	private String count;
	private String picList;
	private String picMd5Sum;

	public String getSendPicsInfo() {
		return sendPicsInfo;
	}

	public void setSendPicsInfo(String sendPicsInfo) {
		this.sendPicsInfo = sendPicsInfo;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getPicList() {
		return picList;
	}

	public void setPicList(String picList) {
		this.picList = picList;
	}

	public String getPicMd5Sum() {
		return picMd5Sum;
	}

	public void setPicMd5Sum(String picMd5Sum) {
		this.picMd5Sum = picMd5Sum;
	}

}
