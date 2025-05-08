package epcentral;

import epcentral.view.EmployeeForm;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            EmployeeForm form = new EmployeeForm();
            form.setVisible(true);
        });
    }
}
