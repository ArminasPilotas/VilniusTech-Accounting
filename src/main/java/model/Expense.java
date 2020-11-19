package model;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Expense implements Serializable {
    private String name;
    private ExpenseType expenseType;
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

    public Expense(String name, ExpenseType expenseType, String amount, Date date, int id, Set<Category> categories) {
        this.name = name;
        this.expenseType = expenseType;
        this.amount = amount;
        this.date = date;
        this.id = id;
        this.categories = categories;
    }

    public Expense() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setExpenseType(ExpenseType expenseType) {
        this.expenseType = expenseType;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ExpenseType getExpenseType() {
        return expenseType;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return
                expenseType  + "Amount -"+amount +"$ DATE " +  date.toString();
    }
}
