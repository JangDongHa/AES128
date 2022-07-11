package com.lab.aes192.service;

import com.lab.aes192.entity.mediumint.MediumIntDummy;
import com.lab.aes192.entity.mediumint.MediumIntDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MediumIntServiceImpl implements TestingService{
    private MediumIntDummyRepository mediumIntDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("MediumInt getTestingWithCBC Start");

        List<MediumIntDummy> mediumIntDummies = mediumIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < mediumIntDummies.size(); i++){
            MediumIntDummy mediumIntDummy = mediumIntDummies.get(i);
            mediumIntDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < mediumIntDummies.size(); i++){
            MediumIntDummy mediumIntDummy = mediumIntDummies.get(i);
            Long data = (Long) mediumIntDummy.findWithDecrypt(AES192.CBCMode(), mediumIntDummy.getId(), aes192, encryptDataRepository);

            if(!mediumIntDummy.getDummy().equals(data)){
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
        System.out.println("MediumInt getTestingWithCTR Start");

        List<MediumIntDummy> mediumIntDummies = mediumIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < mediumIntDummies.size(); i++){
            MediumIntDummy mediumIntDummy = mediumIntDummies.get(i);
            mediumIntDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < mediumIntDummies.size(); i++){
            MediumIntDummy mediumIntDummy = mediumIntDummies.get(i);
            Long data = (Long) mediumIntDummy.findWithDecrypt(AES192.CTRMode(), mediumIntDummy.getId(), aes192, encryptDataRepository);

            if(!mediumIntDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
