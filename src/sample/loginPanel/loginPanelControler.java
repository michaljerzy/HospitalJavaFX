package sample.loginPanel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import sample.ConnectionSQL.ConnectionUtil;
import javafx.scene.control.TextField;
import javafx.scene.control.SplitPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginPanelControler implements Initializable {

    @FXML
    private Label txtError;

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
        if(logIn() == true){
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

    }

    private boolean logIn(){
        String email = this.txtUsername.getText();
        String password = this.txtPassword.getText();
        String sql = "SELECT * FROM HospitalDB.ProgramUser WHERE Email = ? and Password = ?";

        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()){
                txtError.setText("Enter Correct Email/Password");
                System.out.println("Wrong login/haslo");
                return false;
            }else {
                System.out.println("Successfull login ");
                return true;
            }
        }catch (SQLException var5){
            System.err.println(var5.getMessage());
            return false;
        }

    }

    public void exit(MouseEvent mouseEvent) {
        System.exit(0);
    }
}
