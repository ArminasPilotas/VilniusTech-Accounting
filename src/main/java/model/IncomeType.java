package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class IncomeType implements Serializable {
    private String incomeName;
    private String incomeCode;
    private int id;
    private Set<Income> incomes = new HashSet<>();

    public IncomeType(String incomeName, String incomeCode, int id, Set<Income> incomes) {
        this.incomeName = incomeName;
        this.incomeCode = incomeCode;
        this.id = id;
        this.incomes = incomes;
    }

    public IncomeType() {
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public String getIncomeCode() {
        return incomeCode;
    }

    public void setIncomeCode(String incomeCode) {
        this.incomeCode = incomeCode;
    }

    @Override
    public String toString() {
        return
                "Income Name: " + incomeName + " ";

    }
}
