import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OKButton extends Application {
    @Override
    public void start(Stage primaryStage) {
        Button butOK = new Button("OK");
        StackPane pane = new StackPane();
        pane.getChildren().add(butOK);


        Stage secondStage = new Stage();
        StackPane pane2 = new StackPane();
        pane2.getChildren().add(new Text("Arif Danial very handsome and cool"));
        Scene scene1 = new Scene(pane2,200,200);
        secondStage.setScene(scene1);
        butOK.setOnAction(action ->{
            secondStage.show();
        });
        var scene2 = new Scene(pane,200,200);
        primaryStage.setScene(scene2);
        primaryStage.show();
    }
}
