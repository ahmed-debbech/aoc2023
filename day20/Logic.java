import java.io.*;
import java.util.*;

public class Logic {
    public static List<Connection> parseFile(){
        File file = new File("input");
        BufferedReader br = null;
        List<Connection> cn = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String name;
                String outs[];
                name = st.split(" -> ", 2)[0];
                outs = st.split(" -> ", 2)[1].split(",");
                Connection c = new Connection(name, name.substring(0,1), outs);
                cn.add(c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cn;
    }

    public static Map<Module, List<String>> wireCircuit(List<Connection> list) {
        Map<Module, List<String>> circuit = new HashMap<>();

        for(Connection c : list){
            Module m = new Module(c.getName(), c.getType(), c.getCnx());
            circuit.put(m, Arrays.asList(c.getCnx()));
        }
        return circuit;
    }
}
