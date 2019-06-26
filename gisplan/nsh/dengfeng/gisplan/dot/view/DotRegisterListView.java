package nsh.dengfeng.gisplan.dot.view;

/**
 * 网点台账列表视图
 * 
 * @author 赵琦
 *
 */
public class DotRegisterListView {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String chargePerson;
    private String phone;
    private String type;
    private String typeCode;
    private String gatewayInIp;
    private String gatewayOutIp;
    private Double longitude;
    private Double latitude;

    public DotRegisterListView() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getChargePerson() {
        return chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getGatewayInIp() {
        return gatewayInIp;
    }

    public void setGatewayInIp(String gatewayInIp) {
        this.gatewayInIp = gatewayInIp;
    }

    public String getGatewayOutIp() {
        return gatewayOutIp;
    }

    public void setGatewayOutIp(String gatewayOutIp) {
        this.gatewayOutIp = gatewayOutIp;
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

}
