package example.nexacro.uiadapter.option;

import com.nexacro.uiadapter.jakarta.core.annotation.CheckFree;
import com.nexacro.uiadapter.jakarta.core.servlet.CheckFreeHandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * {@code CheckFree} 어노테이션이 선언된 특정 메서드의 경우, 특정 작업(세션 검사)을 생략하는 예제입니다.
 * {@code CheckFreeHandlerInterceptor}를 확장하여 특정 작업(세션 검사)을 하는 예시로 작성되었습니다.
 * 이 인터셉터는 CheckFree 어노테이션의 사용 예시이므로 사용자 세션의 유효성 체크는 별 의미가 없습니다.
 */
public class CheckSessionFreeHandlerInterceptor extends CheckFreeHandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    boolean isFree = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        checkSessionFree(request,  response, handler);
        return isFree;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean checkFree(HttpServletRequest request, HttpServletResponse response) {
        return isFree;
    }

    /**
     * CheckFree 어노테이션의 사용 예시가 있는 함수입니다.
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    boolean checkSessionFree(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // 1. handler 종류 확인
        //	Controller에 있는 메서드를 담당하는 HandlerMethod 타입인지 체크
        if ( handler instanceof HandlerMethod == false ) {
            // return true이면 Controller에 있는 메서드가 아니므로, 그대로 컨트롤러로 진행
            return true;
        }
        // 2.형 변환
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 3. @CheckFree 받아오기
        CheckFree checkFree = handlerMethod.getMethodAnnotation(CheckFree.class);
        // 4. method에 @CheckFree 가 있는 경우, 아래 작업을 하지않고 free pass.
        if (checkFree != null) {
            logger.info("	[ㅁㅁ★ㅁㅁ]  CheckSessionFreeHandlerInterceptor CheckFree = [{}]",isFree);
            return true;
        }
        // 5. ex) interceptor의 본 작업, session 체크 처리.
        HttpSession session = request.getSession();
        if (session == null) {
            // 로그인 페이지로 이동
            response.sendRedirect("/login.do");
            isFree = false;
        }
        return isFree;
    }
}
