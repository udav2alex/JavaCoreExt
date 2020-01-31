package homework1;

import homework1.competitors.Cat;
import homework1.competitors.Competitor;
import homework1.competitors.Human;
import homework1.competitors.Robot;
import homework1.obstacles.JoggingTrack;
import homework1.obstacles.Obstacle;
import homework1.obstacles.Wall;

public class Main {
    public static void main(String[] args) {

        Competitor[] competitors = new Competitor[]{
                new Human("Ivan Vasilievitch"),
                new Cat("Vaska"),
                new Robot("R2D2"),
                new Human("Vasiliy Ivanovitch"),
                new Cat("Hvost")
                };

        Obstacle[] obstacles = new Obstacle[]{
                new Wall(1),
                new JoggingTrack(1000),
                new Wall(2),
                new JoggingTrack(2000),
                new Wall(1)
        };

        for (Competitor competitor : competitors) {
            for (Obstacle obstacle: obstacles) {
                obstacle.dealWith(competitor);
                if (!competitor.isOnDistance()) {
                    System.out.println(competitor.toString() + " left the race!!!");
                    System.out.println("");
                    break;
                } else {
                    System.out.println("");
                }
            }
        }

        System.out.println("");
        System.out.println("--- Results ---");
        for (Competitor competitor: competitors) {
            if (competitor.isOnDistance()) {
                System.out.println(competitor.toString() + " finished the race! Cheers!");
            } else {
                System.out.println(competitor.toString() + " not finished the race...");
            }
        }
    }
}
