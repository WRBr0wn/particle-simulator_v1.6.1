import java.util.Random;

/**
* <h1>Simluation</h1>
* The Simulation class handles the contents of the simulation.
*
* @author  Wyatt Brown
* @version 1.2
* @since   1.0
*/
public class Simulation {
    Random rd = new Random();
    private int numParticles = 0;
    Particle[] particles = new Particle[50];
    private int direction = 1;

    /**
   * This method is used to get all of the particles in the simulation.
   * @return Particle[] - Returns a list of all particles in the simulation.
   */
    public Particle[] getParticles(){
        return particles;
    }

    /**
   * This method is used to get the number of particles in the simulation.
   * @return int - Returns the number of particles in the simulation.
   */
    public int getNumParticles() {
        return numParticles;
    }

    /**
   * This method is used to add particles to the simulation.
   * @param amount number of particles to add to the simulation.
   */
    public void addParticles(int amount) {
        if (numParticles + amount > particles.length) {
            Particle[] tempParticles = new Particle[particles.length * 2];
            for(int i = 0; i < particles.length; i++) {
                tempParticles[i] = particles[i];
            }
            particles = tempParticles;
        }
        for(int i = numParticles; i < numParticles + amount; i++) {
            particles[i] = new Particle(rd.nextFloat(), 1.0, 0.05);
            if(rd.nextFloat() > 0.5) {
                direction = 1;
            }
            else {
                direction = -1;
            }
            particles[i].setVelocity(rd.nextFloat()*direction,0.0);
        }
        numParticles += amount;
    }

    /**
   * This method is used to remove particles from the simulation.
   * @param amount number of particles to remove from the simulation.
   */
    public void removeParticles(int amount) {
        if (numParticles - amount >= 0) {
            for(int i = numParticles - amount; i < numParticles; i++) {
                particles[i] = null;
            }
            numParticles -= amount;
        }
    }

    
    ObstacleNode obstacleList = null;

    /**
   * This method is used to get the obstacles in the simulation.
   * @return ObstacleNode - Returns the head node of linked list of obstacles
   * in the simulation.
   */
    public ObstacleNode getObstacles(){
        return obstacleList;
    }

    /**
   * This method is used to add an obstacle to the simulation.
   * @param obstacle the obstacle to be add to the simulation.
   */
    public void addObstacle(Obstacle obstacle) {
        ObstacleNode newNode = new ObstacleNode(obstacle);

        if (obstacleList == null) {
            obstacleList = newNode;
        } else {
            ObstacleNode current = obstacleList;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    /**
   * This method is used to remove an obstacle from the simulation.
   * @param obstacle the obstacle to be removed from the simulation.
   */
    public void removeObstacle(Obstacle obstacle) {
        ObstacleNode currNode = obstacleList, prev = null;

        if (currNode != null && currNode.getObstacle() == obstacle) {
            obstacleList = currNode.getNext();
        }
        while (currNode != null && currNode.getObstacle() != obstacle) {
            prev = currNode;
            currNode = currNode.getNext();
        }
        if (currNode != null && prev != null) {
            prev.setNext(currNode.getNext());
        }
    }
}