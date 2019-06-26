package nsh.dengfeng.gisplan.selfservice.view;

/**
 * 银行自助设备列表视图
 * 
 * @author 赵琦
 *
 */
public class SelfserviceListView {
    private Long id;
    private Long dotId;
    private String dotName;
    private String code;
    private String name;
    private String address;
    private String type;
    private String typeCode;
    private Double longitude;
    private Double latitude;
    private Double parentLongitude;
    private Double parentLatitude;

    public SelfserviceListView() {};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDotId() {
        return dotId;
    }

    public void setDotId(Long dotId) {
        this.dotId = dotId;
    }

    public String getDotName() {
        return dotName;
    }

    public void setDotName(String dotName) {
        this.dotName = dotName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getParentLongitude() {
        return parentLongitude;
    }

    public void setParentLongitude(Double parentLongitude) {
        this.parentLongitude = parentLongitude;
    }

    public Double getParentLatitude() {
        return parentLatitude;
    }

    public void setParentLatitude(Double parentLatitude) {
        this.parentLatitude = parentLatitude;
    }

}
