package ltd.highsoft.frameworks.application.spring;

import ltd.highsoft.frameworks.application.core.UseCase;
import ltd.highsoft.frameworks.security.core.Authorizer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UseCaseAspect {

    private final Authorizer authorizer = new Authorizer();
    private final ExceptionTranslator exceptionTranslator;

    public UseCaseAspect(ExceptionTranslator exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    @Around("@within(useCase)")
    public Object process(ProceedingJoinPoint joinPoint, UseCase useCase) {
        try {
            return tryToProceed(joinPoint, useCase.requiredAuthorities());
        } catch (Throwable e) {
            throw exceptionTranslator.translate(e);
        }
    }

    private Object tryToProceed(ProceedingJoinPoint joinPoint, String[] requiredAuthorities) throws Throwable {
        authorizer.authorize(requiredAuthorities);
        return joinPoint.proceed();
    }

}
