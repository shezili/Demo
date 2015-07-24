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

import bean.TabRoleprivlege;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabRoleprivlege entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.TabRoleprivlege
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabRoleprivlegeDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabRoleprivlegeDAO.class);
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

	public void save(TabRoleprivlege transientInstance) {
		log.debug("saving TabRoleprivlege instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabRoleprivlege persistentInstance) {
		log.debug("deleting TabRoleprivlege instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TabRoleprivlege findById(java.math.BigDecimal id) {
		log.debug("getting TabRoleprivlege instance with id: " + id);
		try {
			TabRoleprivlege instance = (TabRoleprivlege) getCurrentSession()
					.get("dao.TabRoleprivlege", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabRoleprivlege instance) {
		log.debug("finding TabRoleprivlege instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.TabRoleprivlege")
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
		log.debug("finding TabRoleprivlege instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TabRoleprivlege as model where model."
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
		log.debug("finding all TabRoleprivlege instances");
		try {
			String queryString = "from TabRoleprivlege";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabRoleprivlege merge(TabRoleprivlege detachedInstance) {
		log.debug("merging TabRoleprivlege instance");
		try {
			TabRoleprivlege result = (TabRoleprivlege) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabRoleprivlege instance) {
		log.debug("attaching dirty TabRoleprivlege instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabRoleprivlege instance) {
		log.debug("attaching clean TabRoleprivlege instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabRoleprivlegeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabRoleprivlegeDAO) ctx.getBean("TabRoleprivlegeDAO");
	}
}