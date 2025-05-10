package epcentral;

import epcentral.view.EmployeeForm;

import javax.swing.*;
import java.sql.SQLException;

import static epcentral.Utility.ReadProperties.getEnvironmentName;

public class Main {
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            EmployeeForm form = new EmployeeForm(getEnvironmentName(args[0]));
            form.setVisible(true);
        });
    }
}
