package primitives;

import template.Primitive;

public abstract class Number extends Primitive implements Comparable {


    abstract Char charValue ();

    abstract ShortInt shortValue ();

    abstract Int intValue ();

    abstract LongLongInt longValue ();

    abstract Float floatValue ();

    abstract Double doubleValue ();

    abstract java.lang.Number getPrimitive ();


    protected abstract Number add(Number num);

    protected abstract Number sub(Number num);

    protected abstract Number molt(Number num);

    protected abstract Number div(Number num);

    protected abstract Number neg();

    protected abstract Number opp();


}
