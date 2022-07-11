package com.lab.aes192.entity.encryptdata;

import com.lab.aes192.entity.common.BaseType;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithBigDecimal;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCastingWithByteArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "encrypttable")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EncryptData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "encrypt_data", columnDefinition = "TEXT")
    private String encrypt_data;


    public String getDummy() {
        return encrypt_data;
    }

    public Long getId() {
        return id;
    }
}
