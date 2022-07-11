package com.lab.aes192.service;

import com.lab.aes192.entity.Decimal.DecimalDummy;
import com.lab.aes192.entity.Decimal.DecimalDummyRepository;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.util.List;

@Service
@AllArgsConstructor
public class DecimalServiceImpl implements TestingService{
    private DecimalDummyRepository decimalDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Decimal getTestingWithCBC Start");

        List<DecimalDummy> DecimalDummies = decimalDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < DecimalDummies.size(); i++){
            DecimalDummy decimalDummy = DecimalDummies.get(i);
            decimalDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < DecimalDummies.size(); i++){
            DecimalDummy decimalDummy = DecimalDummies.get(i);
            BigDecimal data = (BigDecimal) decimalDummy.findWithDecrypt(AES192.CBCMode(), decimalDummy.getId(), aes192, encryptDataRepository);

            if(!decimalDummy.getDummy().equals(data)){
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
        System.out.println("Decimal getTestingWithCTR Start");

        List<DecimalDummy> decimalDummies = decimalDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < decimalDummies.size(); i++){
            DecimalDummy decimalDummy = decimalDummies.get(i);
            decimalDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < decimalDummies.size(); i++){
            DecimalDummy decimalDummy = decimalDummies.get(i);
            BigDecimal data = (BigDecimal) decimalDummy.findWithDecrypt(AES192.CTRMode(), decimalDummy.getId(), aes192, encryptDataRepository);

            if(!decimalDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
