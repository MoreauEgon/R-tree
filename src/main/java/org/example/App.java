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
import java.util.Arrays;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) {

        Root root = new Root(50,1,100);

        for (int i = 0; i <= 50; i++ )
        {
            double[] coords = {getRandomDoubleBetweenRange(0, 100), getRandomDoubleBetweenRange(0, 100)};
            double[] dimentions = {getRandomDoubleBetweenRange(0, 100 - coords[0]), getRandomDoubleBetweenRange(0, 100 - coords[1])};

            Node nodeTemp;
            nodeTemp  = new Node(coords,dimentions,true,root);

            root.insertNode(nodeTemp);

        }
        root.PickSeeds();
        System.out.println(Arrays.toString(root.PickSeeds().get(0).coords));

        System.out.println(root.getAmountNodes());
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

        public int getAmountNodes()
        {
            return nodes.size();
        }

        // TODO Pick first entry for each group
        public ArrayList<Node> PickSeeds()
        {
            ArrayList<Node> seedNodes = new ArrayList<Node>();
            double maxArea = 0;
            for (int i = 0; i <= 49; i++ )
            {
                for (int j = 0; j <= 49; j++ )
                {
                  double width = nodes.get(i).coords[0] - nodes.get(j).getCoords()[0];
                  double height= nodes.get(i).getCoords()[1] - nodes.get(j).getCoords()[1];
                  double rect = width * height;

                  Node node1 = nodes.get(i);
                  Node node2 = nodes.get(j);

                  if (rect > maxArea)
                  {
                      maxArea = rect;
                      seedNodes.clear();
                      seedNodes.add(node1);
                      seedNodes.add(node2);
                  }

                }
            }
            return seedNodes;
        }
        // TODO Check if done

        // TODO Select entry to assign


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
        double x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }
}




