package cn.com.eship.dao;

import cn.com.eship.model.Words;

import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/9/13.
 */
public interface WordsDao {
    /**
     * 根据条件查询分词
     *
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public List<Words> findWordsByCondition(Map<String, Object> conditionMap) throws Exception;

    /**
     * 根据查询条件查询数量
     *
     * @param conditionMap
     * @return
     * @throws Exception
     */
    public int findWordsCountByCondition(Map<String, Object> conditionMap) throws Exception;
}
