/*
 *Population.java
 *Manages a population of candidate paths
 */

package geneticAlgorithm;

public class Population {
	
	//Holds population of paths
	Path[] paths;
	
	//Construct a population
	public Population(int populationSize, boolean initialise) {
		paths = new Path[populationSize];
		// If population needs to be initialised do so
		if (initialise) {
			// Loop and create individuals
			for (int i = 0; i < populationSize(); i++){
				Path newRoute = new Path();
				newRoute.generateIndividual();
				savePath(i, newRoute);
			}
		}
	}


	public void savePath(int index, Path route) {
		paths[index] = route;
	}

	public Path getPath(int index) {
		return paths[index];	
	}

	public Path getFittest() {
		Path fittest = paths[0];
		// Loop through individuals to find fittest
		for (int i=1; i < populationSize(); i++) {
			if (fittest.getFitness() <= getPath(i).getFitness()) {
				fittest = getPath(i);
			}
		}
		return fittest;
	}
	
	// Gets population size
	public int populationSize() {
		return paths.length;
	}
}


