package com.lab.aes192.service;

import com.lab.aes192.entity.Float.FloatDummy;
import com.lab.aes192.entity.Float.FloatDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FloatServiceImpl implements TestingService{
    private FloatDummyRepository floatDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Float getTestingWithCBC Start");

        List<FloatDummy> FloatDummies = floatDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < FloatDummies.size(); i++){
            FloatDummy floatDummy = FloatDummies.get(i);
            floatDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < FloatDummies.size(); i++){
            FloatDummy floatDummy = FloatDummies.get(i);
            Float data = (Float) floatDummy.findWithDecrypt(AES192.CBCMode(), floatDummy.getId(), aes192, encryptDataRepository);

            if(!floatDummy.getDummy().equals(data)){
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
        System.out.println("Float getTestingWithCTR Start");

        List<FloatDummy> floatDummies = floatDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < floatDummies.size(); i++){
            FloatDummy floatDummy = floatDummies.get(i);
            floatDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < floatDummies.size(); i++){
            FloatDummy floatDummy = floatDummies.get(i);
            Float data = (Float) floatDummy.findWithDecrypt(AES192.CTRMode(), floatDummy.getId(), aes192, encryptDataRepository);

            if(!floatDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
