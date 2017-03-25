package com.realstate.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.realstate.dao.GenericDao;
import com.realstate.entity.AbstractEntity;

@Repository
@Qualifier("genericDao")
public class GenericDaoImpl implements GenericDao {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public <E extends AbstractEntity> E create(E model) {
		entityManager.persist(model);
		return model;
	}

	@Override
	public <E extends AbstractEntity> E update(E model) {
		return entityManager.merge(model);
	}

	@Override
	public <E extends AbstractEntity> void delete(E model) {
		 entityManager.remove(model);

	}

	@Override
	public <E extends AbstractEntity> E getById(Class<E> modelClass, Serializable id) {
			return entityManager.find(modelClass, id);
	}

	@Override
	public <E extends AbstractEntity> void deleteById(Class<E> modelClass, Serializable id) {
		final E entity = getById(modelClass,id);
		entityManager.remove(entity);

	}

	@Override
	public <E extends AbstractEntity> List<E> getAll(Class<E> model) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(model);
		criteriaQuery.from(model);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public int updateByNamedQuery(String queryName, Map<String, Object> parameters) {
		final Query query = entityManager.createNamedQuery(queryName); 
		if(parameters != null) {
			for(final Entry<String,Object> parameter : parameters.entrySet()) {
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
		}
		return query.executeUpdate();
	}

	@Override
	public <E extends AbstractEntity> List<E> getResultByNamedQuery(String queryName, Map<String, Object> parameters) {
		final Query query = entityManager.createNamedQuery(queryName); 
		if(parameters != null) {
			for(final Entry<String,Object> parameter : parameters.entrySet()) {
				query.setParameter(parameter.getKey(), parameter.getValue());
			}
		}
		return query.getResultList();
	}

}
