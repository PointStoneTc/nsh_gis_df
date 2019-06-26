package nsh.dengfeng.gisplan.dot.entity;

/**
 * 网点类型
 * 
 * @author 赵琦
 *
 */
public enum DotTypeEnum {
    ZH("z", "支行"), FLC("f", "分理处"), ZSFLC("s", "直属分理处"), BMFWD("b", "便民服务点"), LHZZ("l", "离行自助");
    private String key;
    private String desc;

    private DotTypeEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 
     * 通过key查找
     */
    public static DotTypeEnum getEnumByKey(String key) {
        for (DotTypeEnum e : DotTypeEnum.values()) {
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
