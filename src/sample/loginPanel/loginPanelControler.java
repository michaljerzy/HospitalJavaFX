package sample.loginPanel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import sample.ConnectionSQL.ConnectionUtil;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginPanelControler implements Initializable {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private AnchorPane parent;

    @FXML
    private ImageView picServerNotConnect;

    @FXML
    private ImageView picServerConnect;

    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    double x = 0, y = 0;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        makeDragable();
        if(this.con == null){
            this.picServerNotConnect.setVisible(true);
        }else
            this.picServerConnect.setVisible(true);
    }

    public loginPanelControler(){
        this.con = ConnectionUtil.conDB();
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

    public void singIn(MouseEvent mouseEvent) {
        logIn();
    }

    private boolean logIn(){
        String email = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        String sql = "SELECT * FROM Hospital_official.UserProgram WHERE Email = ? and Password = ?";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                return false;
            }else {
                System.out.println("Działa jak natura chciała ");
                return true;
            }
        }catch (SQLException var5){
            System.err.println(var5.getMessage());
            return false;
        }

    }
}
