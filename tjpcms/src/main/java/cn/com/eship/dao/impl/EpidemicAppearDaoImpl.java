package cn.com.eship.dao.impl;

import cn.com.eship.dao.EpidemicAppearDao;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Object> findEpidemicTopten(Map<String,String> mapPram) throws Exception {
        List<Object> valueList = new ArrayList<Object>();
        StringBuffer selectAndFromPart = new StringBuffer("select epidemicAppear.epidemic.epidemicName,sum(epidemicAppear.appearTimes) as appearTimesSum from EpidemicAppear epidemicAppear");
        StringBuffer wherePart = new StringBuffer(" where 1=1");
        StringBuffer lastPart = new StringBuffer(" group by epidemicAppear.epidemic.id order by appearTimesSum DESC");
        if (!mapPram.isEmpty()){
            for (Map.Entry<String, String> entry : mapPram.entrySet()){
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
                            query.setParameter(i, valueList.get(0));
                        }
                        query.setMaxResults(10);
                        List list = query.list();
                        return list;
                    }
                });

        return list != null ? list : null;
    }
}
