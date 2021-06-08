package Application;

import com.mysql.cj.x.protobuf.MysqlxConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Edit {

    @FXML
    private TextField EditNo;

    private boolean NoSearchBtnClicked = false;

    @FXML
    private TextField EditName;

    private boolean NameSearchBinClicked = false;

    @FXML
    private TextField EditID;

    @FXML
    private TextField EditMyanmar;

    @FXML
    private TextField EditEnglish;

    @FXML
    private TextField EditMath;

    @FXML
    private TextField EditPhy;

    @FXML
    private TextField EditChem;

    @FXML
    private TextField EditBio;

    @FXML
    private TextField EditTotal;

    @FXML
    private TextField EditRollNo;

    private Student OldStudentData;
    private Student NewStudentData;
    private String UpdateQuery = "UPDATE student_records SET ";
    private boolean CommaMustBeAdded = false;
    private boolean IsDataTheSame = true;

    //private final DatabaseConnection conn = new DatabaseConnection();     //******************
    //private final Connection connection = conn.getConnection();           //******************

    // For no search button
    // TODO: check if the user click the No. search button when there is no input in No. text field
    // TODO: check if the user entered string values in No. text field
    // TODO: check if the user entered negative values in No. text field
    // TODO: check if the user search the data which is not existed in the database

    public void SearchNo(ActionEvent event){
        try {
            if(EditNo.getText() == null || EditNo.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("No input to search the data");
                alert.setContentText("Please fill only numbers to search the data you want");
                alert.show();
                EditName.setText(""); EditID.setText(""); EditMyanmar.setText("");
                EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                EditRollNo.setText("");
            } else if(!Add.onlyDigits(EditNo.getText())){   // onlyDigits() is from Add class
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Inappropriate input");
                alert.setContentText("Please fill only numbers to search the data you want");
                alert.show();
                EditName.setText(""); EditID.setText(""); EditMyanmar.setText("");
                EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                EditRollNo.setText("");
            } else if(Integer.parseInt(EditNo.getText()) <= 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Inappropriate input");
                alert.setContentText("Please fill only positive numbers to search the data you want");
                alert.show();
                EditName.setText(""); EditID.setText(""); EditMyanmar.setText("");
                EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                EditRollNo.setText("");
            } else {
                DatabaseConnection conn = new DatabaseConnection();     //****************
                Connection connection = conn.getConnection();           //****************

                String sql = "SELECT * FROM student_records WHERE No=" + EditNo.getText();
                Statement smt = connection.createStatement();
                System.out.println("Fetching from database");
                ResultSet rs = smt.executeQuery(sql);
                if(rs.next()){      // The code: rs.next() is so important. Without this, you can't access data in the database
                    EditNo.setText(String.valueOf(rs.getInt("No")));
                    EditName.setText(rs.getString("Name"));
                    EditID.setText(String.valueOf(rs.getInt("ID")));
                    EditMyanmar.setText(String.valueOf(rs.getInt("Myanmar")));
                    EditEnglish.setText(String.valueOf(rs.getInt("English")));
                    EditMath.setText(String.valueOf(rs.getInt("Mathematics")));
                    EditPhy.setText(String.valueOf(rs.getInt("Physics")));
                    EditChem.setText(String.valueOf(rs.getInt("Chemistry")));
                    EditBio.setText(String.valueOf(rs.getInt("Biology")));
                    EditTotal.setText(String.valueOf(rs.getInt("TotalMarks")));
                    EditRollNo.setText(String.valueOf(rs.getInt("RollNo")));

                    NoSearchBtnClicked = true;
                    NameSearchBinClicked = false;
                    OldStudentData = new Student(
                            rs.getInt("No"), rs.getString("Name"),
                            rs.getInt("ID"), rs.getInt("Myanmar"),
                            rs.getInt("English"), rs.getInt("Mathematics"),
                            rs.getInt("Physics"), rs.getInt("Chemistry"),
                            rs.getInt("Biology"), rs.getInt("TotalMarks"),
                            rs.getInt("RollNo")
                            );
                    rs.close();         //******************
                    smt.close();        //******************
                    connection.close(); //******************
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Data Not Exist");
                    alert.setContentText("The data you searched doesn't exist in the database");
                    alert.show();
                    EditName.setText(""); EditID.setText(""); EditMyanmar.setText("");
                    EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                    EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                    EditRollNo.setText("");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // For name search button
    // TODO: check if the user click the button where there is no input in name text field
    // TODO: check if the user entered int values to name text field
    // TODO: check if the user entered the input which doesn't exist in the database

    public void SearchName(ActionEvent event){
        try{
            if(EditName.getText() == null || EditName.getText().trim().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("No input to search the data");
                alert.setContentText("Please fill only characters to search the data you want");
                alert.show();
                EditNo.setText(""); EditID.setText(""); EditMyanmar.setText("");
                EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                EditRollNo.setText("");
            } else if(!Add.isAlpha(EditName.getText())){       // isAlpha() is from Add class
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Inappropriate input");
                alert.setContentText("Please fill only characters to search the data you want");
                alert.show();
                EditNo.setText(""); EditID.setText(""); EditMyanmar.setText("");
                EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                EditRollNo.setText("");
            } else {
                DatabaseConnection conn = new DatabaseConnection();     //****************
                Connection connection = conn.getConnection();           //****************

                String sql = "SELECT * FROM student_records WHERE Name='" + EditName.getText() + "'";
                Statement smt = connection.createStatement();
                System.out.println("Fetching from database");
                ResultSet rs = smt.executeQuery(sql);
                if(rs.next()){      // The code: rs.next() is so important. Without this, you can't access data in the database
                    EditNo.setText(String.valueOf(rs.getInt("No")));
                    EditName.setText(rs.getString("Name"));
                    EditID.setText(String.valueOf(rs.getInt("ID")));
                    EditMyanmar.setText(String.valueOf(rs.getInt("Myanmar")));
                    EditEnglish.setText(String.valueOf(rs.getInt("English")));
                    EditMath.setText(String.valueOf(rs.getInt("Mathematics")));
                    EditPhy.setText(String.valueOf(rs.getInt("Physics")));
                    EditChem.setText(String.valueOf(rs.getInt("Chemistry")));
                    EditBio.setText(String.valueOf(rs.getInt("Biology")));
                    EditTotal.setText(String.valueOf(rs.getInt("TotalMarks")));
                    EditRollNo.setText(String.valueOf(rs.getInt("RollNo")));

                    NameSearchBinClicked = true;
                    NoSearchBtnClicked = false;
                    OldStudentData = new Student(
                            rs.getInt("No"), rs.getString("Name"),
                            rs.getInt("ID"), rs.getInt("Myanmar"),
                            rs.getInt("English"), rs.getInt("Mathematics"),
                            rs.getInt("Physics"), rs.getInt("Chemistry"),
                            rs.getInt("Biology"), rs.getInt("TotalMarks"),
                            rs.getInt("RollNo")
                    );
                    rs.close();         //***************
                    smt.close();        //***************
                    connection.close(); //***************
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Data Not Exist");
                    alert.setContentText("The data you searched doesn't exist in the database");
                    alert.show();
                    EditNo.setText(""); EditID.setText(""); EditMyanmar.setText("");
                    EditEnglish.setText(""); EditMath.setText(""); EditPhy.setText("");
                    EditChem.setText(""); EditBio.setText(""); EditTotal.setText("");
                    EditRollNo.setText("");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // For update button
    // TODO: Handling user inputs
    // TODO: check if the user is trying to update the same data
    // TODO: check if the user doesn't click any search buttons but he filled every blanks and click update button (what will you do?)

    public void Update(ActionEvent event){
        try{
            if(
                    EditNo.getText() == null || EditNo.getText().trim().isEmpty() ||
                    EditName.getText() == null || EditName.getText().trim().isEmpty() ||
                    EditID.getText() == null || EditID.getText().trim().isEmpty() ||
                    EditMyanmar.getText() == null || EditMyanmar.getText().trim().isEmpty() ||
                    EditEnglish.getText() == null || EditEnglish.getText().trim().isEmpty() ||
                    EditMath.getText() == null || EditMath.getText().trim().isEmpty() ||
                    EditPhy.getText() == null || EditPhy.getText().trim().isEmpty() ||
                    EditChem.getText() == null || EditChem.getText().trim().isEmpty() ||
                    EditBio.getText() == null || EditBio.getText().trim().isEmpty() ||
                    EditTotal.getText() == null || EditTotal.getText().trim().isEmpty() ||
                    EditRollNo.getText() == null || EditRollNo.getText().trim().isEmpty()
            ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Not enough input to update data");
                alert.setContentText("You need to fill everything to make changes to the existing data in the database.");
                alert.show();
            } else if (
                (!Add.onlyDigits(EditNo.getText()) || !Add.onlyDigits(EditID.getText()) ||
                !Add.onlyDigits(EditMyanmar.getText()) || !Add.onlyDigits(EditEnglish.getText()) ||
                !Add.onlyDigits(EditMath.getText()) || !Add.onlyDigits(EditPhy.getText()) ||
                !Add.onlyDigits(EditChem.getText()) || !Add.onlyDigits(EditBio.getText()) ||
                !Add.onlyDigits(EditTotal.getText()) || !Add.onlyDigits(EditRollNo.getText())) || !Add.isAlpha(EditName.getText())
            ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Invalid inputs");
                alert.setContentText("You are filling invalid data to inappropriate text fields.\nPlease check your all inputs");
                alert.show();
            } else if(
                    ((Integer.parseInt(EditMyanmar.getText()) < 0) || (Integer.parseInt(EditMyanmar.getText()) > 100)) ||
                    ((Integer.parseInt(EditEnglish.getText()) < 0) || (Integer.parseInt(EditEnglish.getText()) > 100)) ||
                    ((Integer.parseInt(EditMath.getText()) < 0) || (Integer.parseInt(EditMath.getText()) > 100)) ||
                    ((Integer.parseInt(EditPhy.getText()) < 0) || (Integer.parseInt(EditPhy.getText()) > 100)) ||
                    ((Integer.parseInt(EditChem.getText()) < 0) || (Integer.parseInt(EditChem.getText()) > 100)) ||
                    ((Integer.parseInt(EditBio.getText()) < 0) || (Integer.parseInt(EditBio.getText()) > 100)) ||
                    ((Integer.parseInt(EditTotal.getText()) < 0) || (Integer.parseInt(EditTotal.getText()) > 600)) ||
                    (Integer.parseInt(EditNo.getText()) <= 0) || (Integer.parseInt(EditID.getText()) <= 0) ||
                    (Integer.parseInt(EditRollNo.getText()) <= 0)
            ) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Invalid inputs");
                alert.setContentText("You need to fill only valid numbers. Please check your all inputs");
                alert.show();
            } else {
                NewStudentData = new Student(
                        Integer.parseInt(EditNo.getText()), EditName.getText(),
                        Integer.parseInt(EditID.getText()), Integer.parseInt(EditMyanmar.getText()),
                        Integer.parseInt(EditEnglish.getText()), Integer.parseInt(EditMath.getText()),
                        Integer.parseInt(EditPhy.getText()), Integer.parseInt(EditChem.getText()),
                        Integer.parseInt(EditBio.getText()), Integer.parseInt(EditTotal.getText()),
                        Integer.parseInt(EditRollNo.getText())
                );

                UpdateQuery = "UPDATE student_records SET ";
                CommaMustBeAdded = false;
                if(NoSearchBtnClicked || NameSearchBinClicked){
                    UpdateQueryCreator(OldStudentData, NewStudentData);

                    System.out.println(UpdateQuery);

                    if(!IsDataTheSame){
                        DatabaseConnection conn = new DatabaseConnection();
                        Connection connection = conn.getConnection();

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Updating data");
                        alert.setHeaderText("Are you sure you want to update the data to the database");
                        alert.setContentText("Click OK to update the data");
                        alert.showAndWait();

                        if(alert.getResult() == ButtonType.OK) {
                            Statement smt = connection.createStatement();
                            System.out.println("Updating data to the table");

                            smt.executeUpdate(UpdateQuery);
                            System.out.println("Updating completed");

                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("Updating completed");
                            alert1.setHeaderText("Successfully updated!");
                            alert1.setContentText("You data is now changed successfully.");
                            alert1.show();
                            EditNo.setText(""); EditName.setText(""); EditID.setText("");
                            EditMyanmar.setText(""); EditEnglish.setText(""); EditMath.setText("");
                            EditPhy.setText(""); EditChem.setText(""); EditBio.setText("");
                            EditTotal.setText(""); EditRollNo.setText("");
                            smt.close();        //****************
                            connection.close(); //****************
                        }

                        UpdateQuery = "UPDATE student_records SET ";
                        CommaMustBeAdded = false;
                        IsDataTheSame = true;
                        NoSearchBtnClicked = false;
                        NameSearchBinClicked = false;
                    } else {
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Updating the same data");
                        alert1.setHeaderText("Updating the same data");
                        alert1.setContentText("Please make changes to update to the existing data");
                        alert1.show();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning!");
                    alert.setHeaderText("Updating ambiguous data");
                    alert.setContentText("Please first search the data you want to change.\nBecause we aren't sure which data you want to change");
                    alert.show();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateQueryCreator(Student oldStudentData, Student newStudentData){
        if(oldStudentData.getNo() != newStudentData.getNo()) {
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "No=" + String.valueOf(newStudentData.getNo());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", No=" + String.valueOf(newStudentData.getNo());
            }
        }

        if(!oldStudentData.getName().equals(newStudentData.getName())){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "Name='" + newStudentData.getName() + "'";
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", Name='" + newStudentData.getName() + "'";
            }
        }

        if(oldStudentData.getId() != oldStudentData.getId()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "ID=" + String.valueOf(newStudentData.getId());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", ID=" + String.valueOf(newStudentData.getId());
            }
        }

        if(oldStudentData.getMyanmar() != newStudentData.getMyanmar()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "Myanmar=" + String.valueOf(newStudentData.getMyanmar());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", Myanmar=" + String.valueOf(newStudentData.getMyanmar());
            }
        }

        if(oldStudentData.getEnglish() != newStudentData.getEnglish()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "English=" + String.valueOf(newStudentData.getEnglish());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", English=" + String.valueOf(newStudentData.getEnglish());
            }
        }

        if(oldStudentData.getMathematics() != newStudentData.getMathematics()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "Mathematics=" + String.valueOf(newStudentData.getMathematics());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", Mathematics=" + String.valueOf(newStudentData.getMathematics());
            }
        }

        if(oldStudentData.getPhysics() != newStudentData.getPhysics()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "Physics=" + String.valueOf(newStudentData.getPhysics());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", Physics=" + String.valueOf(newStudentData.getPhysics());
            }
        }

        if(oldStudentData.getChemistry() != newStudentData.getChemistry()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "Chemistry=" + String.valueOf(newStudentData.getChemistry());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", Chemistry=" + String.valueOf(newStudentData.getChemistry());
            }
        }

        if(oldStudentData.getBiology() != newStudentData.getBiology()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "Biology=" + String.valueOf(newStudentData.getBiology());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", Biology=" + String.valueOf(newStudentData.getBiology());
            }
        }

        if(oldStudentData.getTotalmarks() != newStudentData.getTotalmarks()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "TotalMarks=" + String.valueOf(newStudentData.getTotalmarks());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", TotalMarks=" + String.valueOf(newStudentData.getTotalmarks());
            }
        }

        if(oldStudentData.getRollno() != newStudentData.getRollno()){
            IsDataTheSame = false;
            if(!CommaMustBeAdded){
                UpdateQuery += "RollNo=" + String.valueOf(newStudentData.getRollno());
                CommaMustBeAdded = true;
            } else {
                UpdateQuery += ", RollNo=" + String.valueOf(newStudentData.getRollno());
            }
        }

        if(!IsDataTheSame){
            UpdateQuery += " WHERE ";

            if(NoSearchBtnClicked){
                UpdateQuery += "No=" + String.valueOf(oldStudentData.getNo()) + ";";
            } else if(NameSearchBinClicked){
                UpdateQuery += "Name='" + oldStudentData.getName() + "';";
            }
        }
    }

    public void Cancel(ActionEvent event){
        EditNo.setText(""); EditName.setText(""); EditID.setText("");
        EditMyanmar.setText(""); EditEnglish.setText(""); EditMath.setText("");
        EditPhy.setText(""); EditChem.setText(""); EditBio.setText("");
        EditTotal.setText(""); EditRollNo.setText("");
        UpdateQuery = "UPDATE student_records SET ";
        CommaMustBeAdded = false;
        NoSearchBtnClicked = false;
        NameSearchBinClicked = false;
    }

    // For Delete button
    // TODO: check if the user doesn't click any search buttons and trying to click the Delete button
    // TODO: although if the user search the data and update that data, delete that data when he clicks the Delete button

    public void Delete(ActionEvent event){
        try{
            if(NoSearchBtnClicked || NameSearchBinClicked){
                DatabaseConnection conn = new DatabaseConnection();     //****************
                Connection connection = conn.getConnection();           //****************

                String sql = "DELETE FROM student_records WHERE " + (NoSearchBtnClicked ? "No=" + OldStudentData.getNo() + ";" : "Name='" + OldStudentData.getName() + "';");
                System.out.println(sql);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Deleting data");
                alert.setHeaderText("Are you sure you want to delete the data from the database");
                alert.setContentText("Click OK to update the data");
                alert.showAndWait();

                if(alert.getResult() == ButtonType.OK) {
                    Statement smt = connection.createStatement();
                    System.out.println("Deleting data from the table");

                    smt.executeUpdate(sql);
                    System.out.println("Deleting completed");

                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Deleting completed");
                    alert1.setHeaderText("Successfully deleted!");
                    alert1.setContentText("You now deleted the data");
                    alert1.show();
                    EditNo.setText(""); EditName.setText(""); EditID.setText("");
                    EditMyanmar.setText(""); EditEnglish.setText(""); EditMath.setText("");
                    EditPhy.setText(""); EditChem.setText(""); EditBio.setText("");
                    EditTotal.setText(""); EditRollNo.setText("");
                    smt.close();
                    connection.close();
                }

                UpdateQuery = "UPDATE student_records SET ";
                CommaMustBeAdded = false;
                IsDataTheSame = true;
                NoSearchBtnClicked = false;
                NameSearchBinClicked = false;
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText("Deleting ambiguous data");
                alert.setContentText("Please first search the data you want to delete.\nBecause we aren't sure if the data doesn't already exist or not.");
                alert.show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
