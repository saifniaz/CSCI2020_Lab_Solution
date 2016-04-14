package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.GraphicsContext.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class Main extends Application {
    private Canvas canvas;

    public static double getAvgCommercialPricesByYear(int i) {
        return avgCommercialPricesByYear[i];
    }

    public static double getAvgHousingPricesByYear(int i) {
        return avgHousingPricesByYear[i];
    }

    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };

    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };

    public static String getAgeGroups(int i) {
        return ageGroups[i];
    }

    public static int getPurchasesByAgeGroup(int i) {
        return purchasesByAgeGroup[i];
    }

    public static Color getPieColours(int i) {
        return pieColours[i];
    }

    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };




    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Group root = new Group();
        Scene scene = new Scene(root, 800, 500, Color.LIGHTBLUE);
        canvas = new Canvas();
        canvas.widthProperty().bind(primaryStage.widthProperty());
        canvas.heightProperty().bind(primaryStage.heightProperty());

        root.getChildren().add(canvas);
        primaryStage.setTitle("Lab 6 Solution");
        primaryStage.setScene(scene);
        primaryStage.show();

        draw(root);
    }


    public void draw(Group group){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        System.out.println("width: " + canvas.getWidth());
        System.out.println("height: " + canvas.getHeight());
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        int x = 50;
        gc.setFill(Color.RED);
        for(int i = 0; i < this.avgHousingPricesByYear.length; i++){
            double temp = getAvgHousingPricesByYear(i);
            gc.fillRect(x, 400 - (temp/5000), 10, (temp/5000));
            x= x + 40;
        }

        int x1 = 60;
        gc.setFill(Color.BLUE);
        for(int j = 0; j < this.avgCommercialPricesByYear.length; j++){
            double temp = getAvgCommercialPricesByYear(j);
            gc.fillRect(x1, 400 - (temp/5000), 10, (temp/5000));
            x1= x1 + 40;
        }
        double i1 = 0.0;
        for(int k = 0; k <this.ageGroups.length; k++){
            double temp = getPurchasesByAgeGroup(k);
            gc.setFill(getPieColours(k));
            gc.fillArc(400, 100, 300, 300, i1, (temp/15.5), ArcType.ROUND);
            i1 = (temp/15.5);
        }

        //gc.setFill(Color.RED);
        //gc.fillArc(400, 200, 200, 200, 0.0, 90.0, ArcType.ROUND);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
