package capitaly;

import capitaly.player.Player;
import capitaly.simulator.Simulator;

import java.io.File;

public class Main {
    private static final String RESOURCES_PATH =
        new File("resources").getAbsolutePath();
    private static final String TEST_RESOURCES_PATH = RESOURCES_PATH + "/test";

    private static String getTestFilePath(final String testFileNumber) {
        return TEST_RESOURCES_PATH + "/" + testFileNumber + "-test.txt";
    }

    private static String getTestDiceRollsFilePath(
        final String testDiceRollsFileNumber
    ) {
        return (
            TEST_RESOURCES_PATH +
            "/" +
            testDiceRollsFileNumber +
            "-dice-rolls-test.txt;"
        );
    }

    public static void main(String[] args) {
        final var simulator = new Simulator(
            getTestFilePath("01"),
            getTestDiceRollsFilePath("01")
        );

        System.out.println(simulator.getBoard().size());
        System.out.println(simulator.getPlayers().size());
        System.out.println(simulator.getDiceRolls());

        // @todo
        // final Player firstEliminated = simulator.run();
        // printResults(firstEliminated);
    }

    private static void printResults(final Player player) {

        System.out.println(
            "Simulation results:\nThe first player to be eliminated was:\n" +
            player.getName()
        );
    }
}
