/**
* <h1>Particle</h1>
* The Particle class handles and updates a moving particle.
*
* @author  Wyatt Brown
* @version 1.2
* @since   1.0
*/
public class Particle{
    private double x;
    private double y;
    private double radius;
    private double velocityX;
    private double velocityY;
    private double gravity;

    /**
   * Constructor 
   * @param x Starting x coordinate of Particle.
   * @param y Starting y cooridnate of Particle.
   * @param r Starting radius of Particle.
   */
    public Particle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.radius = r;
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.gravity = -1.0;
    }

    /**
   * This method is used to get the x coordinate of the particle.
   * @return double - Returns x value of particle.
   */
    public double getX() { return x; }

    /**
   * This method is used to get the y coordinate of the particle.
   * @return double - Returns y value of particle.
   */
    public double getY() { return y; }

    /**
   * This method is used to get the radius of the particle.
   * @return double - Returns radius length of particle.
   */
    public double getRadius() { return radius; }

    /**
   * This method is used to get the x velocity of the particle.
   * @return double - Returns x velocity of particle.
   */
    public double getVelocityX() { return velocityX; }

    /**
   * This method is used to get the y velocity of the particle.
   * @return double - Returns y velocity of particle.
   */
    public double getVelocityY() { return velocityY; }

    /**
   * This method is used to get the gravity acting on the particle.
   * @return double - Returns gravity acting on particle.
   */
    public double getGravity() { return gravity; }

    /**
   * This method is used to set the x coordinate of the particle.
   * @param x the x coordinate the particle is to be set to.
   */
    public void setX(double x) { this.x = x; }

    /**
   * This method is used to set the y coordinate of the particle.
   * @param y the y coordinate the particle is to be set to.
   */
    public void setY(double y) { this.y = y; }

    /**
   * This method is used to set the radius of the particle.
   * @param r the value the particle radius is set to.
   */
    public void setRadius(double r) { 
        if (r > 0) { 
                this.radius = r; 
        } else {
                System.out.println("Radius must be positive");
            }
    }

    /**
   * This method is used to set the x velocity of the particle.
   * @param vx the value the x velocity is set to.
   */
    public void setVelocityX(double vx) { this.velocityX = vx; }

    /**
   * This method is used to set the y velocity of the particle.
   * @param vy the value the y velocity is set to.
   */
    public void setVelocityY(double vy) { this.velocityY = vy; }

    /**
   * This method is used to set the gravity impacting the particle.
   * @param g the value the gravity is set to.
   */
    public void setGravity(double g) { this.gravity = g; }

    /**
   * This method is used to set the velocity vector of the particle.
   * @param vx the value the x velocity is set to.
   * @param vy the value the y velocity is set to.
   */
    public void setVelocity(double vx, double vy) {
        velocityX = vx;
        velocityY = vy;
    }

    /**
   * This method is used to update the particle's position.
   * @param dt the time step the particle is to be updated at.
   */
    public void update(double dt){
        // velocity update
        velocityY = velocityY + gravity * dt;

        // position update
        x = x + velocityX * dt;
        y = y + velocityY * dt;

        // floor collision
        if (y <= radius) {
            // correction
            y = radius;

            // bounce
            velocityY *= -1.0 * 0.6;
        }
    }

    /**
   * This method is used to update the particle's velocity after a collision.
   * @param obstacle the obstacle the particle is colliding with.
   */
    public void handleCollision(Obstacle obstacle) {
        if (obstacle.checkCollision(this)) {
            double[] normal = obstacle.collide(this);

            // bounce (reflect)
            if (Vector2DMath.magnitude(velocityX, velocityY) < 0.2) {
                velocityX = normal[0] * 0.5;
                velocityY = normal[1] * 0.5;
            } else {
                double[] reflect = Vector2DMath.reflect(normal, velocityX, velocityY);
                velocityX = reflect[0] * 0.5;
                velocityY = reflect[1] * 0.5;
            }
        }
    }
}