package com.lab.aes192.service;

import com.lab.aes192.entity.Double.DoubleDummy;
import com.lab.aes192.entity.Double.DoubleDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoubleServiceImpl implements TestingService{
    private DoubleDummyRepository doubleDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Double getTestingWithCBC Start");

        List<DoubleDummy> DoubleDummies = doubleDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < DoubleDummies.size(); i++){
            DoubleDummy doubleDummy = DoubleDummies.get(i);
            doubleDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < DoubleDummies.size(); i++){
            DoubleDummy doubleDummy = DoubleDummies.get(i);
            Double data = (Double) doubleDummy.findWithDecrypt(AES192.CBCMode(), doubleDummy.getId(), aes192, encryptDataRepository);

            if(!doubleDummy.getDummy().equals(data)){
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
        System.out.println("Double getTestingWithCTR Start");

        List<DoubleDummy> doubleDummies = doubleDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < doubleDummies.size(); i++){
            DoubleDummy doubleDummy = doubleDummies.get(i);
            doubleDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < doubleDummies.size(); i++){
            DoubleDummy doubleDummy = doubleDummies.get(i);
            Double data = (Double) doubleDummy.findWithDecrypt(AES192.CTRMode(), doubleDummy.getId(), aes192, encryptDataRepository);

            if(!doubleDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
