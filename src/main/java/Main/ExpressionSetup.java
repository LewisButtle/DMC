package Main;

import com.udojava.evalex.Expression;

import java.util.ArrayList;
import java.util.Arrays;

//Class used to store and handle all interactions with expressions
public class ExpressionSetup {
    //to store the entire expression for display
    private String expression;
    //to store the current parsing equation, set to the basic '0+1'
    private ArrayList<String> current = new ArrayList<String>(Arrays.asList( "0", "+", "1"));
    //to store the actual value of the current expression
    private String value;
    //A boolean value stating if the expression is able to give an answer
    private boolean ready;
    //Detects when an expression has just been evaluated. Used to stop user overwriting answer.
    private boolean evalCheck;
    //Number of expressions used in the current calculation.
    private int expressionScore;
    //An array of allowed numbers
    final String[] numbers = {"0","1","2","3","4","5","6","7","8","9"};
    //An array of allowed operators
    final String[] operators = {"+","-","*"};

    

    //Instantiator, sets original value and expression to 1.
    public ExpressionSetup() {
        expression = "1";
        value = "1";
        ready = true;
        evalCheck = false;
        expressionScore = 0;
    }

    //Gets the current actual value of the expression
    public String getValue() {
        return value;
    }

    //Return the score after calculating the size of the expression, for the floating numbers game
    public int getExpressionSize() {
        return expressionScore-100;
    }

    //adds a new input, decides what to do with it
    public String add(String input) {
        //If the input is a number...
        if (Arrays.asList(numbers).contains(input)) {
            addValue(input);
            return expression;
		}
        //If the input is an operator...
		else if (Arrays.asList(operators).contains(input)) {
            addOperator(input);
            return expression;
		}
        //If the input is '=' and the expression is ready, evaluate the expression.
		else if (input.equals("=") && ready) {
			evaluateCurrent();
            evalCheck = true;
            expression = value;
			return getValue();
        }
        return expression;
    }

    //Check if a character is able to be processed by the expression class.
    public boolean check(String input) {
        if (Arrays.asList(numbers).contains(input) || Arrays.asList(operators).contains(input) || input.equals("=")) {
            return true;
        }
        return false;
    }

    //Reset the count of how large the expression is
    public void resetExpressionCounter(){
        expressionScore = 0;
    }

    //Resets the expression
    public void reset() {
        current = new ArrayList<String>(Arrays.asList( "0", "+", "1"));
        expression = "1";
        value = "1";
        ready = true;
        evalCheck = false;
    }
    //Validates, and adds an integer to the expression
    public void addValue(String newVal) {
        if(!evalCheck) {
            if (Arrays.asList(numbers).contains(expression.substring(expression.length() - 1))) {
                expression = expression.substring(0, expression.length() - 1) + newVal;
            }
            else {
                expression += newVal;
            }
            current.set(2, newVal);
            ready = true;
        }
    }

    //Validates, and adds an operator to the expression
    public void addOperator(String newOperator) {
        if (Arrays.asList(operators).contains(expression.substring(expression.length() - 1))) {
            expression = expression.substring(0, expression.length() - 1) + newOperator; 
        }
        else {
            if(expressionScore < 500) {
                expressionScore+=100;
            }
            evaluateCurrent();
            expression += newOperator;
        }
        current.set(1, newOperator);
        //Show that the expression is not ready to be evaluated, as it ends in an operator
        ready = false;
        evalCheck = false;
    }

    //Evaluates the value of the current expression, and resets the current expression
    public void evaluateCurrent(){
        StringBuilder build = new StringBuilder();
        for (String s : current) {
            build.append(s);
        }
        value = new Expression(build.toString()).eval().toPlainString();
        if (10000 < Integer.parseInt(value) || Integer.parseInt(value) < -10000) {
            reset();
        }
        current.set(0, value);
        current.set(1, "*");
        current.set(2, "1");

    }

}
