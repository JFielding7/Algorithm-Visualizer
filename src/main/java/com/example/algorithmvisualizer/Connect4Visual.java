package com.example.algorithmvisualizer;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

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
    static int TOTAL_NODES = (1 << 8) - 1;
    private Circle[] gameNodes = new Circle[TOTAL_NODES];
    private Line[] gameEdges = new Line[TOTAL_NODES];

    public static void main(String[] args) {
        System.out.println(Integer.highestOneBit(255));
        System.exit(0);
        int[] gameTree = new int[TOTAL_NODES];

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
    @FXML
    private AnchorPane background;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < TOTAL_NODES >> 1; i++) {
            tree[i] = NOT_SOLVED;
        }
        for (int i = TOTAL_NODES >> 1; i < TOTAL_NODES; i++) {
            tree[i] = rand.nextInt(-1, 2);
            if (tree[i] > 1) tree[i] = 1;
        }
        double RADIUS = 6;
        int nodeIndex = 0;
        int rowCount = 1;
        int y = 56;
        int i = 7;
        for (Node node : levels.getChildren()) {
            HBox level = (HBox) node;
            level.setSpacing(2 * RADIUS * ((1 << i) - 1));
            int x = (int) (level.getSpacing() / 2 + RADIUS);
            for (int j = 0; j < rowCount; j++) {
                Circle gameNode = createNode(RADIUS);
                gameNodes[nodeIndex] = gameNode;
                gameNode.setFill(Color.GRAY);
                gameNode.setCenterX(x);
                gameNode.setCenterY(y);
                if (nodeIndex > 0) {
                    Line edge = new Line();
                    edge.setStartX(gameNodes[nodeIndex - 1 >>> 1].getCenterX());
                    edge.setStartY(gameNodes[nodeIndex - 1 >>> 1].getCenterY());
                    edge.setEndX(gameNode.getCenterX());
                    edge.setEndY(gameNode.getCenterY());
                    background.getChildren().add(edge);
                    gameEdges[nodeIndex] = edge;
                    edge.toBack();
                }
//                gameNode.setFill(getColor(tree[nodeIndex++], 7 - i));
                nodeIndex++;
                level.getChildren().add(gameNode);
                x += (int) (level.getSpacing() + 2 * RADIUS);
            }
            rowCount <<= 1;
            i--;
            y += 113;
        }
        new Thread(() -> {
            try {
                solve(-1, 1, 2, 0);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    static int pauseTime = 20;
    private int solve(int alpha, int beta, int branchingFactor, int index) throws InterruptedException {
        Platform.runLater(() -> gameNodes[index].setFill(Color.LIGHTGREEN));
        Thread.sleep(pauseTime);
        int solution = tree[index];
        if (solution != NOT_SOLVED) {
            Platform.runLater(() -> gameNodes[index].setFill(getColor(solution, Integer.numberOfTrailingZeros(Integer.highestOneBit(index + 1)))));
            Thread.sleep(pauseTime);
            return solution;
        }
        orderNum++;
        int move = 0;
        int maxEval = -1;
        for (int i = 0; i < branchingFactor; i++) {
            Platform.runLater(() -> gameNodes[index].setFill(Color.GRAY));
            int eval = -solve(-beta, -alpha, branchingFactor, (index << 1) + move + 1);
            alpha = Math.max(alpha, eval);
            maxEval = Math.max(maxEval, eval);
            Platform.runLater(() -> gameNodes[index].setFill(Color.LIGHTGREEN));
            Thread.sleep(pauseTime);
            if (alpha >= beta) {
                if (i < branchingFactor - 1) prune(index * 2 + 2);
                break;
            }
            move ^= 1;
        }
        int finalMaxEval = maxEval;
        Platform.runLater(() -> gameNodes[index].setFill(getColor(finalMaxEval, Integer.numberOfTrailingZeros(Integer.highestOneBit(index + 1)))));
        Thread.sleep(pauseTime);
        return alpha;
    }

    private void prune(int index) {
        gameNodes[index].setVisible(false);
        gameEdges[index].setVisible(false);
        if (index < TOTAL_NODES >>> 1) {
            prune((index << 1) + 1);
            prune((index << 1) + 2);
        }
    }

    private Circle createNode(double radius) {
        return new Circle(radius, Color.RED);
    }

    private Color getColor(int minimax, int depth) {
        if (minimax == NOT_SOLVED) return Color.GRAY;
        if (minimax == 0) return Color.BLACK;
        if ((minimax == 1) == ((depth & 1) == 0)) return Color.RED;
        return Color.YELLOW;
    }
}
