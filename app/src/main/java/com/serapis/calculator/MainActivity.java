package com.serapis.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private View decorView;
    private boolean troll = true;
    public int i = -1;
    public String numberHandler = "";
    public boolean easteregg = false;
    public double result;
    public double number1;
    public double number2;
    public short handler = 0;
    public String stringResult = "";
    public boolean ce = false;
    public ArrayList term = new ArrayList();
    public boolean buttonDisable = false;
    public int counter;
    public  ArrayList dotBeforeDash = new ArrayList();
    public int x = 0;
    public int plusMinusHandler;
    public boolean minusHandler = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(visibility -> {
            if (visibility == 0){
                decorView.setSystemUiVisibility(hideSystemBars());
            }
        });
        setContentView(R.layout.activity_main);

        Button Null = (Button) findViewById(R.id.button0);
        Button One = (Button) findViewById(R.id.button1);
        Button Two = (Button) findViewById(R.id.button2);
        Button Three = (Button) findViewById(R.id.button3);
        Button Four = (Button) findViewById(R.id.button4);
        Button Five = (Button) findViewById(R.id.button5);
        Button Six = (Button) findViewById(R.id.button6);
        Button Seven = (Button) findViewById(R.id.button7);
        Button Eight = (Button) findViewById(R.id.button8);
        Button Nine = (Button) findViewById(R.id.button9);
        Button Plus = (Button) findViewById(R.id.buttonPlus);
        Button Minus = (Button) findViewById(R.id.buttonMinus);
        Button Mal = (Button) findViewById(R.id.buttonMal);
        Button Geteilt = (Button) findViewById(R.id.buttonGeteilt);
        Button plusMinus = (Button) findViewById(R.id.buttonPlusMinus);
        Button dot = (Button) findViewById(R.id.buttonComma);
        ImageView fbi = (ImageView) findViewById(R.id.fbi);
        Button Equals = (Button) findViewById(R.id.buttonEquals);
        fbi.setVisibility(View.INVISIBLE);
        Button CE = (Button) findViewById(R.id.buttonCE);
        TextView Output = (TextView) findViewById(R.id.Output);
        Output.setMovementMethod(new ScrollingMovementMethod());

        Null.setOnClickListener(view -> {
            changeTerm("0");
            vibrate();
        });

        One.setOnClickListener(view -> {
            changeTerm("1");
            vibrate();
        });

        Two.setOnClickListener(view -> {
            changeTerm("2");
            vibrate();
        });

        Three.setOnClickListener(view -> {
            changeTerm("3");
            vibrate();
        });

        Four.setOnClickListener(view -> {
            changeTerm("4");
            vibrate();
        });

        Five.setOnClickListener(view -> {
            changeTerm("5");
            vibrate();
        });

        Six.setOnClickListener(view -> {
            changeTerm("6");
            vibrate();
        });

        Seven.setOnClickListener(view -> {
            changeTerm("7");
            vibrate();
        });

        Eight.setOnClickListener(view -> {
            changeTerm("8");
            vibrate();
        });

        Nine.setOnClickListener(view -> {
            changeTerm("9");
            vibrate();
        });

        Plus.setOnClickListener(view -> {
            changeTerm("+");
            vibrate();
        });

        Minus.setOnClickListener(view -> {
            changeTerm("-");
            vibrate();
        });

        Mal.setOnClickListener(view -> {
            changeTerm("×");
            vibrate();
        });

        Geteilt.setOnClickListener(view -> {
            changeTerm("/");
            vibrate();
        });

        plusMinus.setOnClickListener(view -> {
            plusMinus();
            vibrate();
        });

        dot.setOnClickListener(view -> {
            dot();
            vibrate();
        });

        CE.setOnClickListener(view -> {
            vibrate();
            Output.scrollTo(0, 0);
            if(!easteregg){
                CE();
            }else{
                TextView Output1 = (TextView) findViewById(R.id.Output);
                Output1.setText(stringResult);
                easteregg = false;
            }
        });



        Equals.setOnClickListener(view -> {
            vibrate();
            Output.scrollTo(0, 0);
            if(term.size() != 0){
                ImproveTerm();
            }
        });
    }

    private void ImproveTerm(){

        if(term.get(term.size()-1) == "+" || term.get(term.size()-1) == "-" || term.get(term.size()-1) == "×" || term.get(term.size()-1) == "/" || term.get(term.size()-1) == "."){
            term.remove(term.size()-1);
        }

        for (int i = 0; i < term.size(); i++) {
            if (term.get(i).toString().equals("/") || term.get(i).toString().equals("×")) {
                dotBeforeDash.add(i);
            }
        }
        System.out.println(dotBeforeDash + " + " + term);
        Calculate();
    }

    private void Output(){
        TextView Output = (TextView) findViewById(R.id.Output);
        i++;
        Output.append(term.get(i).toString());
    }

    private void CE(){
        if(ce){
            ce = false;
            minusHandler = false;
            plusMinusHandler = 0;
            troll = true;
            ImageView fbi = (ImageView) findViewById(R.id.fbi);
            fbi.setVisibility(View.INVISIBLE);
            TextView Output = (TextView) findViewById(R.id.Output);
            term.clear();
            x = 0;
            counter = 0;
            number1 = 0;
            number2 = 0;
            result = 0;
            i = -1;
            Output.setText("");
            disEnableButtons();
            buttonDisable = false;
            numberHandler = "";
        }
    }

    private void showResult(){
        TextView Output = (TextView) findViewById(R.id.Output);

        Output.setText(stringResult);
        checkForEastereggs();
        buttonDisable = true;
        disEnableButtons();
    }

    private void changeTerm(String termChar){
        TextView Output = (TextView) findViewById(R.id.Output);
        String cache;

        if(Objects.equals(termChar, "+") || Objects.equals(termChar, "-") || Objects.equals(termChar, "×") || Objects.equals(termChar, "/")){
            minusHandler = false;
            plusMinusHandler = 0;
        }

        if(Objects.equals(termChar, ".")){
            plusMinusHandler ++;
        }

        if(Objects.equals(termChar, "+") || Objects.equals(termChar, "-") || Objects.equals(termChar, "×") || Objects.equals(termChar, "/") || Objects.equals(termChar, ".")){
            counter = 0;
            if(term.size() != 0) {
                troll = false;
                if (term.get(term.size() - 1) == "+" || term.get(term.size() - 1) == "-" || term.get(term.size() - 1) == "×" || term.get(term.size() - 1) == "/" || term.get(term.size() - 1) == ".") {
                    term.set(term.size() - 1, termChar);
                    cache = Output.getText().toString();
                    cache = cache.substring(0, cache.length()-1);
                    cache += termChar;
                    Output.setText(cache);
                }else{
                    term.add(termChar);
                    Output();
                }
            }
        }else{
            ce = true;
            plusMinusHandler ++;
            counter ++;
            term.add(termChar);
            Output();
        }
    }

    private void Calculate() {
        TextView Output = (TextView) findViewById(R.id.Output);
        if (troll) {
            result = Double.parseDouble(Output.getText().toString());
            checkForDotNull();
        }else{
            handler = 0;
            if (term.contains("+") || term.contains("-") || term.contains("×") || term.contains("/")) {
                while (x <= term.size()) {

                    try {
                        switch (term.get(x).toString()) {
                            case ("+"):
                                switch (handler) {
                                    case (0):
                                        handler = 1;
                                        number1 = Double.parseDouble(numberHandler);
                                        result = number1;
                                        numberHandler = "";
                                        break;
                                    case (1):
                                        System.out.println(numberHandler);
                                        number2 = Double.parseDouble(numberHandler);
                                        result += number2;
                                        numberHandler = "";
                                        break;
                                }
                                break;
                            case ("-"):
                                switch (handler) {
                                    case (0):
                                        handler = 1;
                                        number1 = Double.parseDouble(numberHandler);
                                        result = number1;
                                        numberHandler = "";
                                        break;
                                    case (1):
                                        System.out.println(numberHandler);
                                        number2 = Double.parseDouble(numberHandler);
                                        result += number2;
                                        numberHandler = "";
                                        break;
                                }
                            case ("×"):
                                switch (handler) {
                                    case (0):
                                        handler = 1;
                                        numberHandler.substring(1);
                                        number1 = Double.parseDouble(numberHandler);
                                        result = number1;
                                        numberHandler = "";
                                        break;
                                    case (1):
                                        System.out.println(numberHandler);
                                        numberHandler.substring(1);
                                        number2 = Double.parseDouble(numberHandler);
                                        result *= number2;
                                        numberHandler = "";
                                        break;
                                }
                            case ("/"):
                                switch (handler) {
                                    case (0):
                                        handler = 1;
                                        numberHandler.substring(1);
                                        number1 = Double.parseDouble(numberHandler);
                                        result = number1;
                                        numberHandler = "";
                                        break;
                                    case (1):
                                        System.out.println(numberHandler);
                                        numberHandler.substring(1);
                                        number2 = Double.parseDouble(numberHandler);
                                        result /= number2;
                                        numberHandler = "";
                                        break;
                                }
                        }
                    } catch (Exception e) {
                        try {
                            switch (numberHandler.charAt(0)) {
                                case ('+'):
                                    System.out.println(numberHandler);
                                    number2 = Double.parseDouble(numberHandler);
                                    result += number2;
                                    numberHandler = "";
                                    break;
                                case ('-'):
                                    System.out.println(numberHandler);
                                    number2 = Double.parseDouble(numberHandler);
                                    result += number2;
                                    numberHandler = "";
                                    break;
                                case ('×'):
                                    numberHandler = numberHandler.substring(1);
                                    System.out.println(numberHandler + " X");
                                    number2 = Double.parseDouble(numberHandler);
                                    result *= number2;
                                    numberHandler = "";
                                    break;
                                case ('/'):
                                    numberHandler = numberHandler.substring(1);
                                    System.out.println(numberHandler + " /");
                                    number2 = Double.parseDouble(numberHandler);
                                    result /= number2;
                                    numberHandler = "";
                                    break;
                            }
                        } catch (Exception ignored) {
                        }
                    }

                    try {
                        if (!term.get(x).toString().equals("MINUS")) {
                            numberHandler += term.get(x).toString();
                        } else {
                            numberHandler += "-";
                        }
                    } catch (Exception ignored) {
                    }
                    x += 1;
                }
            }
            checkForDotNull();
        }
    }

    private void checkForDotNull(){
        stringResult = Double.toString(result);

        if(stringResult.endsWith(".0")){
            stringResult = stringResult.substring(0, stringResult.length() - 2);
        }

        showResult();
    }

    private void disEnableButtons(){
        Button Null = (Button) findViewById(R.id.button0);
        Button One = (Button) findViewById(R.id.button1);
        Button Two = (Button) findViewById(R.id.button2);
        Button Three = (Button) findViewById(R.id.button3);
        Button Four = (Button) findViewById(R.id.button4);
        Button Five = (Button) findViewById(R.id.button5);
        Button Six = (Button) findViewById(R.id.button6);
        Button Seven = (Button) findViewById(R.id.button7);
        Button Eight = (Button) findViewById(R.id.button8);
        Button Nine = (Button) findViewById(R.id.button9);
        Button Plus = (Button) findViewById(R.id.buttonPlus);
        Button Minus = (Button) findViewById(R.id.buttonMinus);
        Button Mal = (Button) findViewById(R.id.buttonMal);
        Button Geteilt = (Button) findViewById(R.id.buttonGeteilt);
        Button dot = (Button) findViewById(R.id.buttonComma);
        Button plusMinus = (Button) findViewById(R.id.buttonPlusMinus);
        Button Equals = (Button) findViewById(R.id.buttonEquals);
        if(buttonDisable){
            if (One.isEnabled()) {
                Null.setEnabled(false);
                One.setEnabled(false);
                Two.setEnabled(false);
                Three.setEnabled(false);
                Four.setEnabled(false);
                Five.setEnabled(false);
                Six.setEnabled(false);
                Seven.setEnabled(false);
                Eight.setEnabled(false);
                Nine.setEnabled(false);
                Plus.setEnabled(false);
                Minus.setEnabled(false);
                Mal.setEnabled(false);
                Geteilt.setEnabled(false);
                Equals.setEnabled(false);
                dot.setEnabled(false);
                plusMinus.setEnabled(false);
            }else{
                Null.setEnabled(true);
                One.setEnabled(true);
                Two.setEnabled(true);
                Three.setEnabled(true);
                Four.setEnabled(true);
                Five.setEnabled(true);
                Six.setEnabled(true);
                Seven.setEnabled(true);
                Eight.setEnabled(true);
                Nine.setEnabled(true);
                Plus.setEnabled(true);
                Minus.setEnabled(true);
                Mal.setEnabled(true);
                Geteilt.setEnabled(true);
                Equals.setEnabled(true);
                dot.setEnabled(true);
                plusMinus.setEnabled(true);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkForEastereggs(){
        TextView Output = (TextView) findViewById(R.id.Output);
        ImageView fbi = (ImageView) findViewById(R.id.fbi);

        try {
            switch(stringResult){
                case("-4"):
                    Output.setText("Wann versteht ihr es endlich? Die Lösung ist immer -4!");
                    easteregg = true;
                    break;
                case("328"):
                    Output.setText("Kurze Schweigeminute für Amelie, die schon seit 3:28 Uhr ihren eigenen Stuhl hat");
                    easteregg = true;
                    break;
                case("69"):
                    Output.setText("Schnell, versteck dich, sonst findet dich noch der Gaymaster!");
                    easteregg = true;
                    break;
                case("88"):
                    fbi.setVisibility(View.VISIBLE);
                    easteregg = true;
                    break;
                case("420"):
                    Output.setText("Wann Bubatz legal?");
                    easteregg = true;
            }
        }catch(Exception e){}
    }

    private void plusMinus(){
        TextView Output = (TextView) findViewById(R.id.Output);
        String cache = Output.getText().toString();

        if(term.size() != 0) {
            if (term.get(term.size() - 1).toString().equals("+") || term.get(term.size() - 1).toString().equals("-") || term.get(term.size() - 1).toString().equals("×") || term.get(term.size() - 1).toString().equals("/")) {
                term.add("MINUS");
                minusHandler = true;
                cache += "(-)";
                i++;
                Output.setText(cache);
            }else{
                if (!minusHandler) {
                    minusHandler = true;
                    term.add(term.size() - plusMinusHandler, "MINUS");
                    String newOutput = new StringBuilder(cache).insert(term.size() - plusMinusHandler - 1, "-").toString();
                    Output.setText(newOutput);
                    i++;
                }else{
                    minusHandler = false;
                    term.remove(term.size() - plusMinusHandler - 1);
                    String newOutput = new StringBuilder(cache).deleteCharAt(term.size() - plusMinusHandler).toString();
                    i--;
                    Output.setText(newOutput);
                }
            }
        }else{
            minusHandler = true;
            term.add("MINUS");
            Output.setText("-");
            i++;
        }
    }

    private void dot(){
        changeTerm(".");
    }

    private void vibrate(){
        AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Vibrator vibrator;
        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        switch( audio.getRingerMode() ){
            case AudioManager.RINGER_MODE_NORMAL:
            case AudioManager.RINGER_MODE_VIBRATE:
                vibrator.vibrate(70);
                break;
            case AudioManager.RINGER_MODE_SILENT:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars(){
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }
}