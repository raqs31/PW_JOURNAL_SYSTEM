package pw.mario.journal.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class SimpleFilter
 */
@WebFilter(filterName="SimpleFilter", 
	initParams={@WebInitParam(name="encoding", value=("UTF-8"))},
	urlPatterns={"/*"},
	dispatcherTypes={DispatcherType.REQUEST}
)
public class SimpleFilter implements Filter {
	Logger LOG = Logger.getLogger(SimpleFilter.class);

	String encoding;
	FilterConfig fc;
	
	public SimpleFilter() {
    	LOG.debug("HUEHUE zosta³em stworzony");
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		LOG.debug("filter destroyed");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		LOG.debug("---------------------------------------------");
        LOG.debug("req.getRequestURI()=" + req.getRequestURI());
        LOG.debug("req.getContextPath()=" + req.getContextPath());
        LOG.debug("req.getRequestURL()=" + req.getRequestURL());
        LOG.debug("---------------------------------------------");
		if (encoding != null && request.getCharacterEncoding() == null)
			request.setCharacterEncoding(encoding);

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		LOG.debug("Filter init");
		encoding = fConfig.getInitParameter("encoding");
		fc = fConfig;
	}

}
