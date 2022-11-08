import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Maze extends Application {


    @Override
    public void start(Stage primaryStage) {
        Button goUpBtn = new Button("Up");
        Button goDownBtn = new Button("Down");
        Button goRightBtn = new Button("Right");
        Button goLeftBtn = new Button("Left");

        int[][] rawMap = {
                {1,1,1,1,1,1,1,1,1,1},
                {1,3,0,0,0,0,0,0,0,1},
                {1,1,1,1,0,1,1,1,1,1},
                {1,0,0,0,0,0,0,0,0,1},
                {1,4,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,0,1},
                {1,1,1,1,1,1,0,0,0,1},
                {1,1,1,1,1,1,0,0,0,1},
                {1,1,1,1,1,1,0,4,0,1},
                {1,1,1,1,1,1,1,1,1,1},


        };

        MapBuilder map = new MapBuilder(rawMap);
        Player player = new Player(map);
        map.setPlayer(player);
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: black;-fx-text-fill: white");
        HBox buttons = new HBox();


        buttons.getChildren().addAll(goUpBtn,goDownBtn,goLeftBtn,goRightBtn);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(5);
        bp.setCenter(map.mapDrawer());
        goDownBtn.setOnAction(action ->{
            player.moveDown();
            bp.setCenter(map.mapDrawer());
        });
        goRightBtn.setOnAction(action ->{
            player.moveRight();
            bp.setCenter(map.mapDrawer());
        });
        goLeftBtn.setOnAction(action ->{
            player.moveLeft();
            bp.setCenter(map.mapDrawer());
        });
        goUpBtn.setOnAction(action ->{
            player.moveUp();
            bp.setCenter(map.mapDrawer());
        });


        var scene = new Scene(bp,350,350);
        scene.setOnKeyPressed(action ->{
            if(action.getCode() == KeyCode.DOWN){
                player.moveDown();
                bp.setCenter(map.mapDrawer());
            }
            if(action.getCode() == KeyCode.UP){
                player.moveUp();
                bp.setCenter(map.mapDrawer());
            }
            if(action.getCode() == KeyCode.RIGHT){
                player.moveRight();
                bp.setCenter(map.mapDrawer());
            }
            if(action.getCode() == KeyCode.LEFT){
                player.moveLeft();
                bp.setCenter(map.mapDrawer());
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
