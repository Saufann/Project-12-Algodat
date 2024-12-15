public class Node {
    String kurir, barang;
    Node prev, next;

    Node(String kurir, String barang){
        this.kurir = kurir;
        this.barang = barang;
        this.prev = next = null;
    }
}