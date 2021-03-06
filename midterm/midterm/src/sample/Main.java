package sample;

import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;

public class Main extends Application {

    @FXML
    private Canvas canvas;
    private String[] data;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();

        Scene scene = new Scene(root, 800, 600, Color.BLACK);



        canvas = new Canvas();
        canvas.widthProperty().setValue(800);
        canvas.heightProperty().set(600);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        primaryStage.setTitle("Radioactive Fish");
        primaryStage.setScene(scene);
        primaryStage.show();

        File inFile = new File("fish-radiation-data-2015.csv");
        loadAndPlotData(inFile,gc);
    }


    private void loadAndPlotData(File inputFile, GraphicsContext gc) {
        loadData(inputFile);

        System.out.println("width: " + canvas.getWidth());
        System.out.println("height: " + canvas.getHeight());
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setStroke(Color.WHITE);
        gc.strokeLine(100,500, 100, 100);
        gc.strokeLine(100, 500, 700,500);

        gc.setFill(Color.RED);
        for(int i = 0; i <= this.data.length ; i++){
            double temp = (Double.parseDouble(this.data[i]));
            gc.fillOval(100 - temp, 500 - temp, 1,1 );
        }


    }

    private void loadData(File inFile) {
        if (inFile.exists()) {
            Scanner scanner = new Scanner(inFile);
            int i = 0;
            while (scanner.hasNext()) {
                String word = scanner.next();
                if (word == "Cs-137 (Bq/kg)" || word == "Cs-134 (Bq/kg)" || word == "K-40 (Bq/kg)" || word = "Po-210 (Bq/kg) ") {
                    data[i] = (word);
                    i++;
                }
            }

        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
