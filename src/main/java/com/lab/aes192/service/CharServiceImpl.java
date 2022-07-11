package com.lab.aes192.service;

import com.lab.aes192.entity.Char.CharDummy;
import com.lab.aes192.entity.Char.CharDummyRepository;
import com.lab.aes192.entity.binarydummy.BinaryDummy;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CharServiceImpl implements TestingService{

    private CharDummyRepository charDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Char getTestingWithCBC Start");

        List<CharDummy> charDummies = charDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < charDummies.size(); i++){
            CharDummy charDummy = charDummies.get(i);
            charDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < charDummies.size(); i++){
            CharDummy charDummy = charDummies.get(i);
            String data = (String) charDummy.findWithDecrypt(AES192.CBCMode(), charDummy.getId(), aes192, encryptDataRepository);

            if(!charDummy.getDummy().equals(data)){
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
        System.out.println("Char getTestingWithCTR Start");

        List<CharDummy> charDummies = charDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < charDummies.size(); i++){
            CharDummy charDummy = charDummies.get(i);
            charDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < charDummies.size(); i++){
            CharDummy charDummy = charDummies.get(i);
            String data = (String) charDummy.findWithDecrypt(AES192.CTRMode(), charDummy.getId(), aes192, encryptDataRepository);

            if(!charDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
