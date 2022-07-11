package com.lab.aes192.entity.Char;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@Table(name = "chardummy")
@Builder

public class CharDummy extends BaseType<String> {
/**
 * Char은 일단 전화번호를 가정한 11자리라 생각.
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dummy", columnDefinition = "CHAR(11)")
    private String dummy;

    public CharDummy(){
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
