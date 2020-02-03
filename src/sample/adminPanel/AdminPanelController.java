package sample.adminPanel;

import animatefx.animation.Bounce;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.SplitPane;
import java.awt.*;
import java.awt.event.ActionEvent;
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


    @FXML
    private void handleClicks(ActionEvent event){
        if(event.getSource() == button1){
           // new FadeIn(button1).play();
        }
}

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

}
