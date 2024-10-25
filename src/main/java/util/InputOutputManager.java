package util;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputOutputManager {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static String getUserInput(String prompt) throws Exception {
        if (prompt != null && !prompt.isEmpty()) {
            System.out.print(prompt);
        }
        return br.readLine().trim();
    }
}
