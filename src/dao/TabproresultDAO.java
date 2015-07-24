package dao;

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

import bean.Tabproresult;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabproresult entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabproresult
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabproresultDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabproresultDAO.class);
	// property constants
	public static final String PROPEOPLE = "propeople";
	public static final String RESULTDATAPEOPLE = "resultdatapeople";

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

	public void save(Tabproresult transientInstance) {
		log.debug("saving Tabproresult instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabproresult persistentInstance) {
		log.debug("deleting Tabproresult instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabproresult findById(java.math.BigDecimal id) {
		log.debug("getting Tabproresult instance with id: " + id);
		try {
			Tabproresult instance = (Tabproresult) getCurrentSession().get(
					"dao.Tabproresult", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabproresult instance) {
		log.debug("finding Tabproresult instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabproresult")
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
		log.debug("finding Tabproresult instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabproresult as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPropeople(Object propeople) {
		return findByProperty(PROPEOPLE, propeople);
	}

	public List findByResultdatapeople(Object resultdatapeople) {
		return findByProperty(RESULTDATAPEOPLE, resultdatapeople);
	}

	public List findAll() {
		log.debug("finding all Tabproresult instances");
		try {
			String queryString = "from Tabproresult";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabproresult merge(Tabproresult detachedInstance) {
		log.debug("merging Tabproresult instance");
		try {
			Tabproresult result = (Tabproresult) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabproresult instance) {
		log.debug("attaching dirty Tabproresult instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabproresult instance) {
		log.debug("attaching clean Tabproresult instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabproresultDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabproresultDAO) ctx.getBean("TabproresultDAO");
	}
}