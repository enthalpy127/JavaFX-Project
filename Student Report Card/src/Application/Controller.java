package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private BorderPane mainPane;

    public void Home(ActionEvent event) throws IOException {
        //System.out.println("You clicked home button.");
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("Home");
        mainPane.setCenter(pane);
    }

    public void Add(ActionEvent event) throws  IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("Add");
        mainPane.setCenter(pane);
    }

    public void Edit(ActionEvent event) throws IOException {
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("Edit");
        mainPane.setCenter(pane);
    }

    public void Help(ActionEvent event) throws IOException{
        DatabaseConnection connection = new DatabaseConnection();
        Connection connectDB = connection.getConnection();
        Help help = new Help();
        try{
            String sql = "SELECT * FROM student_records;";
            Statement smt = connectDB.createStatement();
            System.out.println("Fetching from database");
            ResultSet resultSet = smt.executeQuery(sql);

            while(resultSet.next()){
                help.data.add(
                        new Student(
                                resultSet.getInt("No"),
                                resultSet.getString("Name"),
                                resultSet.getInt("ID"),
                                resultSet.getInt("Myanmar"),
                                resultSet.getInt("English"),
                                resultSet.getInt("Mathematics"),
                                resultSet.getInt("Physics"),
                                resultSet.getInt("Chemistry"),
                                resultSet.getInt("Biology"),
                                resultSet.getInt("TotalMarks"),
                                resultSet.getInt("RollNo")
                                )
                );
            }

            resultSet.close();      //**************
            smt.close();            //**************
            connectDB.close();      //**************

        } catch(Exception e){
            e.printStackTrace();
        }

        help.setUpMainTableColumns();
        help.mainTable.setItems(help.data);
        help.mainTable.setLayoutX(21.0);
        help.mainTable.setLayoutY(61.0);
        help.mainTable.setPrefHeight(413.0);
        help.mainTable.setPrefWidth(495.0);
        help.mainTable.setTableMenuButtonVisible(true);

        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("Help");
        pane.getChildren().add(help.mainTable);
        mainPane.setCenter(pane);
    }

    public void About(ActionEvent event) throws IOException{
        FxmlLoader loader = new FxmlLoader();
        Pane pane = loader.getPage("About");
        mainPane.setCenter(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
