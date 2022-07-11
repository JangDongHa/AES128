package com.lab.aes192.service;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.text.TextDummy;
import com.lab.aes192.entity.text.TextDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TextServiceImpl implements TestingService{
    private TextDummyRepository TextDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;

    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Text getTestingWithCBC Start");

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            TextDummy TextDummy = TextDummyRepository.getReferenceById((long)i);
            TextDummyRepository.flush();
            TextDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
            String data = (String) TextDummy.findWithDecrypt(AES192.CBCMode(), TextDummy.getId(), aes192, encryptDataRepository);

            if(!TextDummy.getDummy().equals(data)){
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
        System.out.println("Text getTestingWithCTR Start");

        List<TextDummy> TextDummies = TextDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            TextDummy TextDummy = TextDummyRepository.getReferenceById((long)i);
            TextDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
            String data = (String) TextDummy.findWithDecrypt(AES192.CTRMode(), TextDummy.getId(), aes192, encryptDataRepository);

            if(!TextDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
