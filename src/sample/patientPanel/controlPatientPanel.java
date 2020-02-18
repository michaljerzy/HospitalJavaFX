package sample.patientPanel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controlPatientPanel implements Initializable {

    double x = 0, y = 0;

    @FXML
    private SplitPane parent;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
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
}
