package com.lab.aes192.service;

import com.lab.aes192.entity.json.JsonDummy;
import com.lab.aes192.entity.json.JsonDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.entity.json.JsonDummyRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class JsonServiceImpl implements TestingService{
    private JsonDummyRepository jsonDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("Json getTestingWithCBC Start");

        List<JsonDummy> JsonDummies = jsonDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < JsonDummies.size(); i++){
            JsonDummy jsonDummy = JsonDummies.get(i);
            jsonDummy.saveWithEncrypt(AES192.CBCMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < JsonDummies.size(); i++){
            JsonDummy jsonDummy = JsonDummies.get(i);
            Map<String, Object> data = (Map<String, Object>) jsonDummy.findWithDecrypt(AES192.CBCMode(), jsonDummy.getId(), aes192, encryptDataRepository);

            if(!jsonDummy.getDummy().equals(data)){
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
        System.out.println("Json getTestingWithCTR Start");

        List<JsonDummy> jsonDummies = jsonDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < jsonDummies.size(); i++){
            JsonDummy jsonDummy = jsonDummies.get(i);
            jsonDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < jsonDummies.size(); i++){
            JsonDummy jsonDummy = jsonDummies.get(i);
            Map<String, Object> data = (Map<String, Object>) jsonDummy.findWithDecrypt(AES192.CTRMode(), jsonDummy.getId(), aes192, encryptDataRepository);

            if(!jsonDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
