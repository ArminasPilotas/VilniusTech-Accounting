package hibernate.service;

import model.CompanyUser;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class Service<T> {
    private final Session session;
    private final EntityManagerFactory entityManagerFactory;
    private final Class<T> clazz;

    public Service(Session session, Class<T> clazz) {
        this.session = session;
        this.clazz = clazz;
        entityManagerFactory = session.getEntityManagerFactory();
    }

    public T read(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery = criteriaQuery.select(root).where(
                criteriaBuilder.equal(root.get("id"),id)
        );
        TypedQuery<T> typedQuery=em.createQuery(criteriaQuery);
        return typedQuery.getSingleResult();
    }

    public List<T> readAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        criteriaQuery = criteriaQuery.select(root);
        TypedQuery<T> typedQuery=em.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public T update(T item) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        item = em.merge(item);
        em.persist(item);
        em.getTransaction().commit();
        em.close();

        return item;
    }

    public void delete(T item) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(item));
        em.getTransaction().commit();
        em.close();
    }
}
