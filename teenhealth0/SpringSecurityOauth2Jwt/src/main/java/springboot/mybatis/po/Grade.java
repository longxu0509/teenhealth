package springboot.mybatis.po;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
public enum Grade {
    ONE("差", 1),
    TWO("不合格", 2),
    THREE("处于平均", 3),
    FOUR("良好", 4),
    FIVE("优秀", 5);
    // 成员变量
    private String name;
    private int index;
    // 构造方法
    private Grade(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static int getGrade(String name) {
        for (Grade c : Grade.values()) {
            if (c.getName().equals(name)) {
                return c.getIndex();
            }
        }
        return 0;
    }
    // 普通方法
    public static String getName(int index) {
        for (Grade c : Grade.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    // get set 方法
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
}
