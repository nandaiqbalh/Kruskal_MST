package com.nandaiqbalh;

import java.util.Arrays;

class Graph {

    // Kelas Untuk menghandle edges graph
    class Edge implements Comparable<Edge> {
        int src, dest, weight;

        // Fungsi pembanding digunakan untuk mengurutkan edges berdasarkan weight-nya
        public int compareTo(Edge compareEdge) {
            return this.weight - compareEdge.weight;
        }
    }

    // Kelas untuk menghandle subset union-find
    class subset {
        int parent, rank;
    }

    int V, E; // V = jumlah vertexs, E = jumlah edges
    Edge edge[]; // collection of all edges

    // Membuat graph dengan V vertexs dan E edges
    Graph(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    // Fungsi utility untuk menemukan kumpulan set elemen i (uses path compression technique)
    int find(subset subsets[], int i) {
        // Menemukan root dan jadikan root sebagai parent dari i
        // (path compression)
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);

        return subsets[i].parent;
    }

    // Sebuah fungsi yang melakukan penyatuan dua kumpulan x dan y (menggunakan penyatuan menurut rank)
    void Union(subset subsets[], int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        // Lampirkan rank tree yang lebih kecil di bawah root pohon dengan rank tinggi (Union by Rank)
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

            //Jika peringkatnya sama, maka jadikan salah satu sebagai root dan increment rank satu kali
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    // Fungsi utama untuk membangun MST menggunakan algoritma Kruskal
    void KruskalMST() {
        // Ini akan menyimpan MST yang dihasilkan
        Edge result[] = new Edge[V];

        // Variabel indeks, digunakan untuk result []
        int e = 0;

        // Variabel indeks, digunakan untuk edges yang diurutkan
        int i = 0;
        for (i = 0; i < V; ++i)
            result[i] = new Edge();

        // Langkah 1: Urutkan semua egdes dalam non-penurunan
        // urutan beratnya. Jika kita tidak diijinkan
        // mengubah grafik yang diberikan, kita dapat membuat salinan array edges
        Arrays.sort(edge);

        // Alokasikan memori untuk membuat subset V.
        subset subsets[] = new subset[V];
        for (i = 0; i < V; ++i)
            subsets[i] = new subset();

        // Buat subset V dengan elemen tunggal
        for (int v = 0; v < V; ++v) {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        i = 0; // Index used to pick next edge

        // Jumlah edge yang akan diambil sama dengan V-1
        while (e < V - 1) {
            // Step 2: Pilih edge terkecil. Dan increment-kan
            // indeks untuk iterasi berikutnya
            Edge next_edge = new Edge();
            next_edge = edge[i++];

            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);

            // Jika memasukkan edge ini tidak menyebabkan siklus,
            // sertakan dalam hasil dan tingkatkan indeks hasil untuk tepi berikutnya
            if (x != y) {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
            // Else buang next_edge
        }

        // cetak isi result[] untuk menamppilkan
        // MST yang dibangun
        System.out.println("\nBerikut adalah edges dalam MST yang dibangun");
        System.out.println("SRC -- DEST == WEIGHT");
        int minimumCost = 0;
        for (i = 0; i < e; ++i) {
            System.out.println(" " + result[i].src + "  --   " + result[i].dest + "   ==  " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }
}
