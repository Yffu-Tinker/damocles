package cc.tinker.event;

import java.util.Map;

public class LocationSelect extends ClickEvent {

	private Long location_X;
	private Long location_Y;
	private Integer scale;
	private String label;
	private String poiname;

	public LocationSelect() {
	}

	public LocationSelect(Map<String, String> map) {
		super();
		setLocation_X(Long.valueOf(map.get("Location_X")));
		setLocation_Y(Long.valueOf(map.get("Location_Y")));
		setScale(Integer.valueOf(map.get("Scale")));
		setLabel(map.get("Label"));
		setPoiname(map.get("Poiname"));
	}

	public Long getLocation_X() {
		return location_X;
	}

	public void setLocation_X(Long location_X) {
		this.location_X = location_X;
	}

	public Long getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(Long location_Y) {
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

	public String getPoiname() {
		return poiname;
	}

	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}

}
