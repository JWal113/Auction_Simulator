public class BlindAuction extends Auction {
    public BlindAuction(String sellerFile, String buyerFile) throws Exception {
        super(sellerFile, buyerFile);
    }
    private double highestBid;
    private double currentBid;
    private Buyer winner;

    public void runAuction(){
      int count = 1;
      System.out.println("\nBlind Auction starting...");
      System.out.println("Auction Items: ");
      for (Item item : items) {
          System.out.print(item.getName() + ", ");
      }
      System.out.println();
      for (Item item: items) {
          System.out.println("\n___________________________________________\nITEM " + count + ": " + item + "\n");
          round(item);
          count += 1;
      }
      System.out.println();
      printFinalResults();
      System.out.println("\nBlind Auction ending...");
    }

    public void round(Item item) {
        highestBid = 0;
        for (Buyer buyer: buyers){
            double amountBuyerWantsToBid = item.getStartingPrice() * buyer.getRiskFactor();
            if (amountBuyerWantsToBid <= buyer.getBudget()){
                currentBid = amountBuyerWantsToBid;
            } else {
                currentBid = buyer.getBudget();
            }
            System.out.println("BIDDER " + buyer.getCardNum() + ": " + buyer.getName() + " with budget $" + Math.round(buyer.getBudget()) + " with risk factor of " + Math.round(buyer.getRiskFactor())+ "X BIDS $" + Math.round(currentBid));
            if(currentBid > highestBid && currentBid <= buyer.getBudget()){
                System.out.println("NEW HIGHEST BID: $" + Math.round(currentBid) + " from " + buyer.getName());
                highestBid = currentBid;
                winner = buyer;
            }
        }

        if (highestBid > item.getStartingPrice() && winner != null) {
            System.out.print("The winner of the " + item.getName() + " is " + winner.getName() + " with a bid of $" + Math.round(highestBid) + " and a budget of $" + Math.round(winner.getBudget()));
            item.setEndingPrice(highestBid);
            buyerWhoWonItem.put(item, winner);
            winner.removeMoney(highestBid);
        }
        else{
            System.out.println("No winner.");
        }
    }
}