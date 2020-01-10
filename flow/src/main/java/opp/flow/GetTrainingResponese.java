package opp.flow;

import opp.flow.model.Training;

import java.util.List;

public class GetTrainingResponese {

    private boolean flag;
    private List<Training> training;

    public GetTrainingResponese() {

    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Training> getTraining() {
        return training;
    }

    public void setTraining(List<Training> training) {
        this.training = training;
    }
}
