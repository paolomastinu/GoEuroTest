import java.math.BigDecimal;


public class CSVBeanData {
	private BigDecimal _id;
	private String name;
	private String type;
	private BigDecimal latitude;
	private BigDecimal longitude;
	
	//Getters And Setters
	public BigDecimal get_id() {
		return _id;
	}
	public void set_id(BigDecimal _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
}
