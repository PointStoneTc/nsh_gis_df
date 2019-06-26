package nsh.dengfeng.gisplan.product.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * GpBankProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_product")
public class Product implements java.io.Serializable {
    private static final long serialVersionUID = -2793026528270200968L;
    private Long id; // 主键
    private String name; // 名称
    private String business; // 业务大类
    private String businessCode; // 业务大类编码 d:贷款 z:支付 c:存款 m:中间业务
    private String isEffect; // 是否有效
    private Date effectDate; // 生效时间
    private Date invalidDate; // 失效时间
    private String isCommit; // 是否提交
    private String createBy;
    private Date createDate;
    private String updateBy;
    private Date updateDate;
    private String isDelete;
    private Date deleteDate;

    // Constructors

    /** default constructor */
    public Product() {}

    /** minimal constructor */
    public Product(String name, String business, String businessCode) {
        this.name = name;
        this.business = business;
        this.businessCode = businessCode;
    }

    /** full constructor */
    public Product(String name, String business, String businessCode, String isEffect, Date effectDate, Date invalidDate, String isCommit, String createBy, Date createDate,
            String updateBy, Date updateDate, String isDelete, Date deleteDate) {
        this.name = name;
        this.business = business;
        this.businessCode = businessCode;
        this.isEffect = isEffect;
        this.effectDate = effectDate;
        this.invalidDate = invalidDate;
        this.isCommit = isCommit;
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

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "business", nullable = false, length = 5)
    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    @Column(name = "business_code", nullable = false, length = 1)
    public String getBusinessCode() {
        return this.businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    @Column(name = "is_effect", length = 1)
    public String getIsEffect() {
        return this.isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "effect_date", length = 10)
    public Date getEffectDate() {
        return this.effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "invalid_date", length = 10)
    public Date getInvalidDate() {
        return this.invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    @Column(name = "is_commit", length = 1)
    public String getIsCommit() {
        return isCommit;
    }

    public void setIsCommit(String isCommit) {
        this.isCommit = isCommit;
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
