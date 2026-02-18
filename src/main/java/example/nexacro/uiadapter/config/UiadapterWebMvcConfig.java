package example.nexacro.uiadapter.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.nexacro.uiadapter.jakarta.core.context.ApplicationContextProvider;
import com.nexacro.uiadapter.jakarta.core.resolve.NexacroHandlerMethodReturnValueHandler;
import com.nexacro.uiadapter.jakarta.core.resolve.NexacroMappingExceptionResolver;
import com.nexacro.uiadapter.jakarta.core.resolve.NexacroMethodArgumentResolver;
import com.nexacro.uiadapter.jakarta.core.resolve.NexacroRequestMappingHandlerAdapter;
import com.nexacro.uiadapter.jakarta.core.view.NexacroFileView;
import com.nexacro.uiadapter.jakarta.core.view.NexacroStreamView;
import com.nexacro.uiadapter.jakarta.core.view.NexacroView;
import com.nexacro.uiadapter.jakarta.dao.DbVendorsProvider;
import com.nexacro.uiadapter.jakarta.dao.Dbms;
import com.nexacro.uiadapter.jakarta.dao.dbms.Hsql;

import com.nexacro.java.xapi.tx.PlatformType;
import com.nexacro.java.xeni.services.GridExportImportServlet;

@Configuration
public class UiadapterWebMvcConfig  implements WebMvcConfigurer, WebMvcRegistrations {

	private Logger logger = LoggerFactory.getLogger(UiadapterWebMvcConfig.class);
     /**
     * Spring의 <ApplicationContextProvider>를 제공한다.
     * @return
     */
    @Bean
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }

    /**
     * MultipartFilter 재정의
     * 		Spring boot 에서 default로 사용하는 Multipart 설정을 대신할 MultipartFilter.
     */
    @Bean
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }
   
    /**
     * 엑셀처리 <서블릿> 등록
     * Windows : %USER%\AppData\Local\Temp
	 * Linux : /tmp
     * @return
     */
    @Bean
    public ServletRegistrationBean<GridExportImportServlet> gridExportImportServletBean() {
        ServletRegistrationBean<GridExportImportServlet> bean = new ServletRegistrationBean<GridExportImportServlet>(new GridExportImportServlet(), "/XExportImport.do");
        bean.setLoadOnStartup(1);
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(System.getProperty("java.io.tmpdir"), 10000000, 10000000 * 2, 10000000 / 2);
        bean.setMultipartConfig(multipartConfigElement);
        logger.debug("register GridExportImportServlet at XExportImport.do");
        return bean;
    }
    
    /**
     * 넥사크로플랫폼 <RequestMappingHandlerAdapter> 구현체 등록
     */
    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return new NexacroRequestMappingHandlerAdapter();
    }
    
    /**
     * 넥사크로플랫폼 <ArgumentResolver> 등록
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        
        NexacroMethodArgumentResolver nexacroResolver = new NexacroMethodArgumentResolver();
        resolvers.add(nexacroResolver);
        
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    /**
     * @CheckFree, @LoggingNexacroRequest 동작 샘플링을 위한 추가.
     * - 사용 샘플 : BoardController.select_datalist() // "/select_datalist.do"
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new NexacroInterceptor())
//                .order(1) // 최우선순위.
//                .excludePathPatterns("/css/**", "/images/**", "/js/**");
//        registry.addInterceptor(new CheckSessionFreeHandlerInterceptor())
//                .order(2) // 차순위.
//                .excludePathPatterns("/css/**", "/images/**", "/js/**");
//        registry.addInterceptor(new LoggingCustomHandlerInterceptor())
//                .order(3) // 후순위.
//                .excludePathPatterns("/css/**", "/images/**", "/js/**");
//    }

    /**
     * 넥사크로플랫폼 <ReturnValueHandler> 등록
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        
        NexacroHandlerMethodReturnValueHandler returnValueHandler = new NexacroHandlerMethodReturnValueHandler();
        
        NexacroFileView 	nexacroFileView 	= new NexacroFileView();        
        NexacroView     	nexacroView     	= new NexacroView();
        NexacroStreamView	nexacroStreamView	= new NexacroStreamView();
        nexacroView.setDefaultContentType(PlatformType.CONTENT_TYPE_XML); // CONTENT_TYPE_XML, CONTENT_TYPE_SSV
        nexacroView.setDefaultCharset("UTF-8");
        
        returnValueHandler.setView(nexacroView);
        returnValueHandler.setFileView(nexacroFileView);
        returnValueHandler.setStreamView(nexacroStreamView);
        
        handlers.add(returnValueHandler);
        
        WebMvcConfigurer.super.addReturnValueHandlers(handlers);
    }
    
    /**
     * 넥사크로플랫폼 에러 처리 <ExceptionResolver> 등록
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        
        NexacroView nexacroView = new NexacroView();
        nexacroView.setDefaultContentType(PlatformType.CONTENT_TYPE_XML);
        nexacroView.setDefaultCharset("UTF-8");
        
        NexacroMappingExceptionResolver nexacroException = new NexacroMappingExceptionResolver();
        
        nexacroException.setView(nexacroView);
        nexacroException.setShouldLogStackTrace(true);
        nexacroException.setShouldSendStackTrace(true);
        nexacroException.setDefaultErrorMsg("fail.common.msg");
        nexacroException.setOrder(1);
        resolvers.add(nexacroException);
        
        WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
    }
    
    /**
     *  excel import/export [web.xml]설정 등록
     */
    @Bean
    public ServletContextInitializer initializer() {
        return new ServletContextInitializer() {

            /**
             * 넥사크로플랫폼 엑셀 처리 모듈:nexacro-xeni.jar를 이용한 엑셀 처리 설정 
             */
            @Override
            public void onStartup(ServletContext servletContext) throws ServletException {
                servletContext.setInitParameter("export-path"       , "/excel");  //엑셀 export 임시폴더 생성 기준 디렉터리
                servletContext.setInitParameter("import-path"       , "/excel");  //엑셀 import 임시폴더 생성 기준 디렉터리
                servletContext.setInitParameter("monitor-enabled"   , "true");    //임시파일 삭제를 위한 모니터링 여부
                servletContext.setInitParameter("monitor-cycle-time", "30");      //임시파일 삭제를 위한 모니터링 주기( default:분)
                servletContext.setInitParameter("file-storage-time" , "10");      //임시파일 생성 디렉터리 모니터링 주기 (default:분)
            }
        };
    }
    
    
    /**
     * Dbms별 meta정보 조회를 위한 bean을 <DbVendorsProvider>에 등록
     * mybatis 사용 시 데이터가 없는 경우 null 반환
     * nexacro 컬럼정보 생성을 위해 metadata 정보 조회 후 컬럼 생성 처리
     * 
     * sql-mapper-config.xml 에 정의된 plugin에서 처리
     * <plugins>    
     *     <plugin interceptor="com.nexacro.uiadapter.jakarta.dao.mybatis.NexacroMybatisMetaDataProvider" />
     *     <plugin interceptor="com.nexacro.uiadapter.jakarta.dao.mybatis.NexacroMybatisResultSetHandler" />
     * </plugins>
     * 
     * @return
     */
    @Bean
    public DbVendorsProvider dbmsProvider() {
        
        DbVendorsProvider dbmsProvider = new DbVendorsProvider();
        
        Map<String,Dbms> dbvendors = new HashMap<String,Dbms>();
        
        //프로젝트에서 사용하는 데이터베이스만 사용.
        dbvendors.put("HSQL Database Engine", new Hsql());
        //dbvendors.put("Oracle", new Oracle());
        //dbvendors.put("MySQL", new Mysql());
        
        
        /**
         *  <bean id="hsqlDbms"   class="com.nexacro.uiadapter.jakarta.dao.dbms.Hsql" />
         *  <bean id="oracleDbms" class="com.nexacro.uiadapter.jakarta.dao.dbms.Oracle" />
            <bean id="mssqlDbms"  class="com.nexacro.uiadapter.jakarta.dao.dbms.Mssql" />
            <bean id="mysqlDbms"  class="com.nexacro.uiadapter.jakarta.dao.dbms.Mysql" />
            <bean id="tiberoDbms" class="com.nexacro.uiadapter.jakarta.dao.dbms.Tibero" />
            <bean id="mariaDbms" class="com.nexacro.uiadapter.jakarta.dao.dbms.Mariadb" />

            <bean id="dbmsProvider" class="com.nexacro.uiadapter.jakarta.dao.DbVendorsProvider">
                <property name="dbvendors">
                    <map>
                         <entry key="HSQL Database Engine" value-ref="hsqlDbms"/>
                         <entry key="SQLServer"            value-ref="mssqlDbms"/>
                        <entry key="Oracle"               value-ref="oracleDbms"/>
                        <entry key="MySQL"                value-ref="mysqlDbms"/>
                        <entry key="MariaDB"              value-ref="mysqlDbms"/>
                    </map>
                </property>
            </bean>
         */
                
        dbmsProvider.setDbvendors(dbvendors);
        
        return dbmsProvider;
    }
    
    /*
    // camel(whoAreYou), kebab(who-are-you), snake(who_are_you),upper(WHO_ARE_YOU)
	@Bean(name="etcProperty")
	public EtcPropertiesBase etcProperty(@Value("${nexacro.client-column-case}") String clientColumnCase
										, @Value("${nexacro.db-column-case}") String dbColumnCase
										, @Value("${nexacro.who-are-you}") String whoAreYou
										, @Value("${nexacro.use-request-charset}") String userReqeustCharset
										, @Value("${nexacro.use-request-contenttype}") String useRequestContenttype
										, @Value("${nexacro.trim-paramdataset}") String trimParamdataset
										, @Value("${nexacro.trim-paramvariable}") String trimParamvariable
										, @Value("${nexacro.replace-all-empty-variable}") String replaceAllemptyVariable) {
		EtcPropertiesBase etc = new EtcPropertiesBase();
		etc.addEtcProperty("nexacro.client-column-case", clientColumnCase);
		etc.addEtcProperty("nexacro.db-column-case", dbColumnCase);
		etc.addEtcProperty("nexacro.who-are-you", whoAreYou);
		etc.addEtcProperty("nexacro.use-request-charset", userReqeustCharset);
		etc.addEtcProperty("nexacro.use-request-contenttype", useRequestContenttype);
		etc.addEtcProperty("nexacro.trim-paramdataset", trimParamdataset);
		etc.addEtcProperty("nexacro.trim-paramvariable", trimParamvariable);
		etc.addEtcProperty("nexacro.replace-all-empty-variable", replaceAllemptyVariable);
		return etc;
	}
	
	@Bean(name="etcProperty")
	public EtcPropertiesBase etcProperty( @Value("${nexacro.use-request-contenttype}") String useRequestContenttype) {
		EtcPropertiesBase etc = new EtcPropertiesBase();
		etc.addEtcProperty("nexacro.use-request-contenttype", useRequestContenttype);
		return etc;
	}
    */
}

