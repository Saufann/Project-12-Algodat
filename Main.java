import java.util.Scanner;

class Barang {
    int id;
    String nama;
    String kategori;
    int stok;
    Barang next;

    public Barang(int id, String nama, String kategori, int stok) {
        this.id = id;
        this.nama = nama;
        this.kategori = kategori;
        this.stok = stok;
        this.next = null;
    }
}

class Banner {

    public static void printBanner(String title, String subtitle) {
        int width = 50; // Width of the banner

        String centeredTitle = centerText(title, width);
        String centeredSubtitle = centerText(subtitle, width);

        System.out.println("====================================================");
        System.out.println("=" + centeredTitle + "=");
        System.out.println("=" + centeredSubtitle + "=");
        System.out.println("====================================================");
    }

    private static String centerText(String text, int width) {
        int padding = (width - text.length()) / 2;
        String paddingSpace = " ".repeat(Math.max(0, padding));
        return paddingSpace + text + paddingSpace + (text.length() % 2 == 0 ? "" : " ");
    }
}

class BarangList {
    Barang head;

    public void tambahBarang(int id, String nama, String kategori, int stok) {
        Barang barang = new Barang(id, nama, kategori, stok);
        if (head == null) {
            head = barang;
        } else {
            Barang temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = barang;
        }
    }

    public void displayBarang() {
        Barang temp = head;
        if (temp == null) {
            System.out.println("Tidak ada barang.");
            return;
        }
        while (temp != null) {
            System.out.println("ID: " + temp.id + ", Nama: " + temp.nama + ", Kategori: " + temp.kategori + ", Stok: " + temp.stok);
            temp = temp.next;
        }
    }

    public Barang cariBarang(int id) {
        Barang temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
    public void sorting(String criteria) {
        if (head == null || head.next == null) {
            return;
        }

        boolean swapped;
        do {
            swapped = false;
            Barang current = head;
            while (current.next != null) {
                Barang nextBarang = current.next;
                boolean condition = false;

                switch (criteria.toLowerCase()) {
                    case "id":
                        condition = current.id > nextBarang.id;
                        break;
                    case "nama":
                        condition = current.nama.compareTo(nextBarang.nama) > 0;
                        break;
                    case "kategori":
                        condition = current.kategori.compareTo(nextBarang.kategori) > 0;
                        break;
                }

                if (condition) {
                    int tempId = current.id;
                    String tempNama = current.nama;
                    String tempKategori = current.kategori;
                    int tempStok = current.stok;

                    current.id = nextBarang.id;
                    current.nama = nextBarang.nama;
                    current.kategori = nextBarang.kategori;
                    current.stok = nextBarang.stok;

                    nextBarang.id = tempId;
                    nextBarang.nama = tempNama;
                    nextBarang.kategori = tempKategori;
                    nextBarang.stok = tempStok;

                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
}

class NodeStack {
    int id;
    NodeStack next;

    public NodeStack(int id) {
        this.id = id;
        this.next = null;
    }
}

class Stack {
    NodeStack top;

    public void push(int id) {
        NodeStack newNode = new NodeStack(id);
        newNode.next = top;
        top = newNode;
    }

    public int pop() {
        if (top == null) {
            return -1; 
        }
        int id = top.id;
        top = top.next;
        return id;
    }

    public void display(BarangList barangList) {
        NodeStack temp = top;
        if (temp == null) {
            System.out.println("Keranjang ini kosong.");
            return;
        }
        while (temp != null) {
            Barang barang = barangList.cariBarang(temp.id);
            if (barang != null) {
                System.out.println("ID: " + barang.id + ", Nama: " + barang.nama + ", Kategori: " + barang.kategori);
            }
            temp = temp.next;
        }
    }
}

class NodeQueue {
    String nama;
    Stack keranjang;
    NodeQueue next;

    public NodeQueue(String nama) {
        this.nama = nama;
        this.keranjang = new Stack();
        this.next = null;
    }
}

class Queue {
    NodeQueue front, rear;

    public void enqueue(String nama) {
        NodeQueue newNode = new NodeQueue(nama);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    public NodeQueue dequeue() {
        if (front == null) return null;
        NodeQueue temp = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return temp;
    }

    public NodeQueue cariPembeli(String nama) {
        NodeQueue temp = front;
        while (temp != null) {
            if (temp.nama.equalsIgnoreCase(nama)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}

class TreeNode {
    String name;
    TreeNode firstChild;
    TreeNode nextSibling;

    public TreeNode(String name) {
        this.name = name;
        this.firstChild = null;
        this.nextSibling = null;
    }

    public void addChild(String childName) {
        TreeNode child = new TreeNode(childName);
        if (this.firstChild == null) {
            this.firstChild = child;
        } else {
            TreeNode sibling = this.firstChild;
            while (sibling.nextSibling != null) {
                sibling = sibling.nextSibling;
            }
            sibling.nextSibling = child;
        }
    }

    public void display(String prefix) {
        System.out.println(prefix + "├── " + name);
        if (firstChild != null) {
            firstChild.display(prefix + "│   ");
        }
        if (nextSibling != null) {
            nextSibling.display(prefix);
        }
    }
}

class Tree {
    TreeNode root;

    public Tree(String rootName) {
        this.root = new TreeNode(rootName);
    }

    public void display() {
        if (root != null) {
            System.out.println(root.name);
            if (root.firstChild != null) {
                root.firstChild.display("");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BarangList barangList = new BarangList();
        Queue queue = new Queue();
        Tree tree = new Tree("Produk");
        Banner banner = new Banner(); 

        barangList.tambahBarang(1, "Apel", "Buah", 10);
        barangList.tambahBarang(2, "Mangga", "Buah", 12);
        barangList.tambahBarang(3, "Peach", "Buah", 8);
        barangList.tambahBarang(4, "Chips", "Snack", 20);
        barangList.tambahBarang(5, "Cookie", "Snack", 15);
        barangList.tambahBarang(6, "Ice Cream", "Snack", 10);
        barangList.tambahBarang(7, "Advan Workplus", "Laptop", 5);
        barangList.tambahBarang(8, "Asus Vivobook", "Laptop", 7);
        barangList.tambahBarang(9, "Asus TUF", "Laptop", 4);
        barangList.tambahBarang(10, "Lenovo LOQ", "Laptop", 6);
        barangList.tambahBarang(11, "Lenovo Legion", "Laptop", 3);
        barangList.tambahBarang(12, "Macbook Air", "Laptop", 2);
        barangList.tambahBarang(13, "Vivo Y95", "Smartphone", 10);
        barangList.tambahBarang(14, "POCO M5", "Smartphone", 8);
        barangList.tambahBarang(15, "Samsung S24", "Smartphone", 5);
        barangList.tambahBarang(16, "Iphone 15", "Smartphone", 3);
        barangList.tambahBarang(17, "Itel P53", "Smartphone", 6);
        barangList.tambahBarang(18, "Hush Puppy", "Pakaian Pria", 10);
        barangList.tambahBarang(19, "Cotton Well", "Pakaian Pria", 12);
        barangList.tambahBarang(20, "Celciusmen", "Pakaian Pria", 8);
        barangList.tambahBarang(21, "Adidas", "Pakaian Pria", 15);
        barangList.tambahBarang(22, "Nade", "Pakaian Pria", 10);
        barangList.tambahBarang(23, "Three Second", "Pakaian Pria", 12);
        barangList.tambahBarang(24, "Nike", "Pakaian Pria", 8);
        barangList.tambahBarang(25, "Roxy", "Pakaian Perempuan", 14);
        barangList.tambahBarang(26, "Elle", "Pakaian Perempuan", 16);
        barangList.tambahBarang(27, "Mango", "Pakaian Perempuan", 20);
        barangList.tambahBarang(28, "Ivy Park", "Pakaian Perempuan", 18);
        barangList.tambahBarang(29, "Melissa", "Pakaian Perempuan", 15);
        barangList.tambahBarang(30, "Obermain", "Pakaian Perempuan", 10);
        barangList.tambahBarang(31, "Clarins Men", "Kosmetik Pria", 10);
        barangList.tambahBarang(32, "Kahf Oil and Acne Face Wash", "Kosmetik Pria", 12);
        barangList.tambahBarang(33, "Bromen Brightening", "Kosmetik Pria", 8);
        barangList.tambahBarang(34, "Kelly Lemon Soap", "Kosmetik Pria", 15);
        barangList.tambahBarang(35, "Mattifying Men", "Kosmetik Pria", 10);
        barangList.tambahBarang(36, "Chanel Le Teint", "Kosmetik Perempuan", 12);
        barangList.tambahBarang(37, "Hni Hpai Original", "Kosmetik Perempuan", 8);
        barangList.tambahBarang(38, "Rare Beauty", "Kosmetik Perempuan", 10);
        barangList.tambahBarang(39, "YOU NutriWear", "Kosmetik Perempuan", 14);
        barangList.tambahBarang(40, "Danessa Myrique", "Kosmetik Perempuan", 11);

        TreeNode makanan = new TreeNode("Makanan");
        TreeNode buah = new TreeNode("Buah");
        TreeNode snack = new TreeNode("Snack");
        TreeNode elektronik = new TreeNode("Elektronik");
        TreeNode laptop = new TreeNode("Laptop");
        TreeNode smartphone = new TreeNode("Smartphone");
        TreeNode pakaian = new TreeNode("Pakaian");
        TreeNode pakaianPria = new TreeNode("Pakaian Pria");
        TreeNode pakaianPerempuan = new TreeNode("Pakaian Perempuan");
        TreeNode kosmetik = new TreeNode("Kosmetik");
        TreeNode kosmetikPria = new TreeNode("Kosmetik Pria");
        TreeNode kosmetikPerempuan = new TreeNode("Kosmetik Perempuan");

        tree.root.addChild("Makanan");
        tree.root.addChild("Elektronik");
        tree.root.addChild("Pakaian");
        tree.root.addChild("Kosmetik");

        tree.root.firstChild.addChild("Buah");
        tree.root.firstChild.addChild("Snack");
        tree.root.firstChild.nextSibling.addChild("Laptop");
        tree.root.firstChild.nextSibling.addChild("Smartphone");
        tree.root.firstChild.nextSibling.nextSibling.addChild("Pakaian Pria");
        tree.root.firstChild.nextSibling.nextSibling.addChild("Pakaian Perempuan");
        tree.root.firstChild.nextSibling.nextSibling.nextSibling.addChild("Kosmetik Pria");
        tree.root.firstChild.nextSibling.nextSibling.nextSibling.addChild("Kosmetik Perempuan");

        buah.addChild("Apel");
        buah.addChild("Mangga");
        buah.addChild("Peach");

        snack.addChild("Chips");
        snack.addChild("Cookie");
        snack.addChild("Ice Cream");

        laptop.addChild("Advan Workplus");
        laptop.addChild("Asus Vivobook");
        laptop.addChild("Asus TUF");
        laptop.addChild("Lenovo LOQ");
        laptop.addChild("Lenovo Legion");
        laptop.addChild("Macbook Air");

        smartphone.addChild("Vivo Y95");
        smartphone.addChild("POCO M5");
        smartphone.addChild("Samsung S24");
        smartphone.addChild("Iphone 15");
        smartphone.addChild("Itel P53");

        pakaianPria.addChild("Hush Puppy");
        pakaianPria.addChild("Cotton Well");
        pakaianPria.addChild("Celciusmen");
        pakaianPria.addChild("Adidas");
        pakaianPria.addChild("Nade");
        pakaianPria.addChild("Three Second");
        pakaianPria.addChild("Nike");

        pakaianPerempuan.addChild("Roxy");
        pakaianPerempuan.addChild("Elle");
        pakaianPerempuan.addChild("Mango");
        pakaianPerempuan.addChild("Ivy Park");
        pakaianPerempuan.addChild("Melissa");
        pakaianPerempuan.addChild("Obermain");

        kosmetikPria.addChild("Clarins Men");
        kosmetikPria.addChild("Kahf Oil and Acne Face Wash");
        kosmetikPria.addChild("Bromen Brightening");
        kosmetikPria.addChild("Kelly Lemon Soap");
        kosmetikPria.addChild("Mattifying Men");

        kosmetikPerempuan.addChild("Chanel Le Teint");
        kosmetikPerempuan.addChild("Hni Hpai Original");
        kosmetikPerempuan.addChild("Rare Beauty");
        kosmetikPerempuan.addChild("YOU NutriWear");
        kosmetikPerempuan.addChild("Danessa Myrique");

        tree.root.firstChild.firstChild = buah;
        tree.root.firstChild.firstChild.nextSibling = snack;
        tree.root.firstChild.nextSibling.firstChild = laptop;
        tree.root.firstChild.nextSibling.firstChild.nextSibling = smartphone;
        tree.root.firstChild.nextSibling.nextSibling.firstChild = pakaianPria;
        tree.root.firstChild.nextSibling.nextSibling.firstChild.nextSibling = pakaianPerempuan;
        tree.root.firstChild.nextSibling.nextSibling.nextSibling.firstChild = kosmetikPria;
        tree.root.firstChild.nextSibling.nextSibling.nextSibling.firstChild.nextSibling = kosmetikPerempuan;

        while (true) {
            Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
            System.out.println("\nPilih opsi:");
            System.out.println("1. Tambahkan Pembeli");
            System.out.println("2. Tambahkan Barang ke Keranjang");
            System.out.println("3. Kembalikan Barang dari Keranjang");
            System.out.println("4. Tampilkan Barang yang Ada di Stok");
            System.out.println("5. Tampilkan Keranjang Pembeli");            
            System.out.println("6. Cari Barang");
            System.out.println("7. Urutkan Barang");
            System.out.println("8. Tampilkan Barang Berdasarkan Kategori");
            System.out.println("9. Selesaikan Pesanan");           
            System.out.println("10. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.print("Masukkan Nama Pembeli: ");
                    String nama = scanner.nextLine();
                    queue.enqueue(nama);
                    break;
                case 2:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.print("Masukkan Nama Pembeli: ");
                    String pembeli = scanner.nextLine();
                    NodeQueue pembeliNode = queue.cariPembeli(pembeli);
                    if (pembeliNode != null) {
                        System.out.print("Masukkan ID Barang: ");
                        int idBarang = scanner.nextInt();
                        Barang barang = barangList.cariBarang(idBarang);
                        if (barang != null && barang.stok > 0) {
                            pembeliNode.keranjang.push(idBarang);
                            barang.stok--;
                            System.out.println("Barang " + barang.nama + " ditambahkan ke keranjang.");
                        } else {
                            System.out.println("Barang tidak tersedia atau stok habis.");
                        }
                    } else {
                        System.out.println("Pembeli tidak ditemukan.");
                    }
                    break;
                case 3:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.print("Masukkan Nama Pembeli untuk mengembalikan barang: ");
                    String pembeliKembali = scanner.nextLine();
                    NodeQueue pembeliKembaliNode = queue.cariPembeli(pembeliKembali);
                    if (pembeliKembaliNode != null) {
                        int idBarangDikembalikan = pembeliKembaliNode.keranjang.pop();
                        if (idBarangDikembalikan != -1) {
                            Barang barangDikembalikan = barangList.cariBarang(idBarangDikembalikan);
                            if (barangDikembalikan != null) {
                                barangDikembalikan.stok++;
                                System.out.println("Barang " + barangDikembalikan.nama + " telah dikembalikan ke stok.");
                            } else {
                                System.out.println("Barang tidak ditemukan.");
                            }
                        } else {
                            System.out.println("Keranjang kosong, tidak ada barang untuk dikembalikan.");
                        }
                    } else {
                        System.out.println("Pembeli tidak ditemukan.");
                    }
                    break;
                case 4:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.println("Barang yang Ada di Stok:");
                    barangList.displayBarang();
                    break;
                case 5:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.print("Masukkan Nama Pembeli untuk melihat keranjang: ");
                    String pembeliKeranjang = scanner.nextLine();
                    NodeQueue pembeliKeranjangNode = queue.cariPembeli(pembeliKeranjang);
                    if (pembeliKeranjangNode != null) {
                        pembeliKeranjangNode.keranjang.display(barangList);
                    } else {
                        System.out.println("Pembeli tidak ditemukan.");
                    }
                    break;
                case 6:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.print("Masukkan ID Barang yang dicari: ");
                    int idCari = scanner.nextInt();
                    Barang barangCari = barangList.cariBarang(idCari);
                    if (barangCari != null) {
                        System.out.println("Barang ditemukan: ID: " + barangCari.id + ", Nama: " + barangCari.nama + ", Kategori: " + barangCari.kategori + ", Stok: " + barangCari.stok);
                    } else {
                        System.out.println("Barang tidak ditemukan.");
                    }
                    break;
                case 7:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.print("Pilih kriteria sorting (id, nama, kategori): ");
                    String kriteria = scanner.nextLine();
                    barangList.sorting(kriteria);
                    System.out.println("Barang berhasil diurutkan.");
                    break;
                case 8:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    tree.display();
                    break;
                case 9:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    NodeQueue selesaiPesanan = queue.dequeue();
                    if (selesaiPesanan != null) {
                        System.out.println("Pembeli " + selesaiPesanan.nama + " telah selesai berbelanja.");
                    } else {
                        System.out.println("Antrian kosong.");
                    }
                    break;
                case 10:
                    Banner.printBanner("GroceryGo", "Manajemen Daftar Belanja");
                    System.out.println("Terima kasih!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
                }
            }
        }
}