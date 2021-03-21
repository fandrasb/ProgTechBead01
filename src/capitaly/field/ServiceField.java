package capitaly.field;

import capitaly.player.Player;

public class ServiceField extends Field {
    public ServiceField(int cost) {
        super(cost);
    }

    @Override
    public void visit(Player player) {
        player.visitField(this);
    }
}
