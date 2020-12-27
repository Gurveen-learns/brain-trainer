package gurveen.com.braintrainer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView timerTextView, scoreTextView, problemTextView, resultTextView;
    Button button0, button1, button2, button3, playAgainButton;
    GridLayout gridLayout;
    ConstraintLayout gameConstraint;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int noOfQuestions;


    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("CORRECT!");
            score++;
        } else {
            resultTextView.setText("Wrong :/");
        }
        noOfQuestions++;
        scoreTextView.setText(score + "/" + noOfQuestions);
        newProblem();
    }

    public void start(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameConstraint.setVisibility(View.VISIBLE);

        newProblem();
    }

    public void playAgain(View view) {
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        score = 0;
        noOfQuestions = 0;
        scoreTextView.setText(score + "/" + noOfQuestions);
        gridLayout.setClickable(true);

        newProblem();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("TIME OVER!!");
                playAgainButton.setVisibility(View.VISIBLE);

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        R.raw.alarm_horn);
                mediaPlayer.start();

                gridLayout.setClickable(false);

            }
        }.start();

    }

    public void newProblem() {
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        problemTextView.setText(a + " + " + b);

        locationOfCorrectAnswer = random.nextInt(4);

        answers.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        timerTextView = findViewById(R.id.timerTextView);
        problemTextView = findViewById(R.id.problemTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameConstraint = findViewById(R.id.gameConstraint);
        gridLayout = findViewById(R.id.gridLayout);

        goButton.setVisibility(View.VISIBLE);
        gameConstraint.setVisibility(View.INVISIBLE);

        playAgain(timerTextView);


    }

}
