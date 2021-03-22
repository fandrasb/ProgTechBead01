package capitaly.field;

import capitaly.player.Player;

public class LuckField extends Field {
    public LuckField(final int cost) {
        super(cost);
    }

    @Override
    public void visit(final Player player) {
        player.visitField(this);
    }
}
