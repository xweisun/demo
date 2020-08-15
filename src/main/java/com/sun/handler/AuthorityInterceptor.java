package com.sun.handler;

import com.alibaba.fastjson.JSON;
import com.sun.model.JsonResult;
import com.sun.model.ResultUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @author cuidalong
 * @Description:
 * @Date 2018/6/15 9:48
 */
@Log4j2
@Component
public class AuthorityInterceptor implements HandlerInterceptor {


    /**
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e)
            throws Exception {
    }

    /**
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView)
            throws Exception {
    }

    /**
     * 进入controller层之前拦截请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String servletPath = request.getServletPath();
        String localAddr = request.getLocalAddr();
        String token = request.getHeader("Token");
        if (StringUtils.isBlank(token)) {
            writeResponse(response);
            return false;
        }
        int lastIndex = token.lastIndexOf("#/");
        if (lastIndex > 0){
            token = token.substring(0,lastIndex);
        }
        SessionData sessionData = OcmTokenUtil.getToonType(token, /*applicationProperties.getOcmAppSecret()*/

                                                                   "3455");
        //TODO 测试待删
        sessionData.setToonType("testType");
        if (null == sessionData || StringUtils.isBlank(sessionData.getToonType())) {
            writeResponse(response);
            return false;
        }
        return true;
    }

    /**
     * @param response
     * @throws IOException
     */
    private void writeResponse(HttpServletResponse response) throws IOException {
        Writer writer = null;
        try {
            JsonResult result = ResultUtil.error(0000, "TOKEM" + "不存在");
            //ResponseResult<String> result = ResponseResult.fail(DefaultConstants.TOKEN_NAME + "不存在");
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Expose-Headers", "*");
            writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            writer.close();
        }
    }


}
