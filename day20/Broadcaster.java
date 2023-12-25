public class Broadcaster extends Module{
    private boolean outState;

    public Broadcaster(String name, String type, String[] outputs) {
        super(name, type, outputs);
        outState = false;
    }

    @Override
    public boolean resolve(boolean signal) {
        outState = signal;
        super.outputSignal = signal;
        return outState;
    }

    public boolean isOutState() {
        return outState;
    }

    public void setOutState(boolean outState) {
        this.outState = outState;
    }
}
