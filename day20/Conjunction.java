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

    public List<PrevState> getStates() {
        return states;
    }

    public void setStates(List<PrevState> states) {
        this.states = states;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Module){
            return super.getName().equals(((Module) o).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), states);
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
