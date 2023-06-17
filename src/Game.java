import java.util.Scanner;

public class Game {
    public static void main(String[] args) {

        // At the beginning I create 2 generals and soldiers for them for start
        General general1 = new General("Christine");
        General general2 = new General("Pablo");

        Soldier soldier1 = new Soldier(2, 4);
        Soldier soldier2 = new Soldier(1, 3);
        Soldier soldier3 = new Soldier(4, 5);

        general1.soldiers.add(soldier1);
        general1.soldiers.add(soldier2);
        general1.soldiers.add(soldier3);

        general2.soldiers.add(soldier1);
        general2.soldiers.add(soldier2);
        general2.soldiers.add(soldier3);

        // while loop with options to choose action by generals
        int action1 = 0;
        int action2 = 0;
        while (action1 != 6 || action2 !=6) {
            menu();
            System.out.println("General " + general1.getName() + ", these are possible actions you ca do. " +
                    "Please eneter a number of action you want to perform: ");
            Scanner scanner = new Scanner(System.in);
            action1 = scanner.nextInt();
            general1.actions(general2, action1);
            System.out.println("End of your turn " + general1.getName());
            menu();
            System.out.println("General " + general2.getName() + ", these are possible actions you ca do. " +
                    "Please eneter a number of action you want to perform: ");
            action2 = scanner.nextInt();
            general2.actions(general1, action2);
            System.out.println("End of your turn " + general2.getName());
        }
    }
    public static void menu(){
        System.out.println("1 - maneuver");
        System.out.println("2 - attack");
        System.out.println("3 - buy soldier");
        System.out.println("4 - check coins");
        System.out.println("5 - end round");
        System.out.println("6 - end game");
    }
}