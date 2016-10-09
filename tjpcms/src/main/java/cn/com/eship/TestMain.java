package cn.com.eship;


import cn.com.eship.model.Words;
import cn.com.eship.service.CommonService;
import cn.com.eship.service.WordsService;
import cn.com.eship.service.impl.CommonServiceImpl;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


/**
 * Created by simon on 16/7/14.
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        //test3();
        test8();
    }

    /**
     * insert one date in hbase
     *
     * @throws Exception
     */
    public static void test1() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicInfo"));
        HColumnDescriptor[] hColumnDescriptors = table.getTableDescriptor().getColumnFamilies();
        for (HColumnDescriptor hColumnDescriptor : hColumnDescriptors) {
            System.out.println(hColumnDescriptor.getNameAsString());
        }
        Put put = new Put("http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html".getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "content".getBytes("utf-8"), ("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html> <head>   <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">   <title>世界卫生组织 | 人感染甲型H7N9禽流感病毒—最新简报</title>   <meta name=\"DC.title\" content=\"世界卫生组织 | 人感染甲型H7N9禽流感病毒—最新简报\">   <meta name=\"DC.keywords\" content=\"西太平洋区域 [region],呼吸道疾病 [subject],肺病,急性呼吸道感染,禽流感 [subject],鸡瘟,鸟类流感,疾病暴发新闻 [doctype],中国 [country]\">   <meta name=\"DC.publisher\" content=\"World Health Organization\">   <meta name=\"DC.format\" content=\"text/html\">   <meta name=\"DC.source\" content=\"WHO\">   <meta name=\"DC.date.published\" content=\"2014-06-26 16:45:03\">   <meta name=\"DC.identifier\" content=\"/entity/csr/don/2014_05_28_h7n9/zh/index.html\">   <meta name=\"description\" content=\"中国国家卫生和计划生育委员会于2014年5月26日向世卫组织通报，新增3起人感染甲型H7N9禽流感病毒实验室确诊病例。\">   <meta name=\"webit_document_id\" content=\"183026\">   <meta name=\"webit_document_name\" content=\"ZH DON 2014_05_28_h7n9 H7N9 avian influenza human infections – update\">   <meta name=\"webit_cover_date\" content=\"2014-05-28\">   <meta name=\"burntime\" content=\"2014-06-26 16:45:34.000000\">      <link rel=\"shortcut icon\" href=\"/sysmedia/media/resources/favicon.ico\">   <link rel=\"apple-touch-icon\" href=\"/sysmedia/media/resources/apple-touch-icon.png\">         <!--[if gte IE 9]><![endif]-->   <!--[if !IE]-->      <!--[endif]-->            <link href=\"/sysmedia/media/style/who_responsive.css\" rel=\"stylesheet\" type=\"text/css\">   <!--[if IE]><link href=\"/sysmedia/media/style/css/patches/patch_ie_r.css\" rel=\"stylesheet\" type=\"text/css\" /><![endif]-->   <!--[if IE 7]><link href=\"/sysmedia/media/style/css/patches/patch_ie_7_r.css\" rel=\"stylesheet\" type=\"text/css\" /><![endif]-->   <!--[if IE 6]><link href=\"/sysmedia/media/style/css/patches/patch_ie_6_r.css\" rel=\"stylesheet\" type=\"text/css\" /><![endif]-->   <!--[if lte IE 7]><link href=\"/sysmedia/media/style/css/screen/responsive-ie-fix.css\" rel=\"stylesheet\" type=\"text/css\" /><![endif]-->   <!--[if IE 8]><link href=\"/sysmedia/media/style/css/screen/responsive-ie-fix.css\" rel=\"stylesheet\" type=\"text/css\" /><![endif]-->   <link rel=\"stylesheet\" type=\"text/css\" href=\"/sysmedia/media/style/css/language/lang_zh_r.css\">      <link rel=\"stylesheet\" type=\"text/css\" href=\"/sysmedia/scripts/shadowbox/zh/shadowbox.css\">         <!-- secondary nav styles -->   <style type=\"text/css\">#__csr__don ul {display: block;}#__csr__don ul ul {display: none;}#__csr__don ul .node a {background: url(/sysmedia/media/style/img/node-closed.gif) no-repeat 1px 8px;}#__csr__don a {background: url(/sysmedia/media/style/img/node-open.gif) no-repeat 0px 10px;}#__csr__don.leave a, #__csr__don .leave a, #__csr__don .leaves a {background:none;}#__csr__don a, #__csr__don li a:hover {color:#D86422;}#__csr__don li a {color:#196aaa;}</style>     </head>  <body class=\"main-site\">   <!-- Google Tag Manager -->   <noscript>   <iframe src=\"//www.googletagmanager.com/ns.html?id=GTM-MDCJXB\" height=\"0\" width=\"0\" style=\"display:none;visibility:hidden\"></iframe>  </noscript>      <!-- End Google Tag Manager -->   <!-- begin: page -->   <div id=\"page\" class=\"template_sidebar-marginal\">    <!-- begin: wrapper -->    <div id=\"wrapper\">     <!-- begin: header -->     <div id=\"header\">      <!--including masthead file: /mason/mheader_zh.mc?entity_name=csr -->      <!--googleoff: index-->      <!-- begin: skip to content -->      <div id=\"skip\" class=\"zh\">      <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html#content\">跳转到主要内容</a>     </div>      <!-- CHANGED -->      <!-- div id=\"campaignHighlight\"><a href=\"http://www.who.int/campaigns/no-tobacco-day/2016/zh/\" id =\"campaign-hepatitis-2015\"> <span>世卫组织宣传活动</span><img src=\"http://www.who.int/campaigns/no-tobacco-day/2016/wntd-masterhead-zh.jpg\" alt=\"世卫组织宣传活动\"></a></div -->      <!-- script> $( document ).ready(function() {function getRandomNumber(min, max){return Math.floor(Math.random() * (max - min + 1)) + min;}var i1 = \"http://www.who.int/campaigns/world-blood-donor-day/2016/masthead-girl-zh.jpg\";var i2 = \"http://www.who.int/campaigns/world-blood-donor-day/2016/masthead-boy-zh.jpg\";          var i3 = \"http://www.who.int/campaigns/world-blood-donor-day/2016/masthead-woman-zh.jpg\";var texts = [i1, i2, i3];var cUrl = \"http://www.who.int/campaigns/world-blood-donor-day/2016/zh/\";$(\"#campaignHighlight\").append( '<a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html\" + cUrl +'\" id =\"blood-day-2016\"> <span>Campaign</span><img src=\"'+ texts[getRandomNumber(0, texts.length-1)] +'\" alt=\"Campaign\"></a>');});</script -->      <div id=\"campaignHighlight\">      </div>      <!-- begin: branding -->      <div id=\"branding\">       <a linkindex=\"0\" href=\"http://www.who.int/zh\" title=\"世界卫生组织 | 世界卫生组织\"> </a>      </div>      <!-- end: branding -->      <!-- begin: access -->      <div id=\"access\">       <h3>Access</h3>       <ul>        <li> <a linkindex=\"1\" accesskey=\"0\" title=\"Home\" href=\"http://www.who.int/zh\">Home Alt+0</a> </li>        <li> <a linkindex=\"2\" accesskey=\"1\" title=\"Navigation\" href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html#navigation\">Navigation Alt+1</a> </li>        <li> <a linkindex=\"3\" accesskey=\"2\" title=\"Content\" href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html#main\">Content Alt+2</a> </li>       </ul>      </div>      <!-- end: access -->      <!-- begin: search -->      <span id=\"mobile-search-icon\" class=\"mobile-search-icon\"><i class=\"search-icon-image\"></i></span>      <div id=\"search\">       <h3>搜索</h3>       <form id=\"search_form\" action=\"http://search.who.int/search\">        <span id=\"search_label\"> <label for=\"q\"> 搜索 </label> </span>        <span id=\"search_input\"> <input name=\"q\" id=\"q\" title=\"Search the WHO int. site\" type=\"text\"> </span>        <label for=\"search_submit\" class=\"invisible\"> Submit </label>        <input type=\"hidden\" name=\"ie\" value=\"utf8\">        <input type=\"hidden\" name=\"site\" value=\"who\">        <input type=\"hidden\" name=\"client\" value=\"_zh_r\">        <input type=\"hidden\" name=\"proxystylesheet\" value=\"_zh_r\">        <input type=\"hidden\" name=\"output\" value=\"xml_no_dtd\">        <input type=\"hidden\" name=\"oe\" value=\"utf8\">        <input id=\"search_submit\" value=\"搜索\" title=\"搜索\" type=\"submit\">        <a href=\"http://search.who.int/search?q=&amp;ie=utf8&amp;site=who&amp;client=_zh_r&amp;proxystylesheet=_zh_r&amp;output=xml_no_dtd&amp;oe=UTF-8&amp;ip=10.28.74.124&amp;access=p&amp;sort=date%3AD%3AL%3Ad1&amp;entqr=3&amp;ud=1&amp;proxycustom=%3CADVANCED/%3E\" title=\"高级搜索\" id=\"search_advanced\"> 高级搜索 </a>       </form>      </div>      <!-- end: search -->      <!-- begin: navigation -->      <span id=\"mobile-nav-icon\" class=\"mobile-nav-icon\"><i class=\"nav-icon-image\"></i></span>      <div id=\"navigation\">       <h3>Navigation</h3>       <ul>        <li id=\"navigation_home\"> <a linkindex=\"5\" href=\"http://www.who.int/zh\" title=\"主页\"> <span>主页</span> </a> </li>        <li id=\"navigation_health-topics\"> <a href=\"http://www.who.int/topics/zh\" title=\"健康主题\"> <span>健康主题</span> </a> </li>        <li id=\"navigation_data-and-statistics\"> <a href=\"http://www.who.int/entity/gho/zh\" title=\"全球卫生观察站数据\"> <span>数据和统计数字</span> </a> </li>        <li id=\"navigation_media-centre\"> <a href=\"http://www.who.int/entity/mediacentre/zh\" title=\"媒体中心\"> <span>媒体中心 </span> </a> </li>        <li id=\"navigation_publications\"> <a href=\"http://www.who.int/publications/zh\" title=\"出版物\"> <span>出版物</span> </a> </li>        <li id=\"navigation_countries\"> <a linkindex=\"6\" href=\"http://www.who.int/countries/zh\" title=\"国家\"> <span>国家</span> </a> </li>        <li id=\"navigation_programmes-and-projects\" class=\"selected\"> <a linkindex=\"7\" href=\"http://www.who.int/entity/zh/\" title=\"规划和项目\"> <span>规划和项目</span> </a> </li>        <li id=\"navigation_governance\"> <a href=\"http://www.who.int/governance/zh\" title=\"管理\"> <span>管理</span> </a> </li>        <li id=\"navigation_about-who\" class=\"last  \"> <a href=\"http://www.who.int/about/zh\" title=\"关于世卫组织\"> <span>关于世卫组织</span> </a> </li>       </ul>      </div>      <!-- end: navigation -->      <div class=\"clear\"></div>      <!-- begin: language -->      <div id=\"language\">       <h3>Language</h3>       <div class=\"language_container\">        <div id=\"owl-language-carousel\" dir=\"ltr\" class=\"owl-carousel owl-theme\">         <div id=\"language_ar\" class=\"item\">         <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/ar/index.html\" title=\"阿拉伯文\"><span dir=\"rtl\">عربي</span> </a>         </div>         <div id=\"language_zh\" class=\"item  selected\">         <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html\" title=\"中文\"><span>中文</span> </a>         </div>         <div id=\"language_en\" class=\"item\">         <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/en/index.html\" title=\"英文\"><span>English</span> </a>         </div>         <div id=\"language_fr\" class=\"item\">         <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/fr/index.html\" title=\"法文\"><span>Français</span> </a>         </div>         <div id=\"language_ru\" class=\"item\">         <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/ru/index.html\" title=\"俄文\"><span>Русский</span> </a>         </div>         <div id=\"language_es\" class=\"item last disabled\">         <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html#\" title=\"Esta página no está disponible en castellano\"><span>Español</span> </a>         </div>        </div>       </div>      </div>      <!-- end: language -->      <!-- begin: social links -->      <ul class=\"header-social-links\">       <li><a href=\"http://www.who.int/about/licensing/rss/zh/index.html\" target=\"_blank\" class=\"rss\">RSS Feed</a></li>       <li><a href=\"http://www.youtube.com/who\" target=\"_blank\" class=\"youtube\">Youtube</a></li>       <li><a href=\"https://twitter.com/who\" target=\"_blank\" class=\"twitter\">Twitter</a></li>       <li><a href=\"https://www.facebook.com/WHO\" target=\"_blank\" class=\"facebook\">Facebook</a></li>       <li><a href=\"https://itunes.apple.com/gb/app/who-info/id895463794?mt=8\" target=\"_blank\" class=\"appstore\">iTunes</a></li>       <li><a href=\"https://play.google.com/store/apps/details?id=uk.co.adappt.whoapp\" target=\"_blank\" class=\"playstore\">Play Store</a></li>       <li><a href=\"http://weibo.com/whoinchina\" target=\"_blank\" class=\"weibo\">Weibo</a></li>      </ul>      <!-- end: social links -->      <!--googleon: index-->     </div>     <!-- end: header -->     <!-- begin: main -->     <div id=\"main\">      <!-- begin: title -->      <div id=\"title\">       <h2> 全球预警和应对(GAR) </h2>      </div>      <!-- end: title -->      <!-- begin: sidebar -->      <div id=\"sidebar\">       <!-- begin: entity tab subnavigation -->       <div id=\"subnavigation\">        <h3>导航</h3>        <!-- begin: subnavigation list -->        <ul class=\"subnavigation\">         <li class=\"leave\"><a href=\"http://www.who.int/entity/csr/zh/\">GAR主页</a></li>         <li class=\"leave\"><a href=\"http://www.who.int/entity/csr/alertresponse/zh/\">预警和应对活动</a></li>         <li class=\"leave\"><a href=\"http://www.who.int/entity/csr/disease/zh/\">疾病</a></li>         <li class=\"leave\"><a href=\"http://www.who.int/entity/csr/outbreaknetwork/zh/\">全球疫情警报和反应网络</a></li>        </ul>        <!-- end: subnavigation list -->       </div>       <!-- end: subnavigation -->      </div>      <!-- end: sidebar -->      <!-- begin: content -->      <div id=\"content\">       <!-- begin: primary -->       <div id=\"primary\">        <h1 class=\"headline\">人感染甲型H7N9禽流感病毒—最新简报</h1>        <div class=\"meta\">         <!-- Default DIV wrapper for all story meta data -->         <p><span class=\"\">疾病暴发新闻</span> </p>        </div>        <!-- close of the meta div -->        <p> <em class=\"dateline\"> 2014年5月28日 -</em> <span>中国国家卫生和计划生育委员会于2014年5月26日向世卫组织通报，新增3起人感染甲型H7N9禽流感病毒实验室确诊病例。</span></p>        <!-- begin: div inlay -->        <div class=\"inlay_color\">         <h4 class=\"section_head2\">病例详情如下：</h4>         <ul class=\"disc\">          <li>安徽省安庆市一名40岁男子于5月9日出现症状，5月17日住院，目前病情危重。</li>          <li>安徽省黄山市一名69岁男子于5月14日出现症状，5月16日住院，目前病情危重。</li>          <li>安徽省马鞍山市一名58岁男子于5月8日出现症状，5月19日住院，目前病情危重。</li>         </ul>         <div class=\"clear\">         <!-- all clear -->        </div>        </div>        <!-- end: div inlay -->        <p> <span>中国政府已采取以下监测和控制措施：</span></p>        <ul class=\"disc\">         <li>加强监测和疫情分析；</li>         <li>强化病例管理和治疗；</li>         <li>与公众进行风险沟通并发布信息。 </li>        </ul>        <h3 class=\"section_head1\">散在人间病例</h3>        <p> <span>整体风险评估并未发生变化。</span></p>        <p> <span>以往曾报告称，在中国大陆向香港特别行政区出口的活禽中发现了甲型H7N9禽流感病毒。这表明病毒具有随着活禽流动进行传播的能力，目前没有迹象显示已经发生了甲型H7N9禽流感病毒国际传播。但由于病毒感染并不能使禽类出现疾病体征，因此需要持续开展监测活动。</span></p>        <p> <span>在受影响地区以及也许在周边地区，预计将进一步发生人感染甲型H7N9禽流感病毒散在病例。</span></p>        <p> <span>当出自感染地区的人间病例到国外旅行时，其感染状况可能会在抵达期间或抵达后为他国发现。如出现这种情况，由于这一病毒并不具备在人间易于传播的能力，因此不太可能发生社区传播。在病毒可以适应有效人际传播之前，H7N9病毒通过旅行者正在造成的国际传播风险较低。</span></p>        <h3 class=\"section_head1\">世卫组织的建议</h3>        <p> <span>世卫组织建议，前往已知发生禽流感疫情的国家旅行的人应避免接触家禽养殖场，或避免与活禽市场中的动物接触，或避免进入可能宰杀家禽的场所，或避免接触看似受到家禽或其他动物粪便污染的任何表面。旅行者还应经常用肥皂和水洗手。他们应遵守良好的食品安全和饮食卫生习惯。</span></p>        <p> <span>世卫组织并不建议在入境口岸针对这一事件实施特别筛查，也不建议目前采取任何旅行或者贸易限制措施。</span></p>        <p> <span>对于正在面临禽流感问题的地区旅行或刚从该地区返回的个人，如出现严重急性呼吸道感染症状，通常应考虑作出禽流感病毒感染诊断。</span></p>        <p> <span>世卫组织鼓励各国继续加强流感监测，包括对严重急性呼吸道感染（SARI）实施监测并且对任何异常情形进行认真审查，以确保根据《国际卫生条例（2005）》通报人感染病例，并继续开展国家卫生防范行动。</span></p>        <div class=\"clear\">        <!-- all clear -->       </div>       </div>      <!-- end: primary -->       <!-- begin: marginal -->       <div id=\"marginal\">        <!-- This is where some elements will always be displayed such as inset, table of contents, page tools.  -->        <!--googleoff: all-->        <!-- begin: pageactions -->        <div id=\"pageactions\">         <!-- begin: addthis -->         <div id=\"pageaction_share\">          <div class=\"addthis_toolbox\">           <div class=\"custom_hover\">            <span class=\"custom_button\">分享</span>           </div>           <div class=\"hover_menu\">            <div class=\"links\">             <a class=\"addthis_button_email\" title=\"发送到 发送电子邮件\">发送电子邮件</a>             <a class=\"addthis_button_qzone\" title=\"发送到 QQ空间\">QQ空间</a>             <a class=\"addthis_button_share.renren.com\" title=\"发送到 人人网\">人人网</a>             <a class=\"addthis_button_sinaweibo\" title=\"发送到 新浪微博\">新浪微博</a>             <a class=\"addthis_button_baidu\" title=\"发送到 百度空间\">百度空间</a>             <div class=\"more\">              <a class=\"addthis_button_expanded\">更多</a>             </div>            </div>           </div>                                           </div>         </div>        <!-- end: addthis -->         <div id=\"pageaction_print\">          <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html#\" title=\"打印\"><span>打印</span></a>         </div>        </div>       <!-- end: pageactions -->        <!--googleon: all-->        <!-- begin: colormbox -->        <div class=\"colormbox\">         <h3 class=\"mbox_title\">相关链接</h3>         <ul class=\"list\">          <li> <a href=\"http://www.who.int/entity/influenza/human_animal_interface/influenza_h7n9/zh/index.html\">甲型H7N9禽流感病毒</a> </li>          <li> <a href=\"http://www.who.int/entity/foodsafety/fs_management/live_markets/zh/index.html\">生鲜农贸市场：降低动物传播流感病毒的风险</a> </li>          <li> <a class=\"link_sites\" href=\"http://www.wpro.who.int/entity/china/topics/h7n9_influenza/faq_20140117/zh/index.html\">甲型H7N9禽流感病毒常见问题</a><br><span class=\"link_info\">世卫组织驻华代表处</span> </li>         </ul>        </div>       <!-- end: colormbox -->       </div>      <!-- end: marginal -->       <div class=\"clear\"></div>      </div>      <!-- end: content -->      <div class=\"clear\">       <!-- all clear -->      </div>     </div>     <!-- end: main -->     <div id=\"breadcrumb\">      <h2>You are here:</h2>      <ul>       <li> <a href=\"http://www.who.int/entity/csr/zh/\" title=\"全球预警和应对(GAR)\">全球预警和应对(GAR)</a> </li>       <li class=\"selected\"> <a href=\"http://www.who.int/entity/csr/don/zh/\" title=\"疾病暴发新闻\">疾病暴发新闻</a> </li>      </ul>      <div class=\"clear\"></div>     </div>     <!-- begin: footer -->     <div id=\"footer\">      <!--googleoff: index-->      <!-- begin: doormat -->      <div id=\"doormat\">       <h2>Quick Links</h2>       <!-- column -->       <div class=\"doormat_col_1_3\">        <h3>网站地图</h3>        <ul class=\"footer-col-links\">         <li> <a href=\"http://www.who.int/zh/index.html\">主页</a> </li>         <li> <a href=\"http://www.who.int/topics/zh/index.html\">健康主题</a> </li>         <li> <a href=\"http://www.who.int/entity/gho/zh/index.html\">数据和统计数字</a> </li>         <li> <a href=\"http://www.who.int/entity/mediacentre/zh/index.html\">媒体中心</a> </li>         <li> <a href=\"http://www.who.int/publications/zh/index.html\">出版物</a> </li>         <li> <a href=\"http://www.who.int/countries/zh/index.html\">国家</a> </li>         <li> <a href=\"http://www.who.int/entity/zh/index.html\">规划和项目</a> </li>         <li> <a href=\"http://www.who.int/governance/zh/index.html\">管理</a> </li>         <li> <a href=\"http://www.who.int/about/zh/index.html\">关于世卫组织</a> </li>        </ul>       </div>       <!-- column -->       <div class=\"doormat_col_2_3\">        <h3>帮助和服务</h3>        <ul class=\"footer-col-links\">         <li> <a href=\"http://www.who.int/about/contacthq/zh/index.html\">联系我们</a> </li>         <li> <a href=\"http://www.who.int/suggestions/faq/zh/index.html\">常见问题</a> </li>         <li> <a href=\"http://www.who.int/entity/employment/zh/index.html\">就业机会</a> </li>         <li> <a href=\"http://www.who.int/suggestions/zh/index.html\">意见反馈</a> </li>         <li> <a href=\"http://www.who.int/about/privacy/zh/index.html\">隐私保护</a> </li>         <li> <a href=\"http://www.who.int/about/scamalert/zh/index.html\">欺诈邮件</a> </li>        </ul>       </div>       <!-- column -->       <div class=\"doormat_col_3_3\">        <h3>世卫组织区域办事处</h3>        <ul class=\"footer-col-links\">         <li> <a href=\"http://www.afro.who.int/\" target=\"_new\">世卫组织非洲区域</a> </li>         <li> <a href=\"http://www.paho.org/\" target=\"_new\">世卫组织美洲区域</a> </li>         <li> <a href=\"http://www.searo.who.int/\" target=\"_new\">世卫组织东南亚区域</a> </li>         <li> <a href=\"http://www.euro.who.int/\" target=\"_new\">世卫组织欧洲区域</a> </li>         <li> <a href=\"http://www.emro.who.int/\" target=\"_new\">世卫组织东地中海区域</a> </li>         <li> <a href=\"http://www.wpro.who.int/\" target=\"_new\">世卫组织西太平洋区域</a> </li>        </ul>       </div>       <!-- column -->       <div>        <ul class=\"footer-social-links\">         <li> <a class=\"footer-rss\" href=\"http://www.who.int/about/licensing/rss/zh/index.html\">RSS订阅</a> </li>         <li> <a class=\"footer-youtube\" href=\"http://www.youtube.com/who\" target=\"_new\">世卫组织YouTube频道</a> </li>         <li> <a class=\"footer-twitter\" href=\"http://twitter.com/who\" target=\"_new\">世卫组织Twitter页面</a> </li>         <li> <a class=\"footer-facebook\" href=\"https://www.facebook.com/WHO\" target=\"_new\">世卫组织Facebook页面</a> </li>         <li> <a class=\"footer-appstore\" href=\"https://itunes.apple.com/gb/app/who-info/id895463794?mt=8\" target=\"_new\"> <acronym title=\"World Health Organization\"> WHO</acronym> iTunes</a> </li>         <li> <a class=\"footer-playstore\" href=\"https://play.google.com/store/apps/details?id=uk.co.adappt.whoapp\" target=\"_new\"> <acronym title=\"World Health Organization\"> WHO</acronym> Play Store</a> </li>         <li> <a class=\"footer-weibo\" href=\"http://weibo.com/whoinchina\" target=\"_new\"> <acronym title=\"World Health Organization\"> WHO</acronym> Weibo</a> </li>        </ul>       </div>       <div class=\"clear\">        <!-- all clear -->       </div>      </div>      <!-- end: doormat -->      <!-- begin: foot -->      <div id=\"foot\">       <p><a href=\"http://www.who.int/about/copyright/zh/\">© 世卫组织2016年</a></p>      </div>      <!-- end: foot -->      <a href=\"http://www.who.int/entity/csr/don/2014_05_28_h7n9/zh/index.html#\" class=\"back-to-top\"><span>返回顶部</span></a>            <!--googleon: index-->     </div>     <!-- end: footer -->    </div>    <!-- end: wrapper -->   </div>   <!-- end: page -->        <!-- Elapsed burn time: 4.23603200912476 sec -->  </body></html>").getBytes("utf-8"));
        //date
        put.addColumn("c1".getBytes("utf-8"), "time".getBytes("utf-8"), "2016-08-22".getBytes("utf-8"));
        //标题
        put.addColumn("c1".getBytes("utf-8"), "titel".getBytes("utf-8"), "世界卫生组织 | 人感染甲型H7N9禽流感病毒—最新简报".getBytes("utf-8"));
        //疫情名称
        put.addColumn("c1".getBytes("utf-8"), "epidemicName".getBytes("utf-8"), "人感染甲型H7N9禽流感病毒".getBytes("utf-8"));
        table.put(put);
        table.close();

    }

    /**
     * get some cell value from hbase
     *
     * @throws Exception
     */
    public static void test2() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicInfo"));
        Get get = new Get("http://www.aqsiq.gov.cn/xxgk_13386/tsxx/yqts/201608/t20160822_472656.htm".getBytes("utf-8"));
        Result result = table.get(get);
        List<Cell> cellList = result.getColumnCells("c1".getBytes("utf-8"), "content".getBytes("utf-8"));
        for (Cell cell : cellList) {
            System.out.println(cell);
        }
        table.close();
    }


    /**
     * 百度百科建表
     *
     * @throws Exception
     */
    public static void test3() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Admin admin = connection.getAdmin();
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf("epidemicBaike"));
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("c1");
        hTableDescriptor.addFamily(hColumnDescriptor);
        admin.createTable(hTableDescriptor);
        admin.close();
    }

    public static void test4() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicBaike"));
        Put put = new Put("1".getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "imgUrl".getBytes("utf-8"), "https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1772238410,3442357385&fm=58".getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "content".getBytes("utf-8"), ("<div class='lemma-summary' label-module='lemmaSummary'>\n" +
                "<div class='para' label-module='para'>霍乱是因摄入的食物或水受到霍乱弧菌污染而引起的一种急性腹泻性传染病。每年，估计有300万～500万霍乱病例，另有10万～12万人死亡。病发高峰期在夏季，能在数小时内造成腹泻脱水甚至死亡。</div><div class='para' label-module='para'>霍乱是由霍乱弧菌所引起的。O1和O139这两种霍乱弧菌的血清型能够引起疾病暴发。大多数的疾病暴发由O1型霍乱弧菌引起，而1992年首次在孟加拉国确定的O139型仅限于东南亚一带。非O1非O139霍乱弧菌可引起轻度腹泻，但不会造成疾病流行。最近，在亚洲和非洲的一些地区发现了新的变异菌株。据观察认为，这些菌株可引起更为严重的霍乱疾病，死亡率更高。</div><div class='para' label-module='para'>霍乱弧菌存在于水中，最常见的感染原因是食用被患者粪便污染过的水。霍乱弧菌能产生霍乱毒素，造成分泌性腹泻，即使不再进食也会不断腹泻，洗米水状的粪便是霍乱的特征。</div>\n" +
                "</div>").getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "epidemicName".getBytes("utf-8"), ("霍乱").getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "contentUrl".getBytes("utf-8"), ("http://baike.baidu.com/link?url=nAX-BzmPQiBBlQOgiyjDXbh1OJq40bO9gzUUjnb1Gsp79TGdIv9fILxYg52hNQTVGTO4XWugoQalgZHtz_8iE_").getBytes("utf-8"));
        put.addColumn("c1".getBytes("utf-8"), "summary".getBytes("utf-8"), ("霍乱是因摄入的食物或水受到霍乱弧菌污染而引起的一种急性腹泻性传染病。每年，估计有300万～500万霍乱病例，另有10万～12万人死亡。病发高峰期在夏季，能在数小时内造成腹泻脱水甚至死亡。\n" +
                "霍乱是由霍乱弧菌所引起的。O1和O139这两种霍乱弧菌的血清型能够引起疾病暴发。大多数的疾病暴发由O1型霍乱弧菌引起，而1992年首次在孟加拉国确定的O139型仅限于东南亚一带。非O1非O139霍乱弧菌可引起轻度腹泻，但不会造成疾病流行。最近，在亚洲和非洲的一些地区发现了新的变异菌株。据观察认为，这些菌株可引起更为严重的霍乱疾病，死亡率更高。\n" +
                "霍乱弧菌存在于水中，最常见的感染原因是食用被患者粪便污染过的水。霍乱弧菌能产生霍乱毒素，造成分泌性腹泻，即使不再进食也会不断腹泻，洗米水状的粪便是霍乱的特征").getBytes("utf-8"));
        table.put(put);
        table.close();
    }


    /**
     * 百度百科建表
     *
     * @throws Exception
     */
    public static void test5() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Connection connection = applicationContext.getBean(Connection.class);
        Table table = connection.getTable(TableName.valueOf("epidemicBaike"));
        Scan scan = new Scan();
        //scan.addColumn("c1".getBytes(),"epidemicName".getBytes()).addColumn("c1".getBytes(),"imgUrl".getBytes());
        ResultScanner resultScanner = table.getScanner(scan);
        System.out.println(new String(resultScanner.next().getValue("c1".getBytes(), "epidemicName".getBytes())));

    }


    public static void test6() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommonService commonService = applicationContext.getBean(CommonService.class);
        System.out.println(commonService.makeEpidemicNameListJson());
        System.out.println(commonService.makeRegionListJson());

    }

    public static void test7() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        CommonService commonService = applicationContext.getBean(CommonService.class);
        System.out.println(commonService.makeEpidemicNameListJson());
        System.out.println(commonService.makeRegionListJson());

    }

    public static void test8() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        WordsService wordsService = applicationContext.getBean(WordsService.class);
        System.out.println(wordsService.makeWordsDicListByCondition(null,null,"2"));

    }



}


