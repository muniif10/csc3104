import java.io.Serializable;

public class Mobs implements Serializable, Move {

    @Override
    public String moveHere() {
        return "a fellow inhabitant of this world.";
    }
}
