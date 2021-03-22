package capitaly.field;
import capitaly.player.Player;

abstract public class Field {
    protected final int cost;

    protected Field(final int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    abstract public void visit(final Player player);
}
