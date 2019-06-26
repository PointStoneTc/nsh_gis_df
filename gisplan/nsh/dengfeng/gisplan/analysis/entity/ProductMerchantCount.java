package nsh.dengfeng.gisplan.analysis.entity;

/**
 * 产品类型与商户数量对象
 * 
 * @author 赵琦
 *
 */
public class ProductMerchantCount implements java.io.Serializable {
    private static final long serialVersionUID = -8869760982503604028L;
    private Long productId; // 产品id
    private String productName; // 产品名称
    private String business; // 产品大类
    private Integer MerchantCt; // 商户数量

    public ProductMerchantCount() {}

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Integer getMerchantCt() {
        return MerchantCt;
    }

    public void setMerchantCt(Integer merchantCt) {
        MerchantCt = merchantCt;
    }

}
