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

import bean.Tabresultdatafolder;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabresultdatafolder entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabresultdatafolder
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabresultdatafolderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabresultdatafolderDAO.class);
	// property constants
	public static final String FOLDER_NAME = "folderName";

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

	public void save(Tabresultdatafolder transientInstance) {
		log.debug("saving Tabresultdatafolder instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabresultdatafolder persistentInstance) {
		log.debug("deleting Tabresultdatafolder instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabresultdatafolder findById(java.math.BigDecimal id) {
		log.debug("getting Tabresultdatafolder instance with id: " + id);
		try {
			Tabresultdatafolder instance = (Tabresultdatafolder) getCurrentSession()
					.get("dao.Tabresultdatafolder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabresultdatafolder instance) {
		log.debug("finding Tabresultdatafolder instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabresultdatafolder")
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
		log.debug("finding Tabresultdatafolder instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabresultdatafolder as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFolderName(Object folderName) {
		return findByProperty(FOLDER_NAME, folderName);
	}

	public List findAll() {
		log.debug("finding all Tabresultdatafolder instances");
		try {
			String queryString = "from Tabresultdatafolder";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabresultdatafolder merge(Tabresultdatafolder detachedInstance) {
		log.debug("merging Tabresultdatafolder instance");
		try {
			Tabresultdatafolder result = (Tabresultdatafolder) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabresultdatafolder instance) {
		log.debug("attaching dirty Tabresultdatafolder instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabresultdatafolder instance) {
		log.debug("attaching clean Tabresultdatafolder instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabresultdatafolderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabresultdatafolderDAO) ctx.getBean("TabresultdatafolderDAO");
	}
}