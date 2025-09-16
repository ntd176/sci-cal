package com.example.scientificcalculator;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivitySciCalc";
    // Khai báo biến cho thành phần sẽ được tương tác
    private TextView previousCalculationView;
    private EditText displayEditText;

    // Khai báo biến lưu trữ của máy tính
    private double memoryValue = 0;
    private boolean inDegreesMode = true;   // mặc định Deg

    private Button degButton;
    private Button radButton;
    private Button gradButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        previousCalculationView = findViewById(R.id.previousCalculationView);
        displayEditText = findViewById(R.id.displayEditText);

        displayEditText.setShowSoftInputOnFocus(false);

        if (findViewById(R.id.button25) != null) {
            degButton = findViewById(R.id.button25);
        }
        if (findViewById(R.id.button26) != null) {
            radButton = findViewById(R.id.button26);
        }
        if (findViewById(R.id.button27) != null) {
            gradButton = findViewById(R.id.button27);
        }
        updateAngleModeDisplay();
    }

    private void updateAngleModeDisplay() {
        if (inDegreesMode) {
            mXparser.setDegreesMode();
            Log.d(TAG, "Angle mode set to DEGREES");
            Toast.makeText(this, "Degrees Mode", Toast.LENGTH_SHORT).show();
        } else {
            mXparser.setRadiansMode();
            Log.d(TAG, "Angle mode set to RADIANS");
            Toast.makeText(this, "Radian Mode", Toast.LENGTH_SHORT).show();
        }
    }

    public void appendToDisplay(String stringToAdd) {
        String oldString = displayEditText.getText().toString();
        int cursorPos = displayEditText.getSelectionStart(); // vị trí con trỏ

        if (oldString.equals(getString(R.string.errorText)) || oldString.equals(getString(R.string.nanText))) {
            displayEditText.setText(stringToAdd);
            displayEditText.setSelection(stringToAdd.length());
        } else {
            String leftPart = oldString.substring(0, cursorPos);
            String rightPart = oldString.substring(cursorPos);
            displayEditText.setText(String.format("%s%s%s", leftPart, stringToAdd, rightPart));
            displayEditText.setSelection(cursorPos + stringToAdd.length());
        }
    }

    // --- Nút số (0-9) ---
    public void zeroBTNPush(View view) {
        appendToDisplay(getString(R.string.zeroText));
    }

    public void oneBTNPush(View view) {
        appendToDisplay(getString(R.string.oneText));
    }

    public void twoBTNPush(View view) {
        appendToDisplay(getString(R.string.twoText));
    }

    public void threeBTNPush(View view) {
        appendToDisplay(getString(R.string.threeText));
    }

    public void fourBTNPush(View view) {
        appendToDisplay(getString(R.string.fourText));
    }

    public void fiveBTNPush(View view) {
        appendToDisplay(getString(R.string.fiveText));
    }

    public void sixBTNPush(View view) {
        appendToDisplay(getString(R.string.sixText));
    }

    public void sevenBTNPush(View view) {
        appendToDisplay(getString(R.string.sevenText));
    }

    public void eightBTNPush(View view) {
        appendToDisplay(getString(R.string.eightText));
    }

    public void nineBTNPush(View view) {
        appendToDisplay(getString(R.string.nineText));
    }

    public void decimalBTNPush(View view) {
        appendToDisplay(getString(R.string.decimalText));
    }

    // --- Nút phép toán cơ bản ---
    public void divideBTNPush(View view) {
        appendToDisplay(getString(R.string.divideText));
    }

    public void multiplyBTNPush(View view) {
        appendToDisplay(getString(R.string.multiplyText));
    }

    public void subtractBTNPush(View view) {
        appendToDisplay(getString(R.string.subtractText));
    }

    public void addBTNPush(View view) {
        appendToDisplay(getString(R.string.addText));
    }

    // --- Nút chức năng cơ bản ---
    public void clearBTNPush(View view) {
        displayEditText.setText("");
        previousCalculationView.setText("");
        Log.d(TAG, "Display cleared");
    }

    public void backspaceBTNPush(View view) {
        int cursorPos = displayEditText.getSelectionStart();
        String currentText = displayEditText.getText().toString();

        if (currentText.equals(getString(R.string.errorText)) || currentText.equals(getString(R.string.nanText))) {
            displayEditText.setText("");
            return;
        }

        if (cursorPos > 0 && !currentText.isEmpty()) {
            SpannableStringBuilder ssb = new SpannableStringBuilder(currentText);
            ssb.delete(cursorPos - 1, cursorPos);
            displayEditText.setText(ssb);
            displayEditText.setSelection(cursorPos - 1);
        }
    }

    public void percentBTNPush(View view) {
        appendToDisplay(getString(R.string.percentText));
    }

    public void parOpenBTNPush(View view) {
        appendToDisplay(getString(R.string.parenthesesOpenText));
    }

    public void parCloseBTNPush(View view) {
        appendToDisplay(getString(R.string.parenthesesCloseText));
    }

    // --- Nút chức năng khoa học ---
    public void squareRootBTNPush(View view) {
        appendToDisplay("sqrt(");
    }

    public void piBTNPush(View view) {
        appendToDisplay("pi(");
    }

    public void sinBTNPush(View view) {
        appendToDisplay("sin(");
    }

    public void cosBTNPush(View view) {
        appendToDisplay("cos(");
    }

    public void tanBTNPush(View view) {
        appendToDisplay("tan(");
    }

    public void coTanBTNPush(View view) {
        appendToDisplay("cot(");
    }

    public void lnBTNPush(View view) {
        appendToDisplay("ln(");
    }

    public void logBTNPush(View view) {
        appendToDisplay("log10(");
    }

    public void tenPowerXBTNPush(View view) {
        appendToDisplay("10^(");
    }

    public void absoluteValueXBTNPush(View view) {
        appendToDisplay("abs(");
    }

    public void factorialBTNPush(View view) {
        appendToDisplay("!");
    }

    public void xSquare2BTNPush(View view) {
        appendToDisplay("^(2)");
    }

    public void xPoweryBTNPush(View view) {
        appendToDisplay("^(");
    }

    // --- Chế độ góc ---
    public void deggressBTNPush(View view) {
        if (!inDegreesMode) {
            inDegreesMode = true;
            updateAngleModeDisplay();
        } else {
            Toast.makeText(this, "Already in Degrees Mode", Toast.LENGTH_SHORT).show();
        }
    }

    public void radianBTNPush(View view) {
        if (inDegreesMode) {
            inDegreesMode = false;
            updateAngleModeDisplay();
        } else {
            Toast.makeText(this, "Already in Radians Mode", Toast.LENGTH_SHORT).show();
        }
    }

    public void gradiansBTNPush(View view) {
        Toast.makeText(this, "Gradian mode not fully implemented for direct calculation mode switching", Toast.LENGTH_LONG).show();
        appendToDisplay("grad(");
        Log.w(TAG, "Gradian button pushed");
    }

    // --- Chức năng bộ nhớ ---
    public void memoryplusBTNPush(View view) {
        String currentExpression = displayEditText.getText().toString();
        if (currentExpression.isEmpty() || currentExpression.equals(getString(R.string.errorText)) || currentExpression.equals(getString(R.string.nanText))) {
            return;
        }

        String expressionToCalculate = preprocessExpressionForMxParser(currentExpression);
        Expression exp = new Expression(expressionToCalculate);
        if (exp.checkSyntax()) {
            double value = exp.calculate();
            if (!Double.isNaN(value) && !Double.isInfinite(value)) {
                memoryValue = memoryValue + value;
                Toast.makeText(this, "Added to memory. M = " + memoryValue, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Value " + value + " added to memory. New memoryValue: " + memoryValue);
            } else {
                Toast.makeText(this, "Cannot add invalid result to memory", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Expression error, cannot add to memory", Toast.LENGTH_SHORT).show();
        }
    }

    public void memoryrecallBTNPush(View view) {
        String memoryString;
        if (memoryValue == (long) memoryValue) {
            memoryString = String.format("%d", (long) memoryValue);
        } else {
            memoryString = String.valueOf(memoryValue);
        }
        displayEditText.setText(memoryString);
        displayEditText.setSelection(memoryString.length());
    }

    public void memoryclearBTNPush(View view) {
        memoryValue = 0;
        Toast.makeText(this, "Memory Clear", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "memory cleared");
    }

    private String preprocessExpressionForMxParser(String userInputExpression) {
        String expression = userInputExpression;

        expression = expression.replace(getString(R.string.divideText), "/");
        expression = expression.replace(getString(R.string.multiplyText), "*");

        if (expression.contains(getString(R.string.percentText))) {
            expression = expression.replace(getString(R.string.percentText), "/100");
        }
        Log.d(TAG, "Preprocessed expression: " + expression);
        return expression;
    }

    // --- Nút bằng ---
    public void equalsBTNPush(View view) {
        String userInputExpression = displayEditText.getText().toString();

        if (userInputExpression.isEmpty() || userInputExpression.equals(getString(R.string.errorText)) || userInputExpression.equals(getString(R.string.nanText))) {
            return;
        }

        previousCalculationView.setText(userInputExpression);

        String expressionToCalculate = preprocessExpressionForMxParser(userInputExpression);

        Log.d(TAG, "Expression for mXparser: " + expressionToCalculate);
        Expression exp = new Expression(expressionToCalculate);

        if (exp.checkSyntax()) {
            double result = exp.calculate();
            String resultString;

            if (Double.isNaN(result)) {
                resultString = getString(R.string.nanText);
            } else {
                if (result == (long) result) {
                    resultString = String.format("%d", (long) result);
                } else {
                    resultString = String.valueOf(result);
                }
            }
            displayEditText.setText(resultString);
        } else {
            String errorMessage = exp.getErrorMessage();
            Log.e(TAG, "Syntax Error: " + errorMessage);
            displayEditText.setText(getString(R.string.errorText));
        }
        displayEditText.setSelection(displayEditText.getText().length());
    }
}