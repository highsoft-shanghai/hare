package ltd.highsoft.hare.domain;

public class Person {

    private final String id;
    private final String code;
    private final String name;
    private final String remarks;

    public Person(String id, String code, String name, String remarks) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.remarks = remarks;
    }

    public String id() {
        return id;
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }

    public String remarks() {
        return remarks;
    }

}
