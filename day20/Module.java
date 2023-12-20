public abstract class Module {
    private Boolean input;
    private Boolean output;

    public Boolean getInput() {
        return input;
    }

    public void setInput(Boolean input) {
        this.input = input;
    }

    abstract public Boolean getOutput();

    public void setOutput(Boolean output) {
        this.output = output;
    }
}
