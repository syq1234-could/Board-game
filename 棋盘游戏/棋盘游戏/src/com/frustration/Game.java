package com.frustration;

import java.util.Scanner;

public class Game {
    private Board board;
    private Player[] players;
    private int currentPlayerIndex;
    private boolean gameOver;
    private int totalRounds;

    public Game() {
        this.board = new Board();
        this.players = new Player[2];
        players[0] = new Player(1);
        players[1] = new Player(2);
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.totalRounds = 0;
    }

    public void play(Scanner scanner) {
        System.out.println("Welcome to Simple Frustration!");
        System.out.println("Rules: Two players take turns rolling dice, first to reach the finish line wins.");
        System.out.println("Advanced rules: Exact finish required, bumping sends back to start, occupied spaces blocked, large board (36 cells).");
        
        while (!gameOver) {
            Player currentPlayer = players[currentPlayerIndex];
            System.out.println("\nPlayer " + currentPlayer.getPlayerId() + "'s turn");
            
            // Get dice roll
            int diceRoll = getDiceRoll(scanner);
            
            // Record current position
            int oldPosition = currentPlayer.getPosition();
            
            // Try to move player
            boolean moved = movePlayer(currentPlayer, diceRoll);
            
            // Display turn information
            if (moved) {
                System.out.println("Player " + currentPlayer.getPlayerId() + " rolled " + diceRoll);
                System.out.println("Moved from position " + oldPosition + " to position " + currentPlayer.getPosition());
                System.out.println("Player " + currentPlayer.getPlayerId() + " has completed " + currentPlayer.getRoundCount() + " rounds");
                
                // Check if game over
                if (board.isAtFinish(currentPlayer.getPosition())) {
                    gameOver = true;
                    System.out.println("\nCongratulations! Player " + currentPlayer.getPlayerId() + " wins!");
                    System.out.println("Total rounds: " + totalRounds);
                }
            } else {
                System.out.println("Cannot move, exact finish required.");
                System.out.println("Player " + currentPlayer.getPlayerId() + " has completed " + currentPlayer.getRoundCount() + " rounds");
            }
            
            totalRounds++;
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }

    private int getDiceRoll(Scanner scanner) {
        int diceRoll = -1;
        while (diceRoll < 1 || diceRoll > 6) {
            System.out.print("Please enter dice roll (1-6): ");
            try {
                diceRoll = scanner.nextInt();
                if (diceRoll < 1 || diceRoll > 6) {
                    System.out.println("Invalid dice roll. Please enter a number between 1 and 6.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return diceRoll;
    }

    private boolean movePlayer(Player player, int diceRoll) {
        int oldPosition = player.getPosition();
        int newPosition = oldPosition + diceRoll;
        
        // Check exact finish rule
        if (newPosition > board.getFinishPosition()) {
            System.out.println("Past the finish! Exact roll needed to finish.");
            player.incrementRoundCount();
            return false;
        }
        
        // Check for collision with other player
        Player otherPlayer = players[currentPlayerIndex == 0 ? 1 : 0];
        if (newPosition > 0 && newPosition < board.getFinishPosition() && newPosition == otherPlayer.getPosition()) {
            System.out.println("Bumped into Player " + otherPlayer.getPlayerId() + "! Sending them back to start.");
            otherPlayer.setPosition(0);
        }
        
        // Move player
        player.setPosition(newPosition);
        player.incrementRoundCount();
        
        // Check if reached finish
        if (board.isAtFinish(newPosition)) {
            return true;
        }
        
        return true;
    }
}