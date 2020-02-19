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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.ConnectionSQL.ConnectionUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class controlPatientPanel implements Initializable {

    @FXML
    private TextField txtSearch;
    @FXML
    private ComboBox cbSearch;
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
        cbSearch.getItems().addAll(new String[]{"Pesel", "Imie", "Nazwisko"});

        makeDragable();
        fetColumnList("SELECT * FROM HospitalDB.Patient");
        fetRowList("SELECT * FROM HospitalDB.Patient");

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

    //coś tutaj jest nie tak, getID raczej nie ma sensu
    public void handleClicksSearchPatient(javafx.scene.input.MouseEvent mouseEvent){
        if(cbSearch.getValue() == "Pesel") {
            String st = "SELECT * FROM HospitalDB.Patient WHERE PatientID = ?";
            try {
                preparedStatement = (PreparedStatement) this.connection.prepareStatement(st);
                preparedStatement.setString(1, txtSearch.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            fetColumnList(st);
            fetRowList(st);
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

}
