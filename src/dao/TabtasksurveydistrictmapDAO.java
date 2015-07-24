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

import bean.Tabtasksurveydistrictmap;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabtasksurveydistrictmap entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see dao.Tabtasksurveydistrictmap
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabtasksurveydistrictmapDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabtasksurveydistrictmapDAO.class);
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

	public void save(Tabtasksurveydistrictmap transientInstance) {
		log.debug("saving Tabtasksurveydistrictmap instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabtasksurveydistrictmap persistentInstance) {
		log.debug("deleting Tabtasksurveydistrictmap instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabtasksurveydistrictmap findById(java.math.BigDecimal id) {
		log.debug("getting Tabtasksurveydistrictmap instance with id: " + id);
		try {
			Tabtasksurveydistrictmap instance = (Tabtasksurveydistrictmap) getCurrentSession()
					.get("dao.Tabtasksurveydistrictmap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabtasksurveydistrictmap instance) {
		log.debug("finding Tabtasksurveydistrictmap instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabtasksurveydistrictmap")
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
		log.debug("finding Tabtasksurveydistrictmap instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabtasksurveydistrictmap as model where model."
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
		log.debug("finding all Tabtasksurveydistrictmap instances");
		try {
			String queryString = "from Tabtasksurveydistrictmap";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabtasksurveydistrictmap merge(
			Tabtasksurveydistrictmap detachedInstance) {
		log.debug("merging Tabtasksurveydistrictmap instance");
		try {
			Tabtasksurveydistrictmap result = (Tabtasksurveydistrictmap) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabtasksurveydistrictmap instance) {
		log.debug("attaching dirty Tabtasksurveydistrictmap instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabtasksurveydistrictmap instance) {
		log.debug("attaching clean Tabtasksurveydistrictmap instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabtasksurveydistrictmapDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabtasksurveydistrictmapDAO) ctx
				.getBean("TabtasksurveydistrictmapDAO");
	}
}