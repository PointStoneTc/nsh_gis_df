package nsh.dengfeng.gisplan.util;

/**
 * 简单的key - value 对象
 * 
 * @author 赵琦
 *
 */
public class KeyValueObj {
    private String key;
    private String value;

    public KeyValueObj() {}

    public KeyValueObj(String key, String value) {
        super();
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
