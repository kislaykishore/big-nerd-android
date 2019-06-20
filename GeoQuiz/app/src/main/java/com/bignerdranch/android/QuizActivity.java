package com.bignerdranch.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.geoquiz.Question;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;

    private Button mFalseButton;

    private Button mNextButton;

    private TextView mQuestionTextView;

    private int mCurrentIndex;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mQuestionTextView = findViewById(R.id.question_text_view);
        mNextButton = findViewById(R.id.next_button);
        updateQuestion();
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % (mQuestionBank.length);
                updateQuestion();

            }
        });
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean isCorrect = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int msg;
        if(userPressedTrue == isCorrect) {
            msg = R.string.correct_toast;
        } else {
            msg = R.string.incorrect_toast;
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
