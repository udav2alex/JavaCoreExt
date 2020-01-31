package homework1.obstacles;

import homework1.competitors.Competitor;

public class Wall extends Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void dealWith(Competitor somebody) {
        System.out.println(somebody.getType() + " " + somebody.getName() +
                        " is dealing with " +
                        this.getClass().getSimpleName());
        somebody.jump(height);
    }
}