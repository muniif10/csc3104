import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Maze extends Application {

    public GridPane mapDrawer(int[][] rawMap){
        GridPane gp = new GridPane();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Text m = new Text("M");
                Text x = new Text("#");
                Text sp = new Text(".");
                Text p = new Text("P");
                if (rawMap[i][j] == 1){

                    gp.add(x,i,j);
                    GridPane.setHalignment(x, HPos.CENTER);
                    GridPane.setValignment(x, VPos.CENTER);
                }
                if (rawMap[i][j] == 0){

                    gp.add(sp,i,j);
                    GridPane.setHalignment(sp, HPos.CENTER);
                    GridPane.setValignment(sp, VPos.CENTER);
                }
                if (rawMap[i][j] == 3){

                    gp.add(p,i,j);
                    GridPane.setHalignment(sp, HPos.CENTER);
                    GridPane.setValignment(sp, VPos.CENTER);
                }
                if (rawMap[i][j] == 4){

                    gp.add(m,i,j);
                    GridPane.setHalignment(sp, HPos.CENTER);
                    GridPane.setValignment(sp, VPos.CENTER);
                }
            }
        }
        gp.setAlignment(Pos.CENTER);
        var insetsForPane = new Insets(5,5,5,5);
        gp.setPadding(insetsForPane);
        gp.setVgap(5);
        gp.setHgap(5);
        return gp;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button goDown = new Button("Down");


        int[][] rawMap = {
                {1,1,1,1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,1},
                {1,4,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,0,1},
                {1,1,1,1,1,1,0,0,0,1},
                {1,1,1,1,1,1,0,0,0,1},
                {1,1,1,1,1,1,0,4,0,1},
                {1,1,1,1,1,1,1,1,1,1}

        };


        BorderPane bp = new BorderPane();
        bp.setCenter(mapDrawer(rawMap));
        goDown.setOnAction(action ->{

        });

        var scene = new Scene(bp,300,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
