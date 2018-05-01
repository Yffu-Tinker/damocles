package cc.tinker.event;

import java.util.Map;

public class ScanCodeEvent extends ClickEvent {

	private String scanType;
	private String scanResult;

	public ScanCodeEvent() {
	}

	public ScanCodeEvent(Map<String, String> map) {
		super(map);
		setScanType(map.get("ScanType"));
		setScanResult(map.get("ScanResult"));
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getScanResult() {
		return scanResult;
	}

	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}

}
