import java.util.Objects;

public class FlipFlop extends Module{

    private boolean state;
    public FlipFlop(String name, String type, String[] outputs) {
        super(name, type, outputs);
        state = false;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public boolean resolve(boolean signal) {
        if(!signal){
            super.outputSignal = !state;
            return !state;
        }
        super.outputSignal = state;
        return state;
    }
}
