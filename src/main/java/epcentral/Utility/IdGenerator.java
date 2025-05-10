package epcentral.Utility;

import java.util.Random;

public class IdGenerator {
    public static int generateEmployeeId() {
        Random random = new Random();
        // Generate a number between 10000 and 99999 (inclusive)
        return 10000 + random.nextInt(90000);
    }
}
