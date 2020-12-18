package org.example;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        Root root = new Root(50, 1, 100);

        for (int i = 0; i <= 50; i++) {
            double[] coords = {getRandomDoubleBetweenRange(0, 100), getRandomDoubleBetweenRange(0, 100)};
            double[] dimentions = {getRandomDoubleBetweenRange(0, 100 - coords[0]), getRandomDoubleBetweenRange(0, 100 - coords[1])};

            Node nodeTemp;
            nodeTemp = new Node(coords, dimentions, true, root);

            root.insertNode(nodeTemp);

        }


        //        Linear-cost algorithm
//        root.groupSelection(root.PickSeedsLinear());

        //        Quadratic-cost algorithm
                root.groupSelection(root.PickSeedsQuadratic());

        System.out.println(root.nodes.get(0).getChildren().size());
        System.out.println(root.nodes.get(1).getChildren().size());

    }

    public static class Root {
        private ArrayList<Node> nodes;

        public Root(int maxEntries, int minEntries, int numDims) {
            assert (minEntries <= (maxEntries / 2));
            nodes = new ArrayList<Node>();
        }


        public void insertNode(Node node) {
            nodes.add(node);
        }

        public int getAmountNodes() {
            return nodes.size();
        }

        //        Quadratic-cost algorithm
        public ArrayList<Node> PickSeedsQuadratic() {
            ArrayList<Node> seedNodes = new ArrayList<Node>();
            double maxArea = 0;
            for (int i = 0; i <= 50; i++) {
                for (int j = 0; j <= 50; j++) {
                    double width = nodes.get(i).coords[0] - nodes.get(j).getCoords()[0];
                    double height = nodes.get(i).getCoords()[1] - nodes.get(j).getCoords()[1];
                    double rect = width * height;

                    Node node1 = nodes.get(i);
                    Node node2 = nodes.get(j);

                    if (rect > maxArea) {
                        maxArea = rect;
                        seedNodes.clear();
                        seedNodes.add(node1);
                        seedNodes.add(node2);
                    }

                }
            }
            return seedNodes;
        }

        //        Linear-cost algorithm
        public ArrayList<Node> PickSeedsLinear() {
            ArrayList<Node> seedNodes = new ArrayList<Node>();

            double maxLength = 0;

            for (int i = 0; i <= 50; i++) {
                for (int j = 0; j <= 50; j++) {

                    double rightSideLeft;
                    double leftSideRight;
                    double bottomSideTop;
                    double topSideBottom;

                    rightSideLeft = nodes.get(i).coords[0] + nodes.get(i).dimensions[0];
                    leftSideRight = nodes.get(j).coords[0] + nodes.get(j).dimensions[1];

                    bottomSideTop = nodes.get(i).coords[1] + nodes.get(i).dimensions[1];
                    topSideBottom = nodes.get(j).coords[1] + nodes.get(j).dimensions[0];

                    double tempWidth = rightSideLeft - leftSideRight;
                    double tempHeight = topSideBottom - bottomSideTop;

                    Node node1 = nodes.get(i);
                    Node node2 = nodes.get(j);

                    if (tempHeight > maxLength && tempHeight > 0) {
                        maxLength = tempHeight;
                        seedNodes.clear();
                        seedNodes.add(node1);
                        seedNodes.add(node2);
                    } else if (tempWidth > maxLength && tempWidth > 0) {
                        maxLength = tempWidth;
                        seedNodes.clear();
                        seedNodes.add(node1);
                        seedNodes.add(node2);
                    }

                }
            }
            return seedNodes;
        }

        public void groupSelection(ArrayList<Node> seedNodes) {
            ArrayList<Node> temp = new ArrayList<Node>();
            temp = nodes;
            nodes = seedNodes;
            nodes.get(0).leaf = false;
            nodes.get(1).leaf = false;

            for (Node node : temp) {
                double width1 = node.coords[0] - nodes.get(0).getCoords()[0];
                double height1 = node.coords[1] - nodes.get(0).getCoords()[1];
                double rect1 = width1 * height1;

                double width2 = node.coords[0] - nodes.get(1).getCoords()[0];
                double height2 = node.coords[1] - nodes.get(1).getCoords()[1];
                double rect2 = width2 * height2;

                if (rect1 > rect2) {
                    nodes.get(0).children.add(node);
                } else {
                    nodes.get(1).children.add(node);
                }
            }
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
        boolean leaf;

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
        return (int) ((Math.random() * ((max - min) + 1)) + min);
    }
}





