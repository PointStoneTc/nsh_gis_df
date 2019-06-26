package nsh.dengfeng.gisplan.merchant.view;

/**
 * 商户台账列表视图
 * 
 * @author 赵琦
 *
 */
public class MerchantRegisterListView {
    private Long id;
    private String code;
    private String name;
    private String address;
    private String chargePerson;
    private String phone;
    private String type;
    private String typeCode;
    private String managementType;
    private String managementTypeCode;
    private String managementRange;
    private String managementRangeCode;
    private String accountManager;
    private String expansionTime;
    private Double longitude;
    private Double latitude;

    public MerchantRegisterListView() {}

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

    public String getManagementType() {
        return managementType;
    }

    public void setManagementType(String managementType) {
        this.managementType = managementType;
    }

    public String getManagementTypeCode() {
        return managementTypeCode;
    }

    public void setManagementTypeCode(String managementTypeCode) {
        this.managementTypeCode = managementTypeCode;
    }

    public String getManagementRange() {
        return managementRange;
    }

    public void setManagementRange(String managementRange) {
        this.managementRange = managementRange;
    }

    public String getManagementRangeCode() {
        return managementRangeCode;
    }

    public void setManagementRangeCode(String managementRangeCode) {
        this.managementRangeCode = managementRangeCode;
    }

    public String getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    public String getExpansionTime() {
        return expansionTime;
    }

    public void setExpansionTime(String expansionTime) {
        this.expansionTime = expansionTime;
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
