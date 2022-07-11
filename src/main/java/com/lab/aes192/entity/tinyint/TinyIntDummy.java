package com.lab.aes192.entity.tinyint;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByte;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "tinyintdummy")
@SuperBuilder
// tinyint(4) <- Byte
public class TinyIntDummy extends BaseType<Byte> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "TINYINT(4)")
    private Byte dummy;

    public TinyIntDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithByte());
    }


    @Override
    public Byte getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
