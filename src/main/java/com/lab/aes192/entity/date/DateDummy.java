package com.lab.aes192.entity.date;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithLocalDate;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.type.CalendarDateType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@Table(name = "datedummy")
@SuperBuilder

public class DateDummy extends BaseType<LocalDate> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "Date")
    private LocalDate dummy;

    public DateDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithLocalDate());
    }

    @Override
    public LocalDate getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
