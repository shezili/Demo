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

import bean.Tabbackup;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabbackup entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Tabbackup
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabbackupDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabbackupDAO.class);
	// property constants
	public static final String ORIPEOPLE = "oripeople";
	public static final String ORIROUTE = "oriroute";
	public static final String PREPEOPLE = "prepeople";
	public static final String PREROUTE = "preroute";
	public static final String RESULTPEOPLE = "resultpeople";
	public static final String RESULTROUTE = "resultroute";

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

	public void save(Tabbackup transientInstance) {
		log.debug("saving Tabbackup instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabbackup persistentInstance) {
		log.debug("deleting Tabbackup instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabbackup findById(java.math.BigDecimal id) {
		log.debug("getting Tabbackup instance with id: " + id);
		try {
			Tabbackup instance = (Tabbackup) getCurrentSession().get(
					"dao.Tabbackup", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabbackup instance) {
		log.debug("finding Tabbackup instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Tabbackup")
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
		log.debug("finding Tabbackup instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tabbackup as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByOripeople(Object oripeople) {
		return findByProperty(ORIPEOPLE, oripeople);
	}

	public List findByOriroute(Object oriroute) {
		return findByProperty(ORIROUTE, oriroute);
	}

	public List findByPrepeople(Object prepeople) {
		return findByProperty(PREPEOPLE, prepeople);
	}

	public List findByPreroute(Object preroute) {
		return findByProperty(PREROUTE, preroute);
	}

	public List findByResultpeople(Object resultpeople) {
		return findByProperty(RESULTPEOPLE, resultpeople);
	}

	public List findByResultroute(Object resultroute) {
		return findByProperty(RESULTROUTE, resultroute);
	}

	public List findAll() {
		log.debug("finding all Tabbackup instances");
		try {
			String queryString = "from Tabbackup";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabbackup merge(Tabbackup detachedInstance) {
		log.debug("merging Tabbackup instance");
		try {
			Tabbackup result = (Tabbackup) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabbackup instance) {
		log.debug("attaching dirty Tabbackup instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabbackup instance) {
		log.debug("attaching clean Tabbackup instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabbackupDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabbackupDAO) ctx.getBean("TabbackupDAO");
	}
}