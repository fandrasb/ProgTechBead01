package capitaly.field;

import capitaly.player.Player;

public class RealEstateField extends Field {
    protected final int houseCost;
    protected Player owner;
    protected boolean hasHouse;

    public RealEstateField() {
        super(1000);

        this.houseCost = 4000;
        this.owner = null;
        this.hasHouse = false;
    }

    @Override
    public void visit(final Player player) {
        player.visitField(this);
    }

    public int getHouseCost() {
        return houseCost;
    }

    public Player getOwner() {
        return this.owner;
    }

    public void setOwner(final Player owner) {
        this.owner = owner;
    }

    public boolean hasHouse() {
        return this.hasHouse;
    }

    public void setHasHouse(final boolean hasHouse) {
        this.hasHouse = hasHouse;
    }

    public int getPaymentAmt() {
        return hasHouse() ? 2000 : 500;
    }
}
