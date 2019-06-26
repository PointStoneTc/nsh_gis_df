package nsh.dengfeng.gisplan.storeGeometry.entity;

/**
 * 坐标系
 * 
 * @author 赵琦
 *
 */
public enum CoordinateSysEnum {
    WGS84("w", "大地坐标系"), GCJ02("g", "火星坐标系"), BD09("b", "百度坐标系");
    private String key;
    private String desc;

    private CoordinateSysEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    /**
     * 
     * 通过key查找
     */
    public static CoordinateSysEnum getEnumByKey(String key) {
        for (CoordinateSysEnum e : CoordinateSysEnum.values()) {
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
