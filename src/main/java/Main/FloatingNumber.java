package Main;

public class FloatingNumber {
    //Actual value of the floating number
    int value;
    //Colour of the floating number
    String colour;
    //Coordination of the floating number
    int ypos;
    int xpos;
    //the speed in which the number 'floats'
    int yspeed;

    public FloatingNumber(int xStart, int yStart){
        ypos = yStart;
        xpos = xStart;
    }

    public int retrieveHeight() {
        return ypos;
    }

    public int retrieveValue() {
        return value;
    }

    public String retrieveColour() {
        return colour;
    }

    public void increaseHeight() {
        ypos += yspeed;
    }


}
