package epcentral.view;

import epcentral.Utility.IdGenerator;
import epcentral.data.DAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static epcentral.Utility.IdGenerator.generateEmployeeId;

public class EmployeeForm extends JFrame{
    private static Logger logger = LogManager.getLogger(EmployeeForm.class);
    private JTextField nameField;
    private JTextField ageField;
    private DAO dao;

    public EmployeeForm(String env) {
        dao = new DAO(env);
        setTitle("Employee Form");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Employee Name:");
        nameField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField(5);

        JButton extractButton = new JButton("Extract all values");
        extractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Extract Button Clicked");

            }
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.info("Submit Button Clicked");
                String employeeName = getEmployeeName();
                Integer employeeAge = getEmployeeAge();
                Integer employeeId = generateEmployeeId();
                dao.insertIntoTable(employeeId,employeeName,employeeAge);
                nameField.setText("");
                ageField.setText("");
            }
        });

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(submitButton);
        add(extractButton);

        logger.info("Employee Form started..");
    }

    public String getEmployeeName() {
        logger.info("Employee Name passed {}",nameField.getText());
        return nameField.getText();
    }

    public int getEmployeeAge() {
        try{
            logger.info("Employee Age Passed:: {}",ageField.getText());
            return Integer.parseInt(ageField.getText());
        } catch (NumberFormatException e) {
            logger.error(e.getMessage());
            logger.info("As value passed in age field is NAN, default value passed as 0");
        }
        return 0;
    }
}
