package com.lab.aes192.service;

import com.lab.aes192.entity.date.DateDummy;
import com.lab.aes192.entity.date.DateDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class DateServiceImpl implements TestingService{
    private DateDummyRepository dateDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Date getTestingWithCBC Start");

        List<DateDummy> DateDummies = dateDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < DateDummies.size(); i++){
            DateDummy dateDummy = DateDummies.get(i);
            dateDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < DateDummies.size(); i++){
            DateDummy dateDummy = DateDummies.get(i);
            LocalDate data = (LocalDate) dateDummy.findWithDecrypt(AES192.CBCMode(), dateDummy.getId(), aes192, encryptDataRepository);

            if(!dateDummy.getDummy().equals(data)){
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
        System.out.println("Date getTestingWithCTR Start");

        List<DateDummy> dateDummies = dateDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < dateDummies.size(); i++){
            DateDummy dateDummy = dateDummies.get(i);
            dateDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < dateDummies.size(); i++){
            DateDummy dateDummy = dateDummies.get(i);
            LocalDate data = (LocalDate) dateDummy.findWithDecrypt(AES192.CTRMode(), dateDummy.getId(), aes192, encryptDataRepository);

            if(!dateDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
