package hibernate.service;

import model.ExpenseType;
import org.hibernate.Session;

public class ExpenseTypeService extends Service<ExpenseType> {
    public ExpenseTypeService(Session session) {
        super(session,ExpenseType.class);
    }
    public ExpenseType create(String expenseName, String expenseCode){
        return update(new ExpenseType(),expenseName,expenseCode);
    }
    public ExpenseType update(ExpenseType expenseType,String expenseName,String expenseCode){
        expenseType.setExpenseCode(expenseCode);
        expenseType.setExpenseName(expenseName);
        return update(expenseType);
    }
}
