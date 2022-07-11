package com.lab.aes192.entity.mediumint;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithFloat;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithLong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "mediumintdummy")
@SuperBuilder
//MediumInt = 3Byte 까지 가능. <== - 8,388,608 ~ 8,388,607
public class MediumIntDummy extends BaseType<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "MEDIUMINT(1)")
    private Long dummy;

    public MediumIntDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithLong());
    }


    @Override
    public Long getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
