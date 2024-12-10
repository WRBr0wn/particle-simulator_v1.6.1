/**
* <h1>ObstacleNode</h1>
* The ObstacleNode class the nodes of a linked list of obstacles.
*
* @author  Wyatt Brown
* @version 1.2
* @since   1.0
*/
public class ObstacleNode {
    ObstacleNode next;
    Obstacle obstacle;

    /**
   * Constructor 
   * @param obstacle the obstacle at this node.
   */
    public ObstacleNode(Obstacle obstacle) {
        this.obstacle = obstacle;
        this.next = null;
    }

    /**
   * This method is used to get the obstacle at this node.
   * @return obstacle - Returns the obstacle at this node.
   */
    public Obstacle getObstacle(){
        return obstacle;
    }

    /**
   * This method is used to get the obstacle at the next node.
   * @return obstacle - Returns the obstacle of the next node.
   */
    public ObstacleNode getNext(){
        return next;
    }

    /**
   * This method is used to set the next node after this one.
   * @param next The node set to be next in the linked list
   */
    public void setNext(ObstacleNode next) {
        this.next = next;
    }
}