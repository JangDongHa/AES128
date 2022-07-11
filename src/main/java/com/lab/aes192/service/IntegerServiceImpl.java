package com.lab.aes192.service;

import com.lab.aes192.entity.Int.IntegerDummy;
import com.lab.aes192.entity.Int.IntegerDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IntegerServiceImpl implements TestingService{
    private IntegerDummyRepository integerDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Integer getTestingWithCBC Start");

        List<IntegerDummy> integerDummies = integerDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < integerDummies.size(); i++){
            IntegerDummy integerDummy = integerDummies.get(i);
            integerDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < integerDummies.size(); i++){
            IntegerDummy integerDummy = integerDummies.get(i);
            Integer data = (Integer) integerDummy.findWithDecrypt(AES192.CBCMode(), integerDummy.getId(), aes192, encryptDataRepository);

            if(!integerDummy.getDummy().equals(data)){
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
        System.out.println("Integer getTestingWithCTR Start");

        List<IntegerDummy> integerDummies = integerDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < integerDummies.size(); i++){
            IntegerDummy integerDummy = integerDummies.get(i);
            integerDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < integerDummies.size(); i++){
            IntegerDummy integerDummy = integerDummies.get(i);
            Integer data = (Integer) integerDummy.findWithDecrypt(AES192.CTRMode(), integerDummy.getId(), aes192, encryptDataRepository);

            if(!integerDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
