package at.mlem;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public final class CharsetFilter implements Filter {

    private static class CharsetResponseWrapper extends HttpServletResponseWrapper {
        private boolean contentTypeSet;

        private CharsetResponseWrapper(HttpServletResponse httpServletResponse) {
            super(httpServletResponse);
            contentTypeSet = false;
        }

        @Override
        public void setContentType(String contentType) {
            if (contentType == null || ("text/html".equals(contentType) && this.contentTypeSet)) {
                return;
            }
            super.setContentType(contentType);
            contentTypeSet = true;
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest hreq = (HttpServletRequest) request;

        String encoding;
        HttpServletResponse hresp = new CharsetResponseWrapper((HttpServletResponse) response);
        encoding = StandardCharsets.ISO_8859_1.name();

        if (encoding != null && encoding.length() > 0) {
            // Set to preferred charset; else default will be used.
            Locale defaultLocale = Locale.getDefault();
            hresp.setLocale(defaultLocale);
            String contentType = hresp.getContentType();
            if (contentType == null) {
                hreq.setAttribute("encoding", encoding);
                hresp.setContentType("text/html; charset=" + encoding);
                hresp.setCharacterEncoding(encoding);
                hreq.setCharacterEncoding(encoding);
            }
        }
        chain.doFilter(hreq, hresp);
    }

    @Override
    public void destroy() {

    }

}
