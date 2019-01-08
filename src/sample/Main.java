package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class Main extends Application
{
    long timeStep;
    boolean scoring = true;
    int clicks = 0;
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Clicking game");
        Button button = new Button("0");
        button.setLayoutX(200);
        button.setLayoutY(200);

        Label label = new Label("");
        HBox theBox = new HBox(button,label);
        theBox.setSpacing(20);
        primaryStage.setScene(new Scene(theBox, 500, 425));
        primaryStage.show();
        timeStep = System.nanoTime() + 10000000000L;

        new AnimationTimer()
        {
            public void handle(long now)
            {
                if (now > timeStep)
                {
                    timeStep = now + 10000000000L;
                    scoring = !scoring;
                }
                if (!scoring)
                {
                    button.setText("Times Clicked: " + clicks);
                }
                else
                {
                    button.setText("Click Here");
                }
            }
        }.start();

        button.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent actionEvent)
            {
                clicks++;
                button.setText(String.valueOf(clicks));
            }
        });
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}