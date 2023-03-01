package controller.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaxId {
     public  static Integer createMaxId(Connection connection) throws SQLException {
        int MaxIDDAO = 0;
         List<Integer> ListID = new ArrayList<>();
        String sql = "SELECT Id FROM CAR";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ListID.add(rs.getInt("Id"));
                }
        }
        Integer Idmax = ListID.get(0);
        for (int i = 1; i< ListID.size(); i++) {
            if (Idmax < ListID.get(i)) {
                Idmax = ListID.get(i);
            }
        }
        MaxIDDAO = Idmax+1;
        System.out.println("MaxIDDAO = " + MaxIDDAO);
        return MaxIDDAO;
    }
}