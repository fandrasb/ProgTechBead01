package capitaly.field;

import capitaly.player.Player;

public class LuckField extends Field {
    public LuckField(int cost) {
        super(cost);
    }

    @Override
    public void visit(Player player) {
        player.visitField(this);
    }
}
