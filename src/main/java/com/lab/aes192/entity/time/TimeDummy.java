package com.lab.aes192.entity.time;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithLocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@Table(name = "timedummy")
@SuperBuilder
public class TimeDummy extends BaseType<LocalTime> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private LocalTime dummy;

    public TimeDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithLocalTime());
    }

    @Override
    public LocalTime getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
