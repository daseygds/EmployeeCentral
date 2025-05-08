package epcentral.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeForm extends JFrame{
    private static Logger logger = LogManager.getLogger(EmployeeForm.class);
    private JTextField nameField;
    private JTextField ageField;

    public EmployeeForm() {
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
                getEmployeeName();
                getEmployeeAge();
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
        }finally {
            return 0;
        }
    }
}
