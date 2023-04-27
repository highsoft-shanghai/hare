package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Id;
import ltd.highsoft.hare.frameworks.domain.core.ObjectSink;
import ltd.highsoft.hare.frameworks.domain.core.ValueSink;

import java.util.List;

public class Authority {

    private final Id id;
    private final String name;
    private final Id parentId;
    private final Boolean isLeaf;
    private final String remarks;

    public Authority(Id id, String name, Id parentId, Boolean isLeaf, String remarks) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.isLeaf = isLeaf;
        this.remarks = remarks;
    }

    public static void contents(ValueSink valueSink, List<Authority> featureList) {
        featureList.stream()
            .filter(feature -> "".equals(feature.parentId.asString()))
            .forEach(feature -> feature.content(valueSink.addObject(), featureList));
    }

    public void content(ObjectSink sink, List<Authority> featureList) {
        sink.put("id", id.asString());
        sink.put("parentId", parentId.asString());
        sink.put("name", name);
        sink.put("isLeaf", isLeaf);
        ValueSink children = sink.putArray("children");
        featureList.stream()
            .filter(feature -> feature.parentId.asString().equals(id.asString()))
            .forEach(feature -> feature.content(children.addObject(), featureList));
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Id parentId() {
        return parentId;
    }

    public String remarks() {
        return remarks;
    }

    public Boolean isLeaf() {
        return isLeaf;
    }

}
