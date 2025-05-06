package epcentral;

import epcentral.Utility.ReadProperties;
import epcentral.data.DAO;

import java.sql.SQLException;

import static epcentral.view.ViewRecords.viewRecord;

public class Main {
    public static void main(String[] args) throws SQLException {
        DAO dao = new DAO(ReadProperties.getEnvironmentName(args[0]));
        viewRecord(dao.getAllRecords());
    }
}
