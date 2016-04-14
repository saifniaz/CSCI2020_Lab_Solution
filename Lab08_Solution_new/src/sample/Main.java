package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
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

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class Main extends Application {
    private Stage window;
    private Stage primaryStage;
    private BorderPane layout;
    private TableView<StudentRecord> table;
    private TextField sidField, fnameField, lnameField, gpaField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Lab 08 Solutions");

        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New", imageFile("images/new.png"));
        newMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        newMenuItem.setOnAction( e -> newFile() );
        fileMenu.getItems().add(newMenuItem);

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem openMenuItem = new MenuItem("Open...", imageFile("images/exit.png"));
        fileMenu.getItems().add(openMenuItem);
        openMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openMenuItem.setOnAction( e -> open() );

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem saveMenuItem = new MenuItem("Save", imageFile("images/exit.png"));
        fileMenu.getItems().add(saveMenuItem);
        saveMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveMenuItem.setOnAction( e -> save() );

        MenuItem saveAsMenuItem = new MenuItem("Save As", imageFile("images/exit.png"));
        fileMenu.getItems().add(saveAsMenuItem);
        saveAsMenuItem.setOnAction( e -> saveAs() );

        fileMenu.getItems().add(new SeparatorMenuItem());

        MenuItem exitMenuItem = new MenuItem("Exit", imageFile("images/exit.png"));
        fileMenu.getItems().add(exitMenuItem);
        exitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exitMenuItem.setOnAction( e -> System.exit(0) );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);

        /* create the table (for the center of the user interface) */
        table = new TableView<>();
        table.setItems(DataSource.getAllMarks());
        table.setEditable(true);

        /* create the table's columns */
        TableColumn<StudentRecord,Integer> sidColumn = null;
        sidColumn = new TableColumn<>("SID");
        sidColumn.setMinWidth(100);
        sidColumn.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<StudentRecord,Double> assignmentColumn = null;
        assignmentColumn = new TableColumn<>("Assignments");
        assignmentColumn.setMinWidth(100);
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        TableColumn<StudentRecord,Double> midtermColumn = null;
        midtermColumn = new TableColumn<>("Midterm");
        midtermColumn.setMinWidth(100);
        midtermColumn.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        TableColumn<StudentRecord,Double> finalExamColumn = null;
        finalExamColumn = new TableColumn<>("Final Exam");
        finalExamColumn.setMinWidth(100);
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        TableColumn<StudentRecord,Double> finalMarkColumn = null;
        finalMarkColumn = new TableColumn<>("Final Mark");
        finalMarkColumn.setMinWidth(100);
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        TableColumn<StudentRecord,Double> letterGradeColumn = null;
        letterGradeColumn = new TableColumn<>("Letter Grade");
        letterGradeColumn.setMinWidth(100);
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        table.getColumns().add(sidColumn);
        table.getColumns().add(assignmentColumn);
        table.getColumns().add(midtermColumn);
        table.getColumns().add(finalExamColumn);
        table.getColumns().add(finalMarkColumn);
        table.getColumns().add(letterGradeColumn);

        /* create an edit form (for the bottom of the user interface) */
        GridPane editArea = new GridPane();
        editArea.setPadding(new Insets(10, 10, 10, 10));
        editArea.setVgap(10);
        editArea.setHgap(10);

        Label sidLabel = new Label("SID:");
        editArea.add(sidLabel, 0, 0);
        TextField sidField = new TextField();
        sidField.setPromptText("SID");
        editArea.add(sidField, 1, 0);

        Label assignmentsLabel = new Label("Assignments:");
        editArea.add(assignmentsLabel, 2, 0);
        TextField assignmentsField = new TextField();
        assignmentsField.setPromptText("Assignments/100");
        editArea.add(assignmentsField, 3, 0);

        Label midtermLabel = new Label("Midterm:");
        editArea.add(midtermLabel, 0, 1);
        TextField midtermField = new TextField();
        midtermField.setPromptText("Midterm/100");
        editArea.add(midtermField, 1, 1);

        Label finalExamLabel = new Label("Final Exam:");
        editArea.add(finalExamLabel, 2, 1);
        TextField finalExamField = new TextField();
        finalExamField.setPromptText("Final Exam/100");
        editArea.add(finalExamField, 3, 1);

        Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                String sid = sidField.getText();
                float assignments = Float.parseFloat(assignmentsField.getText());
                float midterm     = Float.parseFloat(midtermField.getText());
                float finalExam   = Float.parseFloat(finalExamField.getText());

                table.getItems().add(new StudentRecord(sid, assignments, midterm, finalExam));

                sidField.setText("");
                assignmentsField.setText("");
                midtermField.setText("");
                finalExamField.setText("");
            }
        });
        editArea.add(addButton, 1, 4);

        /* arrange all components in the main user interface */
        layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(table);
        layout.setBottom(editArea);

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private File currentFilename = null;

    private void newFile() {
        System.out.println("new()");
        ObservableList<StudentRecord> emptyData = FXCollections.<StudentRecord>observableArrayList();
        table.setItems(emptyData);
    }

    private void open() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file for loading");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selected = fileChooser.showOpenDialog(primaryStage);
        if (selected != null) {
            this.currentFilename = selected;
            load();
        }
    }

    private void saveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file for saving");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selected = fileChooser.showSaveDialog(primaryStage);
        if (selected != null) {
            this.currentFilename = selected;
            save();
        }
    }

    private void save() {
        if (this.currentFilename == null) {
            saveAs();
            return;
        } else {
            // store the data in CSV format
            try {
                PrintWriter out = new PrintWriter(this.currentFilename);

                ObservableList<StudentRecord> students = this.table.getItems();
                for (StudentRecord student: students) {
                    out.println(student.getSid() + "," +
                                student.getAssignments() + "," +
                                student.getMidterm() + "," +
                                student.getFinalExam());
                }

                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void load() {
        // load the data in CSV format
        try {
            if (this.currentFilename.exists()) {
                ObservableList<StudentRecord> students = FXCollections.observableArrayList();
                BufferedReader in = new BufferedReader(new FileReader(this.currentFilename));
                String line = null;
                while ((line = in.readLine()) != null) {
                    String[] fields = line.split(",");
                    String sid = fields[0];
                    float assignments = Float.parseFloat(fields[1]);
                    float midterm = Float.parseFloat(fields[2]);
                    float finalExam = Float.parseFloat(fields[3]);
                    students.add(new StudentRecord(sid, assignments, midterm, finalExam));
                }
                in.close();
                table.setItems(students);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImageView imageFile(String filename) {
        return new ImageView(new Image("file:"+filename));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
