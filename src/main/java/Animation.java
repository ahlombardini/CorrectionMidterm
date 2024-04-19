

@FunctionalInterface
public interface Animation<T> {

    T apply(double x, double y, double t);

    //Adapter design pattern
    static <T> Animation<T> ofImage(Image<T> img){
        return (x, y, t) -> img.apply(x, y);
    }

    //Decorator design pattern
    default Animation<T> moving(double vX, double vY){
        return (x, y, t) -> apply(x + t * vX, y + t * vY, t);
    }

    //Adapter design pattern
    default Image<T> snapshot(double tS){
        return (x, y, t) -> apply(x, y, tS);
    }

    //Composite design pattern
    default Animation<T> alternatingWith(Animation<T> other){
        return (x, y, t) -> (int)t % 2 == 0 ? apply(x, y, t) : other.apply(x, y, t);
    }
}
