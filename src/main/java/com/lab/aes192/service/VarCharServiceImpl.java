package com.lab.aes192.service;

import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.varchar.VarCharDummy;
import com.lab.aes192.entity.varchar.VarCharDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class VarCharServiceImpl implements TestingService{
    private VarCharDummyRepository varCharDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;

    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("VarChar getTestingWithCBC Start");

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            VarCharDummy varCharDummy = varCharDummyRepository.getReferenceById((long)i);
            varCharDummyRepository.flush();
            varCharDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
            String data = (String) varCharDummy.findWithDecrypt(AES192.CBCMode(), varCharDummy.getId(), aes192, encryptDataRepository);

            if(!varCharDummy.getDummy().equals(data)){
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
        System.out.println("VarChar getTestingWithCTR Start");

        List<VarCharDummy> VarCharDummies = varCharDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 1 ; i <= 1000; i++){
            VarCharDummy varCharDummy = varCharDummyRepository.getReferenceById((long)i);
            varCharDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
            String data = (String) varCharDummy.findWithDecrypt(AES192.CTRMode(), varCharDummy.getId(), aes192, encryptDataRepository);

            if(!varCharDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
