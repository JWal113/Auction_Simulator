public class Item {
    private String name;
    private int startingPrice;
    private double endingPrice;

    public double getEndingPrice() {
        return endingPrice;
    }
    public void setEndingPrice(double endingPrice) {
        this.endingPrice = endingPrice;
    }

    public String getName() {
        return name;
    }
    public int getStartingPrice() {
        return startingPrice;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
    }
    public Item(){
        this.name = "";
        this.startingPrice = 0;
    }
    public Item(String name, int startingPrice) {
        this.name = name;
        this.startingPrice = startingPrice;
    }
    public String toString(){
        String output = "";
        output += name + ", Starting Price: $" + startingPrice;
        return output;
    }
}
