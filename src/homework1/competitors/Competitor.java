package homework1.competitors;

public interface Competitor {
    // main methods
    default void jump(int height) {
        System.out.println(toString() +
                " want to jump " + height + " meters... " +
                check(height, getJumpMaxHeight()));
    }

    default void run(int distance) {
        System.out.println(toString() +
                " want to run " + distance + " meters... " +
                check(distance, getRunMaxDistance()));
    }

    // getters
    String getName();
    int getJumpMaxHeight();
    int getRunMaxDistance();
    boolean isOnDistance();
    void setOnDistance(boolean onDistance);


    // support methods
    default String getType() {
        return this.getClass().getSimpleName();
    }

    default String check(double goal, double restriction) {
        if (goal <= restriction) {
            return "Success! ;)";
        } else {
            setOnDistance(false);
            return "Fail! :(";
        }
    }
}
