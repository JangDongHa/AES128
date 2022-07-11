package com.lab.aes192.entity.blob;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "blobdummy")
@Builder
public class BlobDummy extends BaseType<byte[]> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "BLOB")
    @Lob
    private byte[] dummy;

    public BlobDummy(){
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
