package homework1.competitors;

public class Cat implements Competitor {
    private int jumpMaxHeight = 3;
    private int runMaxDistance = 1000;
    private boolean onDistance = true;
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getJumpMaxHeight() {
        return jumpMaxHeight;
    }

    public int getRunMaxDistance() {
        return runMaxDistance;
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void setOnDistance(boolean onDistance) {
        this.onDistance = onDistance;
    }

    @Override
    public String toString() {
        return getType() + " " + getName();
    }
}