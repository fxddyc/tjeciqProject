package cn.com.eship.service;


public interface OIEEpidemicSearchService {
    String makeEpidemicNameListJson() throws Exception;
    String makeRegionListJson() throws Exception;
    String makeEpidemicKindListJson() throws Exception;
    String makeEpidemicEventListJson(String pageNo, String epidemicName, String region,String epidemicClass, String startDate, String endDate) throws Exception;
}
