package sample.Testy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectionSQL.ConnectionUtil;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class test implements Initializable {

    @FXML
    public TableView testTable;

    @FXML
    private TableColumn colID;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colTest;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            TableSample();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void TableSample() throws SQLException {
        final ObservableList<Person> data = FXCollections.observableArrayList();
        Connection conn = ConnectionUtil.conDB();
        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT * FROM HospitalDB.Test";
        ResultSet rst;
        int l = 0;
        rst = stm.executeQuery(sql);

        while (rst.next()){
            Person person = new Person(rst.getString("ID"),rst.getString("Name"), rst.getString("Testcol"));
            data.add(person);
            l++;
        }
        for (int i = 0; i < l; i++){
            System.out.println(data.get(i));
        }


        TableColumn firstNameCol = new TableColumn();
        firstNameCol.setText("Pesel");
        firstNameCol.setCellValueFactory(new PropertyValueFactory("PatientID"));
        TableColumn lastNameCol = new TableColumn();
        lastNameCol.setText("Last");
        lastNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));
        TableColumn emailCol = new TableColumn();
        emailCol.setText("Lastname");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory("email"));
       // TableView tableView = new TableView();
        testTable.setItems(data);
        testTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

    }

   /* public  ArrayList<Test> getAllCustomer() throws ClassNotFoundException, SQLException {




        //testTable.setItems();

        Connection conn = ConnectionUtil.conDB();
        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT * FROM HospitalDB.Test ";
        ResultSet rst;
        int l = 0;
        rst = stm.executeQuery(sql);
        ArrayList<Test> testList = new ArrayList<>();
        while (rst.next()) {
            Test test = new Test(rst.getString("ID"), rst.getString("Name"), rst.getString("Testcol"));
            testList.add(test);
            l++;

        }
        for (int i = 0; i >= l; i++) {
            System.out.println(testList.get(i));
        }
        //testTable.setItems();
        return testList;

    }

    */




}
