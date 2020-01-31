package homework1.competitors;

public class Robot implements Competitor {
    private int jumpMaxHeight = 1;
    private int runMaxDistance = 100000;
    private boolean onDistance = true;
    private String id;

    public Robot(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return id;
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
