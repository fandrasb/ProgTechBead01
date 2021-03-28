package capitaly.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import capitaly.field.Field;
import capitaly.field.LuckField;
import capitaly.field.RealEstateField;
import capitaly.field.ServiceField;
import capitaly.player.CautiousPlayer;
import capitaly.player.GreedyPlayer;
import capitaly.player.Player;
import capitaly.player.TacticalPlayer;

public class Simulator {
    final List<Field> board;
    final List<Player> players;
    final List<Integer> diceRolls;
    int currentPlayerIndex;

    public Simulator(final String setupFilePath, final String diceRollsFilePath) {
        board = new ArrayList<>();
        players = new ArrayList<>();
        diceRolls = new ArrayList<>();
        currentPlayerIndex = 0;

        try {
            final var setupFile = new File(setupFilePath);
            final var setupScanner = new Scanner(setupFile);

            setupBoard(setupScanner);
            setupPlayers(setupScanner);

            setupScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + setupFilePath);
            System.exit(1);
        }

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

    public List<Field> getBoard() {
        return new ArrayList<>(board);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<Integer> getDiceRolls() {
        return new ArrayList<>(diceRolls);
    }

    protected Player runTurn(final int diceRoll) {
        final var player = players.get(currentPlayerIndex);

        // @todo

        endTurn();
        return null;
    }

    protected void endTurn() {
        currentPlayerIndex = players.size() % ++currentPlayerIndex;
    }

    protected void setupBoard(final Scanner scanner) {
        final var boardLength = readBoardLength(scanner);

        for (int i = 0; i < boardLength && scanner.hasNextLine(); ++i) {
            final var values = readValues(scanner);
            final var field = fieldFactory(values);

            board.add(field);
        }
    }

    protected void setupPlayers(final Scanner scanner) {
        while (scanner.hasNextLine()) {
            final var values = readValues(scanner);
            final var player = playerFactory(values);

            players.add(player);
        }
    }

    protected int readBoardLength(final Scanner scanner) {
        if (scanner.hasNextLine()) {
            return Integer.parseInt(scanner.nextLine());
        }

        return 0;
    }

    protected String[] readValues(final Scanner scanner) {
        return scanner.nextLine().split("\\s+");
    }

    protected Field fieldFactory(String[] values) {
        switch (values[0]) {
            case "Luck": {
                return new LuckField(Integer.parseInt(values[1]));
            }
            case "Service": {
                return new ServiceField(Integer.parseInt(values[1]));
            }
            case "RealEstate":
            default: {
                return new RealEstateField();
            }
        }
    }

    protected Player playerFactory(String[] values) {
        switch (values[1]) {
            case "Cautious": {
                return new CautiousPlayer(values[0]);
            }
            case "Tactical": {
                return new TacticalPlayer(values[0]);
            }
            case "Greedy":
            default: {
                return new GreedyPlayer(values[0]);
            }
        }
    }

    protected void setupDiceRolls(Scanner scanner) {
        while (scanner.hasNextLine()) {
            final var line = scanner.nextLine();
            System.out.println(line);
            diceRolls.add(Integer.parseInt(line));
        }
    }
}
