import java.util.Random;

public class Buyer extends Person {
    private double budget;
    private double riskFactor;
    private int cardNum;

    public int getCardNum(){
        return cardNum;
    }
    public Buyer(String[] buyerInfo, int cardNum) {
        this.cardNum = cardNum;
        setName(buyerInfo[0]);
        budget = Integer.parseInt(buyerInfo[1]);
        String riskLevel = buyerInfo[2];
        riskFactor = riskFactor(riskLevel);
    }

    public double riskFactor(String riskLevel) {
        Random rand = new Random();
        if (riskLevel.equals("HIGH")) {
            return 5.0 + rand.nextDouble() * (10.0 - 5.0);
        } else if (riskLevel.equals("MEDIUM")) {
            return 3.0 + rand.nextDouble() * (5.0 - 3.0);
        } else {
            return 1.0 + rand.nextDouble() * (3.0 - 1.0);
        }
    }

    public double getRiskFactor() {
        return riskFactor;
    }

    public String toString() {
        String output = "";
        output += "Bidder Card Number: " + cardNum + ", Name: " + getName() + ", Budget: $" + Math.round(budget) + ", Risk Factor: " + Math.round(riskFactor) + "X";
        return output;
    }

    public double getBudget() {
        return budget;
    }

    public void removeMoney(double amount) {
        budget = budget - amount;
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}
