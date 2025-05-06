package epcentral.view;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewRecords {
     public static void viewRecord(ResultSet resultSet) throws SQLException {
         System.out.println("-|----------------------------|-");
         while(resultSet.next()){
             System.out.println("ID "+resultSet.getInt(1));
             System.out.println("Name "+resultSet.getString(2));
             System.out.println("Age "+resultSet.getInt(3));
         }
     }
}
