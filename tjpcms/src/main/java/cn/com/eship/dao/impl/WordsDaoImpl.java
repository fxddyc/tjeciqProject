package cn.com.eship.dao.impl;

import cn.com.eship.dao.WordsDao;
import cn.com.eship.model.Words;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/9/13.
 */
public class WordsDaoImpl implements WordsDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 根据条件查询分词
     *
     * @param conditionMap
     * @return
     * @throws Exception
     */
    @Override
    public List<Words> findWordsByCondition(Map<String, Object> conditionMap) throws Exception {
        List<Object> valuesParts = new ArrayList<Object>();
        StringBuffer hqlBuffer = new StringBuffer("from Words words join fetch words.kindDic t1 where 1 = 1");
        StringBuffer wherePart = new StringBuffer();
        if (conditionMap.get("kindName") != null && StringUtils.isNotBlank((String) conditionMap.get("kindName"))) {
            wherePart.append(" and t1.kindName like ?");
            valuesParts.add("%" + conditionMap.get("kindName") + "%");
        }
        if (conditionMap.get("word") != null && StringUtils.isNotBlank((String) conditionMap.get("word"))) {
            wherePart.append(" and words.word like ?");
            valuesParts.add("%" + conditionMap.get("word") + "%");
        }
        String hql = hqlBuffer.append(wherePart).toString();
        List<Words> list = (List<Words>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        for (int i = 0; i < valuesParts.size(); i++) {
                            query.setParameter(i, valuesParts.get(i));
                        }
                        query.setFirstResult((int) conditionMap.get("pageNo"));
                        query.setMaxResults(10);
                        List list = query.list();
                        return list;
                    }
                });
        return list;
    }

    /**
     * 根据查询条件查询数量
     *
     * @param conditionMap
     * @return
     * @throws Exception
     */
    @Override
    public int findWordsCountByCondition(Map<String, Object> conditionMap) throws Exception {
        List<Object> valuesParts = new ArrayList<Object>();
        StringBuffer hqlBuffer = new StringBuffer("from Words words join fetch words.kindDic t1 where 1 = 1");
        StringBuffer wherePart = new StringBuffer();
        if (conditionMap.get("kindName") != null && StringUtils.isNotBlank((String) conditionMap.get("kindName"))) {
            wherePart.append(" and t1.kindName like ?");
            valuesParts.add("%" + conditionMap.get("kindName") + "%");
        }
        if (conditionMap.get("word") != null && StringUtils.isNotBlank((String) conditionMap.get("word"))) {
            wherePart.append(" and words.word like ?");
            valuesParts.add("%" + conditionMap.get("word") + "%");
        }
        String hql = hqlBuffer.append(wherePart).toString();
        List<Words> list = (List<Words>) hibernateTemplate.execute(
                new HibernateCallback() {
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Query query = session.createQuery(hql);
                        for (int i = 0; i < valuesParts.size(); i++) {
                            query.setParameter(i, valuesParts.get(i));
                        }
                        List list = query.list();
                        return list;
                    }
                });
        return list.size();
    }
}
