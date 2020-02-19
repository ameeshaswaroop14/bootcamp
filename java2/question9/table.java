package java2.question9;

public class table extends furniture{
    String type;
    boolean fire;
    boolean stress;

    public String getType() {
        return type;
    }

    public table(String t, boolean f, boolean s)
    {
        this.type=t;
        this.fire=f;
        this.stress=s;
    }
    public boolean isFire() {
        return fire;
    }

    public boolean isStress() {
        return stress;
    }
    @Override
    public boolean checkStress()
    {
      return stress;
    }

    @Override
    public boolean checkfire() {
        return fire;
    }
}
