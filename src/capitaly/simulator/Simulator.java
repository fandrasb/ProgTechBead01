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

abstract public class Simulator {
    final List<Field> board;
    final List<Player> players;
    final List<Integer> playerPositions;
    int currentPlayerIndex;

    public Simulator(final String setupFilePath) {
        board = new ArrayList<>();
        players = new ArrayList<>();
        playerPositions = new ArrayList<>();
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
    }

    abstract public Player run();

    public List<Field> getBoard() {
        return new ArrayList<>(board);
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    protected Player runTurn(final int diceRoll) {
        final var player = players.get(currentPlayerIndex);
        final var field = board.get(getPlayerPosition(diceRoll));

        field.visit(player);

        if (player.getBalance() < 0) {
            return player;
        }
        endTurn();
        return null;
    }

    protected Integer getPlayerPosition(Integer diceRoll) {
        final var formerPosition = playerPositions.get(currentPlayerIndex);
        final var newPosition = ((formerPosition + diceRoll) % board.size());
        playerPositions.set(currentPlayerIndex, newPosition);

        return newPosition;
    }

    protected void endTurn() {
        ++currentPlayerIndex;
        currentPlayerIndex = currentPlayerIndex % players.size();
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
            playerPositions.add(0);
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
}
