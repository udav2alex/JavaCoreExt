package homework1.obstacles;

import homework1.competitors.Competitor;

public class JoggingTrack extends Obstacle {
    private int distance;

    public JoggingTrack(int distance) {
        this.distance = distance;
    }

    @Override
    public void dealWith(Competitor somebody) {
        System.out.println(somebody.getType() + " " + somebody.getName() +
                " is dealing with " +
                this.getClass().getSimpleName());
        somebody.run(distance);
    }
}
