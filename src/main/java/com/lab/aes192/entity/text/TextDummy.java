package com.lab.aes192.entity.text;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithShort;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "textdummy")
@SuperBuilder
public class TextDummy extends BaseType<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "TEXT")
    private String dummy;

    public TextDummy(){
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
