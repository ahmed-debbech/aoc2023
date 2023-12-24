import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {
    public static void main(String [] args){
        System.err.println("hello!");

        Map<Module, List<String>> circuit = null;

        List<Connection> conns = Logic.parseFile();
        System.err.println(conns);

        circuit = Logic.wireCircuit(conns);
        System.err.println(circuit);

        circuit = Logic.initCircuit(circuit);

        Logic.showCircuit(circuit);

        circuit = Logic.pushButton(circuit);
        Logic.showCircuit(circuit);
    }
}
