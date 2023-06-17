import java.util.Map;
import java.util.TreeMap;

public class Soldier {
    private int degree;
    private int experience;
    private int strength;

    public Soldier(int degree, int experience) {
        if (degree < 1 || degree > 4){
            throw new IllegalArgumentException("Degree can be 1, 2, 3 or 4, you entered " + degree);
        }
        this.degree = degree;
        if (experience > degree*5){
            throw new IllegalArgumentException("Maximum experience is 5*degree. For degree "
                    + rankTranslator(degree) + "it's " + 5*degree);
        }
        this.experience = experience;
        this.strength = degree*experience;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getStrength() {
        return strength;
    }

    private void setStrength(){
        this.strength = this.degree*this.experience;
    }

    public String rankTranslator(){
        Map<Integer, String> mapOfMilitaryRanks = new TreeMap<>();
        mapOfMilitaryRanks.put(1, "private");
        mapOfMilitaryRanks.put(2, "corporal");
        mapOfMilitaryRanks.put(3, "capitan");
        mapOfMilitaryRanks.put(4, "major");
        return mapOfMilitaryRanks.get(this.degree);
    }

    // I need to use it also without already having a soldier
    public String rankTranslator(int degree){
        Map<Integer, String> mapOfMilitaryRanks = new TreeMap<>();
        mapOfMilitaryRanks.put(1, "private");
        mapOfMilitaryRanks.put(2, "corporal");
        mapOfMilitaryRanks.put(3, "capitan");
        mapOfMilitaryRanks.put(4, "major");
        return mapOfMilitaryRanks.get(degree);
    }

    public void experienceUp(){
        this.experience += 1;
        setStrength();
        if (experience == 5*this.degree){
            if (this.degree == 4) {
                System.out.println("degree of this soldier cannot be higher.");
            }
            else {
                this.degree += 1;
                this.experience = 1;
                setStrength();
            }
        }
    }

    public void experienceDown(){
        this.experience -= 1;
        setStrength();
    }
}