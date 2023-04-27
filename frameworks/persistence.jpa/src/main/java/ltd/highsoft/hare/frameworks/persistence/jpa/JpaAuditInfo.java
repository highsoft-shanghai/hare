package ltd.highsoft.hare.frameworks.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ltd.highsoft.hare.frameworks.context.core.Action;
import ltd.highsoft.hare.frameworks.context.core.Actors;
import ltd.highsoft.hare.frameworks.context.core.AssociatedActor;
import ltd.highsoft.hare.frameworks.context.core.AuditInfo;

import java.time.Instant;

import static ltd.highsoft.hare.frameworks.domain.core.Id.id;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JpaAuditInfo {

    private @Column(name = "creator_id") String creatorId;
    private @Column(name = "created_at") Instant createdAt;
    private @Column(name = "last_updater_id") String lastUpdaterId;
    private @Column(name = "last_updated_at") Instant lastUpdatedAt;

    public JpaAuditInfo(AuditInfo auditInfo) {
        this.creatorId = auditInfo.creatorId().asString();
        this.createdAt = auditInfo.createdAt();
        this.lastUpdaterId = auditInfo.lastUpdaterId().asString();
        this.lastUpdatedAt = auditInfo.lastUpdatedAt();
    }

    public AuditInfo asDomain(Actors actors) {
        return new AuditInfo(
            new Action(new AssociatedActor(id(creatorId), actors), createdAt),
            new Action(new AssociatedActor(id(lastUpdaterId), actors), lastUpdatedAt)
        );
    }

}
