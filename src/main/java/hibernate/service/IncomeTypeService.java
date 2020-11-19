package hibernate.service;

import model.IncomeType;
import org.hibernate.Session;

public class IncomeTypeService extends Service<IncomeType> {
    public IncomeTypeService(Session session) {
        super(session, IncomeType.class);
    }
    public IncomeType create(String incomeName,String incomeCode){
        return update(new IncomeType(),incomeName,incomeCode);
    }
    public IncomeType update(IncomeType incomeType,String incomeName,String incomeCode){
        incomeType.setIncomeCode(incomeCode);
        incomeType.setIncomeName(incomeName);
        return update(incomeType);
    }
}
