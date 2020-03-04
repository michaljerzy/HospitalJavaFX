package sample.Testy;

import sample.ConnectionSQL.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class testMain {
/*
    public static void main(String[] args) {
        try {
            getAllCustomer();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Person> getAllCustomer() throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionUtil.conDB();
        Statement stm;
        stm = conn.createStatement();
        String sql = "SELECT * FROM HospitalDB.Test WHERE ID = 1";
        ResultSet rst;
        int l = 0;
        rst = stm.executeQuery(sql);
        ArrayList<Person> testList = new ArrayList<>();
        while (rst.next()){
            Person person = new Person(rst.getString("ID"),rst.getString("Name"), rst.getString("Testcol"));
            testList.add(person);
            l++;
        }
        for (int i = 0; i < l; i++){
            System.out.println(testList.get(i));
        }
        return testList;
    }

 */
}
