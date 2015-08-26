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

import bean.TabidentifierInformation;

/**
 * A data access object (DAO) providing persistence and search support for
 * TabidentifierInformation entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see dao.TabidentifierInformation
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabidentifierInformationDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabidentifierInformationDAO.class);
	// property constants
	public static final String IDENTIFIERNAME = "identifiername";
	public static final String ALIAS = "alias";
	public static final String ABSTRACT_ = "abstract_";
	public static final String LANGUAGE = "language";
	public static final String KEYWORD = "keyword";
	public static final String ARCHIVESNUM = "archivesnum";
	public static final String ARCHIVESTYPE = "archivestype";
	public static final String CARRIERSTYLE = "carrierstyle";
	public static final String SECURITYLEVEL = "securitylevel";

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

	public void save(TabidentifierInformation transientInstance) {
		log.debug("saving TabidentifierInformation instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TabidentifierInformation persistentInstance) {
		log.debug("deleting TabidentifierInformation instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TabidentifierInformation findById(java.math.BigDecimal id) {
		log.debug("getting TabidentifierInformation instance with id: " + id);
		try {
			TabidentifierInformation instance = (TabidentifierInformation) getCurrentSession()
					.get("dao.TabidentifierInformation", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TabidentifierInformation instance) {
		log.debug("finding TabidentifierInformation instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.TabidentifierInformation")
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
		log.debug("finding TabidentifierInformation instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TabidentifierInformation as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdentifiername(Object identifiername) {
		return findByProperty(IDENTIFIERNAME, identifiername);
	}

	public List findByAlias(Object alias) {
		return findByProperty(ALIAS, alias);
	}

	public List findByAbstract_(Object abstract_) {
		return findByProperty(ABSTRACT_, abstract_);
	}

	public List findByLanguage(Object language) {
		return findByProperty(LANGUAGE, language);
	}

	public List findByKeyword(Object keyword) {
		return findByProperty(KEYWORD, keyword);
	}

	public List findByArchivesnum(Object archivesnum) {
		return findByProperty(ARCHIVESNUM, archivesnum);
	}

	public List findByArchivestype(Object archivestype) {
		return findByProperty(ARCHIVESTYPE, archivestype);
	}

	public List findByCarrierstyle(Object carrierstyle) {
		return findByProperty(CARRIERSTYLE, carrierstyle);
	}

	public List findBySecuritylevel(Object securitylevel) {
		return findByProperty(SECURITYLEVEL, securitylevel);
	}

	public List findAll() {
		log.debug("finding all TabidentifierInformation instances");
		try {
			String queryString = "from TabidentifierInformation";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TabidentifierInformation merge(
			TabidentifierInformation detachedInstance) {
		log.debug("merging TabidentifierInformation instance");
		try {
			TabidentifierInformation result = (TabidentifierInformation) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TabidentifierInformation instance) {
		log.debug("attaching dirty TabidentifierInformation instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TabidentifierInformation instance) {
		log.debug("attaching clean TabidentifierInformation instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabidentifierInformationDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabidentifierInformationDAO) ctx
				.getBean("TabidentifierInformationDAO");
	}
}