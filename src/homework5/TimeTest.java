package homework5;

import java.util.Arrays;

public class TimeTest {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static float[] whole = new float[SIZE];
    static float[] part1 = new float[HALF];
    static float[] part2 = new float[HALF];

    public static void main(String[] args) {
        processWholeArray();
        processArrayByParts();
    }

    static void processWholeArray() {
        Arrays.fill(whole, 1f);

        long start;
        start = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            whole[i] = (float) (whole[i]
                    * Math.sin(0.2f + i / 5)
                    * Math.cos(0.2f + i / 5)
                    * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis() - start);
    }

    static void processArrayByParts() {
        Arrays.fill(whole, 1f);

        long start;
        start = System.currentTimeMillis();

        System.arraycopy(whole, 0, part1, 0, HALF);
        System.arraycopy(whole, HALF, part2, 0, HALF);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < HALF; i++) {
                part1[i] = (float) (part1[i]
                        * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            int index = 0;
            for (int i = HALF; i < SIZE; i++) {
                part2[index] = (float) (part2[index]
                        * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));
                index++;
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(part1, 0, whole, 0, HALF);
        System.arraycopy(part2, 0, whole, HALF, HALF);

        System.out.println(System.currentTimeMillis() - start);
    }
}
