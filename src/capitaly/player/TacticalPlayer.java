package capitaly.player;

import capitaly.field.RealEstateField;

public class TacticalPlayer extends Player {
    protected boolean oddTurn;

    public TacticalPlayer(String name) {
        super(name);

        oddTurn = true;
    }

    @Override
    protected void setStateOnFieldVisit() {
        oddTurn = !oddTurn;
    }

    @Override
    protected boolean buyRealEstateRequired(RealEstateField field) {
        return oddTurn;
    }

    @Override
    protected boolean buyHouseRequired(RealEstateField field) {
        return oddTurn;
    }
}
