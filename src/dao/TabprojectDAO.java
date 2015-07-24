package dao;

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

import bean.Tabproject;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabproject entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.Tabproject
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabprojectDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabprojectDAO.class);
	// property constants
	public static final String PROJECTNAME = "projectname";
	public static final String DESCRIBE = "describe";
	public static final String INCHARGE = "incharge";
	public static final String ALIASNAME = "aliasname";

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

	public void save(Tabproject transientInstance) {
		log.debug("saving Tabproject instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabproject persistentInstance) {
		log.debug("deleting Tabproject instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabproject findById(java.math.BigDecimal id) {
		log.debug("getting Tabproject instance with id: " + id);
		try {
			Tabproject instance = (Tabproject) getCurrentSession().get(
					"dao.Tabproject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabproject instance) {
		log.debug("finding Tabproject instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.Tabproject")
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
		log.debug("finding Tabproject instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tabproject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProjectname(Object projectname) {
		return findByProperty(PROJECTNAME, projectname);
	}

	public List findByDescribe(Object describe) {
		return findByProperty(DESCRIBE, describe);
	}

	public List findByIncharge(Object incharge) {
		return findByProperty(INCHARGE, incharge);
	}

	public List findByAliasname(Object aliasname) {
		return findByProperty(ALIASNAME, aliasname);
	}

	public List findAll() {
		log.debug("finding all Tabproject instances");
		try {
			String queryString = "from Tabproject";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabproject merge(Tabproject detachedInstance) {
		log.debug("merging Tabproject instance");
		try {
			Tabproject result = (Tabproject) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabproject instance) {
		log.debug("attaching dirty Tabproject instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabproject instance) {
		log.debug("attaching clean Tabproject instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabprojectDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabprojectDAO) ctx.getBean("TabprojectDAO");
	}
}