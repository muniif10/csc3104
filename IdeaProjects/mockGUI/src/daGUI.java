import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class daGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new BorderPane();
        


        primaryStage.setScene(new Scene(pane));
        primaryStage.show();


    }
}
