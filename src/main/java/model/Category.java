package model;

import java.util.HashSet;
import java.util.Set;

public class Category {
    private String name;
    private Set<CompanyUser> companyUsers = new HashSet<>();
    private Set<Income> incomes = new HashSet<>();
    private Set<Expense> expenses = new HashSet<>();
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category() {
    }

    public Category(String name, Set<CompanyUser> companyUsers, Set<Income> incomes, Set<Expense> expenses) {
        this.name = name;
        this.companyUsers = companyUsers;
        this.incomes = incomes;
        this.expenses = expenses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CompanyUser> getCompanyUsers() {
        return companyUsers;
    }

    public void setCompanyUsers(Set<CompanyUser> companyUsers) {
        this.companyUsers = companyUsers;
    }

    public Set<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(Set<Income> incomes) {
        this.incomes = incomes;
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(Set<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return name;
    }
}
