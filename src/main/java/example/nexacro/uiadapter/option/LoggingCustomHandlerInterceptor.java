package example.nexacro.uiadapter.option;

import com.nexacro.java.xapi.data.Debugger;
import com.nexacro.java.xapi.data.PlatformData;
import com.nexacro.java.xapi.tx.PlatformException;
import com.nexacro.uiadapter.jakarta.core.context.NexacroContext;
import com.nexacro.uiadapter.jakarta.core.context.NexacroContextHolder;
import com.nexacro.uiadapter.jakarta.core.servlet.LoggingNexacroRequestHandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.method.HandlerMethod;

public class LoggingCustomHandlerInterceptor extends LoggingNexacroRequestHandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(LoggingCustomHandlerInterceptor.class);
    private Logger performanceLogger = LoggerFactory.getLogger("com.nexacro.LoggingCustomHandlerInterceptor");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // handler가 HandlerMethod(컨트롤러 메서드) 타입인지 확인
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (this.isLoggingRequest(handler)) {
            this.writePreHandleLog(request, response);
        }

        return true;
    }

    @Override
    protected String getMenuId(HttpServletRequest httpServletRequest) {
        return "testMenu";
    }

    @Override
    protected String getUserId(HttpServletRequest httpServletRequest) {
        return "testUser";
    }


    public void writePreHandleLog(HttpServletRequest request, HttpServletResponse response) {
        StopWatch sw = new StopWatch(this.getClass().getSimpleName());
        String menuId = this.getMenuId(request);
        String userId = this.getUserId(request);
        PlatformData platformData = new PlatformData();

        try {
            sw.start("logging request");
            if (NexacroContextHolder.getNexacroContext() == null) {
                NexacroContext context = NexacroContextHolder.getNexacroContext(request, response);
                platformData = context.getPlatformData();
            }
        } catch (PlatformException e) {
            throw new RuntimeException(e);
        } finally {
            sw.stop();
            //if (this.performanceLogger.isTraceEnabled()) {
                String stopWatchLog = sw.prettyPrint().replace('\r', '_').replace('\n', '_');
                this.performanceLogger.trace("Performance summary:\n{}", stopWatchLog);
                this.logger.info("[preHandle]=======================================");
                this.logger.info("::: SESSION_ID :: {}", request.getRequestedSessionId());
                this.logger.info("::: MENU_ID ::: {}", menuId);
                this.logger.info("::: USER_ID ::: {}", userId);
                this.logger.info("::: START Time (ms) ::: {}", stopWatchLog);
                this.logger.debug("got request=[{}]", this.sanitizeValue((new Debugger()).detail(platformData)));
                this.logger.info("=================================================");
            //}

        }

    }

    private String sanitizeValue(String input) {
        return input;
    }

}
