package at.w2017.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends Activity implements View.OnClickListener {
    //private Member vars
    private Button buttonAdd_;
    private Button buttonSub_;
    private Button buttonMul_;
    private Button buttonEquals_;
    private Button buttonClear_;
    private Button buttonDiv_;
    private Button button0_;
    private Button button1_;
    private Button button2_;
    private Button button3_;
    private Button button4_;
    private Button button5_;
    private Button button6_;
    private Button button7_;
    private Button button8_;
    private Button button9_;
    private TextView numberView_;
    private int firstNumber_;
    private State state;

    //public Member vars


    //methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        buttonAdd_ = (Button) findViewById(R.id.buttonAdd);
        buttonAdd_.setOnClickListener(this);

        buttonClear_ = (Button) findViewById(R.id.buttonClear);
        buttonClear_.setOnClickListener(this);

        buttonSub_ = (Button) findViewById(R.id.buttonSub);
        buttonSub_.setOnClickListener(this);

        buttonMul_ = (Button) findViewById(R.id.buttonMul);
        buttonMul_.setOnClickListener(this);

        buttonDiv_ = (Button) findViewById(R.id.buttonDiv);
        buttonDiv_.setOnClickListener(this);

        buttonEquals_ = (Button) findViewById(R.id.buttonEquals);
        buttonEquals_.setOnClickListener(this);

        button0_ = (Button) findViewById(R.id.button0);
        button0_.setOnClickListener(this);

        button1_ = (Button) findViewById(R.id.button1);
        button1_.setOnClickListener(this);

        button2_ = (Button) findViewById(R.id.button2);
        button2_.setOnClickListener(this);

        button3_ = (Button) findViewById(R.id.button3);
        button3_.setOnClickListener(this);

        button4_ = (Button) findViewById(R.id.button4);
        button4_.setOnClickListener(this);

        button5_ = (Button) findViewById(R.id.button5);
        button5_.setOnClickListener(this);

        button6_ = (Button) findViewById(R.id.button6);
        button6_.setOnClickListener(this);

        button7_ = (Button) findViewById(R.id.button7);
        button7_.setOnClickListener(this);

        button8_ = (Button) findViewById(R.id.button8);
        button8_.setOnClickListener(this);

        button9_ = (Button) findViewById(R.id.button9);
        button9_.setOnClickListener(this);

        numberView_ = (TextView) findViewById(R.id.textViewOutput);
    }

    @Override
    public void onClick(View v) {
        Button clickedButton = (Button) v;
        switch (clickedButton.getId()) {
            case R.id.buttonAdd:
                clearNumberView();
                state = State.ADD;
                break;
            case R.id.buttonSub:
                clearNumberView();
                state = State.SUB;
                break;
            case R.id.buttonMul:
                clearNumberView();
                state = State.MUL;
                break;
            case R.id.buttonDiv:
                clearNumberView();
                state = State.DIV;
                break;
            case R.id.buttonEquals:
                calculateResult();
                state = State.INIT;
                break;
            case R.id.buttonClear:
                clearTextView();
                firstNumber_ = 0;
                state = State.INIT;
                break;
            default:
                String recentNumber = numberView_.getText().toString();
                if (recentNumber.equals("0")) {
                    recentNumber = "";
                }
                recentNumber += clickedButton.getText().toString();
                numberView_.setText(recentNumber);
        }
    }

    private void clearTextView(){
        numberView_.setText("0");
    }

    public void clearNumberView(){
        String tempString = numberView_.getText().toString();
        if(!tempString.equals("")){
            firstNumber_ = Integer.valueOf(tempString);
        }
        numberView_.setText("");
    }

    public enum State {
        ADD, SUB, MUL, DIV, INIT, NUM
    }

    private void calculateResult() {
        int secondNumber = 0;
        String tempString = numberView_.getText().toString();
        if (!tempString.equals("")){
            secondNumber = Integer.valueOf(tempString);
        }
        int result;
        switch (state){
            case ADD:
                result = Calculations.doAddition(firstNumber_ , secondNumber);
                break;
            case SUB:
                result = Calculations.doSubtraction (firstNumber_ , secondNumber);
                break;
            case MUL:
                result = Calculations.doMultiplication(firstNumber_ , secondNumber);
                break;
            case DIV:
                result = Calculations.doDivision(firstNumber_ , secondNumber);
                break;
            default:
                result = secondNumber;
        }
        numberView_.setText(Integer.toString(result));
    }
}