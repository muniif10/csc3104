import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MapBuilder {
    public static int iteration = 0;
    private Player player;
    private int[][] rawMap ={{}};


    public MapBuilder(int [][] rawMap){
        this.rawMap = rawMap;


    }
    public int[][] getRawMap(){
        return rawMap;
    }

    void setPlayer(Player player){
        this.player = player;
    }


    public GridPane mapDrawer(){
        var fontCustom = Font.loadFont("file:src/fonts/SourceCodePro-ExtraBoldItalic.ttf", 16);

        iteration++;
        GridPane gp = new GridPane();

        int [] playerPos = player.getPlayerPos();
        for (int i = 0; i < rawMap[0].length; i++) {
            for (int j = 0; j < rawMap.length; j++) {

                if (playerPos[0] == i && playerPos[1] == j) { // Draw P if location match player location.
                    var text = new Text("P");
                    text.setFill(Color.WHITE);
                    text.setFont(fontCustom);
                    gp.add(text,i,j);
                }
                else{


                    Text m = new Text("M");
                    Text x = new Text("#");

                    Text sp = new Text(".");
                    sp.setOpacity(0.3);
                    x.setOpacity(0.6);
                    Text p = new Text("Error");

                    x.setFont(fontCustom);
                    m.setFont(fontCustom);
                    sp.setFont(fontCustom);
                    p.setFont(fontCustom);
                    x.setFill(Color.WHITE);
                    m.setFill(Color.WHITE);
                    sp.setFill(Color.WHITE);
                    p.setFill(Color.WHITE);

                if (rawMap[i][j] == 1) {

                    gp.add(x, i, j);
                    GridPane.setHalignment(x, HPos.CENTER);
                    GridPane.setValignment(x, VPos.CENTER);
                }
                if (rawMap[i][j] == 0) {

                    gp.add(sp, i, j);
                    GridPane.setHalignment(sp, HPos.CENTER);
                    GridPane.setValignment(sp, VPos.CENTER);
                }
                if (rawMap[i][j] == 3) {

                    gp.add(p, i, j);
                    GridPane.setHalignment(sp, HPos.CENTER);
                    GridPane.setValignment(sp, VPos.CENTER);
                }
                if (rawMap[i][j] == 4) {

                    gp.add(m, i, j);
                    GridPane.setHalignment(sp, HPos.CENTER);
                    GridPane.setValignment(sp, VPos.CENTER);
                }
            }
            }
        }
        gp.setAlignment(Pos.CENTER);
        var insetsForPane = new Insets(5,5,5,5);
        gp.setPadding(insetsForPane);
        gp.setVgap(10);
        gp.setHgap(10);
        return gp;
    }

}
