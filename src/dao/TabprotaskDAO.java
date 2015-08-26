package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import bean.Tabprotask;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabprotask entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Tabprotask
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabprotaskDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabprotaskDAO.class);
	// property constants
	public static final String TASKPROPEOPLE = "taskpropeople";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Tabprotask transientInstance) {
		log.debug("saving Tabprotask instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabprotask persistentInstance) {
		log.debug("deleting Tabprotask instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabprotask findById(java.math.BigDecimal id) {
		log.debug("getting Tabprotask instance with id: " + id);
		try {
			Tabprotask instance = (Tabprotask) getCurrentSession().get(
					"dao.Tabprotask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabprotask instance) {
		log.debug("finding Tabprotask instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Tabprotask")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Tabprotask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tabprotask as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTaskpropeople(Object taskpropeople) {
		return findByProperty(TASKPROPEOPLE, taskpropeople);
	}

	public List findAll() {
		log.debug("finding all Tabprotask instances");
		try {
			String queryString = "from Tabprotask";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabprotask merge(Tabprotask detachedInstance) {
		log.debug("merging Tabprotask instance");
		try {
			Tabprotask result = (Tabprotask) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabprotask instance) {
		log.debug("attaching dirty Tabprotask instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabprotask instance) {
		log.debug("attaching clean Tabprotask instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabprotaskDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabprotaskDAO) ctx.getBean("TabprotaskDAO");
	}
}