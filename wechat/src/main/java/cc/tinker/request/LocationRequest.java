package cc.tinker.request;

import java.util.Map;

public class LocationRequest extends BaseRequest {

	private Double location_X;
	private Double location_Y;
	private Integer scale;
	private String label;

	public LocationRequest() {
	}

	public LocationRequest(Map<String, String> map) {
		super(map);
		setLocation_X(Double.valueOf(map.get("Location_X")));
		setLocation_Y(Double.valueOf(map.get("Location_Y")));
		setScale(Integer.valueOf(map.get("Scale")));
		setLabel(map.get("Label"));
	}

	public Double getLocation_X() {
		return location_X;
	}

	public void setLocation_X(Double location_X) {
		this.location_X = location_X;
	}

	public Double getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(Double location_Y) {
		this.location_Y = location_Y;
	}

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
