package capitaly.simulator;

import capitaly.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDiceRollSimulator extends Simulator {
    final List<Integer> diceRolls;

    public FileDiceRollSimulator(final String setupFilePath, final String diceRollsFilePath) {
        super(setupFilePath);

        diceRolls = new ArrayList<>();
        try {
            final var diceRollsFile = new File(diceRollsFilePath);
            final var diceRollsScanner = new Scanner(diceRollsFile);

            setupDiceRolls(diceRollsScanner);

            diceRollsScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + diceRollsFilePath);
            System.exit(1);
        }
    }

    @Override
    public Player run() {
        Player firstEliminatedPlayer = null;
        for (
            var i = 0;
            i < diceRolls.size() && firstEliminatedPlayer == null;
            ++i
        ) {
            firstEliminatedPlayer = runTurn(diceRolls.get(i));
        }

        return firstEliminatedPlayer;
    }

    protected void setupDiceRolls(Scanner scanner) {
        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            diceRolls.add(Integer.parseInt(line));
        }
    }
}
