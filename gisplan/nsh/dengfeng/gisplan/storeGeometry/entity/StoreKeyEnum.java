package nsh.dengfeng.gisplan.storeGeometry.entity;

/**
 * 坐标存储业务外键
 * 
 * @author 赵琦
 *
 */
public enum StoreKeyEnum {
    WD("d", "网点"), SH("m", "商户"), ZZSB("s", "自助设备");
    private String key;
    private String desc;

    private StoreKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 
     * 通过key查找
     */
    public static StoreKeyEnum getEnumByKey(String key) {
        for (StoreKeyEnum e : StoreKeyEnum.values()) {
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
