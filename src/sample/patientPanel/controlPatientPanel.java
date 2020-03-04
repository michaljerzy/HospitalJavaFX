package sample.patientPanel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.ConnectionSQL.ConnectionUtil;
import sample.Testy.Person;
import sample.forOurClass.Patient;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controlPatientPanel implements Initializable {

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView tblPatient;

    private ObservableList<ObservableList> data;


    PreparedStatement preparedStatement;
    Connection connection = (Connection) ConnectionUtil.conDB();
    //String sqlPatient = "SELECT * FROM HospitalDB.Patient";

    double x = 0, y = 0;

    @FXML
    private SplitPane parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();

        try {
            TableSample("SELECT * FROM HospitalDB.Patient");
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

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

    public void handleClicksSearchPatient(javafx.scene.input.MouseEvent mouseEvent) throws SQLException {
        String string;
        string = txtSearch.getText();
                TableSample("SELECT * FROM HospitalDB.Patient WHERE PatientID  =" + "\"" + string + "\"" + " OR " + "FirstName = " + "\"" + string + "\"" + " OR "
                        + "Lastname = " + "\"" + string + "\"" + " OR " + "Email = " + "\"" + string + "\"" + " OR " + "Phone = " + "\"" + string + "\"" + " OR " + "StreetAdress = "
                        + "\"" + string + "\"" + " OR " + "City = " + "\"" + string + "\"" + " OR " + "Voivodeship = " + "\"" + string + "\""
                        + " OR " + "ZipCode = " + "\"" + string + "\"" + " OR " +"ChronicDiseases = "
                        + "\"" + string + "\"" + " OR " + "PolicyNumber = " + "\"" + string + "\"" + " OR " + "Allergies = " + "\"" + string + "\"" + " OR " + "RoomID = " + "\"" + string + "\"" + " OR " + "OtherHealthConcerns = " + "\"" + string + "\"" );


    }

    public void removeAllRows(){
        for (int i = 0; i<tblPatient.getItems().size(); i++){
            tblPatient.getItems().clear();
            tblPatient.getColumns().clear();
        }
    }

    public void handleClicksAddPatient(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            Node node = (Node)mouseEvent.getSource();
            Stage stage = (Stage)node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene((Parent)FXMLLoader.load(this.getClass().getResource("/sample/addPatientPanel/addPatientPanel.fxml")));
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
        }catch (IOException var5){
            System.err.println(var5.getMessage());
        }
    }

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


    public void TableSample(String sql) throws SQLException {
        removeAllRows();
        final ObservableList<Patient> data = FXCollections.observableArrayList();
        Connection conn = ConnectionUtil.conDB();
        Statement stm;
        stm = conn.createStatement();
        ResultSet rst;
        int l = 0;
        rst = stm.executeQuery(sql);
        removeAllRows();
        while (rst.next()){
            Patient patient = new Patient(rst.getString("PatientID"),rst.getString("FirstName"), rst.getString("Lastname"),
                                          rst.getString("Email"), rst.getString("Phone"), rst.getString("StreetAdress"), rst.getString("City"),
                                          rst.getString("Voivodeship"), rst.getString("ZipCode"), rst.getString("ChronicDiseases"), rst.getString("PolicyNumber"),
                                          rst.getString("Allergies"), rst.getString("RoomID"), rst.getString("OtherHealthConcerns"));
            data.add(patient);
            l++;
        }
        for (int i = 0; i < l; i++){
            data.get(i);
        }


        TableColumn idNameCol = new TableColumn();
        idNameCol.setText("Pesel");
        idNameCol.setCellValueFactory(new PropertyValueFactory("PatientID"));
        TableColumn firstNameCol = new TableColumn();
        firstNameCol.setText("Imię");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        TableColumn lastNameCol = new TableColumn();
        lastNameCol.setText("Nazwisko");
        //emailCol.setMinWidth(200);
        lastNameCol.setCellValueFactory(new PropertyValueFactory("Lastname"));
        TableColumn emailCol = new TableColumn();
        emailCol.setText("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory("Email"));
        TableColumn phoneCol = new TableColumn();
        phoneCol.setText("Telefon");
        phoneCol.setCellValueFactory(new PropertyValueFactory("Phone"));
        TableColumn streetCol = new TableColumn();
        streetCol.setText("Adres");
        streetCol.setCellValueFactory(new PropertyValueFactory("StreetAdress"));
        TableColumn cityCol = new TableColumn();
        cityCol.setText("Miasto");
        cityCol.setCellValueFactory(new PropertyValueFactory("City"));
        TableColumn voivodeshipCol = new TableColumn();
        voivodeshipCol.setText("Województwo");
        voivodeshipCol.setCellValueFactory(new PropertyValueFactory("Voivodeship"));
        TableColumn zipcodeCol = new TableColumn();
        zipcodeCol.setText("Kod pocztowy");
        zipcodeCol.setCellValueFactory(new PropertyValueFactory("ZipCode"));
        TableColumn chronicDiseasesCol = new TableColumn();
        chronicDiseasesCol.setText("Choroby przewlekłe");
        chronicDiseasesCol.setCellValueFactory(new PropertyValueFactory("ChronicDiseases"));
        TableColumn policyNumberCol = new TableColumn();
        policyNumberCol.setText("Numer ubezpieczenia");
        policyNumberCol.setCellValueFactory(new PropertyValueFactory("PolicyNumber"));
        TableColumn allergiesCol = new TableColumn();
        allergiesCol.setText("Alergie");
        allergiesCol.setCellValueFactory(new PropertyValueFactory("Allergies"));
        TableColumn roomIDCol = new TableColumn();
        roomIDCol.setText("Numer pokoju");
        roomIDCol.setCellValueFactory(new PropertyValueFactory("RoomID"));
        TableColumn otherHealthConcernsCol = new TableColumn();
        otherHealthConcernsCol.setText("Inne choroby");
        otherHealthConcernsCol.setCellValueFactory(new PropertyValueFactory("OtherHealthConcerns"));
        tblPatient.setItems(data);
        tblPatient.getColumns().addAll(idNameCol, firstNameCol, lastNameCol, emailCol,phoneCol, streetCol, cityCol,
                                      voivodeshipCol, zipcodeCol, chronicDiseasesCol, policyNumberCol, allergiesCol, roomIDCol);

    }


    /*

    private void fetRowList(String sqlPatient) {
        this.data = FXCollections.observableArrayList();

        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sqlPatient);

            while(rs.next()) {
                ObservableList row = FXCollections.observableArrayList();

                for(int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
                    row.add(rs.getString(i));
                }

                System.out.println("Row [1] added " + row);
                this.data.add(row);
            }

            this.tblPatient.setItems(this.data);
        } catch (SQLException var4) {
            System.err.println(var4.getMessage());
        }

    }



    private void fetColumnList(String sqlPatient) {
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sqlPatient);

            for(int i = 0; i < rs.getMetaData().getColumnCount(); ++i) {
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1).toUpperCase());
                int finalI = i;
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(((ObservableList)param.getValue()).get(finalI).toString());
                    }
                });
                tblPatient.getColumns().removeAll(new Object[]{col});
                tblPatient.getColumns().addAll(new Object[]{col});
                System.out.println("Column [" + i + "] ");
            }
        } catch (Exception var5) {
            System.out.println("Error " + var5.getMessage());
        }
    }

     */

}
