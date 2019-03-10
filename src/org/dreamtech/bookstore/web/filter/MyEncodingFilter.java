package org.dreamtech.bookstore.web.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;


/**
 * @Program: BookStore
 * @Description: 乱码处理
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebFilter("/*")
public class MyEncodingFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("content-type", "text/html;charset=utf-8");
        HttpServletRequest myRequest = new MyRequest(httpServletRequest);
        chain.doFilter(myRequest, httpServletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}

/**
 * @Program: BookStore
 * @Description: 自定义过滤器
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;
    private static final String CHARSET = "utf-8";
    private static final String POST_METHOD = "post";

    MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        String method = request.getMethod();
        if (method.equalsIgnoreCase(POST_METHOD)) {
            try {
                request.setCharacterEncoding(CHARSET);
                return request.getParameterMap();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return super.getParameterMap();
    }

    @Override
    public String getParameter(String name) {
        Map parameterMap = getParameterMap();
        String[] values = (String[]) parameterMap.get(name);
        if (values == null) {
            return null;
        }
        return values[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        Map parameterMap = getParameterMap();
        return (String[]) parameterMap.get(name);
    }

}
