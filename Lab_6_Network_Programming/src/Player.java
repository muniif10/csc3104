import java.io.Serializable;

public class Player implements Serializable {
    //    static ArrayList<Player> players = new ArrayList<>();

    String prompt;
    int x,y;
    Map map;
    public String getPrompt() {
        return prompt;
    }

    public Player setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Player setMap(Map map) {
        this.map = map;
        return this;
    }

    public Map getMap() {
        return map;
    }
    private char roomSymbol(Move obj){
        if (obj instanceof Map.Wall){
            return 'W';
        } else if (obj instanceof Map.Walkway) {
            return '.';
        } else if (obj instanceof Map.Room) {
            return '.';
        }
        return ' ';
    }

    public void currentStatus(){
        // Return back all details about the player current status, places, and mobs.
        // Not done using ChatGPT at all.
        System.out.println("::Your surroundings::");
//            System.out.println("\t\t\t\t\tTOP: " + ((Move)map.roomStruct.get(y-1).get(x)).moveHere());
        System.out.printf("[?] [%s] [?]\n",roomSymbol(((Move)map.roomStruct.get(y-1).get(x))));

//            System.out.print("LEFT: " + ((Move)map.roomStruct.get(y).get(x-1)).moveHere());
//            System.out.println("    RIGHT:  " + ((Move)map.roomStruct.get(y).get(x+1)).moveHere());
        System.out.printf("[%c] [O] [%s]\n",roomSymbol(((Move)map.roomStruct.get(y).get(x-1))),roomSymbol(((Move)map.roomStruct.get(y).get(x+1))));


//            System.out.println("\t\t\t\t\tBOTTOM: " + ((Move)map.roomStruct.get(y+1).get(x)).moveHere());
        System.out.printf("[?] [%s] [?]\n",roomSymbol(((Move)map.roomStruct.get(y+1).get(x))));
        System.out.println("::[Note] W - wall; O - Player; . - Way::");


//            [?] [W] [?]
//            [W] [O] [.]
//            [?] [.] [?]





    }

    Player(Map map, int x, int y) {
        System.out.println("Player is present on the map!");
        this.x = x;
        this.y = y;
        this.map = map;


    }


}
