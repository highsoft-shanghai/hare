package ltd.highsoft.monolithic.domain;

public class Tag {

    private final String id;
    private final String code;
    private final String name;

    public Tag(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
