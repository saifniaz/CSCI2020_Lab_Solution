package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML Canvas canvas;

    public void initialize() {
        List<Float> stockPrices1 = downloadStockPrices("AAPL");
        List<Float> stockPrices2 = downloadStockPrices("GOOG");
        drawLineChart(stockPrices1, stockPrices2);
    }

    private List<Float> downloadStockPrices(String symbol) {
        List<Float> stockPrices = new ArrayList<>();

        try {
            URL url = new URL("http://ichart.finance.yahoo.com/table.csv?s="+symbol+"&a=0&b=01&c=2010&d=11&e=31&f=2015&g=m");
            // date,open,high,low,close,volume,adjusted close
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = in.readLine(); // skip first line
            while ((line = in.readLine()) != null) {
                String[] data = line.split(",");
                stockPrices.add(Float.parseFloat(data[4]));
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stockPrices;
    }

    private void drawLineChart(List<Float> stockPrices1, List<Float> stockPrices2) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // clear the canvas
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        double padding = 50;
        double top = 0 + padding;
        double left = 0 + padding;
        double bottom = canvas.getHeight() - padding;
        double right = canvas.getWidth() - padding;

        // draw the axes
        gc.setStroke(Color.BLACK);
        gc.strokeLine(left, top, left, bottom);   // y-axis
        gc.strokeLine(left, bottom, right, bottom); // x-axis

        // find the y limits
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (int i = 0; i < stockPrices1.size(); i++) {
            if (stockPrices1.get(i) < min) {
                min = stockPrices1.get(i);
            }
            if (stockPrices1.get(i) > max) {
                max = stockPrices1.get(i);
            }
        }
        for (int i = 0; i < stockPrices2.size(); i++) {
            if (stockPrices2.get(i) < min) {
                min = stockPrices2.get(i);
            }
            if (stockPrices2.get(i) > max) {
                max = stockPrices2.get(i);
            }
        }

        // draw the line (for stock price 1)
        plotLine(gc, stockPrices1, Color.BLUE, min, max);
        plotLine(gc, stockPrices2, Color.RED, min, max);
    }

    private void plotLine(GraphicsContext gc, List<Float> stockPrices, Color colour, double min, double max) {
        double padding = 50;
        double top = 0 + padding;
        double left = 0 + padding;
        double bottom = canvas.getHeight() - padding;
        double right = canvas.getWidth() - padding;

        // calculate the x and y spacing
        int count = stockPrices.size();
        double xSpacing = (canvas.getWidth() - (2 * padding)) / count;
        double height = canvas.getHeight() - (2 * padding);

        gc.setStroke(colour);
        double lastX = left;
        double lastY = bottom - (height * stockPrices.get(0) / max);

        for (int i = 1; i < stockPrices.size(); i++) {
            double y = bottom - (height * stockPrices.get(i) / max);
            gc.strokeLine(lastX, lastY, lastX + xSpacing, y);

            lastX += xSpacing;
            lastY = y;
        }
    }
}