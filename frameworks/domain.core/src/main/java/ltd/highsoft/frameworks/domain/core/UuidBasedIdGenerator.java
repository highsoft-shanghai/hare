package ltd.highsoft.frameworks.domain.core;

import com.fasterxml.uuid.Generators;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static ltd.highsoft.frameworks.domain.core.Id.id;

public class UuidBasedIdGenerator implements IdGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSSS");
    private static final long A = 10000000;
    private static final long B = 12219292800L;
    private static final long C = 1000000;
    private static final long D = 100;
    private static final int E = 8;

    @Override
    public Id nextId() {
        return id(UUID.randomUUID().toString().toLowerCase().replace("-", ""));
    }

    @Override
    public Id nextReadableId() {
        var uuid = Generators.timeBasedGenerator().generate();
        return formatUuidTimestamp(uuid.timestamp());
    }

    private Id formatUuidTimestamp(long timestamp) {
        return id(FORMATTER.format(Instant.ofEpochSecond(timestamp / A - B, timestamp % C * D).atZone(ZoneOffset.ofHours(E))));
    }

}
