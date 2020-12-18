package org.example;

//        We assume that as follows:
//
//        (1) Node fanout = 50
//
//        (2) The current number of elements in the node X is 51, and therefore we must split the node X.
//
//        (3) The MBR of the node X is represented by two points (0, 0) and (100, 100)
//
//        (4) Generate 51 elements that contained in the node X using random number generator.
//
//
//
//        After completing your program upload the followings:
//
//        (1) source code
//
//        (2) the image that shows the result of execution of the program.

// TODO: 18/12/2020 Quadratic-cost algorithm

// TODO: 18/12/2020 Linear-cost algorithm

import java.util.ArrayList;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {

        Root root = new Root(50,1,100);



        for (int i = 0; i <= 49; i++ )
        {
            double[] coords = {getRandomDoubleBetweenRange(0, 100), getRandomDoubleBetweenRange(0, 100)};
            double[] dimentions = {getRandomDoubleBetweenRange(0, 100 - coords[0]), getRandomDoubleBetweenRange(0, 100 - coords[1])};

            Node nodeTemp;
            nodeTemp  = new Node(coords,dimentions,true,root);

            root.insertNode(nodeTemp);

            System.out.println(root.nodes.get(i).getCoords()[0]);

        }

//        System.out.println(root.nodes.get(0)..getCoords()[0]);


    }

    public static class Root {
        private final int maxEntries;
        private final int minEntries;
        private final int numDims;
        private ArrayList<Node> nodes;

        public Root(int maxEntries, int minEntries, int numDims) {
            assert (minEntries <= (maxEntries / 2));
            this.numDims = numDims;
            this.maxEntries = maxEntries;
            this.minEntries = minEntries;
            nodes = new ArrayList<Node>();
        }

        public void insertNode (Node node)
        {
            nodes.add(node);
        }

    }

    private static class Node {
        public double[] getCoords() {
            return coords;
        }

        public double[] getDimensions() {
            return dimensions;
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public Root getParent() {
            return parent;
        }

        final double[] coords;
        final double[] dimensions;
        final ArrayList<Node> children;
        final boolean leaf;

        Root parent;

        private Node(double[] coords, double[] dimensions, boolean leaf, Root root) {
            this.coords = new double[coords.length];
            this.dimensions = new double[dimensions.length];
            System.arraycopy(coords, 0, this.coords, 0, coords.length);
            System.arraycopy(dimensions, 0, this.dimensions, 0, dimensions.length);
            this.leaf = leaf;
            children = new ArrayList<Node>();
            parent = root;
        }

    }


    public static double getRandomDoubleBetweenRange(double min, double max) {
        double x = ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }
}




