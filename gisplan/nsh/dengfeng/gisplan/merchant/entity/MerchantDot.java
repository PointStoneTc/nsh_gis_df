package nsh.dengfeng.gisplan.merchant.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GpDotBankProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_merchant_dot")
public class MerchantDot implements java.io.Serializable {
    private static final long serialVersionUID = -6904005816532714140L;
    private Long id; // 主键
    private Long merchantId; // 商户id
    private Long dotId;  //网点id

    // Constructors

    /** default constructor */
    public MerchantDot() {}

    /** full constructor */
    public MerchantDot(Long merchantId, Long dotId) {
        this.merchantId = merchantId;
        this.dotId = dotId;
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

    @Column(name = "merchant_id", nullable = false)
    public Long getMerchantId() {
        return this.merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    @Column(name = "dot_id", nullable = false)
    public Long getDotId() {
        return dotId;
    }

    public void setDotId(Long dotId) {
        this.dotId = dotId;
    }

}
