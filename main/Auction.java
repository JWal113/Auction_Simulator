import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class Auction {
    protected LinkedList<Buyer> buyers;
    protected LinkedList<Seller> sellers;
    protected LinkedList<Item> items;
    protected HashMap<Item, Buyer> buyerWhoWonItem;

    protected LinkedList<Buyer> copyBuyers(LinkedList<Buyer> buyers) {
        return new LinkedList<>(buyers);
    }
    public LinkedList<Buyer> getBuyers() {
        return buyers;
    }
    public LinkedList<Seller> getSellers() {
        return sellers;
    }

    public void setBuyers(LinkedList<Buyer> buyers) {
        this.buyers = buyers;
    }

    public void setSellers(LinkedList<Seller> sellers) {
        this.sellers = sellers;
    }

    public Auction(String sellerFile, String buyerFile) throws Exception {
        items = new LinkedList<>();
        buyerWhoWonItem = new HashMap<>();
        readSellers(sellerFile);
        readBuyers(buyerFile);
        for (Seller seller: sellers) {
            items.addAll(seller.getItems());
        }
    }
    abstract void runAuction();

    protected void readSellers(String sellerFile) throws Exception {
        sellers = new LinkedList<Seller>();
        try (Scanner scanner = new Scanner(new File(sellerFile))) {
            scanner.nextLine();
            while (scanner.hasNext()) {
                String sellerLine = scanner.nextLine();
                Seller seller = new Seller(sellerLine.split("\t"));
                sellers.add(seller);
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Seller file not found at " + sellerFile);
        } catch (Exception e) {
            System.err.println("An error occurred while reading the seller file: " + e.getMessage());
        }
    }
    protected void readBuyers(String buyerFile) throws Exception {
        buyers = new LinkedList<Buyer>();
        try (Scanner scanner = new Scanner(new File(buyerFile))) {
            scanner.nextLine();
            int buyerCardNum = 0;
            while (scanner.hasNext()) {
                buyerCardNum += 1;
                String buyerLine = scanner.nextLine();
                Buyer buyer = new Buyer(buyerLine.split("\t"), buyerCardNum);
                buyers.add(buyer);
            }
        } catch (FileNotFoundException e) {
            throw new Exception("Buyer file not found");
        } catch (Exception e) {
            throw new Exception("Something went wrong with reading the buyer file");
        }
    }
    protected void printFinalResults(){
        System.out.println("\nFINAL RESULTS:");
        for (Item item : items) {
            System.out.println(buyerWhoWonItem.get(item).getName() + " bought " + item.getName() + " for $" + Math.round(item.getEndingPrice()));
        }
    }
}
