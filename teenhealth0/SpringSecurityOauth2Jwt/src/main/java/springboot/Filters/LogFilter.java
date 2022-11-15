package springboot.Filters;

import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import springboot.Filters.wrapper.BodyReaderHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 日志记录的filter
 */
@WebFilter(urlPatterns = "/*", filterName = "logFilter")
@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------------logFilter 过滤器初始化------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 防止流读取一次后就没有了, 所以需要将流继续写出去
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ServletRequest requestWrapper = new BodyReaderHttpServletRequestWrapper(httpServletRequest);
        printRequest(requestWrapper);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    private void printRequest(ServletRequest servletRequest) {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        String requestBody = "";
        String requestContentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (requestContentType != null) {

            //	只拦截 json 请求的参数
            if (requestContentType.startsWith(MediaType.APPLICATION_JSON_VALUE) || requestContentType.startsWith(MediaType.APPLICATION_XML_VALUE)) {
                requestBody = getRequestBody(request);
                log.info("请求开始 url ==={}", uri);
                log.info("请求开始 参数 ==={}", requestBody);
                destroy();
            }
        }
    }

    private String getRequestBody(HttpServletRequest request) {

        int contentLength = request.getContentLength();
        if (contentLength <= 0) {
            return "获取请求体成功";
        }
        try {
            return IOUtils.toString(request.getReader());
        } catch (IOException e) {
            log.error("获取请求体失败", e);
            return "";
        }
    }

    @Override
    public void destroy() {
        System.out.println("--------------过滤器销毁------------");
    }
}
