package Application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add {

    @FXML
    private TextField addNo;
    @FXML
    private TextField addName;
    @FXML
    private TextField addID;
    @FXML
    private TextField addMyanmar;
    @FXML
    private TextField addEnglish;
    @FXML
    private TextField addMath;
    @FXML
    private TextField addPhy;
    @FXML
    private TextField addChem;
    @FXML
    private TextField addBio;
    @FXML
    private TextField addRollNo;
    @FXML
    private TextField addTotal;
    @FXML
    private Button calculate;
    @FXML
    private Button addToDatabase;

    public void Submit(ActionEvent event){
        try {
            if(addNo.getText() == null || addNo.getText().trim().isEmpty() ||
               addName.getText() == null || addName.getText().trim().isEmpty() ||
               addID.getText() == null || addID.getText().trim().isEmpty() ||
               addMyanmar.getText() == null || addMyanmar.getText().trim().isEmpty() ||
               addEnglish.getText() == null || addEnglish.getText().trim().isEmpty() ||
               addMath.getText() == null || addMath.getText().trim().isEmpty() ||
               addPhy.getText() == null || addPhy.getText().trim().isEmpty() ||
               addChem.getText() == null || addChem.getText().trim().isEmpty() ||
               addBio.getText() == null || addBio.getText().trim().isEmpty() ||
               addTotal.getText() == null || addTotal.getText().trim().isEmpty() ||
               addRollNo.getText() == null || addRollNo.getText().trim().isEmpty()){

                //System.out.println("this code here working");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("There are some blanks in a text field(s)");
                alert.setContentText("You need to fill everything to submit to the database.\nPlease check your all inputs");
                alert.show();
            } else if(
                    (!onlyDigits(addNo.getText()) || !onlyDigits(addID.getText()) ||
                    !onlyDigits(addMyanmar.getText()) || !onlyDigits(addEnglish.getText()) ||
                    !onlyDigits(addMath.getText()) || !onlyDigits(addPhy.getText()) ||
                    !onlyDigits(addChem.getText()) || !onlyDigits(addBio.getText()) ||
                    !onlyDigits(addTotal.getText()) || !onlyDigits(addRollNo.getText())) || (!isAlpha(addName.getText()))
            ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Invalid inputs");
                alert.setContentText("You are filling invalid data to inappropriate text fields.\nPlease check your all inputs");
                alert.show();
            } else if(
                    (((Integer.parseInt(addMyanmar.getText()) < 0 || Integer.parseInt(addMyanmar.getText()) > 100)) ||
                    ((Integer.parseInt(addEnglish.getText()) < 0 || Integer.parseInt(addEnglish.getText()) > 100)) ||
                    ((Integer.parseInt(addMath.getText()) < 0 || Integer.parseInt(addMath.getText()) > 100)) ||
                    ((Integer.parseInt(addPhy.getText()) < 0 || Integer.parseInt(addPhy.getText()) > 100)) ||
                    ((Integer.parseInt(addChem.getText()) < 0 || Integer.parseInt(addChem.getText()) > 100)) ||
                    ((Integer.parseInt(addBio.getText()) < 0 || Integer.parseInt(addBio.getText()) > 100))) ||
                    ((Integer.parseInt(addTotal.getText()) > 600) || Integer.parseInt(addTotal.getText()) < 0)
                    || (Integer.parseInt(addNo.getText()) <= 0) || (Integer.parseInt(addID.getText()) <= 0) ||
                    (Integer.parseInt(addRollNo.getText()) <= 0)
            ){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Invalid inputs");
                alert.setContentText("You need to fill only valid numbers. Please check your all inputs");
                alert.show();
            } else {
                DatabaseConnection connection = new DatabaseConnection();
                Connection connectDB = connection.getConnection();

                String sql = "INSERT INTO student_records VALUES (" + addNo.getText().toString() + ", \"" + addName.getText().toString() + "\", "  +
                        addID.getText().toString() + ", "  + addMyanmar.getText().toString() + ", "  + addEnglish.getText().toString() + ", "  + addMath.getText().toString() + ", "  +
                        addPhy.getText().toString() + ", "  + addChem.getText().toString() + ", "  + addBio.getText().toString() + ", "  + addTotal.getText().toString() + ", "  + addRollNo.getText().toString() + ");";
                System.out.println(sql);
                Statement statement = connectDB.createStatement();
                System.out.println("Inserting into table");
                statement.executeUpdate(sql);
                statement.close(); //*******************
                connectDB.close(); //*******************
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Insertion Completed!");
                alert.setHeaderText("Adding student info to the database is finished");
                alert.setContentText("You just added to the database");
                alert.show();
                System.out.println("Insertion completed");
                addNo.setText(""); addName.setText(""); addID.setText(""); addMyanmar.setText("");
                addEnglish.setText(""); addMath.setText(""); addPhy.setText(""); addChem.setText("");
                addBio.setText(""); addTotal.setText(""); addRollNo.setText("");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // For submit button
    // TODO: check if one or more text fields are empty

    // TODO: check if a user entered string values to text fields which must only accept int values
    // TODO: check if a name contains numbers

    // TODO: Check if No, Roll No and ID contain negative numbers
    // TODO: check if each subjects' marks are not more than 100 and also check if those marks are not less than zero
    // TODO: check if total mark is not more than 600 and also check if that total marks are not less than zero

    // For calculate button
    // TODO: check if subjects' text fields are empty
    // TODO: check if a user entered string values to text fields which must only accept int values
    // TODO: check if each subjects' marks are not more than 100 and also check if those marks are not less than zero
    // TODO: check if total mark is not more than 600 and also check if that total marks are not less than zero

    public void Calculate(ActionEvent event){
        if(
            addMyanmar.getText() == null || addMyanmar.getText().trim().isEmpty() ||
            addEnglish.getText() == null || addEnglish.getText().trim().isEmpty() ||
            addMath.getText() == null || addMath.getText().trim().isEmpty() ||
            addPhy.getText() == null || addPhy.getText().trim().isEmpty() ||
            addChem.getText() == null || addChem.getText().trim().isEmpty() ||
            addBio.getText() == null || addBio.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("There are some blanks in a text field(s)");
            alert.setContentText("You need to fill each subjects' marks to calculate to get the total mark.\nPlease check your all inputs");
            alert.show();
        }  else if(
                (!onlyDigits(addMyanmar.getText()) || !onlyDigits(addEnglish.getText()) ||
                !onlyDigits(addMath.getText()) || !onlyDigits(addPhy.getText()) ||
                !onlyDigits(addChem.getText()) || !onlyDigits(addBio.getText()))
        ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Invalid inputs");
            alert.setContentText("You are filling invalid data to inappropriate text fields.\nPlease check your all inputs");
            alert.show();
        } else if(
                ((Integer.parseInt(addMyanmar.getText()) < 0 || Integer.parseInt(addMyanmar.getText()) > 100)) ||
                ((Integer.parseInt(addEnglish.getText()) < 0 || Integer.parseInt(addEnglish.getText()) > 100)) ||
                ((Integer.parseInt(addMath.getText()) < 0 || Integer.parseInt(addMath.getText()) > 100)) ||
                ((Integer.parseInt(addPhy.getText()) < 0 || Integer.parseInt(addPhy.getText()) > 100)) ||
                ((Integer.parseInt(addChem.getText()) < 0 || Integer.parseInt(addChem.getText()) > 100)) ||
                ((Integer.parseInt(addBio.getText()) < 0 || Integer.parseInt(addBio.getText()) > 100))
        ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText("Marks are not valid!");
            alert.setContentText("You need to fill only valid marks between 0 and 100.\nPlease check your all inputs");
            alert.show();
        } else {
            int total = 0;
            total += Integer.parseInt(addMyanmar.getText()) + Integer.parseInt(addEnglish.getText()) +
                    Integer.parseInt(addMath.getText()) + Integer.parseInt(addPhy.getText()) +
                    Integer.parseInt(addChem.getText()) + Integer.parseInt(addBio.getText());
            addTotal.setText(String.valueOf(total));
        }
    }

    public static boolean onlyDigits(String str) {
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);

        if (str == null) {
            return false;
        }

        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }
}
