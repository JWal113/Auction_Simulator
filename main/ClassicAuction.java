import java.util.LinkedList;

public class ClassicAuction extends Auction {

    public ClassicAuction(String sellerFile, String buyerFile) throws Exception {
        super(sellerFile, buyerFile);
    }
    private double highestBid;
    private Buyer winner;

    private void round(Item item) {
        LinkedList<Buyer> roundBuyers = copyBuyers(buyers);
        double price = item.getStartingPrice();
        int bidderNum = 0;
        double currentBid = price;
        while (roundBuyers.size() > 1) {
            Buyer bidder = roundBuyers.get(bidderNum);
            if (currentBid <= bidder.getBudget() && currentBid <= price * bidder.getRiskFactor() && currentBid >= price) {
                System.out.println("BIDDER " + bidder.getCardNum() + " BIDS $" + Math.round(currentBid) + "! Info: {" + bidder + "}");
                highestBid = currentBid;
                winner = bidder;
                currentBid = currentBid + (currentBid * .25);
                System.out.println("\n(NEW ITEM PRICE: $" + Math.round(currentBid) + ")");
            } else {
                roundBuyers.remove(bidder);
                bidderNum = bidderNum % roundBuyers.size();
                System.out.println(bidder.getName() + " (BIDDER " + bidder.getCardNum() + ") drops out of bidding for this item! Info: {" + bidder + "}");
                continue;
            }
            bidderNum = (bidderNum + 1) % roundBuyers.size();
        }
        System.out.println("\nThe winner of the " + item.getName() + " is " + winner.getName().toUpperCase() + " with a bid of $" + Math.round(highestBid) + " and a budget of $" + Math.round(winner.getBudget()));
        winner.removeMoney(highestBid);
        item.setEndingPrice(highestBid);
        buyerWhoWonItem.put(item, winner);
        System.out.println(winner.getName() + " now has $" + Math.round(winner.getBudget()));
    }
    public void runAuction() {
        int count = 1;
        System.out.println("\nClassic Auction starting...");
        System.out.println("Auction Items: ");
        for (Item item : items) {
            System.out.print(item.getName() + ", ");
        }
        System.out.println();
        for (Item item: items) {
            System.out.println("___________________________________________\nITEM " + count + ": " + item + "\n");
            round(item);
            count += 1;
        }
        printFinalResults();
        System.out.println("\nClassic Auction ending...");
    }
}
