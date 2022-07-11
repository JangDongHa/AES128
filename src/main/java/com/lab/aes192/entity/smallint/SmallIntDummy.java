package com.lab.aes192.entity.smallint;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithFloat;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithShort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "smallintdummy")
@SuperBuilder
//smallint 6 < = Short
public class SmallIntDummy extends BaseType<Short>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "SMALLINT(6)")
    private Short dummy;

    public SmallIntDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithShort());
    }


    @Override
    public Short getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
