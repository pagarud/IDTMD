package io.pagarusha.logica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mAnswer1;
    EditText mAnswer2;
    EditText mAnswer3;
    EditText mAnswer4;
    Button mSubmit;
    List<EditText> mAnswers;
    final String[] CONJUNCTION_ANSWERS = new String[]{"t", "f", "f", "f"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAnswer1 = findViewById(R.id.edittext_answer_1);
        mAnswer2 = findViewById(R.id.edittext_answer_2);
        mAnswer3 = findViewById(R.id.edittext_answer_3);
        mAnswer4 = findViewById(R.id.edittext_answer_4);
        mAnswers = new ArrayList<>();
        mAnswers.add(mAnswer1);
        mAnswers.add(mAnswer2);
        mAnswers.add(mAnswer3);
        mAnswers.add(mAnswer4);
        mSubmit = findViewById(R.id.button_submit);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswers(mAnswers, CONJUNCTION_ANSWERS);
            }
        });

    }

    private void checkAnswers(List<EditText> input, String[] answers) {
        int index = 0;
        int countWrong = 0;
        String answer;
        String message;

        for (EditText editText: input) {
            answer = editText.getText().toString().toLowerCase();

            if (!answer.equals(answers[index])) {
                countWrong++;
            }

            index++;
        }

        if (countWrong == 0) {
            message = "Correct";
        } else {
            message = "Wrong";
        }

        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
