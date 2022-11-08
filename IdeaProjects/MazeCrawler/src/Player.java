public class Player implements Moveable{
    // Initialization
    private final int[] playerPos = {0,0};
    private final int[][] rawMap;
    private final MapBuilder map;

    // Constructor
    public Player(MapBuilder map){
        this.map = map;
        this.rawMap = map.getRawMap();
        locatePlayer();
    }

    // Methods
    public int[] getPlayerPos() {
        return playerPos;
    }

    private void locatePlayer(){ // Locate player, called once.
        for (int i = 0; i < rawMap.length; i++) {
            for (int j = 0; j < rawMap[0].length; j++) {
                if (rawMap[i][j] == 3){
                    playerPos[0] = i;
                    playerPos[1] = j;
                    rawMap[i][j] = 0;
                }
            }
        }
    }

    @Override
    public int moveUp() {
        if (rawMap[playerPos[0]][playerPos[1]-1] != 0){
            return 1;
        }
        playerPos[1]--;
        return 0;

    }

    @Override
    public int moveDown() {
        if (rawMap[playerPos[0]][playerPos[1]+1] != 0){
            return 1;
        }
        if (playerPos[1] >=9){
            return 1;
        }
        else {
            playerPos[1]++;
            return 0;
        }
    }

    @Override
    public int moveRight() {
        if (rawMap[playerPos[0]+1][playerPos[1]] != 0){
            return 1;
        }
        if (playerPos[0] >=9){
            return 1;
        }
        else {
            playerPos[0]++;
            return 0;
        }
    }

    @Override
    public int moveLeft() {
        if (rawMap[playerPos[0]-1][playerPos[1]] != 0){
            return 1;
        }

        else {
            playerPos[0]--;
            return 0;
        }
    }
}
