package com.lab.aes192.service;


import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.timestamp.TimeStampDummy;
import com.lab.aes192.entity.timestamp.TimeStampDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TimeStampServiceImpl implements TestingService{
    private TimeStampDummyRepository timeStampDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("TimeStamp getTestingWithCBC Start");

        List<TimeStampDummy> timeStampDummies = timeStampDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < timeStampDummies.size(); i++){
            TimeStampDummy timeStampDummy = timeStampDummies.get(i);
            timeStampDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < timeStampDummies.size(); i++){
            TimeStampDummy timeStampDummy = timeStampDummies.get(i);
            LocalDateTime data = (LocalDateTime) timeStampDummy.findWithDecrypt(AES192.CBCMode(), timeStampDummy.getId(), aes192, encryptDataRepository);

            if(!timeStampDummy.getDummy().equals(data)){
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
        System.out.println("TimeStamp getTestingWithCTR Start");

        List<TimeStampDummy> timeStampDummies = timeStampDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < timeStampDummies.size(); i++){
            TimeStampDummy timeStampDummy = timeStampDummies.get(i);
            timeStampDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < timeStampDummies.size(); i++){
            TimeStampDummy timeStampDummy = timeStampDummies.get(i);
            LocalDateTime data = (LocalDateTime) timeStampDummy.findWithDecrypt(AES192.CTRMode(), timeStampDummy.getId(), aes192, encryptDataRepository);

            if(!timeStampDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
