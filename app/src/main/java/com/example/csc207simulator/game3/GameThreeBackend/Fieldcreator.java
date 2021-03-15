package com.example.csc207simulator.game3.GameThreeBackend;

import java.util.Random;


public class Fieldcreator {
    /**
     * Create a random grid with width, height, chestnumber passed from the engine and update every
     * element in grid with recursion so it will display how many chest is around it
     */
    public static int[][] generate(int chestnumber, int width, int height) {
        // Random for generating numbers
        Random r = new Random();

        int[][] grid = new int[width][height];
        for (int x = 0; x < width; x++) {
            grid[x] = new int[height];
        }

        while (chestnumber > 0) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);

            if (grid[x][y] != 10){
                int temp = r.nextInt(3);
                if(temp==2){
                    grid[x][y] = 11;
                }else grid[x][y] = 10;
                chestnumber--;
            }
        }
        grid = calculateNeigbours(grid, width, height);

        return grid;
    }

    /**
     * Calculate how many chests are around this element for the whole grid
     */
    private static int[][] calculateNeigbours(int[][] grid, final int width, final int height) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                grid[x][y] = getNeighbourNumber(grid, x, y, width, height);
            }
        }

        return grid;
    }

    /**
     * check everything around this element in the grid
     */
    private static int getNeighbourNumber(final int grid[][], final int x, final int y, final int width, final int height) {
        if (grid[x][y] == 10) {
            return 10;
        }else if (grid[x][y] == 11) {
            return 11;
        }

        int count = 0;
        for (int start_row = x - 1; start_row <= x + 1; start_row++) { // check 8 directions
            for (int start_col = y - 1; start_col <= y + 1; start_col++) { // check 8 directions
                if (start_col != y || start_row != x) {
                    if (isChestAt(grid, start_row, start_col, width, height)) { // if a mine is detected
                        count = count + 1; // add 1 to count
                    }
                }
            }
        }
        return count;
    }

    /**
     * check if this element is a chest or secret
     */
    private static boolean isChestAt(final int[][] grid, final int x, final int y, final int width, final int height) {
        if (x >= 0 && y >= 0 && x < width && y < height) {
            if (grid[x][y] >= 10) {
                return true;
            }
        }
        return false;
    }

}