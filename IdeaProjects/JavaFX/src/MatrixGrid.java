import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

// Make a 10x10 matrix cells with cell value ranging from 0 to 1

public class MatrixGrid extends Application {
    @Override
    public void start(Stage primaryStage) {

        // JavaFX uses theatre terminology for its component.
        // A window is called a stage
        // A scene is a view
        // We add individual items or objects into the view which in turn is displayed in on a stage

        // Create Grid that's similar to Excel's layout or squared grid notebook.
        var pane = new GridPane();

        // Loop that add 10 rows to the grid
        for (int i = 0; i < 10; i++) {
            // Inner loop  that add 10 cells in a row
            for (int j = 0; j < 10; j++) {
                // Create a text field object
                var textField = new TextField();

                // Makes the text looks neater
                textField.setPrefColumnCount(1);
                GridPane.setHalignment(textField, HPos.LEFT);
                GridPane.setValignment(textField, VPos.CENTER);
                // Set the text-field to value either 0 or 1
                textField.setText(String.valueOf((int)(Math.random()*3)));

                // Adds the text-field into pane according to the i and j as coordinate for the grid.
                pane.add(textField,i,j);
            }
        }

        // Makes the matrix look neater
        pane.setAlignment(Pos.CENTER);
        var insetsForPane = new Insets(5,5,5,5);
        pane.setPadding(insetsForPane);
        pane.setVgap(5);
        pane.setHgap(5);

        // Create the Scene object
        var scene = new Scene(pane);
        // Sets the scene to the pane we were adding textField into just now.

        primaryStage.setScene(scene);
        primaryStage.setTitle("Matrix Generator Sample"); // Sets the title for our window.
        primaryStage.show(); // Makes the window visible


    }
}
