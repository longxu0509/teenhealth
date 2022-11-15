package springboot.mybatis.po;

import lombok.Data;

import java.util.List;

@Data
public class TrainingVideoCustom extends TTrainingVideo {
    private List<TTrainingVideo> findTrainingVideo;

}
