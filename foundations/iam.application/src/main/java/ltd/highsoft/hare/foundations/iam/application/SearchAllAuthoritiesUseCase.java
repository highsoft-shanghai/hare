package ltd.highsoft.hare.foundations.iam.application;

import ltd.highsoft.hare.foundations.iam.domain.Authorities;
import ltd.highsoft.hare.foundations.iam.domain.Authority;
import ltd.highsoft.hare.frameworks.application.core.UseCase;
import ltd.highsoft.hare.frameworks.domain.core.ValueSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSinkFactory;

@UseCase(requiredAuthorities = ltd.highsoft.hare.frameworks.security.core.Authorities.AUTHENTICATED)
@Deprecated
public class SearchAllAuthoritiesUseCase {
    private final Authorities authorities;
    private final ValueSinkFactory valueSinkFactory;

    public SearchAllAuthoritiesUseCase(Authorities authorities, ValueSinkFactory valueSinkFactory) {
        this.authorities = authorities;
        this.valueSinkFactory = valueSinkFactory;
    }

    public Object execute() {
        ValueSink valueSink = valueSinkFactory.newValueSink();
        Authority.contents(valueSink, authorities.all());
        return valueSink;
    }
}
