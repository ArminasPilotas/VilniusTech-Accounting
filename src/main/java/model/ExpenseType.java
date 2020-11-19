package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ExpenseType implements Serializable {
    private String expenseName;
    private String expenseCode;
    private Set<Expense> expenses = new HashSet<>();
    private int id;

    public ExpenseType(String expenseName, String expenseCode, Set<Expense> expenses, int id) {
        this.expenseName = expenseName;
        this.expenseCode = expenseCode;
        this.expenses = expenses;
        this.id = id;
    }

    public ExpenseType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getExpenseCode() {
        return expenseCode;
    }

    @Override
    public String toString() {
        return
                "Expense Name: " + expenseName + " ";
    }

    public void setExpenseCode(String expenseCode) {
        this.expenseCode = expenseCode;
    }
}
