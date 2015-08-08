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

import bean.LawenforcementReport;

/**
 * A data access object (DAO) providing persistence and search support for
 * LawenforcementReport entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see bean.LawenforcementReport
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class LawenforcementReportDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LawenforcementReportDAO.class);
	// property constants
	public static final String DATESTR = "datestr";
	public static final String MACHINENUMBER = "machinenumber";
	public static final String AIRPORT = "airport";
	public static final String PROJECTNAME = "projectname";
	public static final String PROJECTLOCATION = "projectlocation";
	public static final String LATLON = "latlon";
	public static final String DESCRIPTION = "description";
	public static final String PICTURE_URL = "pictureUrl";
	public static final String FEEDBACK = "feedback";
	public static final String BACKPROJECTNAME = "backprojectname";
	public static final String MARINEUNIT = "marineunit";
	public static final String CITY = "city";
	public static final String ISLEGAL = "islegal";
	public static final String ROUTE = "route";

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

	public void save(LawenforcementReport transientInstance) {
		log.debug("saving LawenforcementReport instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(LawenforcementReport persistentInstance) {
		log.debug("deleting LawenforcementReport instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public LawenforcementReport findById(java.lang.String id) {
		log.debug("getting LawenforcementReport instance with id: " + id);
		try {
			LawenforcementReport instance = (LawenforcementReport) getCurrentSession()
					.get("bean.LawenforcementReport", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(LawenforcementReport instance) {
		log.debug("finding LawenforcementReport instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("bean.LawenforcementReport")
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
		log.debug("finding LawenforcementReport instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from LawenforcementReport as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDatestr(Object datestr) {
		return findByProperty(DATESTR, datestr);
	}

	public List findByMachinenumber(Object machinenumber) {
		return findByProperty(MACHINENUMBER, machinenumber);
	}

	public List findByAirport(Object airport) {
		return findByProperty(AIRPORT, airport);
	}

	public List findByProjectname(Object projectname) {
		return findByProperty(PROJECTNAME, projectname);
	}

	public List findByProjectlocation(Object projectlocation) {
		return findByProperty(PROJECTLOCATION, projectlocation);
	}

	public List findByLatlon(Object latlon) {
		return findByProperty(LATLON, latlon);
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByPictureUrl(Object pictureUrl) {
		return findByProperty(PICTURE_URL, pictureUrl);
	}

	public List findByFeedback(Object feedback) {
		return findByProperty(FEEDBACK, feedback);
	}

	public List findByBackprojectname(Object backprojectname) {
		return findByProperty(BACKPROJECTNAME, backprojectname);
	}

	public List findByMarineunit(Object marineunit) {
		return findByProperty(MARINEUNIT, marineunit);
	}

	public List findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List findByIslegal(Object islegal) {
		return findByProperty(ISLEGAL, islegal);
	}

	public List findByRoute(Object route) {
		return findByProperty(ROUTE, route);
	}

	public List findAll() {
		log.debug("finding all LawenforcementReport instances");
		try {
			String queryString = "from LawenforcementReport";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public LawenforcementReport merge(LawenforcementReport detachedInstance) {
		log.debug("merging LawenforcementReport instance");
		try {
			LawenforcementReport result = (LawenforcementReport) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(LawenforcementReport instance) {
		log.debug("attaching dirty LawenforcementReport instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(LawenforcementReport instance) {
		log.debug("attaching clean LawenforcementReport instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LawenforcementReportDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (LawenforcementReportDAO) ctx.getBean("LawenforcementReportDAO");
	}
}