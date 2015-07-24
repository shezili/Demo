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

import bean.Tabresultmetadata;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tabresultmetadata entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see dao.Tabresultmetadata
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class TabresultmetadataDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TabresultmetadataDAO.class);
	// property constants
	public static final String MAP_NAME = "mapName";
	public static final String MAP_IDENTIFIER = "mapIdentifier";
	public static final String LANGUAGE = "language";
	public static final String CHARACTER_SET = "characterSet";
	public static final String DATA_VERSION = "dataVersion";
	public static final String SAVING_ADDR = "savingAddr";
	public static final String RESULT_QUALITY = "resultQuality";
	public static final String HISTORY = "history";

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

	public void save(Tabresultmetadata transientInstance) {
		log.debug("saving Tabresultmetadata instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tabresultmetadata persistentInstance) {
		log.debug("deleting Tabresultmetadata instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tabresultmetadata findById(java.math.BigDecimal id) {
		log.debug("getting Tabresultmetadata instance with id: " + id);
		try {
			Tabresultmetadata instance = (Tabresultmetadata) getCurrentSession()
					.get("dao.Tabresultmetadata", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tabresultmetadata instance) {
		log.debug("finding Tabresultmetadata instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("dao.Tabresultmetadata")
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
		log.debug("finding Tabresultmetadata instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tabresultmetadata as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMapName(Object mapName) {
		return findByProperty(MAP_NAME, mapName);
	}
	
	public List fuzzyQueryByMapName(Object mapName, int first,int max){
		log.debug("fuzzy finding Tabresultmetadata instance with property: MAP_NAME"
				+ ", value: " + mapName);
		try {
			System.out.println("进入dao，传入的参数是："+mapName);
			mapName = "'%" + mapName + "%'";  
			String queryString = "from Tabresultmetadata as model where model."
					+ MAP_NAME + " like "+mapName;
			Query queryObject = getCurrentSession().createQuery(queryString);
			//queryObject.setParameter(0, mapName);
			System.out.println(queryString);
			queryObject.setFirstResult(first); 
			System.out.println("set first");
			queryObject.setMaxResults(max); 
			System.out.println("set max");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("fuzzy find by property name failed", re);
			throw re;
		}
	}
	
	public long querySizeOfFixedMapName(Object mapName){
		log.debug("fuzzy finding Szie of Tabresultmetadata instance with property: MAP_NAME"
				+ ", value: " + mapName);
		try {
			mapName = "'%" + mapName + "%'";  
			String queryString = "from Tabresultmetadata as model where model."
					+ MAP_NAME + " like "+mapName;
			long count = getCurrentSession().createQuery(queryString).list().size();
			System.out.println("查询出的数目为："+count);
			return count;
		} catch (RuntimeException re) {
			log.error("fuzzy find Szie by property name failed", re);
			throw re;
		}
	}

	public List findByMapIdentifier(Object mapIdentifier) {
		return findByProperty(MAP_IDENTIFIER, mapIdentifier);
	}

	public List findByLanguage(Object language) {
		return findByProperty(LANGUAGE, language);
	}

	public List findByCharacterSet(Object characterSet) {
		return findByProperty(CHARACTER_SET, characterSet);
	}

	public List findByDataVersion(Object dataVersion) {
		return findByProperty(DATA_VERSION, dataVersion);
	}

	public List findBySavingAddr(Object savingAddr) {
		return findByProperty(SAVING_ADDR, savingAddr);
	}

	public List findByResultQuality(Object resultQuality) {
		return findByProperty(RESULT_QUALITY, resultQuality);
	}

	public List findByHistory(Object history) {
		return findByProperty(HISTORY, history);
	}

	public List findAll() {
		log.debug("finding all Tabresultmetadata instances");
		try {
			String queryString = "from Tabresultmetadata";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tabresultmetadata merge(Tabresultmetadata detachedInstance) {
		log.debug("merging Tabresultmetadata instance");
		try {
			Tabresultmetadata result = (Tabresultmetadata) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tabresultmetadata instance) {
		log.debug("attaching dirty Tabresultmetadata instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tabresultmetadata instance) {
		log.debug("attaching clean Tabresultmetadata instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TabresultmetadataDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (TabresultmetadataDAO) ctx.getBean("TabresultmetadataDAO");
	}
}