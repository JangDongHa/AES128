package com.lab.aes192.entity.timestamp;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithLocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(name = "timestampdummy")
@SuperBuilder
public class TimeStampDummy extends BaseType<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "TIMESTAMP")
    private LocalDateTime dummy;

    public TimeStampDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithLocalDateTime());
    }

    @Override
    public LocalDateTime getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
