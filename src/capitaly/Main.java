package capitaly;

import capitaly.field.RealEstateField;
import capitaly.field.Field;
import capitaly.player.GreedyPlayer;
import capitaly.player.Player;

public class Main {

    public static void main(String[] args) {
        Field field = new RealEstateField(500);
        Player player = new GreedyPlayer("Donald J. Trump");

        field.visit(player);

        System.out.println(player.getName() + player.getBalance());
        System.out.println(field.getCost());
    }
}
