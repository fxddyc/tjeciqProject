package cn.com.eship.dao.impl;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.utils.TimeUtils;
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
    public List<Object> findEpidemicTopten(Map<String, Object> mapPram) throws Exception {
        List<Object> valueList = new ArrayList<Object>();
        StringBuffer selectAndFromPart = new StringBuffer("select epidemicAppear.epidemic.epidemicName,sum(epidemicAppear.appearTimes) as appearTimesSum from EpidemicAppear epidemicAppear");
        StringBuffer wherePart = new StringBuffer(" where 1=1");
        StringBuffer lastPart = new StringBuffer(" group by epidemicAppear.epidemic.id order by appearTimesSum DESC");
        if (!mapPram.isEmpty()) {
            for (Map.Entry<String, Object> entry : mapPram.entrySet()) {
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
    public List<EpidemicAppear> findEpidemicAppearListByCondition(Map<String, Object> mapPram) throws Exception {
        List<Object> valuesPart = new ArrayList<Object>();
        StringBuffer hqlBuffer = new StringBuffer("from EpidemicAppear epidemicAppear join fetch epidemicAppear.epidemic t1 join fetch epidemicAppear.region t2 where 1 = 1");
        StringBuffer wherePart = new StringBuffer();
        if (mapPram.get("epidemicName") != null) {
            wherePart.append(" and t1.epidemicName like ?");
            valuesPart.add("%" + mapPram.get("epidemicName") + "%");
        }
        if (mapPram.get("regionCn") != null) {
            wherePart.append(" and t2.regionCn like ?");
            valuesPart.add("%" + mapPram.get("regionCn") + "%");
        }
        if (mapPram.get("startDate") != null) {
            wherePart.append(" and epidemicAppear.appearDate >= date_format(?,'%Y-%m-%d')");
            valuesPart.add(mapPram.get("startDate"));
        }
        if (mapPram.get("endDate") != null) {
            wherePart.append(" and epidemicAppear.appearDate <= date_format(?,'%Y-%m-%d')");
            valuesPart.add(mapPram.get("endDate"));
        }
        String hql = hqlBuffer.append(wherePart).toString();
        List<EpidemicAppear> list = (List<EpidemicAppear>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        query.setFirstResult((int) mapPram.get("pageNo"));
                        query.setMaxResults(10);
                        for (int i = 0; i < valuesPart.size(); i++) {
                            query.setParameter(i, valuesPart.get(i));
                        }
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
//    @Override
//    public Long findEpidemicAppearCount(Map<String, Object> parameMap) throws Exception {
//        List<Object> valuesPart = new ArrayList<Object>();
//        StringBuffer wherePart = new StringBuffer();
//        if (parameMap.get("epidemicName") != null) {
//            wherePart.append(" and t1.epidemicName like %?%");
//            valuesPart.add(parameMap.get("epidemicName"));
//        }
//        if (parameMap.get("regionCn") != null) {
//            wherePart.append(" and t2.regionCn like %?%");
//            valuesPart.add(parameMap.get("regionCn"));
//        }
//        if (parameMap.get("startDate") != null) {
//            wherePart.append(" and epidemicAppear.appearDate >= date_format(?,'%Y-%m-%d')");
//            valuesPart.add(parameMap.get("startDate"));
//        }
//        if (parameMap.get("endDate") != null) {
//            wherePart.append(" and epidemicAppear.appearDate <= date_format(?,'%Y-%m-%d')");
//            valuesPart.add(parameMap.get("endDate"));
//        }
//        String hql = "select count(*) from EpidemicAppear epidemicAppear join fetch epidemicAppear.epidemic t1 join fetch epidemicAppear.region t2 where 1 = 1";
//        Object object = hibernateTemplate.find(hql);
//        return object != null ? ((List<Long>) object).get(0) : new Long(0);
//    }

    @Override
    public EpidemicAppear findEpidemicAppearById(String id) throws Exception {
        return hibernateTemplate.get(EpidemicAppear.class, id);
    }

    @Override
    public List<EpidemicAppear> findEpidemicAppearList(Map<String, Object> mapPram) throws Exception {
        List<Object> valuesPart = new ArrayList<Object>();
        StringBuffer hqlBuffer = new StringBuffer("from EpidemicAppear epidemicAppear join fetch epidemicAppear.epidemic t1 join fetch epidemicAppear.region t2 where 1 = 1");
        StringBuffer wherePart = new StringBuffer();
        if (mapPram.get("epidemicName") != null) {
            wherePart.append(" and t1.epidemicName like ?");
            valuesPart.add("%" + mapPram.get("epidemicName") + "%");
        }
        if (mapPram.get("regionCn") != null) {
            wherePart.append(" and t2.regionCn like ?");
            valuesPart.add("%" + mapPram.get("regionCn") + "%");
        }
        if (mapPram.get("startDate") != null) {
            wherePart.append(" and epidemicAppear.appearDate >= date_format(?,'%Y-%m-%d')");
            valuesPart.add(mapPram.get("startDate"));
        }
        if (mapPram.get("endDate") != null) {
            wherePart.append(" and epidemicAppear.appearDate <= date_format(?,'%Y-%m-%d')");
            valuesPart.add(mapPram.get("endDate"));
        }
        String hql = hqlBuffer.append(wherePart).toString();
        List<EpidemicAppear> list = (List<EpidemicAppear>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        for (int i = 0; i < valuesPart.size(); i++) {
                            query.setParameter(i, valuesPart.get(i));
                        }
                        List list = query.list();
                        return list;
                    }
                });
        return list;
    }
}
