package nsh.dengfeng.gisplan.product.view;

import java.util.Date;

/**
 * 商户产品视图
 * 
 * @author 赵琦
 *
 */
public class MerchantBankProductView {
    private Long id;
    private Long bankProductId;
    private Long merchantId;
    private String merchantName;
    private Long dotId;
    private String dotName;
    private Long productId;
    private String name;
    private String business;
    private String isEffect;
    private Date effectDate;
    private Date invalidDate;

    public MerchantBankProductView() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankProductId() {
        return bankProductId;
    }

    public void setBankProductId(Long bankProductId) {
        this.bankProductId = bankProductId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Long getDotId() {
        return dotId;
    }

    public void setDotId(Long dotId) {
        this.dotId = dotId;
    }

    public String getDotName() {
        return dotName;
    }

    public void setDotName(String dotName) {
        this.dotName = dotName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getIsEffect() {
        return isEffect;
    }

    public void setIsEffect(String isEffect) {
        this.isEffect = isEffect;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }



}
