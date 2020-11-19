package hibernate.service;

import model.Income;
import model.IncomeType;
import org.hibernate.Session;

import java.util.Date;

public class IncomesService extends Service<Income> {
    public IncomesService(Session session) {
        super(session, Income.class);
    }
    public Income create(String name,IncomeType incomeType,String amount,Date date){
        return update(new Income(),name,incomeType,amount,date);
    }
    public Income update(Income income, String name, IncomeType incomeType,String amount,Date date){
        income.setName(name);
        income.setIncomeType(incomeType);
        income.setAmount(amount);
        income.setDate(date);
        return update(income);
    }
}
