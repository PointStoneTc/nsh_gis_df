package nsh.dengfeng.gisplan.analysis.entity;

/**
 * 网点所辖商户数量对象
 * 
 * @author 赵琦
 *
 */
public class DotMerchantCount implements java.io.Serializable {
    private static final long serialVersionUID = -1000400677211611275L;
    private Long dotId; // 网点id
    private String dotName; // 网点名称
    private Integer MerchantCt; // 商户数量

    public DotMerchantCount() {}

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

    public Integer getMerchantCt() {
        return MerchantCt;
    }

    public void setMerchantCt(Integer merchantCt) {
        MerchantCt = merchantCt;
    }

}
