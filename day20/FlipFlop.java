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
    public boolean equals(Object o) {
        if(o instanceof Module){
            return super.getName().equals(((Module) o).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), state);
    }
}
