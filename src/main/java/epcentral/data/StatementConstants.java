package epcentral.data;

public class StatementConstants {
    public static String CREATE_TABLE = "CREATE TABLE employees (\n" +
            "    employee_id INT PRIMARY KEY,\n" +
            "    name NVARCHAR(100) NOT NULL,\n" +
            "    age INT NOT NULL\n" +
            ");";
    public static String INSERT_RECORDS = "INSERT INTO employees (employee_id, name, age) VALUES (?, ?, ?)";
    public static String VIEW_ALL_RECORDS = "SELECT * from employees";
}
