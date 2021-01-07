package Main;

import com.udojava.evalex.Expression;
import java.util.Arrays;

public class ExpressionSetup {
    private String expression;
    private int value;
    String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
    String[] operators = {"+","-","*","="};
    private boolean ready = true;

    public ExpressionSetup() {
        expression = "1";
        value = 0;
    }
    
    public String getExpression() {
        return expression;
    }

    public int getValue() {
        return value;
    }

    public void addValue(String newVal) {
        if (Arrays.asList(numbers).contains(expression.substring(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1) + newVal;
            ready = true;
        }
        else {
            expression += newVal;
        }
        evaluateExpression();
    }

    public void addOperator(String newOperator) {
        if (Arrays.asList(operators).contains(expression.substring(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1) + newOperator;
            ready = false;
        }
        else {
            expression += newOperator;
        }
    }

    public void evaluateExpression() {
        
    }

}
