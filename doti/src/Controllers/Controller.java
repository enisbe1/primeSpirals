package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Controller {

    @FXML
    private TextField inputi;

    @FXML
    private Button submiti;

    @FXML
    private AnchorPane root;


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

    @FXML
    private void getNumber(){
        if(inputi.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please write an integer");
            alert.showAndWait();
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
        }else if(!isNumeric(inputi.getText())){
            Path currentRelativePath = Paths.get("");
            String s = currentRelativePath.toAbsolutePath().toString();
            System.out.println("Current relative path is: " + s);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("The input should be an integer");
            alert.showAndWait();
        }else{
            submiti.setOnAction(event -> {
               try{
                 HomeHandle(event);
                   int numri = Integer.parseInt(inputi.getText());
                 System.out.println(primeNumbersBruteForce(numri));

               } catch (IOException e){
                   e.printStackTrace();
               }
            });



        }
    }

    @FXML
    public void pressButton(ActionEvent event,int x){
        Circle circle = new Circle(root.getWidth()/2,root.getHeight()/2, 4);
        circle.setStroke(Color.BLACK);
        root.getChildren().add(circle);
    }

    @FXML
    public void HomeHandle(ActionEvent event) throws IOException {
        Parent dot;
        dot = FXMLLoader.load(getClass().getResource("/views/dotGenerator.fxml"));

        Scene dotScene = new Scene(dot);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dotScene);
        window.show();
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
