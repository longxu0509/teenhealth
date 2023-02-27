package springboot.mybatis.po;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2023-02-15
 */
public class TDict implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private String value;


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

    @Override
    public String toString() {
        return "TDict{" +
        "key=" + key +
        ", value=" + value +
        "}";
    }
}
