package ltd.highsoft.hare.foundations.iam.domain;

import ltd.highsoft.hare.frameworks.domain.core.Name;
import ltd.highsoft.hare.frameworks.domain.core.Payload;
import ltd.highsoft.hare.frameworks.domain.core.Remarks;
import ltd.highsoft.hare.frameworks.domain.core.ScopedId;
import ltd.highsoft.hare.frameworks.security.core.GrantedAuthorities;

import java.util.HashSet;

import static ltd.highsoft.hare.frameworks.domain.core.NameFieldType.asName;
import static ltd.highsoft.hare.frameworks.domain.core.RemarksFieldType.asRemarks;
import static ltd.highsoft.hare.frameworks.domain.core.StringFieldType.asString;

public class Role {

    private final ScopedId id;
    private Name name;
    private GrantedAuthorities authorities;
    private Remarks remarks;
    private final boolean predefined;

    public Role(ScopedId id, Name name, GrantedAuthorities authorities, Remarks remarks, boolean predefined) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
        this.remarks = remarks;
        this.predefined = predefined;
    }

    public Role(ScopedId id, Payload payload) {
        this.id = id;
        this.name = payload.get("name", asName());
        this.authorities = GrantedAuthorities.of(new HashSet<>(payload.get("authorities", asString().array())));
        this.remarks = payload.get("remarks", asRemarks());
        this.predefined = false;
    }

    public ScopedId id() {
        return id;
    }

    public Name name() {
        return name;
    }

    public GrantedAuthorities grantedAuthorities() {
        return authorities;
    }

    public Remarks remarks() {
        return remarks;
    }

    public boolean predefined() {
        return predefined;
    }

    public void update(Payload payload) {
        this.name = payload.get("name", asName());
        this.authorities = GrantedAuthorities.of(new HashSet<>(payload.get("authorities", asString().array())));
        this.remarks = payload.get("remarks", asRemarks());
    }

}
