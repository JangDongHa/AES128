package com.lab.aes192.entity.tinyblob;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "tinyblobdummy")
@Builder
public class TinyBlobDummy extends BaseType<byte[]> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "TINYBLOB")
    private byte[] dummy;

    public TinyBlobDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithByteArray());
    }


    @Override
    public byte[] getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
