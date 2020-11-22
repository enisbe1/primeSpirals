package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static javafx.geometry.Pos.*;

public class Main extends Application {

    Scene scene1, scene2;
    Pane layout2= new Pane();


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Prime Spirals");
        TextField textField = new TextField();
        textField.setPromptText("Write The number");
        textField.setPadding(new Insets(5,5,5,5));
        Button button1= new Button("Generate");
        button1.setOnAction(e->{
            System.out.println(textField.getText());
            if(textField.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please write an integer");
                alert.showAndWait();
            }     else if(!isNumeric(textField.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("The input should be an integer");
                alert.showAndWait();
            } else{

                    primaryStage.setScene(scene2);

                int numri = Integer.parseInt(textField.getText());
                System.out.println(primeNumbersBruteForce(numri));
                int size = primeNumbersBruteForce(numri).size();
                double heightM = layout2.getHeight()/2;
                double widthM = layout2.getWidth()/2;
                double forth = Math.PI/4;
                for (int i = 0;i<size;i++){
                    if ( (primeNumbersBruteForce(numri).get(i)) %(2*Math.PI) < (Math.PI/2) && primeNumbersBruteForce(numri).get(i) % (2 * Math.PI)>0 ){
                        Circle circle = new Circle(widthM+primeNumbersBruteForce(numri).get(i),heightM-primeNumbersBruteForce(numri).get(i),1);
                        circle.setStroke(Color.BLACK);
                        layout2.getChildren().add(circle);
                    }else if((primeNumbersBruteForce(numri).get(i)) %(2*Math.PI) < (Math.PI) && (primeNumbersBruteForce(numri).get(i)) %(2*Math.PI) >(Math.PI/2) ){
                        Circle circle = new Circle(widthM-primeNumbersBruteForce(numri).get(i),heightM-primeNumbersBruteForce(numri).get(i),1);
                        circle.setStroke(Color.BLACK);
                        layout2.getChildren().add(circle);
                    }else if((primeNumbersBruteForce(numri).get(i)) % (2 * Math.PI ) < ( 1.5 * Math.PI ) && (primeNumbersBruteForce(numri).get(i)) % ( 2 * Math.PI ) >(Math.PI) ){
                        Circle circle = new Circle(widthM-primeNumbersBruteForce(numri).get(i),heightM+primeNumbersBruteForce(numri).get(i),1);
                        circle.setStroke(Color.BLACK);
                        layout2.getChildren().add(circle);
                    }else if((primeNumbersBruteForce(numri).get(i)) % ( 2 * Math.PI ) > ( 1.5 * Math.PI ) && (primeNumbersBruteForce(numri).get(i)) % ( 2 * Math.PI) < ( 2* Math.PI) ){
                        Circle circle = new Circle(widthM+primeNumbersBruteForce(numri).get(i),heightM+primeNumbersBruteForce(numri).get(i),1);
                        circle.setStroke(Color.BLACK);
                        layout2.getChildren().add(circle);

                    }

                }
            }
        });



        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(textField, button1);
        scene1= new Scene(layout1, 400 , 400);
        layout1.setAlignment(CENTER);

        scene2= new Scene(layout2,800,800);

        primaryStage.setResizable(false);
        primaryStage.setScene(scene1);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public static List<Integer> primeNumbersBruteForce(int n) {
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (isPrimeBruteForce(i)) {
                primeNumbers.add(i);
            }
        }
        return primeNumbers;
    }
    public static boolean isPrimeBruteForce(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}




