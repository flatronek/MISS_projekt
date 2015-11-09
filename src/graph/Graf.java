/*
 * authors: Dominik Juraszek, Konrad Chmiel, Sebastian Brandys
 */
package graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import exceptions.NoPossibilityToCreateGraphException;
import exceptions.ProblemWithReadingGraphFromFileException;

public class Graf {

    private static final Random rand = new Random(); // object that generates random numbers
    private Graph<Integer, String> graph; // graph
    private int searchedKCliqueSize; // size of K-Clique that we try to find in graph
    private Factory<Integer> vertexFactory; // vertex factory, for drawing
    private Factory<String> edgeFactory; // edge factory, for drawing
    private int nodeCount, edgeCount; // only for drawing, do not use

    /**
     * Constructor - creates random sparse graph with given parameters
     *
     * @param vertices - amount of vertices
     * @param edges - amount of edges
     * @param existedKCliqueSize - minimum k-clique size (amount of vertices)
     * that will exist in created graph
     * @throws NoPossibilityToCreateGraphException
     * @throws GeneticAlgorithmException
     */
    public Graf(int vertices, int edges, int existedKCliqueSize) throws NoPossibilityToCreateGraphException {
        String problem;
        if ((problem = checkPossibilityOfCreationNewGraph(vertices, edges)) != null) {
            throw new NoPossibilityToCreateGraphException(problem);
        }
        if (existedKCliqueSize > vertices) {
            throw new NoPossibilityToCreateGraphException("Nie mo¿na utworzyæ grafu o " + vertices + " wierzcho³kach zawieraj¹cego klikê o rozmiarze " + existedKCliqueSize);
        }
        if ((existedKCliqueSize * (existedKCliqueSize - 1) / 2) > edges) {
            throw new NoPossibilityToCreateGraphException("Nie mo¿na utworzyæ grafu o "  + vertices + "wierzcho³kach, który ma " + edges + " krawêdzie i zawiera klikê o rozmiarze " + existedKCliqueSize);
        }
        if (existedKCliqueSize > vertices) {
            throw new NoPossibilityToCreateGraphException("Nie mo¿na utworzyæ grafu o "  + vertices + "wierzcho³kach, który ma " + edges + " krawêdzie i zawiera klikê o rozmiarze " + existedKCliqueSize);
        }
        graph = createGraphVertices(vertices);
        LinkedList<Integer> verticesList = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            verticesList.add(i);
        }
        LinkedList<Integer> verticesList2 = new LinkedList<>();
        for (int i = 1; i <= existedKCliqueSize; i++) {
            int randV = rand.nextInt(verticesList.size());
            verticesList2.add(verticesList.get(randV));
            verticesList.remove(randV);
        }
        LinkedList<Edge> edgesList = new LinkedList<>();
        for (int i = 1; i <= existedKCliqueSize; i++) {
            for (int j = i + 1; j <= existedKCliqueSize; j++) {
                edgesList.add(new Edge(verticesList2.get(i - 1), verticesList2.get(j - 1)));
            }
        }
        int kCliqueEgdesAmount = existedKCliqueSize * (existedKCliqueSize - 1) / 2;
        fillGraphWithEdges(graph, edgesList, 0, kCliqueEgdesAmount);
        edgesList = new LinkedList<>();
        for (int i = 1; i <= vertices - existedKCliqueSize; i++) {
            for (int j = i + 1; j <= vertices - existedKCliqueSize; j++) {
                edgesList.add(new Edge(verticesList.get(i - 1), verticesList.get(j - 1)));
            }
        }
        for (int i = 1; i <= vertices - existedKCliqueSize; i++) {
            for (int j = 1; j <= existedKCliqueSize; j++) {
                edgesList.add(new Edge(verticesList.get(i - 1), verticesList2.get(j - 1)));
            }
        }
        fillGraphWithEdges(graph, edgesList, kCliqueEgdesAmount, edges);
    }

    /**
     * Constructor - creates random sparse graph with given parameters
     *
     * @param vertices - amount of vertices
     * @param edges - amount of edges
     * @throws NoPossibilityToCreateGraphException
     * @throws GeneticAlgorithmException
     */
    public Graf(int vertices, int edges) throws NoPossibilityToCreateGraphException{
    	  generate(vertices,edges);
    }
    
 
    
    
    public Graph generate(int vertices, int edges) {

    	Random random = new Random();
		int numNodes = vertices;
		 graph = createGraphVertices(vertices);

			int init_num_nodes = 5;

			

			//Get the current time
			long time = System.currentTimeMillis();

			// Create N nodes
			int[] nodes = new int[numNodes];

			//Keep track of the degree of each node
			int degrees[] = new int[numNodes];


			//set the number of edges to zero
			int numEdges = 0;
			
			//Set up the initial  complete seed network
			for (int i = 0; i < init_num_nodes; i++) 
			{
				for (int j = (i + 1); j < init_num_nodes; j++) 
				{
					//Create the new edge
					graph.addEdge("EDGE" + numEdges, i, j);	
					
					//increment the degrees for each nodes
					degrees[i]++;
					degrees[j]++;

					//Increment the edges
					numEdges++;
				}
			}


			//Add each node one at a time
			for (int i = init_num_nodes; i < numNodes; i++) 
			{
			
				int added = 0;
				double degreeIgnore = 0;
				//Add the appropriate number of edges
				for (int m = 0; m < edges; m++) 
				{
					//keep a running talley of the probability
					double prob = 0;
					//Choose a random number
					double randNum = random.nextDouble();
					
					
					
					//Try to add this node to every existing node
					for (int j = 0; j < i; j++) 
					{
		
							//Increment the talley by the jth node's probability
							prob += (double) ((double) degrees[j])
								/ ((double) (2.0d * numEdges) - degreeIgnore);
						
						
						//System.out.println(m + "\t"  + j +"\t" + prob + " < " + randNum  + "-" + degreeIgnore );


						//If this pushes us past the the probability
						if (randNum <= prob) 
						{
							// Create and edge between node i and node j
							graph.addEdge("EDGE" + numEdges, i, j);

							degreeIgnore += degrees[j];
							numEdges++;
							//increment the number of edges
							added++;
							//increment the degrees of each node
							degrees[i]++;
							degrees[j]++;
							

							
							//Stop iterating for this probability, once we have found a single edge
							break;
						}
					}
				}
			//	numEdges += added;
			}
			
			//return the resulting network
			return graph;
		}
    
    
    

    /**
     * Constructor used only for graph drawing.
     */
    public Graf() {
        graph = new SparseGraph<>();
        nodeCount = 1;
        edgeCount = 1;
        vertexFactory = new Factory<Integer>() {
            @Override
            public Integer create() {
                return nodeCount++;
            }
        };
        edgeFactory = new Factory<String>() {
            @Override
            public String create() {
                return "EDGE" + edgeCount++;
            }
        };
    }

    /**
     * Checks if there is a possibility to create graph with given amount of
     * vertices and edges
     *
     * @param vertices - amount of vertices
     * @param edges - amount of edges
     * @return message with found problem or null if everything is ok
     */
    private String checkPossibilityOfCreationNewGraph(int vertices, int edges) {
        if (vertices < 1) {
            return "Iloœæ wierzcho³ków nie mo¿e byæ mniejsza ni¿ 1";
        }
        if (edges < 0) {
            return "Iloœæ krawêdzi nie mo¿e byæ mniejsza ni¿ 0 ";
        }
        if (edges > (vertices * (vertices - 1) / 2)) {
            return "Zbyt wiele krawêdzi, nie mo¿na utworzyæ grafu";
        }
        return null;
    }

    /**
     * Constructor - reads graph from file
     *
     * @param filePath - path to file
     * @throws ProblemWithReadingGraphFromFileException
     * @throws NoPossibilityToCreateGraphException
     */
    public Graf(String filePath) throws ProblemWithReadingGraphFromFileException, NoPossibilityToCreateGraphException {
        File file = new File(filePath);
        if (file.exists() == false) {
            throw new ProblemWithReadingGraphFromFileException("Plik " + file.getName() + " nie istnieje");
        } else if (file.isDirectory() == true) {
            throw new ProblemWithReadingGraphFromFileException("Podany plik nie jest folderem");
        } else if (file.isHidden() == true) {
            throw new ProblemWithReadingGraphFromFileException("File " + file.getName() + " is hidden");
        } else if (file.canRead() == false) {
            throw new ProblemWithReadingGraphFromFileException("Brak dostêpu do pliku");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            int verticesAmount = Integer.parseInt(bufferedReader.readLine());
            if (verticesAmount < 1) {
                throw new NoPossibilityToCreateGraphException("Iloœæ wierzcho³ków nie mo¿e byæ mniejsza ni¿ 1");
            }
            // graph = createGraphVertices(verticesAmount);
            int edgesAmount = Integer.parseInt(bufferedReader.readLine());
            if (edgesAmount < 0) {
                throw new NoPossibilityToCreateGraphException("Iloœæ krawêdzi nie mo¿e byæ mniejsza ni¿ 0");
            }
            if (edgesAmount > (verticesAmount * (verticesAmount - 1) / 2)) {
                throw new NoPossibilityToCreateGraphException("Zbyt wiele krawêdzi by wygenerowaæ graf");
            }

            graph = new SparseGraph<>();
            for (int i = 1; i <= verticesAmount; i++) {
                graph.addVertex((Integer) Integer.parseInt(bufferedReader.readLine()));
            }

            for (int i = 1; i <= edgesAmount; i++) {
                String line = bufferedReader.readLine();
                String[] splitted = line.split(" ");
                int firstVertex = Integer.parseInt(splitted[0]);
                int secondVertex = Integer.parseInt(splitted[1]);
                if (firstVertex < 1 || secondVertex < 1) {// || firstVertex > verticesAmount || secondVertex > verticesAmount) {
                    throw new ProblemWithReadingGraphFromFileException("Nie mo¿na utworzyæ grafu z podan¹ iloœci¹ wierzcho³ków i krawêdzi");
                }
                // if (firstVertex >= secondVertex) {
                //     throw new ProblemWithReadingGraphFromFileException("File format is wrong");
                // }
                graph.addEdge("EDGE" + i, Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
            }
        } catch (NumberFormatException e) {
            throw new ProblemWithReadingGraphFromFileException("Z³y format pliku");
        } catch (FileNotFoundException e) {
            throw new ProblemWithReadingGraphFromFileException("Nie znaleziono pliku");
        } catch (IOException e) {
            throw new ProblemWithReadingGraphFromFileException("B³¹d podczas odczytywania grafu z pliku");
        }
    }



    /**
     * Removes edge if it's source and destination is the same vertex.
     */
    public boolean removeSingleVertexLoopback() {
        boolean removed = false;
        for (String e : graph.getEdges()) {
            int temp = 0;
            boolean first = true;
            for (int i : graph.getEndpoints(e)) {
                if (first) {
                    temp = i;
                    first = false;
                } else if (temp == i) {
                    graph.removeEdge(e);
                    removed = true;
                }
            }
        }
        return removed;
    }

    /**
     * Getter
     *
     * @return graph
     */
    public Graph<Integer, String> getGraph() {
        return graph;
    }

    /**
     * Getter
     *
     * @return k-clique size
     */
    public int getsearchedKCliqueSize() {
        return searchedKCliqueSize;
    }

    /**
     * Getter
     *
     * @return vertex factory
     */
    public Factory<Integer> getVertexFactory() {
        return vertexFactory;
    }

    /**
     * Getter
     *
     * @return edge factory
     */
    public Factory<String> getEdgeFactory() {
        return edgeFactory;
    }

    /**
     * Setter
     *
     * @param searchedKCliqueSize
     */
    public void setsearchedKCliqueSize(int searchedKCliqueSize) {
        this.searchedKCliqueSize = searchedKCliqueSize;
    }

    /**
     * Getter
     *
     * @return graph vertex amount
     */
    public int getVertexCount() {
        return graph.getVertexCount();
    }

    /**
     * Getter
     *
     * @return graph edges amount
     */
    public int getEdgeCount() {
        return graph.getEdgeCount();
    }

    /**
     * Checks if first and second vertices are connected by edge
     *
     * @param firstVertex - first vertex
     * @param secondVertex - second vertex
     * @return true if between vertices is edge, false otherwise
     */
    public boolean isNeighbor(int firstVertex, int secondVertex) {
        return graph.isNeighbor(firstVertex, secondVertex);
    }

    /**
     * Creates graph with verticesAmount vertices (without any edge)
     *
     * @param verticesAmount - amount of vertices
     * @return graph
     */
    private Graph<Integer, String> createGraphVertices(int verticesAmount) {
        Graph<Integer, String> tempGraph = new SparseGraph<>();
        for (int i = 1; i <= verticesAmount; i++) {
            tempGraph.addVertex((Integer) i);
        }
        return tempGraph;
    }

    /**
     * Adds to graph new edges
     *
     * @param graph - graph
     * @param possibleEdges - list of edges that could be add
     * @param existedEdgesAmount - amount of edges that already exists in graph
     * @param demandedEdgesAmount - amount of edges that should be in graph
     */
    private void fillGraphWithEdges(Graph<Integer, String> graph, LinkedList<Edge> possibleEdges, int existedEdgesAmount, int demandedEdgesAmount) {
        for (int i = existedEdgesAmount + 1; i <= demandedEdgesAmount; i++) {
            int randEdge = rand.nextInt(possibleEdges.size());
            graph.addEdge("EDGE" + i, possibleEdges.get(randEdge).getFirstVertex(), possibleEdges.get(randEdge).getSecondVertex());
            possibleEdges.remove(randEdge);
        }
    }

    /**
     * Creates list of all possible edges that can be added to graph
     *
     * @param verticesAmount - amount of vertices
     * @return list of edges
     */
    private LinkedList<Edge> createListWithPossibleEdges(int verticesAmount) {
        LinkedList<Edge> edgesList = new LinkedList<>();
        for (int i = 1; i <= verticesAmount; i++) {
            for (int j = i + 1; j <= verticesAmount; j++) {
                edgesList.add(new Edge(i, j));
            }
        }
        return edgesList;
    }

    @Override
    public String toString() {
        return graph.toString();
    }

    /**
     * Repairs graph if any vertex was removed while drawing. If user removed
     * vertex number 3 (so left f.e. 1,2,4,5), function relabels it to 1,2,3,4.
     */
    public void repairGraphAfterEditing() {
        LinkedList<Integer> vertices = new LinkedList<>(graph.getVertices());
        int last = vertices.get(vertices.size() - 1);
        if (last > vertices.size()) {
            for (int j = 0; j < vertices.size(); j++) {
                if (vertices.get(j) != j + 1) {
                    int temp = vertices.get(vertices.size() - 1);
                    changeEdge(temp, j + 1);
                    graph.removeVertex(temp);
                    graph.addVertex(j + 1);
                    vertices.remove(vertices.size() - 1);
                    vertices.add(j, j + 1);
                }
            }
        }
    }

    /**
     * Removes edges containig toReplace vertex and inserts same with replacing
     * vertex
     *
     * @param toReplace - number of vertex to replace
     * @param replacing - number of vertex to insert instead of toReplace
     */
    private void changeEdge(int toReplace, int replacing) {
        Collection<String> edges = graph.getEdges();
        for (String e : edges) {
            Pair<Integer> endpoints = graph.getEndpoints(e);
            if (endpoints.getFirst() == toReplace) {
                graph.removeEdge(e);
                graph.addEdge(e, replacing, endpoints.getSecond());
            } else if (endpoints.getSecond() == toReplace) {
                graph.removeEdge(e);
                graph.addEdge(e, replacing, endpoints.getFirst());
            }
        }
    }
}
