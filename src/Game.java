public class Game {
    public static void main(String[] args) {

        // insert scanner and ask for both general's names
        General general1 = new General("Krysia");

        // while loop with options to choose action by generals

        //tests
        Soldier soldier1 = new Soldier(2, 4);
        Soldier soldier2 = new Soldier(1, 3);
        Soldier soldier3 = new Soldier(4, 5);
        general1.soldiers.add(soldier1);
        general1.soldiers.add(soldier2);
        general1.soldiers.add(soldier3);
        general1.printSoldiers();
        general1.maneuver();
        general1.printSoldiers();

    }
}