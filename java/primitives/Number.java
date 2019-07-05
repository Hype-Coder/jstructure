package primitives;

import template.Primitive;

public abstract class Number extends Primitive implements Comparable {


    abstract primitives.Char charValue ();

    abstract primitives.ShortInt shortValue ();

    abstract primitives.Int intValue ();

    abstract primitives.LongLongInt longValue ();

    abstract primitives.Float floatValue ();

    abstract primitives.Double doubleValue ();

    abstract java.lang.Number getPrimitive ();


    protected abstract Number add(Number num);

    protected abstract Number sub(Number num);

    protected abstract Number molt(Number num);

    protected abstract Number div(Number num);

    protected abstract Number neg();

    protected abstract Number opp();


}
