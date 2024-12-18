public class NodeStack {
    String nama;
    double harga;
    String kategori;
    NodeStack next;

    public NodeStack(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.next = null;
    }
}