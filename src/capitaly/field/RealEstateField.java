package capitaly.field;

import capitaly.player.Player;

public class RealEstateField extends Field {
    protected Player owner;
    protected boolean hasHouse;

    public RealEstateField(int cost) {
        super(cost);

        this.owner = null;
        this.hasHouse = false;
    }

    @Override
    public void visit(Player player) {
        player.visitField(this);
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean hasHouse() {
        return this.hasHouse;
    }

    public void setHasHouse(boolean hasHouse) {
        this.hasHouse = hasHouse;
    }
}
