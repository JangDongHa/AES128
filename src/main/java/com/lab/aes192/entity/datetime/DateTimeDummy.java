package com.lab.aes192.entity.datetime;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithLocalDateTime;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Table(name = "datetimedummy")
@SuperBuilder
public class DateTimeDummy extends BaseType<LocalDateTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private LocalDateTime dummy;

    public DateTimeDummy(){
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
