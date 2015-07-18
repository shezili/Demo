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

import bean.Tabrsproductstatus;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabrsproductstatus entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabrsproductstatus
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabrsproductstatusDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabrsproductstatusDAO.class);
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

	public void save(Tabrsproductstatus transientInstance) {
		log.debug("saving Tabrsproductstatus instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabrsproductstatus persistentInstance) {
		log.debug("deleting Tabrsproductstatus instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabrsproductstatus findById(java.math.BigDecimal id) {
		log.debug("getting Tabrsproductstatus instance with id: " + id);
		try {
			Tabrsproductstatus instance = (Tabrsproductstatus) getCurrentSession()
					.get("dao.Tabrsproductstatus", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabrsproductstatus instance) {
		log.debug("finding Tabrsproductstatus instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabrsproductstatus")
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
		log.debug("finding Tabrsproductstatus instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabrsproductstatus as model where model."
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
		log.debug("finding all Tabrsproductstatus instances");
		try {
			String queryString = "from Tabrsproductstatus";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabrsproductstatus merge(Tabrsproductstatus detachedInstance) {
		log.debug("merging Tabrsproductstatus instance");
		try {
			Tabrsproductstatus result = (Tabrsproductstatus) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabrsproductstatus instance) {
		log.debug("attaching dirty Tabrsproductstatus instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabrsproductstatus instance) {
		log.debug("attaching clean Tabrsproductstatus instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabrsproductstatusDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabrsproductstatusDAO) ctx.getBean("TabrsproductstatusDAO");
	}
}