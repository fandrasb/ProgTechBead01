package capitaly.simulator;

import capitaly.player.Player;

import java.util.Random;

public class RandomDiceRollSimulator extends Simulator {
    final Random random;

    public RandomDiceRollSimulator(final String setupFilePath) {
        super(setupFilePath);

        random = new Random();
    }

    @Override
    public Player run() {
        Player firstEliminatedPlayer = null;

        while (firstEliminatedPlayer == null) {
            firstEliminatedPlayer = runTurn(getDiceRoll());
        }

        return firstEliminatedPlayer;
    }

    protected Integer getDiceRoll() {
        return random.nextInt(6) + 1;
    }
}
