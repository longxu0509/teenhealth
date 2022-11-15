package springboot.mybatis.po;

public class TWqxTrainCustom extends TWqxTrain {
    private TStudent tStudent;

    public TTrainingVideo gettTrainingVideo() {
        return tTrainingVideo;
    }

    public void settTrainingVideo(TTrainingVideo tTrainingVideo) {
        this.tTrainingVideo = tTrainingVideo;
    }

    private TTrainingVideo tTrainingVideo;

    public TStudent gettStudent() {
        return tStudent;
    }

    public void settStudent(TStudent tStudent) {
        this.tStudent = tStudent;
    }
}
