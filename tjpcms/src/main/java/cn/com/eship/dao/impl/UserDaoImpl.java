package cn.com.eship.dao.impl;

import cn.com.eship.dao.UserDao;
import cn.com.eship.utils.CommenUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;

@Repository
public class UserDaoImpl implements UserDao{

    private final HibernateTemplate hibernateTemplate;

    @Autowired
    public UserDaoImpl(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Map<String,Object> checkUserIdentity(String userID, String passWd) {
        Map<String,Object> resultMap = new HashMap<>();
        String sql = "SELECT * from t_user a left join t_authority b on a.authority_id=b.id WHERE a.user_name= ? AND a.passwd = ?";
        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                query.setParameter(0,userID);
                query.setParameter(1,passWd);
                query.setMaxResults(1);
                List list = query.list();
                if (list.size()<=0){
                    resultMap.put("result",false);
                }else {
                    Map map = (Map)list.get(0);
                    resultMap.put("result",true);
                    resultMap.put("authority",map.get("menu_id"));
                    resultMap.put("userName",map.get("user_name"));
                    return resultMap;
                }
                return resultMap;
            }
        });
        return resultMap;
    }

    @Override
    public List<Map<String,Object>> findUserList() {
        List<Map<String,Object>> jsonList = new ArrayList<>();
        String sql = "SELECT a.id,a.user_name,a.passwd,b.name from t_user a " +
                "left join t_authority b on a.authority_id=b.id " +
                "ORDER BY a.authority_id";
        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                List list = query.list();
                if(list==null||list.size()<=0) return jsonList;
                for (int i=0;i<list.size();i++){
                    Map<String,Object> map2= new HashMap<>();
                    Map map = (Map)list.get(i);
                    map2.put("name",map.get("user_name"));
                    map2.put("passWd",map.get("passwd"));
                    map2.put("authority",map.get("name"));
                    map2.put("id",map.get("id"));
                    jsonList.add(map2);
                }
                return jsonList;
            }
        });
        return jsonList;
    }

    @Override
    public Map<String,Object> updateUserInfo(String id, String userName,String passWd,String authority) {
        if (StringUtils.isEmpty(id)){
            id = CommenUtils.getUUID();
        }
        Map<String,Object> jsonMap = new HashMap<>();
        String sql = "INSERT INTO t_user (id,user_name,passwd,authority_id)" +
                " VALUES (?,?,?,(SELECT id from t_authority WHERE name=?)) " +
                " ON DUPLICATE KEY UPDATE user_name=?,passwd=?,authority_id=(SELECT id from t_authority WHERE name=?)";
        String finalId = id;
        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                query.setParameter(0, finalId);
                query.setParameter(1,userName);
                query.setParameter(2,passWd);
                query.setParameter(3,authority);
                query.setParameter(4,userName);
                query.setParameter(5,passWd);
                query.setParameter(6,authority);
                int i = query.executeUpdate();
                if(i>0){
                    jsonMap.put("result",true);
                    jsonMap.put("id", finalId);
                    jsonMap.put("passWd",passWd);
                    jsonMap.put("userName",userName);
                    jsonMap.put("authority",authority);
                }else {
                    jsonMap.put("result",false);
                }
                return jsonMap;
            }
        });
        return jsonMap;
    }

    @Override
    public Map<String,Object> deleteUser(String id){
        if (StringUtils.isEmpty(id)) return null;
        Map<String,Object> jsonMap = new HashMap<>();
        String sql = "DELETE FROM t_user WHERE id=?";
        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
                query.setParameter(0, id);
                int i = query.executeUpdate();
                if(i>0){
                    jsonMap.put("result",true);
                }else {
                    jsonMap.put("result",false);
                }
                return jsonMap;
            }
        });
        return jsonMap;
    }
}
