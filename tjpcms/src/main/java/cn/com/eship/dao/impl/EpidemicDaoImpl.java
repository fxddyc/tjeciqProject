package cn.com.eship.dao.impl;

import cn.com.eship.dao.EpidemicDao;
import cn.com.eship.model.Epidemic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by simon on 16/7/14.
 */
@Repository
public class EpidemicDaoImpl implements EpidemicDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 获取所有疫情总数
     *
     * @return
     */
    @Override
    public Long findEpidemicCount() throws Exception {
        return (long) hibernateTemplate.find("from Epidemic epidemic").size();
    }

    @Override
    public List<Epidemic> findEpidemicList() throws Exception {
        return hibernateTemplate.loadAll(Epidemic.class);
    }
}
