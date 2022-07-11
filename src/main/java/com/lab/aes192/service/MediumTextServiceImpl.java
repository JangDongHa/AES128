package com.lab.aes192.service;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.mediumtext.MediumTextDummy;
import com.lab.aes192.entity.mediumtext.MediumTextDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MediumTextServiceImpl implements TestingService{
    private MediumTextDummyRepository mediumTextDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;

    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("MediumText getTestingWithCBC Start");

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            MediumTextDummy mediumTextDummy = mediumTextDummyRepository.getReferenceById((long)i);
            mediumTextDummyRepository.flush();
            mediumTextDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
            String data = (String) mediumTextDummy.findWithDecrypt(AES192.CBCMode(), mediumTextDummy.getId(), aes192, encryptDataRepository);

            if(!mediumTextDummy.getDummy().equals(data)){
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
        System.out.println("MediumText getTestingWithCTR Start");

        List<MediumTextDummy> mediumTextDummies = mediumTextDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            MediumTextDummy mediumTextDummy = mediumTextDummyRepository.getReferenceById((long)i);
            mediumTextDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
            String data = (String) mediumTextDummy.findWithDecrypt(AES192.CTRMode(), mediumTextDummy.getId(), aes192, encryptDataRepository);

            if(!mediumTextDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
