/**
* <h1>Vector2DMath</h1>
* The Vector2DMath class calculates the magniude, norm, and reflection of a vector.
*
* @author  Wyatt Brown
* @version 1.2
* @since   1.0
*/
public class Vector2DMath {

  /**
   * This method is used to calculate the mgnitude of a vector.
   * @param x The x value of the vector.
   * @param y The y value of the vector.
   * @return double - The magnitude of the vector.
   */
  public static double magnitude(double x, double y) {
    return Math.sqrt(x*x + y*y);
  }
  
  /**
   * This method is used to calculate the norm of a vector.
   * @param x The x value of the vector.
   * @param y The y value of the vector.
   * @return double[] - The normal vector.
   */
  public static double[] normal(double x, double y) {
    double length = magnitude(x, y);
    return new double[] {x * 1.0/length, y * 1.0/length};
  }
  
  /**
   * This method is used to calculate the reflection vector.
   * @param N The norm or direction of the vector.
   * @param x The x value of the vector.
   * @param y The y value of the vector.
   * @return double[] - The reflection vector.
   */
  public static double[] reflect(double[] N, double x, double y) {
      double rx = x - 2.0 * (N[0]*x + N[1]*y)*N[0];
      double ry = y - 2.0 * (N[0]*x + N[1]*y)*N[1];
      return new double[] {rx, ry};
  }
}