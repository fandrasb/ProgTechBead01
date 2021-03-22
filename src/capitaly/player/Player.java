package capitaly.player;

import capitaly.field.LuckField;
import capitaly.field.RealEstateField;
import capitaly.field.ServiceField;

abstract public class Player {
    protected final String name;
    protected int balance;

    public Player(String name) {
        this.name = name;
        this.balance = 10000;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(final int balance) {
        this.balance = balance;
    }

    abstract protected void setStateOnFieldVisit();
    abstract protected boolean buyRealEstateRequired(RealEstateField field);
    abstract protected boolean buyHouseRequired(RealEstateField field);

    public void visitField(ServiceField field) {
        setStateOnFieldVisit();
        this.balance -= field.getCost();
    }

    public void visitField(LuckField field) {
        setStateOnFieldVisit();
        this.balance += field.getCost();
    }

    public void visitField(RealEstateField field) {
        setStateOnFieldVisit();

        if (field.getOwner() == null) {
            if (!buyRealEstateRequired(field)) {
                return;
            }

            buyRealEstate(field);
            return;
        }

        if (field.getOwner() != this) {
            payOwner(field);
            return;
        }

        if (!field.hasHouse()) {
            if (!buyHouseRequired(field)) {
                return;
            }

            buyHouse(field);
        }
    }

    protected void buyRealEstate(RealEstateField field) {
        if (balance >= field.getCost()) {
            balance -= field.getCost();
            field.setOwner(this);
        }
    }

    protected void buyHouse(RealEstateField field) {
        if (balance >= field.getHouseCost()) {
            balance -= field.getHouseCost();
            field.setHasHouse(true);
        }
    }

    protected void payOwner(RealEstateField field) {
        balance -= field.getPaymentAmt();
        field.getOwner().setBalance(
            field.getOwner().getBalance() + field.getPaymentAmt()
        );
    }
}
