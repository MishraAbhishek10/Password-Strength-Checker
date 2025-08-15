import java.util.Scanner;

public class PasswordStrengthChecker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Take User ID
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();

        String password;
        boolean isStrong = false;

        // Keep asking until strong password is entered
        do {
            System.out.print("Enter Password: ");
            password = sc.nextLine();
            isStrong = checkPasswordStrength(userId, password);
        } while (!isStrong);

        sc.close();
    }

    public static boolean checkPasswordStrength(String userId, String password) {

        // 1. If password same as userId
        if (password.equals(userId)) {
            System.out.println("Very Weak Password ❌");
            System.out.println("Hint: Password cannot be same as User ID.\n");
            return false;
        }

        // 2. If length less than 8
        if (password.length() < 8) {
            System.out.println("Weak Password ❌");
            System.out.println("Hint: Try password length >= 8.\n");
            return false;
        }

        // 3. If half or more of the password matches userId
        String halfPassword = password.substring(0, password.length() / 2);
        if (userId.contains(halfPassword)) {
            System.out.println("Weak Password ❌");
            System.out.println("Hint: Half of password matches User ID.\n");
            return false;
        }

        // 4. Check strength based on complexity
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) hasUpper = true;
            else if (Character.isLowerCase(ch)) hasLower = true;
            else if (Character.isDigit(ch)) hasDigit = true;
            else if ("@_!#$%^&*()-+=<>?/".contains(String.valueOf(ch))) hasSpecial = true;
        }

        // Strength calculation
        int points = 0;
        if (hasUpper) points++;
        if (hasLower) points++;
        if (hasDigit) points++;
        if (hasSpecial) points++;

        if (points == 4) {
            System.out.println("Strong Password ✅");
            System.out.println("Hint: Good job! Your password is secure.\n");
            return true;
        } else if (points >= 2) {
            System.out.println("Medium Strength Password ⚠");
            System.out.println("Hint: Add missing character types (uppercase, lowercase, digit, special) for a strong password.\n");
            return false;
        } else {
            System.out.println("Weak Password ❌");
            System.out.println("Hint: Needs more variety (uppercase, lowercase, digit, special) for security.\n");
            return false;
        }
    }
}
