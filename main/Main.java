public class Main {
    public static void main(String[] args) throws Exception {
        String buyerFile = "buyers.txt";
        String sellerFile = "sellers.txt";
        try {
            Auction classicAuction = new ClassicAuction(sellerFile, buyerFile);
            classicAuction.runAuction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Auction blindAuction = new BlindAuction(sellerFile, buyerFile);
            blindAuction.runAuction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
