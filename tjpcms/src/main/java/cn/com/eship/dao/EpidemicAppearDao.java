package cn.com.eship.dao;

import cn.com.eship.model.EpidemicAppear;

import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/7/14.
 */
public interface EpidemicAppearDao {
    /**
     * 获取全球疫情TOP10
     *
     * @return
     * @throws Exception
     */
    public List<Object> findEpidemicTopten(Map<String, String> mapPram) throws Exception;

    public List<Object> findEpidemicAppearList(Map<String, String> mapPram) throws Exception;


    public List<Object> findEpidemicAppearRegionCount(Map<String, String> mapPram) throws Exception;

    public List<EpidemicAppear> findEpidemicAppearList(Integer startPosition) throws Exception;

    /**
     * 根据条件查询疫情发生表总条数
     * @return
     * @throws Exception
     */
    public Long findEpidemicAppearCount() throws Exception;

    public EpidemicAppear findEpidemicAppearById(String id) throws Exception;

}
