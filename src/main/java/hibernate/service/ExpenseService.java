package hibernate.service;

import model.Expense;
import model.ExpenseType;
import org.hibernate.Session;

import java.util.Date;

public class ExpenseService extends Service<Expense> {
    public ExpenseService(Session session) {
        super(session, Expense.class);
    }
    public Expense create(String name,ExpenseType expenseType,String amount,Date date){
        return update(new Expense(),name,expenseType,amount,date);
    }
    public Expense update(Expense expense, String name, ExpenseType expenseType, String amount, Date date){
        expense.setName(name);
        expense.setExpenseType(expenseType);
        expense.setAmount(amount);
        expense.setDate(date);
        return update(expense);
    }
}
