package capitaly.player;

import capitaly.field.LuckField;
import capitaly.field.RealEstateField;
import capitaly.field.ServiceField;

abstract public class Player {
    protected int balance;
    protected String name;

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

    public void setBalance(int balance) {
        this.balance = balance;
    }

    abstract public void visitField(RealEstateField field);

    public void visitField(ServiceField field) {
        this.balance -= field.getCost();
    }

    public void visitField(LuckField field) {
        this.balance += field.getCost();
    }

    protected void buyRealEstate(RealEstateField realEstate) {
        if (balance >= 1000) {
            balance -= 1000;
            realEstate.setOwner(this);
        }
    }

    protected void buyHouse(RealEstateField realEstate) {
        if (balance >= 4000) {
            balance -= 4000;
            realEstate.setHasHouse(true);
        }
    }

    protected void payOwner(RealEstateField realEstate) {
        int payment = realEstate.hasHouse() ? 2000 : 500;
        balance -= payment;
        realEstate.getOwner().setBalance(
            realEstate.getOwner().getBalance() + payment
        );
    }
}
