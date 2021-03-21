package capitaly.player;

import capitaly.field.RealEstateField;

public class GreedyPlayer extends Player {
    public GreedyPlayer(String name) {
        super(name);
    }

    @Override
    public void visitField(RealEstateField field) {
        if (field.getOwner() == null) {
            buyRealEstate(field);
            return;
        }

        if (field.getOwner() != this) {
            payOwner(field);
            return;
        }

        if (!field.hasHouse()) {
            buyHouse(field);
        }
    }
}
