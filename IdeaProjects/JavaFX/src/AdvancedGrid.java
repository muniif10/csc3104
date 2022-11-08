import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdvancedGrid extends Application {


    // Refactored matrix generation
    public GridPane getMatrix(){
        var pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        var insetsForPane = new Insets(5,5,5,5);
        pane.setPadding(insetsForPane);
        pane.setVgap(5);
        pane.setHgap(5);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                var textField = new TextField();
                textField.setPrefColumnCount(1);
                textField.setText(String.valueOf((int)(Math.random()*2)));
                if(textField.getText().equals("1")){
                    textField.setStyle("-fx-background-color: #f37d7d;-fx-text-fill: white");
                }
//                if(textField.getText().equals("2")){
//                    textField.setStyle("-fx-background-color: green;-fx-text-fill: white");
//                }
                GridPane.setHalignment(textField, HPos.LEFT);
                GridPane.setValignment(textField, VPos.CENTER);
                pane.add(textField,i,j);
            }
        }
        pane.setStyle("-fx-background-color: #c9c9c9");
        return pane;
    }

    @Override
    public void start(Stage primaryStage) {

        // JavaFX uses theatre terminology for its component.
        // A window is called a stage
        // A scene is a view
        // We add individual items or objects into the view which in turn is displayed in on a stage

        var mainPane = new BorderPane();

        var butRefresh = new Button("Refresh");

        mainPane.setCenter(getMatrix());
        BorderPane.setAlignment(butRefresh,Pos.CENTER);
        mainPane.setPadding(new Insets(5,5,5,5));
        mainPane.setBottom(butRefresh);
        mainPane.setStyle("-fx-background-color: #c9c9c9");
        var scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Matrix Generator Sample");
        primaryStage.show();

        butRefresh.setOnAction(act ->{
            mainPane.setCenter(getMatrix());
            System.out.println(act.toString()); // Debug purpose

        });
        butRefresh.requestFocus();
        primaryStage.getIcons().add(new Image("icon.png"));
    }
}
