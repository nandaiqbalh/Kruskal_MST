package com.nandaiqbalh;

import java.lang.*;
public class Main{
    // Main-function
    public static void main(String[] args) {

        int V = 8; // Number of vertices in graph
        int E = 13; // Number of edges in graph

        // membuat objek graph dari kelas Graph
        Graph graph = new Graph(V, E);

        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 3;

        graph.edge[1].src = 0;
        graph.edge[1].dest = 5;
        graph.edge[1].weight = 34;

        graph.edge[2].src = 0;
        graph.edge[2].dest = 7;
        graph.edge[2].weight = 80;

        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 1;

        graph.edge[4].src = 1;
        graph.edge[4].dest = 7;
        graph.edge[4].weight = 68;

        graph.edge[5].src = 3;
        graph.edge[5].dest = 7;
        graph.edge[5].weight = 39;

        graph.edge[6].src = 4;
        graph.edge[6].dest = 7;
        graph.edge[6].weight = 14;

        graph.edge[7].src = 4;
        graph.edge[7].dest = 6;
        graph.edge[7].weight = 68;

        graph.edge[8].src = 4;
        graph.edge[8].dest = 2;
        graph.edge[8].weight = 23;

        graph.edge[9].src = 2;
        graph.edge[9].dest = 6;
        graph.edge[9].weight = 12;

        graph.edge[10].src = 6;
        graph.edge[10].dest = 7;
        graph.edge[10].weight = 99;

        graph.edge[11].src = 7;
        graph.edge[11].dest = 5;
        graph.edge[11].weight = 25;

        graph.edge[12].src = 3;
        graph.edge[12].dest = 4;
        graph.edge[12].weight = 53;

        // Function call
        graph.KruskalMST();
    }
}