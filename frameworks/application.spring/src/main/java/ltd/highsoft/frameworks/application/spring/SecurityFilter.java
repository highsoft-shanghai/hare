package ltd.highsoft.frameworks.application.spring;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ltd.highsoft.frameworks.security.core.ContextLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@WebFilter("/**")
public class SecurityFilter extends OncePerRequestFilter {

    private final ContextLoader contextLoader;

    public SecurityFilter(ContextLoader contextLoader) {
        this.contextLoader = contextLoader;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try (HttpHeaderContextLoader loader = new HttpHeaderContextLoader(contextLoader)) {
            loader.load(request);
            filterChain.doFilter(request, response);
        }
    }

}
