package nsh.dengfeng.gisplan.selfservice.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GpSelfservice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_selfservice")
public class Selfservice implements java.io.Serializable {
    private static final long serialVersionUID = 888087123059310497L;
    private Long id;  //
    private Long dotId;  //网点id
    private String code;  //自助设备编号
    private String name;  //名称
    private String address;  //地址
    private String type;  //设备类型
    private String typeCode;  //设备类型编码 z:智能柜员机 t:填单机 c:查询机 a:ATM s:CRS t:网银体验机
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String isDelete;
    private Date deleteDate;

    // Constructors

    /** default constructor */
    public Selfservice() {}

    /** minimal constructor */
    public Selfservice(Long dotId, String name, String address, String type, String typeCode) {
        this.dotId = dotId;
        this.name = name;
        this.address = address;
        this.type = type;
        this.typeCode = typeCode;
    }

    /** full constructor */
    public Selfservice(Long dotId, String code, String name, String address, String type, String typeCode, String createBy, Date createDate, String updateBy, Date updateDate,
            String isDelete, Date deleteDate) {
        this.dotId = dotId;
        this.code = code;
        this.name = name;
        this.address = address;
        this.type = type;
        this.typeCode = typeCode;
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

    @Column(name = "dot_id", nullable = false)
    public Long getDotId() {
        return this.dotId;
    }

    public void setDotId(Long dotId) {
        this.dotId = dotId;
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
