package sample.forOurClass;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class makeDragable {

    private double x = 0, y = 0;

    @FXML
    private SplitPane parent;

    public makeDragable(double x, double y, SplitPane parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public SplitPane getParent() {
        return parent;
    }

    public void setParent(SplitPane parent) {
        this.parent = parent;
    }

    public void makeTake(){
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
