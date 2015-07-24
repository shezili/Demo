package dao;

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

import bean.Tabplane;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabplane entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Tabplane
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabplaneDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabplaneDAO.class);
	// property constants
	public static final String PLANENUM = "planenum";
	public static final String PLANEMSG = "planemsg";

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

	public void save(Tabplane transientInstance) {
		log.debug("saving Tabplane instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabplane persistentInstance) {
		log.debug("deleting Tabplane instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabplane findById(java.math.BigDecimal id) {
		log.debug("getting Tabplane instance with id: " + id);
		try {
			Tabplane instance = (Tabplane) getCurrentSession().get(
					"dao.Tabplane", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabplane instance) {
		log.debug("finding Tabplane instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Tabplane")
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
		log.debug("finding Tabplane instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tabplane as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPlanenum(Object planenum) {
		return findByProperty(PLANENUM, planenum);
	}

	public List findByPlanemsg(Object planemsg) {
		return findByProperty(PLANEMSG, planemsg);
	}

	public List findAll() {
		log.debug("finding all Tabplane instances");
		try {
			String queryString = "from Tabplane";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabplane merge(Tabplane detachedInstance) {
		log.debug("merging Tabplane instance");
		try {
			Tabplane result = (Tabplane) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabplane instance) {
		log.debug("attaching dirty Tabplane instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabplane instance) {
		log.debug("attaching clean Tabplane instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabplaneDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabplaneDAO) ctx.getBean("TabplaneDAO");
	}
}