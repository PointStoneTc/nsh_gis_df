package nsh.dengfeng.gisplan.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GpDotBankProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_merchant_bank_product")
public class MerchantBankProduct implements java.io.Serializable {
    private static final long serialVersionUID = 5388425433723974991L;
    private Long id; // 主键
    private Long merchantId; // 商户id
    private Long bankProductId; // 银行产品id

    // Constructors

    /** default constructor */
    public MerchantBankProduct() {}

    /** full constructor */
    public MerchantBankProduct(Long merchantId, Long bankProductId) {
        this.merchantId = merchantId;
        this.bankProductId = bankProductId;
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

    @Column(name = "bank_product_id", nullable = false)
    public Long getBankProductId() {
        return this.bankProductId;
    }

    public void setBankProductId(Long bankProductId) {
        this.bankProductId = bankProductId;
    }

}
