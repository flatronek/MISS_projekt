package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.media.j3d.Link;

import edu.uci.ics.jung.graph.Graph;
import graph.Edge;
import graph.Graf;

public class ContactsWebCreator {

	private int amountOfContacts;
	private int amountOfFamilies;
	private Graf graf;
	private Agent[] agents;
	private Graph<Integer,String> graph;
	private Family[] families;
	private School[] schools;
	private Job[] jobs;
	private int licz=0;
	private int amountOfSchools;
	private int amountOfJobs;

	public int getAmountOfContacts() {
		return amountOfContacts;
	}
	

	public ContactsWebCreator(Graf graf) {
		super();
		this.graf = graf;
	}


	public void setAmountOfContacts(int vertices) {
		amountOfContacts = vertices;
	}


	public void generateWeb() {
		createAgents();
		createFamilies();
		createSchools();
		createJobs();
		createNodes();
		int[] array = new int[graph.getVertexCount()];
        for (Integer v : graph.getVertices()){
        	array[v]=graph.degree(v);
        }

        Map<Integer,Integer> degreeMap = new HashMap<>();
        for (int k=0;k<array.length;k++){
        	if(degreeMap.containsKey(array[k])) {
        		degreeMap.put(array[k], degreeMap.get(array[k])+1);
        	}
        	else {
        		degreeMap.put(array[k], 1);
        	}
        }
 
        
        graf.getChart().createlineChart(degreeMap);
        double[] arrayfactors = new double[graph.getVertexCount()];
        for (Integer v : graph.getVertices()){
        	arrayfactors[v] = 0;
        	int licz=0;
        	Collection<Integer> list = graph.getNeighbors(v);
        	Integer[] foos = list.toArray(new Integer[list.size()]);
        	for(int k =0;k<foos.length;k++){
        		for(int i = k+1; i<foos.length;i++){
        			if(graph.findEdge(foos[k], foos[i])!=null) {
        				licz++;
        			}
        		}
        	}
        	arrayfactors[v]=licz;
        }
//        for(int k=0;k<arrayfactors.length;k++){
//        	System.out.println(arrayfactors[k]);
//        }
        for(int k=0;k<arrayfactors.length;k++){
        	int temp =graph.getNeighborCount(k)*(graph.getNeighborCount(k)-1);
        	if(temp==0) temp=1;
        	arrayfactors[k]=(arrayfactors[k]*2)/temp;
        	System.out.println(arrayfactors[k]);
        }
        double generalFactor=0;
        for(int k=0;k<arrayfactors.length;k++){
        	generalFactor = generalFactor + arrayfactors[k];
        }
        generalFactor = generalFactor/graph.getVertexCount();
        System.out.println("general"+generalFactor);
        graf.getChart1().createlineChart(arrayfactors);
//		
////		
////	   	Random random = new Random();
////	   	int edges = 3;
////			int numNodes = amountOfContacts;
////			 Graph graph = graf.createGraphVertices(amountOfContacts);
////
////				int init_num_nodes = 5;
////
////				
////
////				//Get the current time
////				long time = System.currentTimeMillis();
////
////				// Create N nodes
////				int[] nodes = new int[numNodes];
////
////				//Keep track of the degree of each node
////				int degrees[] = new int[numNodes];
////
////
////				//set the number of edges to zero
////				int numEdges = 0;
////				
////				//Set up the initial  complete seed network
////				for (int i = 0; i < init_num_nodes; i++) 
////				{
////					for (int j = (i + 1); j < init_num_nodes; j++) 
////					{
////						//Create the new edge
////						graph.addEdge("EDGE" + numEdges, i, j);	
////						
////						//increment the degrees for each nodes
////						degrees[i]++;
////						degrees[j]++;
////
////						//Increment the edges
////						numEdges++;
////					}
////				}
////
////
////				//Add each node one at a time
////				for (int i = init_num_nodes; i < numNodes; i++) 
////				{
////				
////					int added = 0;
////					double degreeIgnore = 0;
////					//Add the appropriate number of edges
////					for (int m = 0; m < edges; m++) 
////					{
////						//keep a running talley of the probability
////						double prob = 0;
////						//Choose a random number
////						double randNum = random.nextDouble();
////						
////						
////						
////						//Try to add this node to every existing node
////						for (int j = 0; j < i; j++) 
////						{
////			
////								//Increment the talley by the jth node's probability
////								prob += (double) ((double) degrees[j])
////									/ ((double) (2.0d * numEdges) - degreeIgnore);
////							
////							
////							//System.out.println(m + "\t"  + j +"\t" + prob + " < " + randNum  + "-" + degreeIgnore );
////
////
////							//If this pushes us past the the probability
////							if (randNum <= prob) 
////							{
////								// Create and edge between node i and node j
////								graph.addEdge("EDGE" + numEdges, i, j);
////
////								degreeIgnore += degrees[j];
////								numEdges++;
////								//increment the number of edges
////								added++;
////								//increment the degrees of each node
////								degrees[i]++;
////								degrees[j]++;
////								
////
////								
////								//Stop iterating for this probability, once we have found a single edge
////								break;
////							}
////						}
////					}
////				//	numEdges += added;
////				}
				System.out.println("jestem");
				//return the resulting network
				graf.setFinalGraph(graph);
		
	}

    private void createNodes() {
    	LinkedList<Agent> nodes = new LinkedList<>();
       	for(int k=0; k<amountOfContacts;k++){
    		if(rand.nextInt(100)>96) nodes.add(agents[k]);
    	}
       	for(Agent a:nodes){
       		for(int k=0;k<amountOfContacts;k++){
       			if(rand.nextInt(100)>96){
       			    graph.addEdge("EDGE" + licz, a.getId(), agents[k].getId());
    	            licz++;
       			}
       		}
       	}
		
	}


	private void createJobs() {
    	LinkedList<Agent> workers = new LinkedList<>();
    	for(int k=0; k<amountOfContacts;k++){
    		if(agents[k].getAge()==2 || agents[k].getAge()==3) workers.add(agents[k]);
    	}
    	amountOfJobs = workers.size()/3;
		jobs = new Job[amountOfJobs];
		for(int i = 0; i < jobs.length; i++){
			jobs[i] = new Job();
		}
		for(Agent a:workers){
			
				int i = rand.nextInt(amountOfJobs);
				
					jobs[i].addAgent(a);
					
				
			
		}
		
		LinkedList<Edge> edgesList = new LinkedList<>();
		for(int k=0;k<amountOfJobs;k++){

			 edgesList = new LinkedList<>();

	        
	        for (int i = 0; i < jobs[k].getWorkers().size(); i++) {

	            for (int j = i + 1; j < jobs[k].getWorkers().size(); j++) {
	                edgesList.add(new Edge( jobs[k].getWorkers().get(i).getId(),  jobs[k].getWorkers().get(j).getId()));
	            }
	        }
	        for (int i = 0; i < edgesList.size(); i++) {
	            graph.addEdge("EDGE" + licz, edgesList.get(i).getFirstVertex(), edgesList.get(i).getSecondVertex());
	            licz++;
	           // edgesList.remove(i);
	        }
		}
		
		
	}


	private void createSchools() {
    
    	LinkedList<Agent> kids = new LinkedList<>();
    	for(int k=0; k<amountOfContacts;k++){
    		if(agents[k].getAge()==1) kids.add(agents[k]);
    	}
    	amountOfSchools = kids.size()/5;
		schools = new School[amountOfSchools];
		for(int i = 0; i < schools.length; i++){
			schools[i] = new School();
		}
		for(Agent a:kids){
			
				int i = rand.nextInt(amountOfSchools);
				
					schools[i].addAgent(a);
					
				
			
		}
		
		LinkedList<Edge> edgesList = new LinkedList<>();
		for(int k=0;k<amountOfSchools;k++){

			 edgesList = new LinkedList<>();

	        
	        for (int i = 0; i < schools[k].getStudents().size(); i++) {

	            for (int j = i + 1; j < schools[k].getStudents().size(); j++) {
	                edgesList.add(new Edge( schools[k].getStudents().get(i).getId(),  schools[k].getStudents().get(j).getId()));
	            }
	        }
	        for (int i = 0; i < edgesList.size(); i++) {
	            graph.addEdge("EDGE" + licz, edgesList.get(i).getFirstVertex(), edgesList.get(i).getSecondVertex());
	            licz++;
	           // edgesList.remove(i);
	        }
		}
		
		
		
		
	}


	private void createFamilies() {
		licz=0;
    	amountOfFamilies = amountOfContacts/4;
		families = new Family[amountOfFamilies];
		for(int i = 0; i < families.length; i++){
			families[i] = new Family();
		}
		for(int k=0 ;k<amountOfContacts;k++){
			
				int i = rand.nextInt(amountOfFamilies);
				
					families[i].addAgent(agents[k]);
					agents[k].setFamily(families[i]);
				
			
		}
		
		LinkedList<Edge> edgesList = new LinkedList<>();
		for(int k=0;k<amountOfFamilies;k++){

			 edgesList = new LinkedList<>();

	        
	        for (int i = 0; i < families[k].getFamily().size(); i++) {

	            for (int j = i + 1; j < families[k].getFamily().size(); j++) {
	                edgesList.add(new Edge(families[k].getFamily().get(i).getId(), families[k].getFamily().get(j).getId()));
	            }
	        }
	        for (int i = 0; i < edgesList.size(); i++) {
	            graph.addEdge("EDGE" + licz, edgesList.get(i).getFirstVertex(), edgesList.get(i).getSecondVertex());
	            licz++;
	           // edgesList.remove(i);
	        }
		}
 


 

       

		
		
	}

	private static final Random rand = new Random(); // object that generates random numbers
	private void createAgents() {
		// TODO Auto-generated method stub
		agents = new Agent[amountOfContacts];
		for(int i = 0; i < agents.length; i++){
			agents[i] = new Agent();
		}
		for(int i=0;i<amountOfContacts;i++){
			agents[i].setId(i);
			agents[i].setAge(rand.nextInt(4));
		
		}
		graph = graf.createGraphVertices(amountOfContacts);
		
		
	}

}
