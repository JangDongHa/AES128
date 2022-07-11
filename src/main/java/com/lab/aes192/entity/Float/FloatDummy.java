package com.lab.aes192.entity.Float;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithFloat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "floatdummy")
@SuperBuilder
public class FloatDummy extends BaseType<Float> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private Float dummy;

    public FloatDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithFloat());
    }

    @Override
    public Float getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
