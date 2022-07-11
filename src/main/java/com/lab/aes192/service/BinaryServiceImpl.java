package com.lab.aes192.service;

import com.lab.aes192.entity.bigint.BigIntDummy;
import com.lab.aes192.entity.bigint.BigIntDummyRepository;
import com.lab.aes192.entity.binarydummy.BinaryDummy;
import com.lab.aes192.entity.binarydummy.BinaryDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BinaryServiceImpl implements TestingService{

    private BinaryDummyRepository binaryDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Binary getTestingWithCBC Start");

        List<BinaryDummy> binaryDummies = binaryDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < binaryDummies.size(); i++){
            BinaryDummy binaryDummy = binaryDummies.get(i);
            binaryDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < binaryDummies.size(); i++){
            BinaryDummy binaryDummy = binaryDummies.get(i);
            String data = (String) binaryDummy.findWithDecrypt(AES192.CBCMode(), binaryDummy.getId(), aes192, encryptDataRepository);

            if(!binaryDummy.getDummy().equals(data)){
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

        System.out.println("Binary getTestingWithCBC Start");

        long beforeTime = System.currentTimeMillis();
        List<BinaryDummy> binaryDummies = binaryDummyRepository.findAll();

        for(int i = 0 ; i < binaryDummies.size(); i++){
            BinaryDummy binaryDummy = binaryDummies.get(i);
            binaryDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < binaryDummies.size(); i++){
            BinaryDummy binaryDummy = binaryDummies.get(i);
            String data = (String) binaryDummy.findWithDecrypt(AES192.CTRMode(), binaryDummy.getId(), aes192, encryptDataRepository);

            if(!binaryDummy.getDummy().equals(data)){
                count++;
            }
        }

        long afterTime = System.currentTimeMillis();


        System.out.println("ErrorCount = " + count);

        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
