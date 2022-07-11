package com.lab.aes192.service;

import com.lab.aes192.entity.date.DateDummy;
import com.lab.aes192.entity.date.DateDummyRepository;
import com.lab.aes192.entity.datetime.DateTimeDummy;
import com.lab.aes192.entity.datetime.DateTimeDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DateTimeServiceImpl implements TestingService{
    private DateTimeDummyRepository dateTimeDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("DateTime getTestingWithCBC Start");

        List<DateTimeDummy> DateTimeDummies = dateTimeDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < DateTimeDummies.size(); i++){
            DateTimeDummy dateTimeDummy = DateTimeDummies.get(i);
            dateTimeDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < DateTimeDummies.size(); i++){
            DateTimeDummy dateTimeDummy = DateTimeDummies.get(i);
            LocalDateTime data = (LocalDateTime) dateTimeDummy.findWithDecrypt(AES192.CBCMode(), dateTimeDummy.getId(), aes192, encryptDataRepository);

            if(!dateTimeDummy.getDummy().equals(data)){
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
        System.out.println("DateTime getTestingWithCTR Start");

        List<DateTimeDummy> dateTimeDummies = dateTimeDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < dateTimeDummies.size(); i++){
            DateTimeDummy dateTimeDummy = dateTimeDummies.get(i);
            dateTimeDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < dateTimeDummies.size(); i++){
            DateTimeDummy dateTimeDummy = dateTimeDummies.get(i);
            LocalDateTime data = (LocalDateTime) dateTimeDummy.findWithDecrypt(AES192.CTRMode(), dateTimeDummy.getId(), aes192, encryptDataRepository);

            if(!dateTimeDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
