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

import bean.TabPrivlege;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabPrivlege entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.TabPrivlege
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabPrivlegeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabPrivlegeDAO.class);
	// property constants
	public static final String PRIV = "priv";
	public static final String PRIVDESCRIPTION = "privdescription";

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

	public void save(TabPrivlege transientInstance) {
		log.debug("saving TabPrivlege instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabPrivlege persistentInstance) {
		log.debug("deleting TabPrivlege instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TabPrivlege findById(java.math.BigDecimal id) {
		log.debug("getting TabPrivlege instance with id: " + id);
		try {
			TabPrivlege instance = (TabPrivlege) getCurrentSession().get(
					"dao.TabPrivlege", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabPrivlege instance) {
		log.debug("finding TabPrivlege instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.TabPrivlege")
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
		log.debug("finding TabPrivlege instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TabPrivlege as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPriv(Object priv) {
		return findByProperty(PRIV, priv);
	}

	public List findByPrivdescription(Object privdescription) {
		return findByProperty(PRIVDESCRIPTION, privdescription);
	}

	public List findAll() {
		log.debug("finding all TabPrivlege instances");
		try {
			String queryString = "from TabPrivlege";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabPrivlege merge(TabPrivlege detachedInstance) {
		log.debug("merging TabPrivlege instance");
		try {
			TabPrivlege result = (TabPrivlege) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabPrivlege instance) {
		log.debug("attaching dirty TabPrivlege instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabPrivlege instance) {
		log.debug("attaching clean TabPrivlege instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabPrivlegeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabPrivlegeDAO) ctx.getBean("TabPrivlegeDAO");
	}
}