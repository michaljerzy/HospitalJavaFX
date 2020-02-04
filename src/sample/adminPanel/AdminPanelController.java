package sample.adminPanel;

import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.SplitPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {

    double x = 0, y = 0;

    @FXML
    private SplitPane parent;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
    }

    //class for the mouse touching panel and setup Opacity
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


    public void handleClicks(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() == button1){
            // new FadeIn(button1).play();

            try {
                Node node = (Node)mouseEvent.getSource();
                Stage stage = (Stage)node.getScene().getWindow();
                stage.close();
                Scene scene = new Scene((Parent)FXMLLoader.load(this.getClass().getResource("/sample/adminPanel/adminPanel.fxml")));
                //stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }catch (IOException var5){
                System.err.println(var5.getMessage());
            }
        }
        if(mouseEvent.getSource() == button2){
            // new FadeIn(button1).play();

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
    }
}
