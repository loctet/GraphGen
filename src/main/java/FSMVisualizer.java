import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import static guru.nidi.graphviz.model.Factory.*;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Shape;

public class FSMVisualizer {
    public static void main(String[] args) {
        try {
        	if (args.length < 2) {
        		System.exit(0);
        	}
            // Read the JSON file into a String
            String jsonText = new String(Files.readAllBytes(Paths.get(args[0])));

            // Parse the JSON String into a JSONObject
            JSONObject jsonObject = new JSONObject(jsonText);

            // Extract FSM details from the JSONObject
            String initialState = jsonObject.getString("initialState");
            JSONArray finalStates = jsonObject.getJSONArray("finalStates");
            
            // Create a mutable graph using Graphviz
            MutableGraph graph = mutGraph("FSM").setDirected(true);
            
            // Add nodes and transitions to the graph
            jsonObject.getJSONArray("transitions").forEach(item -> {
                 JSONObject transition = (JSONObject) item;
                String from = transition.getString("from");
                String to = transition.getString("to");
                
                JSONObject oCaller = transition.getJSONObject("caller");
                String caller = "";
                
                String firstC = oCaller.keySet().iterator().next();
                JSONArray firstCV = oCaller.getJSONArray(firstC);
                if (firstCV.isEmpty()) {
                	JSONObject newParticipants = transition.getJSONObject("newParticipants");
                	
                	
            		if (newParticipants.isEmpty())
            			caller = firstC;
            		else {
            			String newPk = newParticipants.keySet().iterator().next();
                    	String newPV = newParticipants.getString(newPk);
                    	caller = "ν ".concat(firstC).concat(" : ").concat(newPV.toString());
            		}
                } else {
            		caller = "any ".concat(firstC).concat(" : ").concat(firstCV.get(0).toString());
            	}
                
                String label = caller.concat(" > ").concat(transition.getString("actionLabel"));
                
                // Add nodes and edges to the graph
                MutableNode fromNode = mutNode(from).add(Shape.CIRCLE);
                MutableNode toNode = mutNode(to).add(Shape.CIRCLE);
                fromNode.addLink(to(toNode).with(Label.of(label)));

                graph.add(fromNode);
                graph.add(toNode);
            });

            // Customize initial and final states
            graph.nodes().forEach(node -> {
                if (node.name().toString().equals(initialState)) {
                    node.attrs().add("color", "green");
                }
                if (finalStates.toList().contains(node.name().toString())) {
                    node.attrs().add("color", "red");
                }
            });

            // Generate and save the FSM image
            Graphviz.fromGraph(graph).render(Format.PNG).toFile(new java.io.File(args[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


