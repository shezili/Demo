package dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
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

import bean.Tabrstask;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabrstask entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Tabrstask
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabrstaskDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabrstaskDAO.class);
	// property constants
	public static final String TASKNAME = "taskname";
	public static final String STATE = "state";
	public static final String FILESTRUCTURE = "filestructure";
	public static final String LATIEST_MODI_USER = "latiestModiUser";
	public static final String FTP_PATHNAME = "ftpPathname";

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

	public void save(Tabrstask transientInstance) {
		log.debug("saving Tabrstask instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabrstask persistentInstance) {
		log.debug("deleting Tabrstask instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabrstask findById(java.math.BigDecimal id) {
		log.debug("getting Tabrstask instance with id: " + id);
		try {
			Tabrstask instance = (Tabrstask) getCurrentSession().get(
					"dao.Tabrstask", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabrstask instance) {
		log.debug("finding Tabrstask instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Tabrstask")
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
		log.debug("finding Tabrstask instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tabrstask as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTaskname(Object taskname) {
		return findByProperty(TASKNAME, taskname);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByFilestructure(Object filestructure) {
		return findByProperty(FILESTRUCTURE, filestructure);
	}

	public List findByLatiestModiUser(Object latiestModiUser) {
		return findByProperty(LATIEST_MODI_USER, latiestModiUser);
	}

	public List findByFtpPathname(Object ftpPathname) {
		return findByProperty(FTP_PATHNAME, ftpPathname);
	}

	public List findAll() {
		log.debug("finding all Tabrstask instances");
		try {
			String queryString = "from Tabrstask";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabrstask merge(Tabrstask detachedInstance) {
		log.debug("merging Tabrstask instance");
		try {
			Tabrstask result = (Tabrstask) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabrstask instance) {
		log.debug("attaching dirty Tabrstask instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabrstask instance) {
		log.debug("attaching clean Tabrstask instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabrstaskDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabrstaskDAO) ctx.getBean("TabrstaskDAO");
	}
}