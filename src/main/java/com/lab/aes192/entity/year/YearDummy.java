package com.lab.aes192.entity.year;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCasting;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithInteger;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithShort;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "yeardummy")
@SuperBuilder
public class YearDummy extends BaseType<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "YEAR")
    private Integer dummy;

    public YearDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithInteger());
    }

    @Override
    public Integer getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
