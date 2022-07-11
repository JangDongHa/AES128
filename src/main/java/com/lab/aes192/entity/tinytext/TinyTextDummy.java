package com.lab.aes192.entity.tinytext;

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
@Table(name = "tinytextdummy")
@SuperBuilder
public class TinyTextDummy extends BaseType<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy")
    private String dummy;

    public TinyTextDummy(){
        super();
        super.setBaseTypeValueCasting(new BaseTypeValueCastingWithString());
    }


    @Override
    public String getDummy() {
        return dummy;
    }

    @Override
    public Long getId() {
        return id;
    }
}
