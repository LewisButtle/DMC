package Main;

import java.awt.*;
import java.util.SplittableRandom;

//A class to store and handle all interactions with an individual floating number
public class FloatingNumber {
    //Actual value of the floating number
    private int value;
    //Colour of the floating number
    private Color colour;
    private int colourValue;
    //Coordination of the floating number
    private int ypos;
    private int xpos;
    //The original X position, to help 'float' around this axis.
    private int xposOriginal;
    //the speed in which the number 'floats'
    private int yspeed;
    private int xspeed;
    //score worth of the floating number
    private int score;
    //The randomized barrier of the x axis in which the number will 'float' between.
    private int barrier;

    //Initialise a floating number
    //'xStart': The starting 'x' coordinate
    //'yStart': The starting 'y' coordinate
    //'colourtype': The chosen colour of the number(0 for green, 1 for amber, 2 for red)
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

    //Specifically set or change variables for the floating number
    //'colourset': Chosen actual colour (green, orange, red)
    //'yspeedset': The vertical speed of the floating number
    //'xspeedset': The speed in which the number will float side to side
    //'scoreset': The amount of score a number is worth
    //'valueset': The actual value of the number
    private void setNumber(Color colourset, int yspeedset, int scoreset, int valueset, int xspeedset) {
        colour = colourset;
        yspeed = yspeedset;
        score = scoreset;
        value = valueset;
        xspeed = xspeedset;
        //This barrier is the random horizontal length the number will 'bounce' between as it travels up
        barrier = randomNumber(10, 70);
    }

    ////'Getter' functions////
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

    //Move the number's coordinates by one 'frame', by applying both x and y speed to the current coordinates
    public void increaseHeight() {
        if(xpos > xposOriginal + barrier || xpos < xposOriginal - barrier) {
            xspeed *= -1;
        }
        xpos += xspeed;
        ypos -= yspeed;
    }

    //Check if a given number matches the floating number's value
    //'answer': The answer being compared to the actual value
    public boolean checkAnswer(int answer) {
        return value == answer;
    }

    //Produce a random integer between two given values
    //'min': The minimum possible random number
    //'max': The maximum possible random number
    private int randomNumber(int min, int max){
        return new SplittableRandom().nextInt(min, max+1);
    }
}
