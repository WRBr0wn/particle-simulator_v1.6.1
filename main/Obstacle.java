/**
* <h1>Obstacle</h1>
* The Obstacle class handles and implements collisions with particles in the simulation.
*
* @author  Wyatt Brown
* @version 1.2
* @since   1.0
*/
public class Obstacle{
    private double x;
    private double y;
    private double radius;

    /**
   * Constructor 
   * @param x Starting x coordinate of Obstacle.
   * @param y Starting y cooridnate of Obstacle.
   * @param radius - Starting radius of Obstacle.
   */
    public Obstacle(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    /**
   * This method is used to get the x coordinate of the obstacle.
   * @return double - Returns x value of obstacle.
   */
    public double getX() { return x; }

    /**
   * This method is used to get the y coordinate of the obstacle.
   * @return double - Returns y value of obstacle.
   */
    public double getY() { return y; }

    /**
   * This method is used to get the radius of the obstacle.
   * @return double - Returns radius of obstacle.
   */
    public double getRadius() { return radius; }

    /**
   * This method is used to set the x coordinate of the obstacle.
   * @param x the x coordinate the obstacle is to be set to.
   */
    public void setX(double x) { this.x = x; }

    /**
   * This method is used to set the y coordinate of the obstacle.
   * @param y the x coordinate the obstacle is to be set to.
   */
    public void setY(double y) { this.y = y; }

    /**
   * This method is used to set the radius of the obstacle.
   * @param r the radius the obstacle is to have.
   */
    public void setRadius(double r) { 
        if (r > 0) { 
                this.radius = r; 
        } else {
                System.out.println("Radius must be positive");
            }
    }
    
    /**
   * This method is used to Check for a collision with a particle.
   * @param p This is the particle that is being checked for collision.
   * @return boolean - True if there is a collision between the Obstacle and Particle.
   */
    public boolean checkCollision(Particle p) {
        double distance = Vector2DMath.magnitude(x-p.getX(), y-p.getY());
        return distance <= radius + p.getRadius();
    }

    /**
   * This method is used to simulate particle collisions with the object.
   * Stopping them from crossing.
   * @param p This is the particle that is colliding with the object.
   * @return double[] - Normal vector between Obstacle and Particle.
   */
    public double[] collide(Particle p) {
        double[] normal = Vector2DMath.normal(p.getX() - this.x, p.getY() - this.y);
        double distance = radius + p.getRadius();
        p.setX(x + normal[0] * distance);
        p.setY(y + normal[1] * distance);
        return normal;
    }
}