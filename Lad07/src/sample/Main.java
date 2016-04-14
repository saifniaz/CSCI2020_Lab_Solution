package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

public class Main extends Application {
    private File file = new File("weatherwarnings-2015.csv");
    private int flashCount = 0, thunderCount = 0, marineCount = 0, tornadoCount = 0;
    private Canvas canvas;

    public static Color getPieColours(int i) {
        return pieColours[i];
    }

    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.ORANGE,
            Color.ROSYBROWN
    };

    String[] words = {"FLASH FLOOD", "SEVERE THUNDERSTORM", "SPECIAL MARINE", "TORNADO"};

    @Override
    public void start(Stage primaryStage) throws Exception{

        try{
            processFile();
        }catch (Exception e){
            e.printStackTrace();
        }

        int[] index = {flashCount, thunderCount, marineCount, tornadoCount};

        Group root = new Group();
        Scene scene = new Scene(root, 800, 600, Color.WHITE);

        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 07");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(index);

    }

    public void draw(int[] index) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int st = 0;
        int ad = 200;
        int aS = 220;
        Font font = new Font("Arial", 15);
        for(int i = 0; i < index.length; i++){
            int temp = index[i];
            gc.setFill(getPieColours(i));
            gc.fillRect(100, ad, 40, 30);
            gc.fillArc(400, 100, 300, 300, st, (temp/80), ArcType.ROUND);
            st += (temp/80);
            ad += 50;
            gc.setFill(Color.BLACK);
            gc.fillText(words[i], 150, aS);
            aS += 50;
        }

    }

    public void processFile() throws  Exception{
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
            String temp = scanner.next();
            if(temp.equals("FLASH FLOOD")){
                flashCount++;
            }else if(temp.equals("SEVERE THUNDERSTORM")){
                thunderCount++;
            }else if(temp.equals("SPECIAL MARINE")){
               marineCount++;
            }else if(temp.equals("TORNADO")){
                tornadoCount++;
            }else{
                continue;
            }
        }

        System.out.println(flashCount + " " + thunderCount + " " + marineCount + " " + tornadoCount);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
