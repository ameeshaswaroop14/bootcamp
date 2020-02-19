package java2.question5;

class tryingClone implements Cloneable {
    String name;
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}