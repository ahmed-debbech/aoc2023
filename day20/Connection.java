import java.util.Arrays;
import java.util.List;

public class Connection {
    private String name;
    private String type;
    private String[] cnx;

    public Connection(String name, String type, String[] cnx) {
        switch(type){
            case "%":
                this.name = name.substring(1);
                this.type = "flip";
                break;
            case "&":
                this.name = name.substring(1);
                this.type = "conj";
                break;
            case "b": this.type = "broad";
            this.name = name;
                break;

        }
        this.cnx = cnx;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cnx=" + Arrays.toString(cnx) +
                '}';
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

    public String[] getCnx() {
        return cnx;
    }

    public void setCnx(String[] cnx) {
        this.cnx = cnx;
    }
}
