import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogicWorkflow {
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
            Module m = null;
            if(c.getType().equals("conj")) {
                m = new Conjunction(c.getName(), c.getType(), c.getCnx());
            }else {
                if (c.getType().equals("flip")) {
                    m = new FlipFlop(c.getName(), c.getType(), c.getCnx());
                } else {
                    m = new Broadcaster(c.getName(), c.getType(), c.getCnx());
                }
            }
            circuit.put(m, Arrays.asList(c.getCnx()));
        }
        return circuit;
    }

    public static void showCircuit(Map<Module, List<String>> circuit){
        for(Map.Entry<Module, List<String>> entry : circuit.entrySet()){
            System.err.println("****** entry ********");
            System.err.println("name : " + entry.getKey().getName());
            System.err.println("type : " + entry.getKey().getType());
            System.err.println("outputSignal : " + entry.getKey().isOutputSignal());
            if(entry.getKey() instanceof Conjunction){
                Conjunction conj = (Conjunction) entry.getKey();
                for(int j=0; j<=conj.getStates().size()-1; j++){
                    System.err.println("prevOut : " + conj.getStates().get(j));
                }
            }else{
                if(entry.getKey() instanceof FlipFlop){
                    FlipFlop flp = (FlipFlop) entry.getKey();
                    System.err.println("flipState : " + flp.isState());
                }else{
                    Broadcaster brd = (Broadcaster) entry.getKey();
                    System.err.println("broadState: " + brd.isOutState());
                }
            }
            System.err.print("outs : [");
            Arrays.stream(entry.getKey().getOutputs()).forEach(s -> {
                System.err.print(s);
            });
            System.err.println("]");
            System.err.println("**************");
            System.err.println();
        }
    }
    public static Map<Module, List<String>> initCircuit(Map<Module, List<String>> circuit){

        for(Map.Entry<Module, List<String>> entry : circuit.entrySet()){
            if((entry.getKey().getType().equals("conj"))){
                Conjunction conj = null;
                for(Map.Entry<Module, List<String>> entry1 : circuit.entrySet()){
                    if(!entry.getKey().getName().equals(entry1.getKey().getName()))
                        if((!entry1.getKey().getType().equals("conj"))){
                            for(String outs : entry1.getValue()) {
                                if(outs.equals(entry.getKey().getName())) {
                                    conj = (Conjunction) entry.getKey();
                                    conj.getStates().add(new Conjunction.PrevState(entry1.getKey().getName()));
                                }
                            }
                        }
                }
            }
        }
        return circuit;
    }

    private static Map<Module, List<String>>  flow(Map<Module, List<String>> circuit, Module start){
        List<String> list = circuit.get(start);
        if(list == null || list.size() == 0) return circuit;
        System.err.println(list);
        for(String str : list){
            Module key = circuit.entrySet().stream().filter(entry -> str.equals(entry.getKey().getName())).map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
            key.resolve(start.outputSignal);
        }
        for(String str : list){
            Module key = circuit.entrySet().stream().filter(entry -> str.equals(entry.getKey().getName())).map(Map.Entry::getKey).collect(Collectors.toList()).get(0);
            circuit = flow(circuit, key);
        }

        return circuit;
    }
    public static Map<Module, List<String>> pushButton(Map<Module, List<String>> circuit){
        return flow(circuit, new Module("broadcaster", "broad", null));
    }
}
