package geneticAlgorithm;

import java.util.ArrayList;

public class PF_GA {
	private static ArrayList<PF_GA> destinationNodes = new ArrayList<PF_GA>();
	public static int[][] map = new int[][]{
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
		{1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
	};
	
	public static int x;
	public static int y;
	
	
	public PF_GA(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		return "(" + getX() + ", " + getY() + ")";
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public double Chebyshev(PF_GA node){
		return new Double((Math.abs(getX() - node.getX()) + (Math.abs(getY() - node.getY()) + (1 - 2*1) * Math.min(Math.abs(getX() - node.getX()), 
				Math.abs(getY() - node.getY())))));
	}
	
	public static PF_GA getNode(int index){
		return (PF_GA)destinationNodes.get(index);
	}
	
	public static int numberOfNodes(){
		return destinationNodes.size();
	}
	
	public static void main(String[] args) {
		
		for(int i = 0; i < map.length; i++){
			for (int j = 0; j <map.length; j++){
				if (map[j][i] == 0)
					destinationNodes.add(new PF_GA(j,i));
			}
			
			long begin = System.currentTimeMillis();
			//Initialise Population
			Population population = new Population(50, true);
			System.out.println("Initial Distance: " + population.getFittest().getDistance());
			
			//Evolve population for 100 generations
			population = GA.evolvePopulation(population);
			for(int p = 0; p < 100; p++){
				population = GA.evolvePopulation(population);
			}
			
			System.out.println("Finished");
			long end = System.currentTimeMillis();
			System.out.println("Time = " + (end - begin) + "ms");
			System.out.println("Final distance: " + population.getFittest().getDistance());
			System.out.println("Solution:");
			System.out.println(population.getFittest());
		}
	}
}
