package Main;

import com.udojava.evalex.Expression;

import java.util.ArrayList;
import java.util.Arrays;

public class ExpressionSetup {
    private String expression;
    private ArrayList<String> current = new ArrayList<String>(Arrays.asList( "0", "+", "1"));
    private String value;
    private boolean ready = true;
    String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
    String[] operators = {"+","-","*"};

    //Instantiator, sets original value and expression to 1.
    public ExpressionSetup() {
        expression = "1";
        value = "1";
    }
    
    //Gets the current expression for displaying in graphics
    public String getExpression() {
        return expression;
    }

    //Gets the current actual value of the expression
    public String getValue() {
        expression = value;
        return value;
    }

    // Get the ready state of the expression, is it ready to be evaluated?
    public boolean getReady() {
        return ready;
    }

    //Validates, and adds an integer to the expression
    public void addValue(String newVal) {
        if (Arrays.asList(numbers).contains(expression.substring(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1) + newVal;
        }
        else {
            expression += newVal;
        }
        
        current.set(2, newVal);
        System.out.println("EXPRESSION: " + expression);
        ready = true;
    }

    //Validates, and adds an operator to the expression
    public void addOperator(String newOperator) {
        if (Arrays.asList(operators).contains(expression.substring(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1) + newOperator; 
        }
        else {
            evaluateCurrent();
            expression += newOperator;
        }
        current.set(1, newOperator);
        System.out.println("EXPRESSION: " + expression);
        ready = false;
    }

    //Evaluates the value of the current expression.
    public void evaluateCurrent(){
        System.out.println("EXPRESSION: " + expression);
        StringBuilder build = new StringBuilder();
        for (String s : current) {
            build.append(s);
        }
        System.out.println("SOLVING = " + build.toString());
        value = new Expression(build.toString()).eval().toPlainString();
        current.set(0, value);

    }

}
