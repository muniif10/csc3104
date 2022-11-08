import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FineCalculator extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // ALlow user to toggle between one of the options
        final ToggleGroup group = new ToggleGroup();
        // Radio buttons, bike and car.
        RadioButton bike = new RadioButton("Bike");
        RadioButton car = new RadioButton("Car");


        // Textfield where user can enter their values and program can take that
        // value as input.
        var speedLimit = new TextField();
        TextField speed = new TextField();
        // Resize textfield to be smaller
        speed.setPrefColumnCount(5);
        // Ditto
        speedLimit.setPrefColumnCount(5);

        // Have bike to be selected when program starts
        bike.setSelected(true);
        // Add the two radio buttons to the toggle group
        // Toggle group tells you which button was selected by user.
        bike.setToggleGroup(group);
        car.setToggleGroup(group);

        // Root vbox
        VBox verticalListGroup = new VBox();

        HBox hb_Speed = new HBox();
        HBox hb_SpeedLim = new HBox();
        var speedText = new Label("Speed");
        hb_Speed.getChildren().addAll(speedText,speed);
        var speedLimText = new Text("Speed Limit");


        hb_SpeedLim.getChildren().addAll(speedLimText,speedLimit);
        Insets leftInset = new Insets(5, 0, 0, 10);
        HBox.setMargin(speedText,leftInset);
        HBox.setMargin(speedLimText,leftInset);
        HBox.setMargin(speed, leftInset);
        HBox.setMargin(speedLimit, leftInset);

        // Buttons
        Button calcBut = new Button("Calculate");
        Button resetBut = new Button("Reset");
        HBox buttonsBox = new HBox();





        buttonsBox.getChildren().addAll(calcBut,resetBut);
        HBox.setMargin(calcBut,leftInset);
        HBox.setMargin(resetBut,leftInset);

        HBox resultBox = new HBox();
        var resultText = new Text("Fine (RM): ");
        var resultant = new TextField();
        resultant.setEditable(false);
        resultant.setPrefColumnCount(5);
        HBox.setMargin(resultText,leftInset);
        HBox.setMargin(resultant,leftInset);

        resultBox.getChildren().addAll(resultText,resultant);

        verticalListGroup.getChildren().addAll(bike,car,hb_Speed,hb_SpeedLim,resultBox,buttonsBox);

        VBox.setMargin(bike, new Insets(5,0,5,10));
        VBox.setMargin(car, new Insets(5,0,5,10));


        //BorderPane bp = new BorderPane();
        var scene = new Scene(verticalListGroup,210,200);
        primaryStage.setScene(scene);
        primaryStage.show();

        resetBut.setOnAction(action ->{
            speed.setText("");
            speedLimit.setText("");
            resultant.setText("");
            bike.setSelected(true);
        });

        calcBut.setOnAction(action ->{
            var inputSpeed = Double.parseDouble(speed.getText());
            var inputSpeedLimit = Double.parseDouble(speedLimit.getText());

            if (group.getSelectedToggle().equals(car)){
                if(inputSpeed <= inputSpeedLimit ){
                    resultant.setText("0.0");
                }
                else {
                    var calculation = (inputSpeed - inputSpeedLimit) * (inputSpeed - inputSpeedLimit) * 0.5;
                    resultant.setText(String.valueOf(calculation));
                }

            }
            if (group.getSelectedToggle().equals(bike)){
                if(inputSpeed < inputSpeedLimit ){
                    resultant.setText("0.0");
                }
                else {
                    var calculation = 30+ (inputSpeed-inputSpeedLimit);
                    resultant.setText(String.valueOf(calculation));
                }

            }
        });

    }
}
