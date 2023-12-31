package com.example.myquizapp;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
TextView totalQuestionTextView;
    TextView questionTextView;
        Button Submitbtn,ansA,ansB,ansC,ansD;

int score=0;
    int totalQuestion= QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.AnsA);
                ansB = findViewById(R.id.AnsB);
                ansC = findViewById(R.id.AnsC);
                        ansD = findViewById(R.id.AnsD);
        Submitbtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
                ansB.setOnClickListener(this);
                ansC.setOnClickListener(this);
                        ansD.setOnClickListener(this);
                        Submitbtn.setOnClickListener(this);
         totalQuestionTextView.setText("Total Question :"+totalQuestion);
              loadNewQuestion();}
    public void onClick(View view){

        ansA.setBackgroundColor(Color.RED);
                ansB.setBackgroundColor(Color.RED);
                ansC.setBackgroundColor(Color.RED);
                        ansD.setBackgroundColor(Color.RED);
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.rightanswer[currentQuestionIndex])) {
            score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }else{
            selectedAnswer=clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.BLUE);
        }  }
    void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestion){
            finishedQuiz();
            return;
        }
      questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
      ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishedQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.70){
          passStatus = "passed";
        }
        else{
            passStatus = "fail";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();}
    void restartQuiz(){
        score=0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}