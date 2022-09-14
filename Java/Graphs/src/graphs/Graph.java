package graphs;

import java.util.*;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;
	
	public Graph() {
		this.adjacencyMap= new HashMap<String, HashMap<String, Integer>>();
		this.dataMap=new HashMap<String, E>();
	}
	public void addVertex(String vertexName, E data) {
		if(adjacencyMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("This is already in the map"); 
		}
		else {
//			Integer num=data;
			dataMap.put(vertexName, data);
			adjacencyMap.put(vertexName, new HashMap<String, Integer>());
		}
	}
	public void addDirectedEdge(String startVertexName, String endVertexName, 
			int cost) {
		if(!(dataMap.containsKey(startVertexName))|| 
				!(adjacencyMap.containsKey(endVertexName))) {
			throw new IllegalArgumentException("Not in Map"); 

		}
		else {
			adjacencyMap.get(startVertexName).put(endVertexName, cost);
		}
	}
	public String toString(){
		Set<String> ts= new TreeSet<String>();
		Set<String> ts2= new TreeSet<String>();
		ts.addAll(dataMap.keySet());
		String ans = "Vertices: "+ts+"\n"+"Edges:\n";
		for(String s1: adjacencyMap.keySet()) {
			ts2.add("Vertex("+s1+")--->"+adjacencyMap.get(s1)+"\n");
		}
		for(String s : ts2) {
			ans+=s;
		}
		return ans.substring(0, ans.length()-1);
	}
	public Map<String,Integer> getAdjacentVertices(String vertexName){
		Map<String, Integer> map= new HashMap<String, Integer>();
		return map;
		
	}
	public int getCost(String startVertexName, String endVertexName) {
		return 0;
	}
	public Set<String> getVertices(){
		Set<String> set= new HashSet<String>();
		for(String s: adjacencyMap.keySet()) {
			set.add(s);
		}return set;
	}
	public E getData(String vertexName) {
		if(!(adjacencyMap.containsKey(vertexName))){
			throw new IllegalArgumentException(vertexName+" Not in Map");
		}
		else {
			return dataMap.get(vertexName);
		}
	}
	public void doDepthFirstSearch(String startVertexName,
            CallBack<E> callback) {
		
		Set<String> visited= new HashSet<String>();
		Stack<String> stack= new Stack<String>();
		if(!(dataMap.containsKey(startVertexName))) {
			throw new IllegalArgumentException("Not in Map");
		}
		
			stack.push(startVertexName);
			while(!stack.isEmpty()) {
				String s = stack.pop();
				if(!visited.contains(s)) {
					callback.processVertex(s, dataMap.get(s));
					for(String s1: adjacencyMap.get(s).keySet()) {
						stack.push(s1);
					}
//					stack.addAll(adjacencyMap.get(s).keySet());
					visited.add(s);
				}
			}
		
	}
	public void doBreadthFirstSearch(String startVertexName,
            CallBack<E> callback) {
		Set<String> visited= new HashSet<String>();
		LinkedList<String> list= new LinkedList<String>();
		if(!(dataMap.containsKey(startVertexName))) {
			throw new IllegalArgumentException("Not in Map");
		}
		
			list.add(startVertexName);
			while(!list.isEmpty()) {
				String s = list.poll();
				if(!visited.contains(s)) {
					callback.processVertex(s, dataMap.get(s));
					list.addAll(adjacencyMap.get(s).keySet());
					visited.add(s);
				}
			}
		
		
		
	}
	public int doDijkstras(String startVertexName, String endVertexName, 
			ArrayList<java.lang.String> shortestPath) {
		PriorityQueue<String> queue = new PriorityQueue<String>();
		Iterator<String> iterate= queue.iterator();
		
		return 0;
	}


}