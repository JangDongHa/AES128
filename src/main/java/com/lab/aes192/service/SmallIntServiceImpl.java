package com.lab.aes192.service;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.smallint.SmallIntDummy;
import com.lab.aes192.entity.smallint.SmallIntDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SmallIntServiceImpl implements TestingService{
    private SmallIntDummyRepository smallIntDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("SmallInt getTestingWithCBC Start");

        List<SmallIntDummy> smallIntDummies = smallIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < smallIntDummies.size(); i++){
            SmallIntDummy smallIntDummy = smallIntDummies.get(i);
            smallIntDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < smallIntDummies.size(); i++){
            SmallIntDummy smallIntDummy = smallIntDummies.get(i);
            Short data = (Short) smallIntDummy.findWithDecrypt(AES192.CBCMode(), smallIntDummy.getId(), aes192, encryptDataRepository);

            if(!smallIntDummy.getDummy().equals(data)){
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
        System.out.println("SmallInt getTestingWithCTR Start");

        List<SmallIntDummy> smallIntDummies = smallIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < smallIntDummies.size(); i++){
            SmallIntDummy smallIntDummy = smallIntDummies.get(i);
            smallIntDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < smallIntDummies.size(); i++){
            SmallIntDummy smallIntDummy = smallIntDummies.get(i);
            Short data = (Short) smallIntDummy.findWithDecrypt(AES192.CTRMode(), smallIntDummy.getId(), aes192, encryptDataRepository);

            if(!smallIntDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
