/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private int numVertices;
	private int numEdges;
	private Map<GeographicPoint, ArrayList<RoadEdge>> map;
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		map = new HashMap<GeographicPoint, ArrayList<RoadEdge>>();
		numVertices = 0;
		numEdges = 0;
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		HashSet<GeographicPoint> ret = new HashSet<GeographicPoint>();
		for(GeographicPoint g : map.keySet()) ret.add(g);
		return ret;
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		// Implemented 0108
		if(location == null) return false;
		if(map.containsKey(location)) return false;
		else map.put(location, new ArrayList<RoadEdge>());
		numVertices++;
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		if(from == null || to == null) {
			throw new IllegalArgumentException();
		}
//		RoadNode found = toNode(from);
		RoadEdge r = new RoadEdge(from, to, roadName, roadType, length);
		map.get(from).add(r);
		numEdges++;
	}
//	public GeographicPoint toPoint(RoadNode r) {
//		GeographicPoint g = r.getPoint();
//		return g;
//	}
//	public RoadNode toNode(GeographicPoint g) {
//		for(RoadNode r : map.keySet()) {
//			if(r.getX()==g.getX() && r.getY()==g.getY()) return r;
//		}
//		return null;
//	}
		
	public List<GeographicPoint> getNeighbours(GeographicPoint curr) {
		ArrayList<GeographicPoint> n = new ArrayList<GeographicPoint>();
		ArrayList<RoadEdge> t = map.get(curr);
		for(RoadEdge r : t) {
			if(r.getStart().equals(curr)) n.add(r.getEnd());
		}
		return n;
	}
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		//initialize queue list and visited list
		Queue<GeographicPoint> q = new LinkedList<GeographicPoint>();
		HashSet<GeographicPoint> v = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> p = new HashMap<GeographicPoint, GeographicPoint>();
		LinkedList<GeographicPoint> ret = new LinkedList<GeographicPoint>();
		boolean found = false;
		q.add(start);
		v.add(start);

		while(!q.isEmpty()) {
			GeographicPoint curr = q.remove();
			List<GeographicPoint> n = getNeighbours(curr);
			System.out.println(String.format("got %d neighbours", n.size()));
			if(curr.equals(goal)) {
				//System.out.println("found!");
				found = true;
				break;
			}
			for(GeographicPoint g : n) {
				if(!v.contains(g)) {
					q.add(g);
					v.add(g);
					p.put(g, curr);
				}
			}
		}
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		if(!found) {
			//System.out.println("No path exists");
			return null;
		}
		GeographicPoint curr = goal;
		ret.addFirst(curr);
		while(!curr.equals(start)) {
			GeographicPoint prev = p.get(curr);
			ret.addFirst(prev);
			curr = prev;
			//System.out.println(String.format("adding path %d", ret.size()));
		}
		return ret;
	}
	

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// check map
//		for(ArrayList<RoadEdge> re : map.values()) {
//			for(RoadEdge r : re) {
//				GeographicPoint rf = r.getStart();
//				GeographicPoint rt = r.getEnd(); 
//				System.out.println(String.format("from %f, %f -> to %f, %f",rf.getX(), rf.getY(), rt.getX(), rt.getY()));
//			}
//		}
		// TODO: Implement this method in WEEK 4
		int count = 0;
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		Comparator<GeoDistancePair> comparator = new GeoComparator();
		Queue<GeoDistancePair> q = new PriorityQueue<GeoDistancePair>(comparator);
		HashSet<GeographicPoint> v = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> p = new HashMap<GeographicPoint, GeographicPoint>();
		LinkedList<GeographicPoint> ret = new LinkedList<GeographicPoint>();
		boolean found = false;
		
		q.add(new GeoDistancePair(start, 0.0));
		//v.add(start);

		while(!q.isEmpty()) {
			GeoDistancePair currp = q.poll();
			double currDis = currp.getDistance();
			GeographicPoint curr = currp.getG();
			List<GeographicPoint> n = getNeighbours(curr);
//			System.out.println("curr : "+curr.getX() +" "+curr.getY());
//			System.out.println(String.format("got %d neighbours", n.size()));
			count++;
			if(!v.contains(curr)) {
				v.add(curr);
				if(curr.equals(goal)) {
//					System.out.println("--------------------------------------------------found!");
					found = true;
					break;
				}
				for(GeographicPoint g : n) {
					boolean ok = true;
					if(!v.contains(g)) {
//					if(g.distance(curr)<shorter && !v.contains(g)) {
//					if(!found) {
//						shorter = g.distance(curr);
						q.offer(new GeoDistancePair(g, currDis+g.distance(curr)));
/*						System.out.println(String.format("adding to q geopoint(%f, %f), dist : %f", g.getX(), g.getY(),
								(currDis+g.distance(curr)))+String.format("\ncurrent q size : %d", q.size()));
						System.out.println("printing q...");
						for(GeoDistancePair gp : q) {
							System.out.println(gp.getG().getX()+"\t"+gp.getG().getY()+"\t"+gp.getDistance());
						}
						System.out.println("end of q...");
//						v.add(g);
*/						for(GeoDistancePair gp : q) {
							if(gp.getG().equals(g) && gp.getDistance()<(currDis+g.distance(curr))) {
								ok = false;
							}
						}
						if(ok) p.put(g, curr);
					}
					
				}
			}
		}
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		if(!found) {
			System.out.println("No path exists");
			return null;
		}
		GeographicPoint curr = goal;
		ret.addFirst(curr);
//		while(!(curr.equals(start))) {
		while(!(start.getX()==curr.getX() && start.getY()==curr.getY()) && count<100) {
			GeographicPoint prev = p.get(curr);
			ret.addFirst(prev);
			curr = prev;
//			System.out.println(String.format("adding path %d, current lat long : %f, %f", ret.size(), curr.getX(), curr.getY()));
		}
		System.out.println("visited "+ count+"\tv size "+v.size());
		return ret;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		int count = 0;
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		Comparator<GeoDistancePair2> comparator = (Comparator<GeoDistancePair2>) new GeoComparator2();
		Queue<GeoDistancePair2> q = new PriorityQueue<GeoDistancePair2>(comparator);
		HashSet<GeographicPoint> v = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> p = new HashMap<GeographicPoint, GeographicPoint>();
		LinkedList<GeographicPoint> ret = new LinkedList<GeographicPoint>();
		boolean found = false;
		
		q.add(new GeoDistancePair2(start, 0.0, goal.distance(start)));
		//v.add(start);

		while(!q.isEmpty()) {
			GeoDistancePair2 currp = q.poll();
			double currDis = currp.getDistS();
			GeographicPoint curr = currp.getG();
			List<GeographicPoint> n = getNeighbours(curr);
			count++;
			if(!v.contains(curr)) {
				v.add(curr);
				if(curr.equals(goal)) {
					found = true;
					break;
				}
				for(GeographicPoint g : n) {
					boolean ok = true;
					if(!v.contains(g)) {
						q.offer(new GeoDistancePair2(g, currDis+g.distance(curr), g.distance(goal)));
						for(GeoDistancePair2 gp : q) {
							if(gp.getG().equals(g) && gp.getDistS()<(currDis+g.distance(curr))) {
								ok = false;
							}
						}
						if(ok) p.put(g, curr);
					}
				}
			}
		}

		if(!found) {
			System.out.println("No path exists");
			return null;
		}
		GeographicPoint curr = goal;
		ret.addFirst(curr);
		while(!(start.getX()==curr.getX() && start.getY()==curr.getY()) && count<100) {
			GeographicPoint prev = p.get(curr);
			ret.addFirst(prev);
			curr = prev;
		}
		System.out.println("visited "+ count+"\tv size "+v.size());
		return ret;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		/*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		
		
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		
		
	}
	
}
