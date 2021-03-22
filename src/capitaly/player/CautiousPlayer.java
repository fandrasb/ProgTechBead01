package capitaly.player;

import capitaly.field.RealEstateField;

public class CautiousPlayer extends Player {
    protected int limit;

    public CautiousPlayer(String name) {
        super(name);
    }

    @Override
    protected void setStateOnFieldVisit() {
        limit = balance / 2;
    }

    @Override
    protected boolean buyRealEstateRequired(RealEstateField field) {
        return field.getHouseCost() > limit;
    }

    @Override
    protected boolean buyHouseRequired(RealEstateField field) {
        return field.getHouseCost() > limit;
    }
}
