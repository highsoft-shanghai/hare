package ltd.highsoft.hare.frameworks.domain.core;

public class BadInputException extends DomainException {

    public BadInputException(I18nMessage message) {
        super(message);
    }

}
