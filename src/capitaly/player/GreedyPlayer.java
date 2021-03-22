package capitaly.player;

import capitaly.field.RealEstateField;

public class GreedyPlayer extends Player {
    public GreedyPlayer(String name) {
        super(name);
    }

    @Override
    protected void setStateOnFieldVisit() {}

    @Override
    protected boolean buyRealEstateRequired(RealEstateField field) {
        return true;
    }

    @Override
    protected boolean buyHouseRequired(RealEstateField field) {
        return true;
    }
}
