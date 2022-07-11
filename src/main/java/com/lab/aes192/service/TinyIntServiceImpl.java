package com.lab.aes192.service;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.tinyint.TinyIntDummy;
import com.lab.aes192.entity.tinyint.TinyIntDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TinyIntServiceImpl implements TestingService{
    private TinyIntDummyRepository tinyIntDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("TinyInt getTestingWithCBC Start");

        List<TinyIntDummy> tinyIntDummies = tinyIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < tinyIntDummies.size(); i++){
            TinyIntDummy tinyIntDummy = tinyIntDummies.get(i);
            tinyIntDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < tinyIntDummies.size(); i++){
            TinyIntDummy tinyIntDummy = tinyIntDummies.get(i);
            Byte data = (Byte) tinyIntDummy.findWithDecrypt(AES192.CBCMode(), tinyIntDummy.getId(), aes192, encryptDataRepository);

            if(!tinyIntDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
    @Override
    public void getTestingWithCTR() {
        int count = 0;
        System.out.println("TinyInt getTestingWithCTR Start");

        List<TinyIntDummy> tinyIntDummies = tinyIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < tinyIntDummies.size(); i++){
            TinyIntDummy tinyIntDummy = tinyIntDummies.get(i);
            tinyIntDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < tinyIntDummies.size(); i++){
            TinyIntDummy tinyIntDummy = tinyIntDummies.get(i);
            Byte data = (Byte) tinyIntDummy.findWithDecrypt(AES192.CTRMode(), tinyIntDummy.getId(), aes192, encryptDataRepository);

            if(!tinyIntDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
