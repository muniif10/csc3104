import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
    int[][] mapData;
    ArrayList<ArrayList<Object>> roomStruct = new ArrayList<>();

    public Map() {
        // Outer array is y
        // Inner array is x
        mapData = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // Horizontal accessed through .get(y)
                {1, 0, 0, 2, 2, 2, 1, 0, 0, 1}, // Vertical accessed through .get(y).get(x)
                {1, 0, 1, 2, 3, 2, 1, 0, 0, 1},
                {1, 0, 1, 2, 2, 2, 1, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 9, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 3, 1, 1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    }

    public Map createRoom() {
        for (int y = 0; y < 10; y++) {
            ArrayList<Object> a = new ArrayList<>();
            for (int x = 0; x < 10; x++) {
                if (mapData[y][x] == 1) {
                    a.add(new Wall());
                } else if (mapData[y][x] == 2 || mapData[y][x] == 3) {
                    if (mapData[y][x] == 3) {
                        a.add(new Room("a treasure"));
                    } else a.add(new Room());
                } else if (mapData[y][x] == 9) {
                    a.add(new Mobs());

                } else if (mapData[y][x] == 0) {
                    a.add(new Walkway());
                }
            }
            roomStruct.add(y, a);
            a = null;
        }
        return this;
    }

    class Wall implements Move,Serializable{
        private String desc;
        Wall() {
            this("a wall, blocking your path.");
        }
        Wall(String desc){
            this.desc = desc;

        }

        @Override
        public String moveHere() {
            return desc;
        }
    }

    class Walkway implements Move,Serializable{
        String desc;
        Walkway() {
            this("empty path, for you to move.");
        }
        Walkway(String desc){
            this.desc = desc;

        }

        @Override
        public String moveHere() {

            return desc;
        }
    }

    class Room implements Move,Serializable{
        private String desc;
        String contains;

        Room(String contains,String desc) {
            this.contains = contains;
            this.desc = desc;
        }
        Room(String contains){
            this(contains,"walkable. ");
        }

        Room() {
            this("nothing is here. ", "walkable. ");
        }

        @Override
        public String moveHere() {
            return desc;
        }
    }
}
