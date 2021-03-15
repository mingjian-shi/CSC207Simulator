package com.example.csc207simulator.game1.GameOneUI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.csc207simulator.Player;
import com.example.csc207simulator.R;
import com.example.csc207simulator.SetupManagement.SettableActivity;
import com.example.csc207simulator.game1.GameOneBackend.Book;
import com.example.csc207simulator.game1.GameOneBackend.BookFactory;
import com.example.csc207simulator.game1.GameOneBackend.BookManager;
import com.example.csc207simulator.game1.GameOneBackend.BookSetter;
import com.example.csc207simulator.game1.GameOneBackend.GameOneManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


/**
 * The UI class for GameOne.
 */
public class GameOneActivity extends SettableActivity implements View.OnClickListener {

    /**
     * Represents the position of Books on the screen.
     */
    final int[] idLst = new int[6];

    /**
     * The CurrentPlayer who is playing the game;
     */
    private Player currentPlayer;

    /**
     * the initial knowledge of player.
     */
    private int knowledgeInt = 0;

    /**
     * A boolean to check whether the game is started.
     */
    private boolean started = false;

    /**
     * The total knowledge displays on the screen.
     */
    private TextView knowledge;

    /**
     * The time(in seconds) displays on the screen.
     */
    private TextView countDownText;

    /**
     * The Button to start GameOne.
     */
    private Button countDownButton;

    /**
     * The MainTimer for GameOne.
     */
    private CountDownTimer countDownTimer;

    /**
     * The timer that controls appearance of the books.
     */
    private CountDownTimer count;

    /**
     * The TimeLimit for the player to play GameOne
     */
    private long timeLeft = 60000; //1 min

    /**
     * Call the GameOneManager class
     */
    private GameOneManager gameOneManager;

    /**
     * The six Images of Books to be clicked
     */
    private ImageView v1;
    private ImageView v2;
    private ImageView v3;
    private ImageView v4;
    private ImageView v5;
    private ImageView v6;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameone);

        initializeGame();
        setImageButtons();
        setCountDownButton();
        setSkipButton();

    }

    /**
     * Sets the skip button.
     */
    private void setSkipButton() {
        Button skip = findViewById(R.id.skip_button);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(started){
                    countDownTimer.cancel();
                    count.cancel();
                }
                endgame();
            }
        });
    }

    /**
     * Sets the ImageButtons for the game
     */
    private void setImageButtons() {
        // Created six Clickable Image on the UI
        v1 = findViewById(R.id.imageView);
        v2 = findViewById(R.id.imageView2);
        v3 = findViewById(R.id.imageView3);
        v4 = findViewById(R.id.imageView4);
        v5 = findViewById(R.id.imageView5);
        v6 = findViewById(R.id.imageView6);
        v1.setOnClickListener(this);
        v2.setOnClickListener(this);
        v3.setOnClickListener(this);
        v4.setOnClickListener(this);
        v5.setOnClickListener(this);
        v6.setOnClickListener(this);

    }

    /**
     * Defines the onClickListener for the countDownButton.
     */
    private void setCountDownButton() {
        countDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                started = true;
                    v.setVisibility(View.INVISIBLE);
                    startCountdown();
                    countdown();
            }
        });
    }

    /**
     * Initializes the game by retrieving required information from the app manager and creating
     * the game one manager.
     */
    private void initializeGame() {
        currentPlayer = super.appManager.getCurrentPlayer();
        knowledge = findViewById(R.id.knowledgeint);
        countDownText = findViewById(R.id.countdown_text);
        countDownButton = findViewById(R.id.countdown_button);
        BookManager bookManager = new BookManager(new ArrayList<Book>(), idLst,
                new BookFactory(), new BookSetter(idLst));
        this.gameOneManager = new GameOneManager(currentPlayer.getKnowledge(), bookManager);
    }

    /**
     * Start the Main timer
     */
    @SuppressLint("SetTextI18n")
    private void startCountdown() {
        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownText();


            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                countDownButton.setText("Start");
                countDownButton.setVisibility(View.INVISIBLE);
                endgame();

            }
        }.start();
    }

    /**
     * Change the Time display on the screen accounting to the remaining time.
     */
    private void updateCountDownText() {
        int minutes = (int) timeLeft / 1000 / 60;
        int seconds = (int) timeLeft / 1000 % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countDownText.setText(timeFormatted);
    }

    /**
     * A timer for the appearance of the books, generate a random book each 2 seconds
     */
    private void countdown() {
        final List<ImageView> lst = new ArrayList<>();
        lst.add(v1);
        lst.add(v2);
        lst.add(v3);
        lst.add(v4);
        lst.add(v5);
        lst.add(v6);
        gameOneManager.createItems();
        count = new CountDownTimer(60000, 1500) {
            public void onTick(long millisUntilFinished) {
                gameOneManager.update();
                setImageAppearance();
                attemptBonusActivation(millisUntilFinished);
            }
            public void onFinish() {
            }

            /**
             * Checks if the conditions for activating the bonus is correct. If so, activate the
             * bonus.
             * @param millisUntilFinished the time left in the game.
             */
            private void attemptBonusActivation(long millisUntilFinished){
                if(millisUntilFinished < 45000 && knowledgeInt == 0){
                    gameOneManager.activateBonus();
                }
            }

            /**
             * Decides the image appearance, whether it is visible; what it looks like based on
             * back end information.
             */
            private void setImageAppearance(){
                Random rand = new Random();
                //nextInt is exclusive, so bound must be +1 the actual size
                int randomNum = rand.nextInt(6) + 1;

                while(gameOneManager.checkClicked(randomNum)){
                    randomNum = rand.nextInt(6) + 1;
                }
                if (gameOneManager.isBad(randomNum)){
                    lst.get(randomNum - 1).setImageResource(R.drawable.bad);
                }
                else{
                    lst.get(randomNum - 1).setImageResource(R.drawable.book);
                }

                lst.get(randomNum - 1).setVisibility(View.VISIBLE);

                disappear(lst.get(randomNum - 1), randomNum);
            }
        }.start();
    }

    /**
     * Increasing the knowledge display on the screen.
     */
    @SuppressLint("SetTextI18n")
    private void increase() {
        knowledgeInt = gameOneManager.getKnowledge();
        knowledge.setText(Integer.toString(knowledgeInt));
    }

    void disappear(final ImageView view, final int id ) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setVisibility(View.INVISIBLE);
                gameOneManager.deleteBook(id);
                gameOneManager.update();
            }
        }, 3000);
    }


    /**
     * Terminate GameOne
     */
    private void endgame() {
        int performance = (int) Math.floor(knowledgeInt / 20);
        currentPlayer.addKnowledge(this.knowledgeInt);
        currentPlayer.addPerformance(performance);
        currentPlayer.addScore(currentPlayer.getKnowledge() * performance);
        Intent intent = new Intent(this, GameOneEndActivity.class);
        super.attachToIntent(intent);
        startActivity(intent);
    }

  /**
   * Universal Click method for all image
   * the Knowledge of the player will gain for clicking the book, and then the book will disappear;
   */
  @Override
  public void onClick(View v) {
      int id = getIdOfClicked(v);
      gameOneManager.itemClicked(id);
            if (gameOneManager.checkClicked(id)) {
                v.setVisibility(View.INVISIBLE);
            }
            gameOneManager.update();
            increase();

    }

    /**
     * Get the id of the clicked ImageButton
     * @param v the View representing the ImageButton tha twas clicked.
     *
     * @return the id of the clicked ImageButton.
     */
    private int getIdOfClicked(View v) {
        int id;
        switch(v.getId()) {
            case R.id.imageView:
                id = 1;
                break;
            case R.id.imageView2:
                id = 2;
                break;
            case R.id.imageView3:
                id = 3;
                break;
            case R.id.imageView4:
                id = 4;
                break;
            case R.id.imageView5:
                id = 5;
                break;
            default:
                id = 6;
                break;
            }
        return id;
    }

    /**
     * Resets the user interface to reflect any changes from the backend.
     */
    @Override
    protected void resetContentView() {
        setContentView(R.layout.activity_gameone);
    }

}

