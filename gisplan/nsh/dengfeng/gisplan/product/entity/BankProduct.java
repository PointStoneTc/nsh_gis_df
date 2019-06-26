package nsh.dengfeng.gisplan.product.entity;

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
@Table(name = "gp_bank_product")
public class BankProduct implements java.io.Serializable {
    private static final long serialVersionUID = 5388425433723974991L;
    private Long id; // 主键
    private Long dotId; // 网点id
    private Long productId; // 银行产品id

    // Constructors

    /** default constructor */
    public BankProduct() {}

    /** full constructor */
    public BankProduct(Long dotId, Long productId) {
        this.dotId = dotId;
        this.productId = productId;
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
        return dotId;
    }

    public void setDotId(Long dotId) {
        this.dotId = dotId;
    }

    @Column(name = "product_id", nullable = false)
    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}
