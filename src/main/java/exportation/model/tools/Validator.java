package exportation.model.tools;

import java.util.regex.Pattern;

public class Validator {
    public static String nameValidator(String name, String message) throws Exception {
        if (Pattern.matches("^[a-zA-Z\\s]{3,30}$", name)) {
            return name;
        } else {
            throw new Exception(message);
        }
    }

    public static String phoneValidator(String phoneCode, String message) throws Exception {
        if (Pattern.matches("^\\d{4}|[\\+ + [0]{2} + \\d{2}]$", phoneCode)) {
            return phoneCode;
        } else {
            throw new Exception(message);
        }
    }
}

