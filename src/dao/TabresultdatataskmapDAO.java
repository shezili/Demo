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

import bean.Tabresultdatataskmap;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabresultdatataskmap entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabresultdatataskmap
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabresultdatataskmapDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabresultdatataskmapDAO.class);
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

	public void save(Tabresultdatataskmap transientInstance) {
		log.debug("saving Tabresultdatataskmap instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabresultdatataskmap persistentInstance) {
		log.debug("deleting Tabresultdatataskmap instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabresultdatataskmap findById(java.math.BigDecimal id) {
		log.debug("getting Tabresultdatataskmap instance with id: " + id);
		try {
			Tabresultdatataskmap instance = (Tabresultdatataskmap) getCurrentSession()
					.get("dao.Tabresultdatataskmap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabresultdatataskmap instance) {
		log.debug("finding Tabresultdatataskmap instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabresultdatataskmap")
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
		log.debug("finding Tabresultdatataskmap instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabresultdatataskmap as model where model."
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
		log.debug("finding all Tabresultdatataskmap instances");
		try {
			String queryString = "from Tabresultdatataskmap";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabresultdatataskmap merge(Tabresultdatataskmap detachedInstance) {
		log.debug("merging Tabresultdatataskmap instance");
		try {
			Tabresultdatataskmap result = (Tabresultdatataskmap) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabresultdatataskmap instance) {
		log.debug("attaching dirty Tabresultdatataskmap instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabresultdatataskmap instance) {
		log.debug("attaching clean Tabresultdatataskmap instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabresultdatataskmapDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabresultdatataskmapDAO) ctx.getBean("TabresultdatataskmapDAO");
	}
}