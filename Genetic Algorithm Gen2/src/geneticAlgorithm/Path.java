package geneticAlgorithm;

import java.util.*;

public class Path{

    ArrayList<PF_GA> path = new ArrayList<PF_GA>();
    private double fitness = 0;
    private int distance = 0;
	private int x;
	private int y;
	Boolean occupied = false;
    
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

    // Creates a random individual
    protected List <PF_GA>  generateIndividual(PF_GA pfga) {    
    	List<PF_GA> toCheck = new LinkedList<PF_GA>();
    	int x = pfga.x;
    	int y = pfga.y;
    	
    	if(x > 0 && y > 0 && x < map.length - 1){   //North
			for(int i = -9; i <= 9; i++){
				if(map[y-1][x-i] == 1){
					occupied = true;
				}
			}
			if(occupied == false ){
				toCheck.add(new PF_GA(x, y-1));
			}
			occupied = false;
}
    		
        
        // Randomly reorder the tour
        Collections.shuffle(path);
    }

    // Gets a city from the tour
    public PF_GA getNode(int pathPosition) {
        return (PF_GA)path.get(pathPosition);
    }

    // Sets a city in a certain position within a tour
    public void setNode(int pathPosition, PF_GA node) {
        path.set(pathPosition, node);
        // If the tours been altered we need to reset the fitness and distance
        fitness = 0;
        distance = 0;
    }
    
    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getDistance();
        }
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
                pathDistance += fromNode.Chebyshev(destinationNode);
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
