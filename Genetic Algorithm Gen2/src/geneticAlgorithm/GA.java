/*
* GA.java
* Manages algorithms for evolving population
* @Author Aaron Edwards Student No.: 14813558
*/

package geneticAlgorithm;

public class GA {

    /* GA parameters */
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // Evolves a population over one generation
    public static Population evolvePopulation(Population population) {
        Population newPopulation = new Population(population.populationSize(), false);

        // Keep our best individual if elitism is enabled
        int elitismOffset = 0;
        if (elitism) {
            newPopulation.savePath(0, population.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from
        // Current population
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            // Select parents
            Path parent1 = tournamentSelection(population);
            Path parent2 = tournamentSelection(population);
            // Crossover parents
            Path child = crossover(parent1, parent2);
            // Add child to new population
            newPopulation.savePath(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.populationSize(); i++) {
            mutate(newPopulation.getPath(i));
        }

        return newPopulation;
    }

    // Applies crossover to a set of parents and creates offspring
    public static Path crossover(Path parent1, Path parent2) {
        // Create new child path
        Path child = new Path();

        // Get start and end sub path positions for parent1's path
        int startPos = (int) (Math.random() * parent1.pathSize());
        int endPos = (int) (Math.random() * parent1.pathSize());

        // Loop and add the sub path from parent1 to our child
        for (int i = 0; i < child.pathSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.setNode(i, parent1.getNode(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.setNode(i, parent1.getNode(i));
                }
            }
        }

        // Loop through parent2's city path
        for (int i = 0; i < parent2.pathSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsNode(parent2.getNode(i))) {
                // Loop to find a spare position in the child's path
                for (int ii = 0; ii < child.pathSize(); ii++) {
                    // Spare position found, add city
                    if (child.getNode(ii) == null) {
                        child.setNode(ii, parent2.getNode(i));
                        break;
                    }
                }
            }
        }
        return child;
    }

    // Mutate a path using swap mutation
    private static void mutate(Path path) {
        // Loop through path cities
        for(int pathPos1=0; pathPos1 < path.pathSize(); pathPos1++){
            // Apply mutation rate
            if(Math.random() < mutationRate){
                // Get a second random position in the path
                int pathPos2 = (int) (path.pathSize() * Math.random());

                // Get the cities at target position in path
                PF_GA node1 = path.getNode(pathPos1);
                PF_GA node2 = path.getNode(pathPos2);

                // Swap them around
                path.setNode(pathPos2, node1);
                path.setNode(pathPos1, node2);
            }
        }
    }

    // Selects candidate path for crossover
    private static Path tournamentSelection(Population population) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random candidate path and
        // add it
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.populationSize());
            tournament.savePath(i, population.getPath(randomId));
        }
        // Get the fittest path
        Path fittest = tournament.getFittest();
        return fittest;
    }
}
