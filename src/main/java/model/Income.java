package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Income implements Serializable {
    private String name;
    private IncomeType incomeType;
    private String amount;
    private Date date=new Date();
    private int id;
    private Set<Category> categories = new HashSet<>();

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Income(String name, IncomeType incomeType, String amount, Date date, int id, Set<Category> categories) {
        this.name = name;
        this.incomeType = incomeType;
        this.amount = amount;
        this.date = date;
        this.id = id;
        this.categories = categories;
    }

    public Income() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                 incomeType + "Amount +"+amount +"$ DATE " +  date.toString();
    }
}
