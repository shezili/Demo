package dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import bean.Tabresultdata;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabresultdata entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabresultdata
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabresultdataDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabresultdataDAO.class);
	// property constants
	public static final String PATH = "path";
	public static final String FILENAME = "filename";
	public static final String EXTENSION = "extension";
	public static final String STATE = "state";
	public static final String RESOLUTION = "resolution";

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

	public void save(Tabresultdata transientInstance) {
		log.debug("saving Tabresultdata instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabresultdata persistentInstance) {
		log.debug("deleting Tabresultdata instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabresultdata findById(java.math.BigDecimal id) {
		log.debug("getting Tabresultdata instance with id: " + id);
		try {
			Tabresultdata instance = (Tabresultdata) getCurrentSession().get(
					"bean.Tabresultdata", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabresultdata instance) {
		log.debug("finding Tabresultdata instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("bean.Tabresultdata")
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
		log.debug("finding Tabresultdata instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabresultdata as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List findByFilename(Object filename) {
		return findByProperty(FILENAME, filename);
	}

	public List findByExtension(Object extension) {
		return findByProperty(EXTENSION, extension);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByResolution(Object resolution) {
		return findByProperty(RESOLUTION, resolution);
	}

	public List findAll() {
		log.debug("finding all Tabresultdata instances");
		try {
			String queryString = "from Tabresultdata";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabresultdata merge(Tabresultdata detachedInstance) {
		log.debug("merging Tabresultdata instance");
		try {
			Tabresultdata result = (Tabresultdata) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabresultdata instance) {
		log.debug("attaching dirty Tabresultdata instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabresultdata instance) {
		log.debug("attaching clean Tabresultdata instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabresultdataDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabresultdataDAO) ctx.getBean("TabresultdataDAO");
	}
}