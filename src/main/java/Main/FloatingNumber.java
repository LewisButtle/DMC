package Main;

import java.awt.*;
import java.util.SplittableRandom;

public class FloatingNumber {
    //Actual value of the floating number
    int value;
    //Colour of the floating number
    Color colour;
    int colourValue;
    //Coordination of the floating number
    int ypos;
    int xpos;
    //The original X position, to help 'float' around this axis.
    int xposOriginal;
    //the speed in which the number 'floats'
    int yspeed;
    int xspeed;
    //score worth of the floating number
    int score;
    //The randomized barrier of the x axis in which the number will 'float' between.
    int barrier;

    public FloatingNumber(int xStart, int yStart, int colourtype){
        ypos = yStart;
        xpos = xStart;
        xposOriginal = xStart;
        colourValue = colourtype;
        //To set colour, depending on int value of colour (0 for green, 1 for amber, 2 for red)
        switch(colourtype) {
            //green selected
            case 0:
            setNumber(Color.green, 3, 500, randomNumber(10, 50), 1);
            break;
            //amber selected
            case 1:
            setNumber(Color.orange, 2, 1000, randomNumber(51, 100), -1);
            break;
            //red selected
            case 2:
            setNumber(Color.red, 1, 2500, randomNumber(100, 500), 1);
            break;
        }
    }

    private void setNumber(Color colourset, int yspeedset, int scoreset, int valueset, int xspeedset) {
        colour = colourset;
        yspeed = yspeedset;
        score = scoreset;
        value = valueset;
        xspeed = xspeedset;
        barrier = randomNumber(10, 70);
    }

    public int retrieveYPosition() {
        return ypos;
    }

    public int retrieveXPosition() {
        return xpos;
    }

    public int RetrieveXPosOriginal(){
        return xposOriginal;
    }

    public int retrieveScore() {
        return score;
    }

    public int retrieveColourValue() {
        return colourValue;
    }

    public int retrieveValue() {
        return value;
    }

    public String retrieveStringValue() {
        return String.valueOf(value);
    }

    public Color retrieveColour() {
        return colour;
    }

    public void increaseHeight() {
        if(xpos > xposOriginal + barrier || xpos < xposOriginal - barrier) {
            xspeed *= -1;
        }
        xpos += xspeed;
        ypos -= yspeed;
    }

    public boolean checkAnswer(int answer) {
        if (value == answer) {
            return true;
        }
        else {
            return false;
        }
    }

    private int randomNumber(int min, int max){
        return new SplittableRandom().nextInt(min, max+1);
    }


}