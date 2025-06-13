public class MainTimer {

    /**
     * Runs the given Runnable (your main logic),
     * measures and prints the total execution time in seconds (with milliseconds).
     */
    public static void runWithTiming(Runnable mainLogic) {
        long startTime = System.nanoTime();

        // Run your main program logic
        mainLogic.run();

        long endTime = System.nanoTime();
        double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.printf("Total execution time: %.3f seconds%n", elapsedSeconds);
    }
}