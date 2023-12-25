import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Conjunction extends Module{

    static class PrevState{
        public String outputingMod;
        public boolean state;

        @Override
        public String toString() {
            return "{" +
                    "outputingMod :'" + outputingMod + '\'' +
                    ", state :" + state +
                    "}";
        }

        public PrevState(String out) {
            this.outputingMod = out;
            this.state = false;
        }
    }
    private List<PrevState> states;
    public Conjunction(String name, String type, String[] outputs) {
        super(name, type, outputs);
        states = new ArrayList<>();
    }

    @Override
    public boolean resolve(boolean signal) {
        for(PrevState ps : states){
            if(ps.state == false){
                super.outputSignal = true;
                return true;
            }
        }
        super.outputSignal = false;
        return false;
    }

    public List<PrevState> getStates() {
        return states;
    }

    public void setStates(List<PrevState> states) {
        this.states = states;
    }


    @Override
    public String toString() {
        return "conj : {" +
                "name :'" + super.getName() + '\'' +
                ", type :'" + super.getType() + '\'' +
                ", outputs : " + Arrays.toString(super.getOutputs()) +
                "states :" + states +
                "}";
    }
}
