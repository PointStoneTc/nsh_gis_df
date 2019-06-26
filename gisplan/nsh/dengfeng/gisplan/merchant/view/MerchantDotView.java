package nsh.dengfeng.gisplan.merchant.view;

/**
 * 商户银行关系视图
 * 
 * @author 赵琦
 *
 */
public class MerchantDotView {
    private Long id; // 主键
    private Long merchantId; // 商户id
    private Long dotId; // 网点id
    private String dotName; // 网点名称

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
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

}
