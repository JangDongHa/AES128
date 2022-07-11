package com.lab.aes192.entity.Double;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithBigDecimal;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithDouble;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "doubledummy")
@SuperBuilder

public class DoubleDummy extends BaseType<Double> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private Double dummy;

    public DoubleDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithDouble());
    }


    @Override
    public Double getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
