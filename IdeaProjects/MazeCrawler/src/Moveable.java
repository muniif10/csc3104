public interface Moveable {

    /**
     * @return 0 for not blocked, 1 for failed movement
     */
    int moveUp();

    /**
     * @return 0 for not blocked, 1 for failed movement
     */
    int moveDown();

    /**
     * @return 0 for not blocked, 1 for failed movement
     */
    int moveRight();

    /**
     * @return 0 for not blocked, 1 for failed movement
     */
    int moveLeft();
}
