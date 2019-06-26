package nsh.dengfeng.gisplan.dot.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GpDot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_dot")
public class Dot implements java.io.Serializable {
    private static final long serialVersionUID = 5305225694399420178L;
    private Long id; // 主键
    private String code; // 网点编号
    private String name; // 名称
    private String address; // 地址
    private String chargePerson; // 负责人
    private String phone; // 电话
    private String type; // 网点类型
    private String typeCode; // 网点类型编码 z:支行 f:分理处 s:直属分理处 b:便民服务点 l:离行自助
    private String gatewayInIp; // 外网网关
    private String gatewayOutIp; // 内网网关
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String isDelete;
    private Date deleteDate;

    // Constructors

    /** default constructor */
    public Dot() {}

    /** minimal constructor */
    public Dot(String name, String address, String type, String typeCode) {
        this.name = name;
        this.address = address;
        this.type = type;
        this.typeCode = typeCode;
    }

    /** full constructor */
    public Dot(String code, String name, String address, String chargePerson, String phone, String type, String typeCode, String gatewayInIp, String gatewayOutIp, String createBy,
            Date createDate, String updateBy, Date updateDate, String isDelete, Date deleteDate) {
        this.code = code;
        this.name = name;
        this.address = address;
        this.chargePerson = chargePerson;
        this.phone = phone;
        this.type = type;
        this.typeCode = typeCode;
        this.gatewayInIp = gatewayInIp;
        this.gatewayOutIp = gatewayOutIp;
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

    @Column(name = "type", nullable = false, length = 15)
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

    @Column(name = "gateway_in_ip", length = 15)
    public String getGatewayInIp() {
        return this.gatewayInIp;
    }

    public void setGatewayInIp(String gatewayInIp) {
        this.gatewayInIp = gatewayInIp;
    }

    @Column(name = "gateway_out_ip", length = 15)
    public String getGatewayOutIp() {
        return this.gatewayOutIp;
    }

    public void setGatewayOutIp(String gatewayOutIp) {
        this.gatewayOutIp = gatewayOutIp;
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
