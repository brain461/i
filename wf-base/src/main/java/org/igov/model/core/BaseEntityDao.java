package org.igov.model.core;

import org.igov.service.exception.EntityNotFoundException;
import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.util.List;

/**
 * P - type of primary key of entity
 * Убрать полностью не получилось
 * <p/>
 * use {@link GenericEntityDao} instead if possible
 */
@Repository
public class BaseEntityDao<P extends Serializable> {

    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity<P>> T findById(Class<T> entityType, P id) {
        T entity = (T) getSession().get(entityType, id);

        if (entity == null) {
            throw new EntityNotFoundException(id);
        }

        return entity;
    }

    private <T extends Entity<P>> boolean hasOrderField(Class<T> entityClass, String fieldName) {
        PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(entityClass);

        boolean res = false;
        for (PropertyDescriptor pd : propertyDescriptors) {
            if (pd.getName().equals(fieldName)) {
                res = true;
                break;
            }
        }

        return res;
    }

    @SuppressWarnings("unchecked")
    public <T extends Entity<P>> List<T> findAll(Class<T> entityType) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityType);

        final String orderFieldName = "order";
        if (hasOrderField(entityType, orderFieldName)) {
            criteria.addOrder(Order.asc(orderFieldName));
        }

        return criteria.getExecutableCriteria(getSession()).list();
    }
    
    @SuppressWarnings("unchecked")
    public <T extends Entity<P>> List<T> findAll(Class<T> entityType, String orderFieldName) {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityType);
        if (hasOrderField(entityType, orderFieldName)) {
            criteria.addOrder(Order.asc(orderFieldName));
        }

        return criteria.getExecutableCriteria(getSession()).list();
    }

    public <T extends Entity<P>> void delete(T entity) {
        getSession().delete(entity);
    }

    public <T extends Entity<P>> T saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        return entity;
    }

    public <T extends Entity<P>> void saveOrUpdate(T[] entities) {
        for (T e : entities) {
            saveOrUpdate(e);
        }
    }
}
