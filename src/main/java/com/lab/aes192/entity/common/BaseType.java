package com.lab.aes192.entity.common;

import com.lab.aes192.entity.encryptdata.EncryptData;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import com.lab.aes192.utils.typecastingwithbytearray.BaseTypeValueCasting;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@SuperBuilder
@Slf4j
public abstract class BaseType<T>  {
    private BaseTypeValueCasting baseTypeValueCasting;



    public void saveWithEncrypt(String mode, AES192 aes192, EncryptDataRepository encryptDataRepository){

        String encode = aes192.aesEncode(baseTypeValueCasting.toString(getDummy()), mode);

        EncryptData encryptData = EncryptData.builder().id(getId()).encrypt_data(encode).build();
        encryptDataRepository.save(encryptData);

    }
    public Object findWithDecrypt(String mode, Long id, AES192 aes192, EncryptDataRepository encryptDataRepository){
        Optional<EncryptData> encryptData = encryptDataRepository.findById(id);


        byte[] decodeData = aes192.aesDecode(encryptData.get().getDummy(), mode);
        T data = (T)baseTypeValueCasting.toValue(new String(decodeData));

        return data;
    }
    public abstract T getDummy();
    public abstract Long getId();

}
