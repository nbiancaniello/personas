package com.mercel.personas.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.inssjp.commons.paging.PagingLoadConfig;
import com.mercel.personas.model.Persona;

public class HibernateDaoImpl implements PersonaDAO {
	
	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public List<Persona> findPersonas(PagingLoadConfig paginado, String sortField, String sortOrder, String keyword, String columnSearch) {
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Criteria consulta = session.createCriteria(Persona.class);
				
		if(!columnSearch.trim().equals("") && !keyword.trim().equals("")){
			consulta.add( Restrictions.like(columnSearch, keyword.toUpperCase()));
		}
		
		if(!sortField.trim().equals("") && !sortOrder.trim().equals("")){
			consulta.addOrder(sortOrder == "asc" ? Order.asc(sortField) : Order.desc(sortField));
		}

		consulta.setFirstResult(paginado.getOffset() * paginado.getLimit())
				.setMaxResults(paginado.getLimit());
//		Salida al EAGER
//		consulta.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		
		@SuppressWarnings("unchecked")
		List<Persona> listadoPersonas = consulta.list(); 
		session.close();
		return listadoPersonas;
	}

	@Override
	public Persona getById(int idPersona) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Criteria consulta = session.createCriteria(Persona.class);
		Persona persona = (Persona) consulta.add( Restrictions.idEq(idPersona)).uniqueResult();
		
		if(persona.getCuentasBancarias() != null){
			persona.getCuentasBancarias().size();			
		}
		
		session.close();
		return persona;
	}

	@Override
	public int getPersonasCantidad() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPersonasCantidad(String keyword, String columnSearch) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Criteria consulta = session.createCriteria(Persona.class);
				
		if(!columnSearch.trim().equals("") && !keyword.trim().equals("")){
			consulta.add( Restrictions.like(columnSearch, keyword.toUpperCase()));
		}
		
		Integer rowCount = Integer.parseInt(consulta.setProjection(Projections.rowCount()).uniqueResult().toString());
		session.close();
		return rowCount;
	}

	@Override
	public String save(Persona tmpPersona) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.merge(tmpPersona);
		
		session.getTransaction().commit();
		session.close();
		return null;
	}

	@Override
	public String delete(Persona personaToRemove) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		try{
			session.beginTransaction();
			
			session.delete(personaToRemove);
			System.out.println("Removed persona id: " + personaToRemove.getId());
			System.out.println("Commitianding...");
			session.getTransaction().commit();
		} catch (HibernateException e) {
            log.error(e.getMessage(), e);
            session.getTransaction().rollback();
            
        }finally{
        	if(session.isOpen()){
        		session.close();
        	}
        }
		return null;
	}

}