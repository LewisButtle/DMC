package Main;

//A generic tuple class, to handle two abstract data types
public class Tuple<X,Y> {
    public final X first;
    public final Y second;
    
    public Tuple(X first, Y second) {
        this.first = first;
        this.second = second;
    }
}