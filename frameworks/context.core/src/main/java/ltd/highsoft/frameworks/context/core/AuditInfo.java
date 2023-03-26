package ltd.highsoft.frameworks.context.core;

import ltd.highsoft.frameworks.domain.core.*;

import java.time.Instant;

public class AuditInfo {

    private final Action creation;
    private final Action lastUpdate;

    public AuditInfo(Action creation, Action lastUpdate) {
        this.creation = creation;
        this.lastUpdate = lastUpdate;
    }

    public void content(ObjectSink sink) {
        sink.put("creatorId", creatorId().asString());
        sink.put("creatorName", creation.actor().value().name());
        sink.put("createdAt", createdAt());
        sink.put("lastUpdaterId", lastUpdaterId().asString());
        sink.put("lastUpdaterName", lastUpdate.actor().value().name());
        sink.put("lastUpdatedAt", lastUpdatedAt());
    }

    public Id creatorId() {
        return creation.actor().id();
    }

    public Instant createdAt() {
        return creation.at();
    }

    public Id lastUpdaterId() {
        return lastUpdate.actor().id();
    }

    public Instant lastUpdatedAt() {
        return lastUpdate.at();
    }

    public Action creation() {
        return creation;
    }

    public Action lastUpdate() {
        return lastUpdate;
    }

}
