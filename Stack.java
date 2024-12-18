public class Stack {
    NodeStack top;
    int jumlahBarang = 0;
    String nama;

    public Stack(String nama) {
        this.top = null;
        this.nama = nama;
    }

    // Menambahkan barang ke stack
    public void push(String namaBarang, double harga, String kategori) {
        NodeStack newNode = new NodeStack(namaBarang, harga, kategori);
        newNode.next = top;
        top = newNode;
        jumlahBarang++;
    }

    // Menghapus barang paling atas dari stack (pop)
    public void pop() {
        if (top != null) {
            top = top.next;
            jumlahBarang--;
        } else {
            System.out.println("Stack kosong, tidak ada barang yang dapat dihapus.");
        }
    }
    public void displayStack() {
        NodeStack temp = top;
        System.out.printf(nama.toUpperCase());

        while (temp != null) {
            System.out.println("Nama Barang : " + temp.nama);
            System.out.println("Harga : " + temp.harga);
            System.out.println("Kategori : " + temp.kategori);
            temp = temp.next;
        }
        System.out.println();
    }
}