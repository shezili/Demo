package dao;

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

import bean.Tabplanefortask;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabplanefortask entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabplanefortask
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabplanefortaskDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabplanefortaskDAO.class);
	// property constants

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

	public void save(Tabplanefortask transientInstance) {
		log.debug("saving Tabplanefortask instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabplanefortask persistentInstance) {
		log.debug("deleting Tabplanefortask instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabplanefortask findById(java.math.BigDecimal id) {
		log.debug("getting Tabplanefortask instance with id: " + id);
		try {
			Tabplanefortask instance = (Tabplanefortask) getCurrentSession()
					.get("dao.Tabplanefortask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabplanefortask instance) {
		log.debug("finding Tabplanefortask instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabplanefortask")
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
		log.debug("finding Tabplanefortask instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabplanefortask as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Tabplanefortask instances");
		try {
			String queryString = "from Tabplanefortask";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabplanefortask merge(Tabplanefortask detachedInstance) {
		log.debug("merging Tabplanefortask instance");
		try {
			Tabplanefortask result = (Tabplanefortask) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabplanefortask instance) {
		log.debug("attaching dirty Tabplanefortask instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabplanefortask instance) {
		log.debug("attaching clean Tabplanefortask instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabplanefortaskDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabplanefortaskDAO) ctx.getBean("TabplanefortaskDAO");
	}
}