import java.util.Arrays;
import java.util.Objects;

public class Module {

    private String name;
    private String type;
    private String[] outputs;


    public Module(String name, String type, String[] outputs) {
        this.name = name;
        this.type = type;
        this.outputs = outputs;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Module){
            return name.equals(((Module) o).getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return " mod :{" +
                "name :'" + name + '\'' +
                ", type :'" + type + '\'' +
                ", outputs : " + Arrays.toString(outputs) +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getOutputs() {
        return outputs;
    }

    public void setOutputs(String[] outputs) {
        this.outputs = outputs;
    }
}
