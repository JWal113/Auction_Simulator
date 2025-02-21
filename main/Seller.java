import java.util.LinkedList;
public class Seller extends Person{

    private LinkedList<Item> items;

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public Seller() {
        items = new LinkedList<>();
    }

    public Seller(String[] sellerInfo) {
        items = new LinkedList<>();
        setName(sellerInfo[0]);
        String[] sellerItems = new String[sellerInfo.length - 1];
        System.arraycopy(sellerInfo, 1, sellerItems, 0, sellerInfo.length - 1);
        for (String itemLine: sellerItems) {
            String[] itemInfo = itemLine.split(":");
            Item item = new Item(itemInfo[0], Integer.parseInt(itemInfo[1]));
            items.add(item);
        }
    }

    public String toString(){
        StringBuilder output = new StringBuilder();
        output.append("Seller Name: ").append(getName()).append("\nItem(s): ");
        for (Item item: items) {
            output.append(item);
        }
        return output.toString();
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}
