package nsh.dengfeng.gisplan.merchant.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GpMerchant entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_merchant")
public class Merchant implements java.io.Serializable {
    private static final long serialVersionUID = 5530586115775269576L;
    private Long id;  //主键
    private String code;  //商户编号
    private String name;  //名称
    private String address;  //地址
    private String chargePerson;  //负责人
    private String phone;  //电话
    private String type;  //商户类型
    private String typeCode;  //商户类型编码 d:对公 g:个体 r:个人
    private String managementType;  //经营大类
    private String managementTypeCode;  //经营大类编码 l:零售 p:批发 f:服务 c:餐饮
    private String managementRange;  //经营范围
    private String managementRangeCode;  //经营范围编码 c:餐饮 f:服装 j:建材
    private String accountManager;  //营销客户经理
    private String expansionTime;  //拓展时间
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String isDelete;
    private Date deleteDate;

    // Constructors

    /** default constructor */
    public Merchant() {}

    /** minimal constructor */
    public Merchant(String name, String address, String type, String typeCode, String managementType, String managementTypeCode, String managementRange,
            String managementRangeCode) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.typeCode = typeCode;
        this.managementType = managementType;
        this.managementTypeCode = managementTypeCode;
        this.managementRange = managementRange;
        this.managementRangeCode = managementRangeCode;
    }

    /** full constructor */
    public Merchant(String code, String name, String address, String chargePerson, String phone, String type, String typeCode, String managementType,
            String managementTypeCode, String managementRange, String managementRangeCode, String accountManager, String expansionTime, String createBy, Date createDate,
            String updateBy, Date updateDate, String isDelete, Date deleteDate) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.chargePerson = chargePerson;
        this.phone = phone;
        this.type = type;
        this.typeCode = typeCode;
        this.managementType = managementType;
        this.managementTypeCode = managementTypeCode;
        this.managementRange = managementRange;
        this.managementRangeCode = managementRangeCode;
        this.accountManager = accountManager;
        this.expansionTime = expansionTime;
        this.createBy = createBy;
        this.createDate = createDate;
        this.updateBy = updateBy;
        this.updateDate = updateDate;
        this.isDelete = isDelete;
        this.deleteDate = deleteDate;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "code", length = 10)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address", nullable = false, length = 50)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "charge_person", length = 10)
    public String getChargePerson() {
        return this.chargePerson;
    }

    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    @Column(name = "phone", length = 15)
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "type", nullable = false, length = 5)
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "type_code", nullable = false, length = 1)
    public String getTypeCode() {
        return this.typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    @Column(name = "management_type", nullable = false, length = 5)
    public String getManagementType() {
        return this.managementType;
    }

    public void setManagementType(String managementType) {
        this.managementType = managementType;
    }

    @Column(name = "management_type_code", nullable = false, length = 1)
    public String getManagementTypeCode() {
        return this.managementTypeCode;
    }

    public void setManagementTypeCode(String managementTypeCode) {
        this.managementTypeCode = managementTypeCode;
    }

    @Column(name = "management_range", nullable = false, length = 5)
    public String getManagementRange() {
        return this.managementRange;
    }

    public void setManagementRange(String managementRange) {
        this.managementRange = managementRange;
    }

    @Column(name = "management_range_code", nullable = false, length = 1)
    public String getManagementRangeCode() {
        return this.managementRangeCode;
    }

    public void setManagementRangeCode(String managementRangeCode) {
        this.managementRangeCode = managementRangeCode;
    }

    @Column(name = "account_manager", length = 10)
    public String getAccountManager() {
        return this.accountManager;
    }

    public void setAccountManager(String accountManager) {
        this.accountManager = accountManager;
    }

    @Column(name = "expansion_time", length = 50)
    public String getExpansionTime() {
        return this.expansionTime;
    }

    public void setExpansionTime(String expansionTime) {
        this.expansionTime = expansionTime;
    }

    @Column(name = "create_by", length = 20)
    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "create_date", length = 19)
    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "update_by", length = 15)
    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "update_date", length = 19)
    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Column(name = "is_delete", length = 1)
    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Column(name = "delete_date", length = 19)
    public Date getDeleteDate() {
        return this.deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

}
