package nsh.dengfeng.gisplan.product.entity;

/**
 * 银行产品商业大类
 * 
 * @author 赵琦
 *
 */
public enum BusinessEnum {
    DK("d", "贷款"), ZK("c", "存款"), ZF("z", "支付"), ZJYW("r", "中间业务");
    private String key;
    private String desc;

    private BusinessEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 
     * 通过key查找
     */
    public static BusinessEnum getEnumByKey(String key) {
        for (BusinessEnum e : BusinessEnum.values()) {
            if (key.equals(e.key)) {
                return e;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
