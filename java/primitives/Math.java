package primitives;


import java.math.BigInteger;

public class Math {


    public static Number add(Number a, Number b){

        return a.add(b);

    }

    public static Number sub(Number a, Number b){

        return a.sub(b);

    }

    public static Number div(Number a, Number b){

        return a.div(b);

    }

    public static Number opposite(Number a) {

        return a.opp();

    }

    public static Number negate(Number a) {

        return a.neg();

    }

    public static Number molt(Number a, Number b) {

        return a.molt(b);

    }

}