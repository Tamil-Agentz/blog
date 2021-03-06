package com.swayam.practice.algos.graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>Problem</h1>
 * <p>
 * Eulerian Path is a path in graph that visits every edge exactly once.
 * Eulerian Circuit is an Eulerian Path which starts and ends on the same
 * vertex. The task is to find that there exists the Euler Path or circuit or
 * none in given undirected graph.
 * </p>
 * <h2>Input</h2>
 * <p>
 * First line consists of test cases T. First line of every test case consists
 * of 2 integers V and E, denoting the number of vertex and edges. Second line
 * of every test case consists of 2*E spaced integers denoting the edge
 * connected.
 * </p>
 * <h2>Output</h2>
 * <p>
 * Return the int value to print 1 if Euler path exists print 2 if Euler cycle
 * exists else 0.
 * </p>
 * <h2>Details</h2> <a href=
 * "https://practice.geeksforgeeks.org/problems/euler-circuit-and-path/1">https://practice.geeksforgeeks.org/problems/euler-circuit-and-path/1</a>
 * <h1>Solution</h1>
 * <p>
 * <h2>Semi Eulerian Graph</h2>
 * 
 * It is a graph which has a Eulerian Path. An Eulerian Path is defined as a
 * trail that covers all the edges of the graph exactly once.
 * 
 * <h2>Eulerian Graph</h2>
 * 
 * It is a graph that has a Eulerian Path that starts and ends in the same
 * vertex
 * 
 * <h2>Condition for Semi Euler Graph</h2>
 * 
 * All the vertices except 2 should have even no. of edges. These 2 would be the
 * start and end points.
 * 
 * <h2>Condition for Euler Graph</h2>
 * 
 * All the vertices should have even no. of edges.
 * </p>
 *
 */
public class EulerCircuit {

    public static void main(String[] a) throws IOException, URISyntaxException {

	List<String> lines = Files.readAllLines(Paths.get(EulerCircuit.class
		.getResource("/com/swayam/practice/algos/graph/euler-circuit.txt").toURI()));

	int totalTestCases = Integer.parseInt(lines.get(0));

	IntStream.rangeClosed(1, totalTestCases).filter(row -> row % 2 != 0).map(row -> {
	    String verticesAndEdges = lines.get(row);
	    String edgeDetails = lines.get(row + 1);
	    return testEuler(verticesAndEdges, edgeDetails);
	}).forEach(System.out::println);

    }

    private static int testEuler(String verticesAndEdges, String edgeDetails) {

	String[] verticesAndEdgesArray = verticesAndEdges.split("\\s");
	int vertexCount = Integer.parseInt(verticesAndEdgesArray[0]);
	int edgeCount = Integer.parseInt(verticesAndEdgesArray[1]);

	String[] edgeDetailsArray = edgeDetails.split("\\s");

	Map<Integer, List<Vertex>> vertexIdMap =
		IntStream.range(0, edgeDetailsArray.length).filter(row -> row % 2 == 0)
			.mapToObj(row -> new Edge(row, Integer.parseInt(edgeDetailsArray[row]),
				Integer.parseInt(edgeDetailsArray[row + 1])))
			.flatMap(edge -> Arrays.asList(new Vertex(edge.fromVertex, edge.edgeId),
				new Vertex(edge.toVertex, edge.edgeId)).stream())
			.collect(Collectors.groupingBy(Vertex::getVertexId));

	Map<Integer, Integer> vertexDegreeMap = vertexIdMap.entrySet().stream()
		.collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().size()));

	int verticesWithEvenDegree =
		(int) vertexDegreeMap.values().stream().filter(size -> size % 2 == 0).count();

	boolean hasEulerianPath = verticesWithEvenDegree == vertexCount - 2;
	boolean isEulerianGraph = verticesWithEvenDegree == vertexCount;

	if (isEulerianGraph) {
	    return 2;
	} else if (hasEulerianPath) {
	    return 1;
	} else {
	    return 0;
	}

    }

    private static class Vertex {

	private final int vertexId;
	private final int edgeId;

	public Vertex(int vertexId, int edgeId) {
	    this.vertexId = vertexId;
	    this.edgeId = edgeId;
	}

	public int getVertexId() {
	    return vertexId;
	}

	public int getEdgeId() {
	    return edgeId;
	}

    }

    private static class Edge {

	private final int edgeId;
	private final int fromVertex;
	private final int toVertex;

	public Edge(int edgeId, int fromVertex, int toVertex) {
	    this.edgeId = edgeId;
	    this.fromVertex = fromVertex;
	    this.toVertex = toVertex;
	}

    }

}
