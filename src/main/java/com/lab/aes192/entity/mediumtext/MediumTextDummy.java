package com.lab.aes192.entity.mediumtext;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithFloat;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "mediumtextdummy")
@SuperBuilder
public class MediumTextDummy extends BaseType<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "MEDIUMTEXT")
    private String dummy;

    public MediumTextDummy(){
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
