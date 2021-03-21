package capitaly.field;
import capitaly.player.Player;

abstract public class Field {
    protected int cost;

    protected Field(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    abstract public void visit(Player player);
}
