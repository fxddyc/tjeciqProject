package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicItemDao;
import cn.com.eship.model.ChineseStandardAnimalEpidemicItem;
import cn.com.eship.service.EpidemicItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by simon on 2017/9/25.
 */
@Service
public class EpidemicItemServiceImpl implements EpidemicItemService {
    private final EpidemicItemDao epidemicItemDao;

    @Autowired
    public EpidemicItemServiceImpl(EpidemicItemDao epidemicItemDao) {
        this.epidemicItemDao = epidemicItemDao;
    }

    @Override
    public List<ChineseStandardAnimalEpidemicItem> findEpidemicItemList(ChineseStandardAnimalEpidemicItem epidemicItem) throws Exception {
        return epidemicItemDao.findEpidemicItemList(epidemicItem);
    }

}
