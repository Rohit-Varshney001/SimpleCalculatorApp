package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Context;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView resultDisp, solutionDisp;
    MaterialButton buttonC,buttonBracOpen,buttonBracClose,buttondivide,buttonMultiply,button9,button8,button7,button4,
            button5,button6,buttonplus,buttonminus,button3,button2,button1,buttonAC,button0,buttonDot,buttonEquals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultDisp = findViewById(R.id.result_dis);
        solutionDisp = findViewById(R.id.solution_disp);

        assignId(buttonC,R.id.button_c);
        assignId(buttonplus,R.id.button_plus);
        assignId(buttonminus,R.id.button_minus);
        assignId(buttonMultiply,R.id.button_multiply);
        assignId(buttondivide,R.id.button_divide);
        assignId(buttonAC,R.id.button_AC);
        assignId(buttonDot,R.id.button_dot);
        assignId(buttonBracOpen,R.id.button_openBracket);
        assignId(buttonBracClose,R.id.button_closeBracket);
        assignId(buttonEquals,R.id.button_equals);
        assignId(button0,R.id.button_0);
        assignId(button9,R.id.button_9);
        assignId(button8,R.id.button_8);
        assignId(button7,R.id.button_7);
        assignId(button6,R.id.button_6);
        assignId(button5,R.id.button_5);
        assignId(button4,R.id.button_4);
        assignId(button3,R.id.button_3);
        assignId(button2,R.id.button_2);
        assignId(button1,R.id.button_1);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String buttonText = btn.getText().toString();
        String dataToCalc = solutionDisp.getText().toString();

        if(buttonText.equals("AC")){
            solutionDisp.setText("");
            resultDisp.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionDisp.setText(resultDisp.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalc = dataToCalc.substring(0,dataToCalc.length()-1);
        }else{
            dataToCalc = dataToCalc + buttonText;
        }

        solutionDisp.setText(dataToCalc);
        String finalResult = getResult(dataToCalc);

        if(!finalResult.equals("err")){
            resultDisp.setText(finalResult);
        }

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "err";
        }
    }
}
