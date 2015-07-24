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

import bean.TabRole;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabRole entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see dao.TabRole
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabRoleDAO {
	private static final Logger log = LoggerFactory.getLogger(TabRoleDAO.class);
	// property constants
	public static final String ROLE = "role";
	public static final String ROLEDESCRIPTION = "roledescription";

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

	public void save(TabRole transientInstance) {
		log.debug("saving TabRole instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabRole persistentInstance) {
		log.debug("deleting TabRole instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TabRole findById(java.math.BigDecimal id) {
		log.debug("getting TabRole instance with id: " + id);
		try {
			TabRole instance = (TabRole) getCurrentSession().get("dao.TabRole",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabRole instance) {
		log.debug("finding TabRole instance by example");
		try {
			List results = getCurrentSession().createCriteria("dao.TabRole")
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
		log.debug("finding TabRole instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TabRole as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List findByRoledescription(Object roledescription) {
		return findByProperty(ROLEDESCRIPTION, roledescription);
	}

	public List findAll() {
		log.debug("finding all TabRole instances");
		try {
			String queryString = "from TabRole";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabRole merge(TabRole detachedInstance) {
		log.debug("merging TabRole instance");
		try {
			TabRole result = (TabRole) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabRole instance) {
		log.debug("attaching dirty TabRole instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabRole instance) {
		log.debug("attaching clean TabRole instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabRoleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TabRoleDAO) ctx.getBean("TabRoleDAO");
	}
}