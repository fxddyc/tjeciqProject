package cn.com.eship.service;


public interface OIEEpidemicSearchService {
    String makeEpidemicNameListJson() throws Exception;
    String makeRegionListJson() throws Exception;
    String makeEpidemicKindListJson() throws Exception;
    String makeEpidemicEventListJson(String pageNo, String epidemicName, String region,String epidemicClass, String startDate, String endDate,String interval) throws Exception;
    String makeEpidemicSourceJson(String rowKey) throws Exception;
    String makeEpidemicRecentOutbreakRegionJson(int interval) throws Exception;
}
