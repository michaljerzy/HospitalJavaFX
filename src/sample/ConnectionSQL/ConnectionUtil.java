package sample.ConnectionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    Connection conn = null;

    public ConnectionUtil() {

    }

    public static Connection conDB(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://srv03.mikr.us:20357/HospitalDB","michu","Mic0804732");
            return con;
        } catch (SQLException | ClassNotFoundException var1) {
            System.err.println("ConnectionUtil : " + var1.getMessage());
            return null;
        }
    }
}
