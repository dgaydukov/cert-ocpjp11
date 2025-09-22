import java.io.Console;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class ConsoleApp{
    public static void main(String[] arr) {
        Console console = System.console();
        if (console == null){
            throw new RuntimeException("console is not available");
        }
        System.out.print("enter your username: ");
        String username = console.readLine();
        System.out.print("enter your password: ");
        char[] password = console.readPassword();
        System.out.println("Your data => " + username + "/" + Arrays.toString(password));

        Scanner scanner = new Scanner(System.in);
        System.out.print("enter your firstName: ");
        String firstName = scanner.nextLine();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String lastName;
        System.out.print("enter your lastName: ");
        try {
            lastName = reader.readLine();
        } catch (IOException ex){
            throw new RuntimeException(ex);
        }

        System.out.println("Your name => " + firstName + " " + lastName);
    }
}