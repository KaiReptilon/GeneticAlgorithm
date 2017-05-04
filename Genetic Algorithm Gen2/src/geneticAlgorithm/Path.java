//@Author Aaron Edwards Student No.: 14813558

package geneticAlgorithm;

import java.util.*;

public class Path{

    ArrayList<PF_GA> path = new ArrayList<PF_GA>();
    private double fitness = 0;
    private int distance = 0;
	private int x;
	private int y;
	Boolean occupied = false;
	ArrayList<String> checked = new ArrayList<String>(); 
    
    public Path(){
        for (int i = 0; i < PF_GA.numberOfNodes(); i++) {
            path.add(null);
        }
    }
    
    public Path(ArrayList path){
        this.path = path;
    }
    
    
    
    public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

    // Generates our nodes of the map to check.
    protected List<PF_GA>  generateIndividual(PF_GA path) {    
    	List<PF_GA> toCheck = new LinkedList<PF_GA>();
    			int x = path.x;
    			int y = path.y;	
    			for(String s: checked){
    				if(s.equals("(" + x + ", " + y + ") ")){
    					return toCheck;
    				}
    			}
    			if(y > 0)
    				if(PF_GA.map[y-1][x] == 0) //N
    					toCheck.add(new PF_GA(x, y-1));
    			if(y < PF_GA.map.length - 1)
    				if(PF_GA.map[y+1][x] == 0) //S
    					toCheck.add(new PF_GA(x, y+1));
    			if(x < PF_GA.map.length - 1)
    				if(PF_GA.map[y][x+1] == 0) //E
    					toCheck.add(new PF_GA(x+1, y));

    			if(x > 0){
    				if(PF_GA.map[y][x-1] == 0) //W
    					toCheck.add(new PF_GA(x-1, y));
    				if(y < PF_GA.map.length - 1 && PF_GA.map[y+1][x-1] == 0) //SW
    					toCheck.add(new PF_GA(x-1, y+1));
    			}
    			if(y > 0 && PF_GA.map[y-1][x+1] == 0){ //NE
    					toCheck.add(new PF_GA(x+1, y-1));
    				if(x > 0 && PF_GA.map[y-1][x-1] == 0) //NW
    					toCheck.add(new PF_GA(x-1, y-1));
    			}
    			if(y < PF_GA.map.length - 1)
    				if(x < PF_GA.map.length - 1)
    					if(PF_GA.map[y+1][x+1] == 0) //SE
    						toCheck.add(new PF_GA(x+1, y+1));
    			checked.add("(" + x + ", " + y + ") ");
    			return toCheck;
    		}

    // Gets a city from the tour
    public PF_GA getNode(int pathPosition) {
        return (PF_GA)path.get(pathPosition);
    }

    // Sets a city in a certain position within a tour
    public void setNode(int pathPosition, PF_GA node) {
        path.set(pathPosition, node);
        fitness = 0;
        distance = 0;
    }
    
    // Gets the tours fitness
    public double getFitness(List<PF_GA> finalPath) {
    	fitness = finalPath.size();
        return fitness;
    }
    
    // Gets the total distance of the tour
    public int getDistance(){
        if (distance == 0) {
            int pathDistance = 0;
            for (int nodeIndex=0; nodeIndex < pathSize(); nodeIndex++) {
                PF_GA fromNode = getNode(nodeIndex);
                PF_GA destinationNode;
                if(nodeIndex+1 < pathSize()){
                    destinationNode = getNode(nodeIndex+1);
                }
                else{
                    destinationNode = getNode(0);
                }
                pathDistance += fromNode.Heuristic(destinationNode);
            }
            distance = pathDistance;
        }
        return distance;
    }

    // Get number of cities on our tour
    public int pathSize() {
        return path.size();
    }
    
    // Check if the tour contains a city
    public boolean containsNode(PF_GA node){
        return path.contains(node);
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < pathSize(); i++) {
            geneString += getNode(i)+"|";
        }
        return geneString;
    }
}
