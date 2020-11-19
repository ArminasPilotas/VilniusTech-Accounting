package hibernate.service;

import model.Category;
import model.CompanyUser;
import model.Expense;
import model.Income;
import org.hibernate.Session;

import java.util.Set;

public class CategoryService extends Service<Category> {
    public CategoryService(Session session) {
        super(session, Category.class);
    }
    public Category create(String name,Set<CompanyUser> companyUsers,Set<Income> incomes,Set<Expense> expenses){
        return update(new Category(),name,companyUsers,incomes,expenses);
    }
    public Category update(Category category, String name, Set<CompanyUser> companyUsers, Set<Income> incomes, Set<Expense> expenses){
        category.setName(name);
        category.setCompanyUsers(companyUsers);
        category.setIncomes(incomes);
        category.setExpenses(expenses);
        return update(category);
    }
}
