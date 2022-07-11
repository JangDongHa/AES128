package com.lab.aes192.service;

import com.lab.aes192.entity.year.YearDummy;
import com.lab.aes192.entity.year.YearDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@AllArgsConstructor
public class YearServiceImpl implements TestingService{
    private YearDummyRepository yearDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Year getTestingWithCBC Start");

        List<YearDummy> yearDummies = yearDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < yearDummies.size(); i++){
            YearDummy yearDummy = yearDummies.get(i);
            yearDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < yearDummies.size(); i++){
            YearDummy yearDummy = yearDummies.get(i);
            Integer data = (Integer) yearDummy.findWithDecrypt(AES192.CBCMode(), yearDummy.getId(), aes192, encryptDataRepository);

            if(!yearDummy.getDummy().equals(data)){
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
        System.out.println("Year getTestingWithCTR Start");

        List<YearDummy> yearDummies = yearDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < yearDummies.size(); i++){
            YearDummy yearDummy = yearDummies.get(i);
            yearDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < yearDummies.size(); i++){
            YearDummy yearDummy = yearDummies.get(i);
            Integer data = (Integer) yearDummy.findWithDecrypt(AES192.CTRMode(), yearDummy.getId(), aes192, encryptDataRepository);

            if(!yearDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
