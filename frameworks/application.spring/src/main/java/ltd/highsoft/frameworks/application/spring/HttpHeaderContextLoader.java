package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.security.core.ContextLoader;

import jakarta.servlet.http.HttpServletRequest;

public class HttpHeaderContextLoader implements AutoCloseable {

    private final ContextLoader contextLoader;

    public HttpHeaderContextLoader(ContextLoader authorizer) {
        this.contextLoader = authorizer;
    }

    public void load(HttpServletRequest request) {
        contextLoader.load(new BearerToken(request.getHeader("Authorization")).value());
    }

    @Override
    public void close() {
        contextLoader.clear();
    }

}
