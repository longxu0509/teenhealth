package springboot.mybatis.po;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
public class TWqxplanStudentCustom extends TWqxplanStudent{
    private TWqxplanNp tWqxplanNp;

    public TWqxplanNp gettWqxplanNp() {
        return tWqxplanNp;
    }

    public void settWqxplanNp(TWqxplanNp tWqxplanNp) {
        this.tWqxplanNp = tWqxplanNp;
    }
}
