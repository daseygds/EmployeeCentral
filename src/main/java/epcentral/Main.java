package epcentral;

import epcentral.Utility.ReadProperties;
import epcentral.data.DAO;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        DAO dao = new DAO(ReadProperties.getEnvironmentName(args[0]));
        dao.insertIntoTable(7,"bb",14);

    }
}
