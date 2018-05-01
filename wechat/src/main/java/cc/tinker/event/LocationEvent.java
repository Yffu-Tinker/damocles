package cc.tinker.event;

import java.util.Map;

public class LocationEvent extends BaseEvent {

	private Double latitude;
	private Double longitude;
	private Double precision;

	public LocationEvent() {
	}

	public LocationEvent(Map<String, String> map) {
		super(map);
		setLatitude(Double.valueOf(map.get("Latitude")));
		setLongitude(Double.valueOf(map.get("Longitude")));
		setPrecision(Double.valueOf(map.get("Precision")));
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getPrecision() {
		return precision;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

}
