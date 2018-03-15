import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Created by Mitet0 on 3/16/2018
 */
public class Runner {

    public static void main(String[] args) {
        System.out.println("WELCOME TO OUR OS");
        Scanner scanner = new Scanner(System.in);
        ProcessorManager manager = new ProcessorManager();
        String command = scanner.nextLine();
        while (true) {
            manager.executeCommand(command);
            command = scanner.nextLine();
        }
    }



}
