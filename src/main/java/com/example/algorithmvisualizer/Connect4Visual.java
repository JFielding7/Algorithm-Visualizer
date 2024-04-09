package com.example.algorithmvisualizer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Connect4Visual implements Initializable {
    static int positions = 0;
    static int NOT_SOLVED = 2;

    static Random rand = new Random();
    static ArrayList<Integer> moveOrder = new ArrayList<>();
    static int[] tree = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, -1, 1, -1, -1, 1, 1, -1, 1, 1, 0, 1, 1, 0, 0, -1, 0, 0, 0, -1, -1, 0, 1, 1, 1, 1, 1, 1, 0, -1, 0, -1, 0, -1, 1, -1, 0, -1, 1, 0, -1, 1, -1, -1, 0, -1, -1, 1, 0, -1, 0, -1, -1, -1, 0, -1, -1, 1, -1, -1, 1, 1, -1, 0, 0, 0, 0, -1, 1, 0, -1, 0, -1, 0, -1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, -1, 1, -1, 1, -1, 0, -1, -1, -1, -1, 0, 0, -1, -1, -1, 1, -1, 1, -1, -1, -1, 0, 1, -1, 1, 1, 0, -1, 1, 0, 1, -1, 1, -1, 0, 0, 1};
    static int[] optimalMoveOrder = {1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1};
    static int nodes = (1 << 20) - 1;
    private Circle[] gameNodes = new Circle[nodes];

    public static void main(String[] args) {
        int[] gameTree = new int[nodes];

        int iterations = 0;
//        while (true) {
//            iterations++;
//            positions = 0;
//            moveOrder.clear();
//            for (int i = 0; i < nodes >> 1; i++) {
//                gameTree[i] = notCalculated;
//            }
//            for (int i = nodes >> 1; i < nodes; i++) {
//                gameTree[i] = rand.nextInt(-1, 2);
//            }
////            solve(gameTree);
//            System.out.println(positions);
//            if (positions == 30) {
//                System.out.println(Arrays.toString(gameTree));
//                System.out.println(moveOrder);
//                System.out.println(iterations);
//                break;
//            }
//        }
        System.out.println(moveOrder.size());
        positions = 0;
        int[] tree = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 1, -1, 1, -1, -1, 1, 1, -1, 1, 1, 0, 1, 1, 0, 0, -1, 0, 0, 0, -1, -1, 0, 1, 1, 1, 1, 1, 1, 0, -1, 0, -1, 0, -1, 1, -1, 0, -1, 1, 0, -1, 1, -1, -1, 0, -1, -1, 1, 0, -1, 0, -1, -1, -1, 0, -1, -1, 1, -1, -1, 1, 1, -1, 0, 0, 0, 0, -1, 1, 0, -1, 0, -1, 0, -1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 1, 1, -1, 1, -1, 1, -1, 0, -1, -1, -1, -1, 0, 0, -1, -1, -1, 1, -1, 1, -1, -1, -1, 0, 1, -1, 1, 1, 0, -1, 1, 0, 1, -1, 1, -1, 0, 0, 1};
        int[] moveOrder = {1, 0, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1};
        int wins = 0;
        int losses = 0;
        int draws = 0;
        for (int pos : tree) {
            if (pos == -1) wins++;
            if (pos == 1) losses++;
            if (pos == 0) draws++;
        }
        System.out.println(wins);
        System.out.println(losses);
        System.out.println(draws);
        System.out.println(solve(tree, moveOrder));
        System.out.println(positions);
    }

    public static int solve(int[] gameTree, int[] moveOrder) {
        return solve(-1, 1, 2, 0, gameTree, moveOrder);
    }

    static int orderNum = -1;

    public static int solve(int alpha, int beta, int branching, int index, int[] gameTree, int[] moveOrder) {
        positions++;
        if (gameTree[index] != NOT_SOLVED) return gameTree[index];
        orderNum++;
        int move = moveOrder[orderNum];
        for (int i = 0; i < branching; i++) {
            alpha = Math.max(alpha, -solve(-beta, -alpha, branching, (index << 1) + move + 1, gameTree, moveOrder));
            if (alpha >= beta) return alpha;
            move ^= 1;
        }
        return alpha;
    }

    @FXML
    private VBox levels;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double RADIUS = 6;
        int nodeCount = 1;
        int i = 7;
        for (Node node : levels.getChildren()) {
            HBox level = (HBox) node;
            level.setSpacing(2 * RADIUS * ((1 << i) - 1));
            for (int j = 0; j < nodeCount; j++) {
                level.getChildren().add(createNode(RADIUS));
            }
            System.out.println(level.getSpacing());
            nodeCount <<= 1;
            i--;
        }
    }

    private static int solve(int alpha, int beta, int branching, int index) {
        int solution = tree[index];
        if (solution != NOT_SOLVED) return solution;
        orderNum++;
        int move = optimalMoveOrder[orderNum];
        for (int i = 0; i < branching; i++) {
            alpha = Math.max(alpha, -solve(-beta, -alpha, branching, (index << 1) + move + 1));
            if (alpha >= beta) break;
            move ^= 1;
        }

        return alpha;
    }

    private Circle createNode(double radius) {
        Circle node = new Circle(radius, Color.RED);
        return node;
    }
}
