package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import javax.activation.DataSource;

public class Main extends Application {
    private BorderPane layout;
    private TableView<Student> table;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab 05 Solution");

        table = new TableView<Student>();
        table.setItems(sample.DataSource.getAllMarks());
        table.setEditable(true);

        TableColumn<Student,Integer> sidColumn = null;
        sidColumn = new TableColumn<>("SID");
        sidColumn.setMinWidth(100);
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<Student,String> AssignmentColumn = null;
        AssignmentColumn = new TableColumn<>("Assignment");
        AssignmentColumn.setMinWidth(100);
        AssignmentColumn.setCellValueFactory(new PropertyValueFactory<>("assignment"));
        //AssignmentColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());

        TableColumn<Student,String> MidtermColumn = null;
        MidtermColumn = new TableColumn<>("Midterm");
        MidtermColumn.setMinWidth(100);
        MidtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        //MidtermColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());

        TableColumn<Student,String> finalColumn = null;
        finalColumn = new TableColumn<>("Final Exam");
        finalColumn.setMinWidth(100);
        finalColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        //finalColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());

        TableColumn<Student,String> finalMColumn = null;
        finalMColumn = new TableColumn<>("Final Marks");
        finalMColumn.setMinWidth(100);
        finalMColumn.setCellValueFactory(new PropertyValueFactory<>("finalMarks"));
       // finalMColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());


        TableColumn<Student,String> letterColumn = null;
        letterColumn = new TableColumn<>("Letter Grade");
        letterColumn.setMinWidth(100);
        letterColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        //letterColumn.setCellFactory(TextFieldTableCell.<Student>forTableColumn());


        table.getColumns().add(sidColumn);
        table.getColumns().add(AssignmentColumn);
        table.getColumns().add(MidtermColumn);
        table.getColumns().add(finalColumn);
        table.getColumns().add(finalMColumn);
        table.getColumns().add(letterColumn);

        layout = new BorderPane();
        layout.setCenter(table);

        Scene scene = new Scene(layout, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
