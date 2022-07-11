package com.lab.aes192.entity.bigint;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCasting;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithInteger;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithLong;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "bigintdummy")
@AllArgsConstructor
@ToString
@SuperBuilder
public class BigIntDummy extends BaseType<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private Long dummy;

    public BigIntDummy() {
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
