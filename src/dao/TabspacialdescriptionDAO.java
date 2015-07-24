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

import bean.Tabspacialdescription;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabspacialdescription entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabspacialdescription
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabspacialdescriptionDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabspacialdescriptionDAO.class);
	// property constants
	public static final String WEST_LONGITUDE = "westLongitude";
	public static final String EAST_LONGITUDE = "eastLongitude";
	public static final String SOUTH_LATITUDE = "southLatitude";
	public static final String NORTH_LATITUDE = "northLatitude";
	public static final String REGION_NAME = "regionName";
	public static final String AREA = "area";
	public static final String PROJECTION_TYPE = "projectionType";
	public static final String ELLIPSOID = "ellipsoid";
	public static final String COORDINATE_TYPE = "coordinateType";
	public static final String PROJECTION_PARAMETER = "projectionParameter";
	public static final String ELLIPSOID_PARAMETER = "ellipsoidParameter";

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

	public void save(Tabspacialdescription transientInstance) {
		log.debug("saving Tabspacialdescription instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabspacialdescription persistentInstance) {
		log.debug("deleting Tabspacialdescription instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabspacialdescription findById(java.math.BigDecimal id) {
		log.debug("getting Tabspacialdescription instance with id: " + id);
		try {
			Tabspacialdescription instance = (Tabspacialdescription) getCurrentSession()
					.get("dao.Tabspacialdescription", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabspacialdescription instance) {
		log.debug("finding Tabspacialdescription instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabspacialdescription")
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
		log.debug("finding Tabspacialdescription instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabspacialdescription as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByWestLongitude(Object westLongitude) {
		return findByProperty(WEST_LONGITUDE, westLongitude);
	}

	public List findByEastLongitude(Object eastLongitude) {
		return findByProperty(EAST_LONGITUDE, eastLongitude);
	}

	public List findBySouthLatitude(Object southLatitude) {
		return findByProperty(SOUTH_LATITUDE, southLatitude);
	}

	public List findByNorthLatitude(Object northLatitude) {
		return findByProperty(NORTH_LATITUDE, northLatitude);
	}

	public List findByRegionName(Object regionName) {
		return findByProperty(REGION_NAME, regionName);
	}

	public List findByArea(Object area) {
		return findByProperty(AREA, area);
	}

	public List findByProjectionType(Object projectionType) {
		return findByProperty(PROJECTION_TYPE, projectionType);
	}

	public List findByEllipsoid(Object ellipsoid) {
		return findByProperty(ELLIPSOID, ellipsoid);
	}

	public List findByCoordinateType(Object coordinateType) {
		return findByProperty(COORDINATE_TYPE, coordinateType);
	}

	public List findByProjectionParameter(Object projectionParameter) {
		return findByProperty(PROJECTION_PARAMETER, projectionParameter);
	}

	public List findByEllipsoidParameter(Object ellipsoidParameter) {
		return findByProperty(ELLIPSOID_PARAMETER, ellipsoidParameter);
	}

	public List findAll() {
		log.debug("finding all Tabspacialdescription instances");
		try {
			String queryString = "from Tabspacialdescription";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabspacialdescription merge(Tabspacialdescription detachedInstance) {
		log.debug("merging Tabspacialdescription instance");
		try {
			Tabspacialdescription result = (Tabspacialdescription) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabspacialdescription instance) {
		log.debug("attaching dirty Tabspacialdescription instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabspacialdescription instance) {
		log.debug("attaching clean Tabspacialdescription instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabspacialdescriptionDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabspacialdescriptionDAO) ctx
				.getBean("TabspacialdescriptionDAO");
	}
}