package com.example.csc207simulator.game3.GameThreeBackend;


import android.content.Context;
import android.widget.Toast;



public class GameThreeEngine {
    private static GameThreeEngine instance;

    private static int CHEST_NUMBER = 10;
    private static int WIDTH = 8;
    private static int HEIGHT = 8;
    private static int EQUIPMENT = 5;
    private static int EQUIPMENTMAIN = 5;
    private boolean setup = false;
    private Context context;
    public static GameThreeManager gm = new GameThreeManager(0);
    private static Gridelement[][] dungeonGrid = new Gridelement[WIDTH][HEIGHT];
    private static int secret;

    public static GameThreeEngine getInstance() {
        if (instance == null) {
            instance = new GameThreeEngine();
        }

        return instance;
    }

    public GameThreeEngine() {
    }



    public GameThreeEngine(int width, int height, int number, int equipment) {
        WIDTH = width;
        HEIGHT = height;
        CHEST_NUMBER = number;
        EQUIPMENTMAIN = equipment;
        EQUIPMENT = equipment;
        dungeonGrid = new Gridelement[WIDTH][HEIGHT];

    }

    /**
     * create a grid
     */

    public void createGrid(Context context) {

        this.context = context;
        // create the grid and store it
        int[][] Grid = Fieldcreator.generate(CHEST_NUMBER, WIDTH, HEIGHT);
        setGrid(context, Grid);

    }


    private void setGrid(Context context, int[][] grid) {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (dungeonGrid[x][y] == null) {
                    dungeonGrid[x][y] = new Gridelement(context, x, y);
                }
                dungeonGrid[x][y].setValue(grid[x][y]);
                dungeonGrid[x][y].invalidate();
            }
        }
    }

    public Gridelement getelement(int position) {
        int x = position % WIDTH;
        int y = position / WIDTH;

        return dungeonGrid[x][y];
    }

    public Gridelement getelement(int x, int y) {
        return dungeonGrid[x][y];
    }

    public void click(int x, int y) {
        if (x >= 0 && y >= 0 && x < WIDTH && y < HEIGHT && !getelement(x, y).isClicked()) {
            getelement(x, y).setClicked();

            if (getelement(x, y).getValue() == 0) {
                for (int a = -1; a <= 1; a++) {
                    for (int b = -1; b <= 1; b++) {
                        if (!(a == b && a == 0)) {
                            click(x + a, y + b);
                        }
                    }
                }
            }


        }

        checkEnd();
    }

    private boolean checkEnd() {
        int equipmentused = EQUIPMENT - EQUIPMENTMAIN;
        int notRevealed = WIDTH * HEIGHT;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (getelement(x, y).isRevealed()) {
                    notRevealed--;
                }
            }
        }

        if (!gm.isFinsihed && notRevealed - equipmentused <= 0) {
            int score = finalscore() * 2;
            Toast.makeText(context, "Click the top button to see your final score and replay!", Toast.LENGTH_SHORT).show();
            gm.setSecret(secret);
            gm.setFinished(true);
            gm.setscore(score);
        }
        return false;
    }

    private int finalscore() {
        int treasures = 0;
        secret = 0;
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                if (getelement(x, y).isChest() && getelement(x, y).isSearched()) {
                    treasures++;
                }if (getelement(x, y).isSecret() && getelement(x, y).isSearched()) {
                    treasures=treasures+10;
                    secret++;
                }
            }
        }
        int score = treasures*20/EQUIPMENT+EQUIPMENT*3+treasures*3;
        return score;
    }

    public void search(int x, int y) {
        if (!(getelement(x, y).isClicked())) {
            boolean isSearched = getelement(x, y).isSearched();
            getelement(x, y).setSearched(!isSearched);
            if (isSearched) {
                EQUIPMENTMAIN++;
            } else {
                EQUIPMENTMAIN--;
            }
            if (EQUIPMENTMAIN <= 0) {
                onGameLost();
            }
            checkEnd();
            if (EQUIPMENTMAIN != 0) {
                Toast.makeText(context, "You have " + EQUIPMENTMAIN + " equipments left", Toast.LENGTH_SHORT).show();
            }
            getelement(x, y).invalidate();
        }
    }

    private void onGameLost() {

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                getelement(x, y).setRevealed();
            }
        }
        int score = finalscore() * 2;
        Toast.makeText(context, "Click the top button to see your final score and replay!", Toast.LENGTH_SHORT).show();
        gm.setFinished(true);
        gm.setSecret(secret);
        gm.setscore(score);
    }

    public void resetGame() {
        for (int a = 0; a < WIDTH; a++) {
            for (int b = 0; b < HEIGHT; b++) {
                dungeonGrid[a][b].resetclickable();
            }
        }
    }

    public int getCHEST_NUMBER() {
        return CHEST_NUMBER;
    }

    public void setCHEST_NUMBER(int CHEST_NUMBER) {
        this.CHEST_NUMBER = CHEST_NUMBER;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int getEQUIPMENT() {
        return EQUIPMENT;
    }

    public void setEQUIPMENT(int EQUIPMENT) {
        this.EQUIPMENT = EQUIPMENT;
    }

    public int getEQUIPMENTMAIN() {
        return EQUIPMENTMAIN;
    }

    public void setEQUIPMENTMAIN(int EQUIPMENTMAIN) {
        this.EQUIPMENTMAIN = EQUIPMENTMAIN;
    }
}