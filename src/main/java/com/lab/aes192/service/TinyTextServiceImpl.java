package com.lab.aes192.service;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.text.TextDummy;
import com.lab.aes192.entity.text.TextDummyRepository;
import com.lab.aes192.entity.tinytext.TinyTextDummy;
import com.lab.aes192.entity.tinytext.TinyTextDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class TinyTextServiceImpl implements TestingService{
    private TinyTextDummyRepository tinyTextDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;

    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("TinyText getTestingWithCBC Start");

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            TinyTextDummy tinyTextDummy = tinyTextDummyRepository.getReferenceById((long)i);
            tinyTextDummyRepository.flush();
            tinyTextDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
            String data = (String) tinyTextDummy.findWithDecrypt(AES192.CBCMode(), tinyTextDummy.getId(), aes192, encryptDataRepository);

            if(!tinyTextDummy.getDummy().equals(data)){
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
        System.out.println("TinyText getTestingWithCTR Start");

        List<TinyTextDummy> TinyTextDummies = tinyTextDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            TinyTextDummy tinyTextDummy = tinyTextDummyRepository.getReferenceById((long)i);
            tinyTextDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
            String data = (String) tinyTextDummy.findWithDecrypt(AES192.CTRMode(), tinyTextDummy.getId(), aes192, encryptDataRepository);

            if(!tinyTextDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
