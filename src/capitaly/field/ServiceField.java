package capitaly.field;

import capitaly.player.Player;

public class ServiceField extends Field {
    public ServiceField(final int cost) {
        super(cost);
    }

    @Override
    public void visit(final Player player) {
        player.visitField(this);
    }
}
