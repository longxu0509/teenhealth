package springboot.mybatis.po;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
public enum Action {
    PUSHUP("俯卧撑", "上肢前侧力量", "胸部", 1),
    SITUP("仰卧起坐", "腹部力量", "核心",2),
    JUMP("原地纵跳", "下肢力量", "下肢", 3),
    SITFORWARD("坐位体前屈", "柔韧性", "核心", 4),
    ClOSEEYESTAND("闭眼单腿站", "下肢力量", "核心",5),
    PULLUP("引体向上", "上肢后侧力量", "背部", 6),
    LRUNNING("3000米跑", "心肺耐力", "有氧",7),
    SRUNNING("蛇形跑", "下肢力量", "腿部",8),
    WOODENHORSE("木马2", "心肺耐力", "有氧",9),
    PARALLELBAR("双杠3", "心肺耐力", "有氧",10),
    LOADCOMB("负重组合", "心肺耐力", "有氧",11),
    LEGHIGBRIDGE("单腿臀桥", "核心力量", "核心",12),
    DEEPSQUAT("深蹲训练", "下肢力量", "腿部",13),
    JUMPINGJACK("开合跳", "心肺耐力", "有氧",14),
    DEEPSQUATJUMP("深蹲跳", "心肺耐力", "有氧",15),
    HIGHKNEELIFT("高抬腿训练", "心肺耐力", "有氧",16),
    JUMPROPE("跳绳", "核心力量", "核心",17),
    ARMEXTENSION("双臂伸展", "核心力量", "上肢",18);


    // 成员变量
    private String name;
    private String type;
    private String position;
    private int index;
    // 构造方法
    private Action(String name, String type, String position, int index) {
        this.name = name;
        this.index = index;
        this.type = type;
        this.position = position;
    }
    // 普通方法
    public static String getName(int index) {
        for (Action c : Action.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
