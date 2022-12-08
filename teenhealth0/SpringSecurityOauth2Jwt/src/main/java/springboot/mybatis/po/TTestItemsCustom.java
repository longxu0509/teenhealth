package springboot.mybatis.po;

import lombok.Data;
import springboot.Controller.TTrainingVideoController;

/**
 * @author xulong
 * @version 1.0
 * @email longxu@mail.ustc.edu.cn
 */
@Data
public class TTestItemsCustom extends TTestItems{
    private TTrainingVideo tTrainingVideo;
}
