package sample.addPatientPanel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionSQL.ConnectionUtil;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class addPatientControl implements Initializable {

    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtVoivodeship;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtSecondName;
    @FXML
    private TextField txtRoom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtStreet;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtPolicyNumber;
    @FXML
    private TextField txtChronicDiseases;
    @FXML
    private TextField txtAllergies;
    @FXML
    private TextField txtOtherHealthConcerns;
    @FXML
    private TextField txtPhone;

    PreparedStatement preparedStatement;
    Connection connection = (Connection) ConnectionUtil.conDB();
    //String sqlPatient = "SELECT * FROM HospitalDB.Patient";

    @FXML
    private SplitPane parent;

    double x = 0, y = 0;


    private void makeDragable(){
        parent.setOnMousePressed(((mouseEvent) ->{
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        }));

        parent.setOnMouseDragged(((mouseEvent) -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
            stage.setOpacity(0.8f);

        }));

        parent.setOnDragDone(((dragEvent) -> {
            Stage stage = (Stage) ((Node) dragEvent.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        }));

        parent.setOnMouseReleased(((mouseEvent) -> {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);

        }));

    }
    //function button click, check and safe data
    public void handleClicksAddToDataBase(javafx.scene.input.MouseEvent mouseEvent){
        if(txtID.getText().isEmpty() && txtFirstName.getText().isEmpty() && txtSecondName.getText().isEmpty() && txtVoivodeship.getText().isEmpty()&& txtAllergies.getText().isEmpty() &&
                txtPolicyNumber.getText().isEmpty() && txtOtherHealthConcerns.getText().isEmpty() && txtRoom.getText().isEmpty() && txtChronicDiseases.getText().isEmpty() && txtZipCode.getText().isEmpty() &&
                txtCity.getText().isEmpty() && txtPhone.getText().isEmpty() && txtStreet.getText().isEmpty() && txtRoom.getText().isEmpty()) {
            saveData();
        }else {
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText("Wypełnij każdą tabelkę!");
        }
    }

    //open new Panel
    public void handleClicksHome(javafx.scene.input.MouseEvent mouseEvent){
        try {
            Node node = (Node)mouseEvent.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene((Parent) FXMLLoader.load(this.getClass().getResource("/sample/adminPanel/adminPanel.fxml")));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch (IOException var5){
            System.err.println(var5.getMessage());
        }
    }

    //open new Panel
    public void handleClicksPatient(javafx.scene.input.MouseEvent mouseEvent){
        try {
            Node node = (Node)mouseEvent.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene((Parent)FXMLLoader.load(this.getClass().getResource("/sample/patientPanel/patientPanel.fxml")));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch (IOException var5){
            System.err.println(var5.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblStatus.setVisible(false);
        makeDragable();
    }

    //save data with database HosptalDB and table Patient
    private String saveData(){
        try {
            String st = "INSERT INTO HospitalDB.Patient (PatientID, FirstName, Lastname, Email, Phone, StreetAdress, City, Voivodeship," +
                    " ZipCode, ChronicDiseases, PolicyNumber, Allergies, RoomID, OtherHealthConcerns) VALUES (?, ? , ? , ? , ? , ? , ? , ? , ? , ? ,?, ? , ? ,?)";
            preparedStatement = (PreparedStatement)this.connection.prepareStatement(st);
            preparedStatement.setString(1, txtID.getText());
            preparedStatement.setString(2, txtFirstName.getText());
            preparedStatement.setString(3, txtSecondName.getText());
            preparedStatement.setString(4, txtEmail.getText());
            preparedStatement.setString(5, txtPhone.getText());
            preparedStatement.setString(6, txtStreet.getText());
            preparedStatement.setString(7, txtCity.getText());
            preparedStatement.setString(8, txtVoivodeship.getText());
            preparedStatement.setString(9, txtZipCode.getText());
            preparedStatement.setString(10, txtChronicDiseases.getText());
            preparedStatement.setString(11, txtPolicyNumber.getText());
            preparedStatement.setString(12, txtAllergies.getText());
            preparedStatement.setString(13, txtRoom.getText());
            preparedStatement.setString(14, txtOtherHealthConcerns.getText());
            preparedStatement.executeUpdate();
            lblStatus.setVisible(true);
            lblStatus.setTextFill(Color.GREEN);
            lblStatus.setText("Dodanie danych do bazy powiodło się");
            return "Success";
        }catch (SQLException var2){
            System.out.println(var2.getMessage());
            lblStatus.setVisible(true);
            lblStatus.setTextFill(Color.TOMATO);
            lblStatus.setText(var2.getMessage());
            return "Exception";
        }
    }
}
