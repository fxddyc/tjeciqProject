package cn.com.eship.dao.impl;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.model.EpidemicAppear;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/7/14.
 */
@Repository
public class EpidemicAppearDaoImpl implements EpidemicAppearDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Object> findEpidemicTopten(Map<String, String> mapPram) throws Exception {
        List<Object> valueList = new ArrayList<Object>();
        StringBuffer selectAndFromPart = new StringBuffer("select epidemicAppear.epidemic.epidemicName,sum(epidemicAppear.appearTimes) as appearTimesSum from EpidemicAppear epidemicAppear");
        StringBuffer wherePart = new StringBuffer(" where 1=1");
        StringBuffer lastPart = new StringBuffer(" group by epidemicAppear.epidemic.id order by appearTimesSum DESC");
        if (!mapPram.isEmpty()) {
            for (Map.Entry<String, String> entry : mapPram.entrySet()) {
                wherePart.append(" and ").append(entry.getKey() + " = ").append("?");
                valueList.add(entry.getValue());
            }
        }
        String hql = selectAndFromPart.append(wherePart).append(lastPart).toString();
        List<Object> list = (List<Object>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        for (int i = 0; i < valueList.size(); i++) {
                            query.setParameter(i, valueList.get(i));
                        }
                        query.setMaxResults(10);
                        List list = query.list();
                        return list;
                    }
                });

        return list != null ? list : null;
    }

    @Override
    public List<Object> findEpidemicAppearList(Map<String, String> mapPram) throws Exception {
        List<Object> valueList = new ArrayList<Object>();
        StringBuffer selectPart = new StringBuffer("select epidemicAppear.epidemic.epidemicName,sum(epidemicAppear.appearTimes) as appearTimesSum from EpidemicAppear epidemicAppear");
        StringBuffer wherePart = new StringBuffer(" where 1 = 1");
        StringBuffer lastPart = new StringBuffer(" group by epidemicAppear.epidemic.id order by appearTimesSum DESC");
        if (!mapPram.isEmpty()) {
            if (StringUtils.isNotBlank(mapPram.get("startDate"))) {
                wherePart.append(" and epidemicAppear.appearDate >= date_format(?,'%Y-%m-%d')");
                valueList.add(mapPram.get("startDate"));
            }
            if (StringUtils.isNotBlank(mapPram.get("endDate"))) {
                wherePart.append(" and epidemicAppear.appearDate <= date_format(?,'%Y-%m-%d')");
                valueList.add(mapPram.get("endDate"));
            }
            if (StringUtils.isNotBlank(mapPram.get("region"))) {
                wherePart.append(" and epidemicAppear.region.id = ?");
                valueList.add(mapPram.get("region"));
            }
        }
        String hql = selectPart.append(wherePart).append(lastPart).toString();

        List<Object> list = (List<Object>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        for (int i = 0; i < valueList.size(); i++) {
                            query.setParameter(i, valueList.get(i));
                        }
                        List list = query.list();
                        return list;
                    }
                });
        return list != null ? list : null;
    }

    @Override
    public List<Object> findEpidemicAppearRegionCount(Map<String, String> mapPram) throws Exception {
        List<Object> valueList = new ArrayList<Object>();
        StringBuffer selectPart = new StringBuffer("select epidemicAppear.region.regionEn,sum(epidemicAppear.appearTimes) as appearTimesSum from EpidemicAppear epidemicAppear");
        StringBuffer wherePart = new StringBuffer(" where 1 = 1");
        StringBuffer lastPart = new StringBuffer(" group by epidemicAppear.region.regionEn");
        if (!mapPram.isEmpty()) {
            if (StringUtils.isNotBlank(mapPram.get("startDate"))) {
                wherePart.append(" and epidemicAppear.appearDate >= date_format(?,'%Y-%m-%d')");
                valueList.add(mapPram.get("startDate"));
            }
            if (StringUtils.isNotBlank(mapPram.get("endDate"))) {
                wherePart.append(" and epidemicAppear.appearDate <= date_format(?,'%Y-%m-%d')");
                valueList.add(mapPram.get("endDate"));
            }
            if (StringUtils.isNotBlank(mapPram.get("epidemicName"))) {
                wherePart.append(" and epidemicAppear.epidemic.epidemicName like ?");
                valueList.add("%" + mapPram.get("epidemicName") + "%");
            }
        }
        String hql = selectPart.append(wherePart).append(lastPart).toString();
        List<Object> list = (List<Object>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        for (int i = 0; i < valueList.size(); i++) {
                            query.setParameter(i, valueList.get(i));
                        }
                        List list = query.list();
                        return list;
                    }
                });

        return list != null ? list : null;
    }

    @Override
    public List<EpidemicAppear> findEpidemicAppearList(Integer startPosition) throws Exception {
        //return hibernateTemplate.loadAll(EpidemicAppear.class);
        String hql = "from EpidemicAppear epidemicAppear join fetch epidemicAppear.epidemic join fetch epidemicAppear.region";
        List<EpidemicAppear> list = (List<EpidemicAppear>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        query.setFirstResult(startPosition);
                        query.setMaxResults(10);
                        List list = query.list();
                        return list;
                    }
                });

        return list;
    }

    @Override
    public List<EpidemicAppear> findEpidemicAppearListByCondition(Integer startPosition, Map<String, String> mapPram) throws Exception {
        String hql = "from EpidemicAppear epidemicAppear join fetch epidemicAppear.epidemic join fetch epidemicAppear.region";
        List<EpidemicAppear> list = (List<EpidemicAppear>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        query.setFirstResult(startPosition);
                        query.setMaxResults(10);
                        List list = query.list();
                        return list;
                    }
                });

        return list;
    }

    /**
     * 根据条件查询疫情发生表总条数
     *
     * @return
     * @throws Exception
     */
    @Override
    public Long findEpidemicAppearCount() throws Exception {
        String hql = "select count(*) from EpidemicAppear epidemicAppear";
        Object object = hibernateTemplate.find(hql);
        return object != null ? ((List<Long>) object).get(0) : new Long(0);
    }

    @Override
    public EpidemicAppear findEpidemicAppearById(String id) throws Exception {
        return hibernateTemplate.get(EpidemicAppear.class, id);
    }
}
