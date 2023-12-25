import java.util.List;
import java.util.Map;

public class Part1 {
    public static void main(String [] args){
        System.err.println("hello!");

        Map<Module, List<String>> circuit = null;

        List<Connection> conns = LogicWorkflow.parseFile();
        System.err.println(conns);

        circuit = LogicWorkflow.wireCircuit(conns);
        System.err.println(circuit);

        circuit = LogicWorkflow.initCircuit(circuit);

        LogicWorkflow.showCircuit(circuit);

        circuit = LogicWorkflow.pushButton(circuit);
        LogicWorkflow.showCircuit(circuit);
    }
}
