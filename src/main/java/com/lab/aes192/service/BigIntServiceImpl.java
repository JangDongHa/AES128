package com.lab.aes192.service;

import com.lab.aes192.entity.bigint.BigIntDummy;
import com.lab.aes192.entity.bigint.BigIntDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BigIntServiceImpl implements TestingService{

    private BigIntDummyRepository bigIntDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("BigInt getTestingWithCBC Start");

        List<BigIntDummy> bigIntDummies = bigIntDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < bigIntDummies.size(); i++){
            BigIntDummy bigIntDummy = bigIntDummies.get(i);
            bigIntDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < bigIntDummies.size(); i++){
            BigIntDummy bigIntDummy = bigIntDummies.get(i);
            Long data = (Long) bigIntDummy.findWithDecrypt(AES192.CBCMode(), bigIntDummy.getId(), aes192, encryptDataRepository);

            if(!bigIntDummy.getDummy().equals(data)){
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

        System.out.println("BigInt getTestingWithCTR Start");

        long beforeTime = System.currentTimeMillis();
        List<BigIntDummy> bigIntDummies = bigIntDummyRepository.findAll();

        for(int i = 0 ; i < bigIntDummies.size(); i++){
            BigIntDummy bigIntDummy = bigIntDummies.get(i);
            bigIntDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < bigIntDummies.size(); i++){
            BigIntDummy bigIntDummy = bigIntDummies.get(i);
            Long data = (Long) bigIntDummy.findWithDecrypt(AES192.CTRMode(), bigIntDummy.getId(), aes192, encryptDataRepository);

            if(!bigIntDummy.getDummy().equals(data)){
                count++;
            }
        }

        long afterTime = System.currentTimeMillis();


        System.out.println("ErrorCount = " + count);

        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
