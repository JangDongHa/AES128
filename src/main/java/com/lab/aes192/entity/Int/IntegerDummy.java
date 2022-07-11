package com.lab.aes192.entity.Int;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithFloat;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "integerdummy")
@SuperBuilder
//int(11) < = Integer
public class IntegerDummy extends BaseType<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private Integer dummy;

    public IntegerDummy(){
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
