import java.util.*;

public class General {
    private String name;
    private int coins;
    List<Soldier> soldiers = new LinkedList<>();

    public General(String name) {
        this.name = name;
        // for start every General gets 80 coins.
        this.coins = 80;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void buySoldier(){
        int degree;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter degree of soldier you want to buy.\n 1 (private), 2 (corporal), 3 (capitan), 4(major)" +
                "\nThe cost of one soldier is 10*degree: ");
        degree = scanner.nextInt();
        while (degree > 4 || degree < 1){
            System.out.println("Degree can be 1, 2, 3 or 4. You typed " + degree);
            degree = scanner.nextInt();
        }
        while (degree*10 > this.coins){
            System.out.println("You don't have " + 10*degree + " coins. If you still want to buy a soldier enter "
            + "degree again. If you don't want to buy soldier enter 99.");
            degree = scanner.nextInt();
            if (degree == 99) break;
        }
        if (degree != 99) {
            this.soldiers.add(new Soldier(degree,1));
            this.coins = this.coins - degree*10;
        }
    }

    public void soldierRemover(Soldier soldier){
        if (soldier.getExperience() == 0){
            soldiers.remove(soldier);
        }
    }

    public void printSoldiers(){
        Iterator<Soldier> iterator = soldiers.iterator();
        int i = 1;
        while(iterator.hasNext()){
            Soldier soldier = iterator.next();
            System.out.println("Soldier " + i + " degree: " + soldier.rankTranslator(soldier.getDegree()) +
                    " experience: " + soldier.getExperience());
            i++;
        }
    }

    public void maneuver(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want all army to take part in maneuver? \nEnetr yes/no: ");
        String decision = scanner.nextLine();
        int cost = 0;
        if (decision.equals("yes")){
            Iterator<Soldier> iterator = soldiers.iterator();
            while(iterator.hasNext()){
                Soldier soldier = iterator.next();
                soldier.experienceUp();
                cost += soldier.getDegree();
            }
            this.coins = this.coins - cost;
        }
        else if (decision.equals("no")) {
            List<Integer> soldiersInManeuver = new LinkedList<>();
            System.out.println("This is the list of your soldiers.");
            this.printSoldiers();
            System.out.println("Enter numbers  of soldiers that you want to take" +
                    "part in maneuver (separated by spaces, first soldier has number 0): ");
            Scanner scan = new Scanner(System.in);
            String soldiersNumbersInLine = scan.nextLine();
            String[] soldiersNumbers = soldiersNumbersInLine.split(" ");
            for (String word : soldiersNumbers){
                int number = Integer.parseInt(word);
                soldiersInManeuver.add(number);
            }
            Iterator<Integer> iterator = soldiersInManeuver.iterator();
            while(iterator.hasNext()){
                Integer number = iterator.next();
                soldiers.get(number).experienceUp();
                cost += soldiers.get(number).getDegree();
            }
            this.coins = this.coins - cost;
        }
        else System.out.println("Invalid comand");
    }

    public void generalLose(General general){
        general.coins = (int) (general.coins - Math.ceil(0.1*general.coins));
        for (Soldier soldier : general.soldiers){
            soldier.experienceDown();
        }
    }
    public void generalWin(General general){
        for (Soldier soldier : general.soldiers){
            soldier.experienceUp();
        }
    }
    public void attack(General general){
        int sum1 = 0;
        int sum2 = 0;
        for (Soldier soldier : this.soldiers){
            sum1 += soldier.getStrength();
        }
        for (Soldier soldier : general.soldiers){
            sum2 += soldier.getStrength();
        }

        if (sum1 > sum2){
            generalLose(general);
            generalWin(this);
        } else if (sum1 < sum2){
            generalLose(this);
            generalWin(general);
        } else {
            Random rand = new Random();
            int random1 = rand.nextInt(0, this.soldiers.size());
            this.soldiers.remove(random1);
            int random2 = rand.nextInt(0, general.soldiers.size());
            this.soldiers.remove(random2);
        }

    }
    public void actions(General general, int action){
        if (action == 1){
            this.maneuver();
            this.printSoldiers();
        } else if (action == 2){
            this.attack(general);
            System.out.println("General's " + this.getName() + " soldiers");
            this.printSoldiers();
            System.out.println("General's " + general.getName() + " soldiers");
            general.printSoldiers();
        } else if (action == 3){
            this.buySoldier();
            this.printSoldiers();
        } else if (action == 4){
            System.out.println(this.getCoins());
        } else if (action == 5){
            System.out.println("You chose no action. Now is other's general turn.");
        } else if (action == 6){
            System.out.println("You chose to end the game.");
        } else System.out.println("Wrong action number.");
    }
}