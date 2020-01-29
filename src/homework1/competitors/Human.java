package homework1.competitors;

public class Human implements Competitor {
    private int jumpMaxHeight = 2;
    private int runMaxDistance = 3000;
    private boolean onDistance = true;
    private String name;

    public Human(String name) {
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
