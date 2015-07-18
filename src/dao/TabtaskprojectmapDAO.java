package dao;

import java.math.BigDecimal;
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

import bean.Tabtaskprojectmap;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabtaskprojectmap entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabtaskprojectmap
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabtaskprojectmapDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabtaskprojectmapDAO.class);
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

	public void save(Tabtaskprojectmap transientInstance) {
		log.debug("saving Tabtaskprojectmap instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabtaskprojectmap persistentInstance) {
		log.debug("deleting Tabtaskprojectmap instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabtaskprojectmap findById(java.math.BigDecimal id) {
		log.debug("getting Tabtaskprojectmap instance with id: " + id);
		try {
			Tabtaskprojectmap instance = (Tabtaskprojectmap) getCurrentSession()
					.get("dao.Tabtaskprojectmap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabtaskprojectmap instance) {
		log.debug("finding Tabtaskprojectmap instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabtaskprojectmap")
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
		log.debug("finding Tabtaskprojectmap instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabtaskprojectmap as model where model."
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
		log.debug("finding all Tabtaskprojectmap instances");
		try {
			String queryString = "from Tabtaskprojectmap";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabtaskprojectmap merge(Tabtaskprojectmap detachedInstance) {
		log.debug("merging Tabtaskprojectmap instance");
		try {
			Tabtaskprojectmap result = (Tabtaskprojectmap) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabtaskprojectmap instance) {
		log.debug("attaching dirty Tabtaskprojectmap instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabtaskprojectmap instance) {
		log.debug("attaching clean Tabtaskprojectmap instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabtaskprojectmapDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabtaskprojectmapDAO) ctx.getBean("TabtaskprojectmapDAO");
	}
}