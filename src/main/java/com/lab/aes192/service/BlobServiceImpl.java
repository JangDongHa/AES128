package com.lab.aes192.service;

import com.lab.aes192.entity.bigint.BigIntDummy;
import com.lab.aes192.entity.bigint.BigIntDummyRepository;
import com.lab.aes192.entity.blob.BlobDummy;
import com.lab.aes192.entity.blob.BlobDummyRepository;
import com.lab.aes192.entity.encryptdata.EncryptDataRepository;
import com.lab.aes192.utils.crypto.AES192;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BlobServiceImpl implements TestingService{


    private BlobDummyRepository blobDummyRepository;
    private AES192 aes192;
    private EncryptDataRepository encryptDataRepository;
    @Override
    public void getTestingWithCBC() {
        int count = 0;
        System.out.println("BLOB getTestingWithCBC Start");

        List<BlobDummy> blobDummies = blobDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < blobDummies.size(); i++){
            BlobDummy blobDummy = blobDummies.get(i);
            blobDummy.saveWithEncrypt(AES192.CBCNoPaddingMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < blobDummies.size(); i++){
            BlobDummy blobDummy = blobDummies.get(i);
            byte[] data = (byte[]) blobDummy.findWithDecrypt(AES192.CBCNoPaddingMode(),blobDummy.getId(), aes192, encryptDataRepository);

            if(!blobDummy.getDummy().equals(data)){
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
        System.out.println("BLOB getTestingWithCTR Start");

        List<BlobDummy> blobDummies = blobDummyRepository.findAll();

        Long beforeTime = System.currentTimeMillis();

        for(int i = 0 ; i < blobDummies.size(); i++){
            BlobDummy blobDummy = blobDummies.get(i);
            blobDummy.saveWithEncrypt(AES192.CTRMode(), aes192, encryptDataRepository);
        }

        for(int i = 0 ; i < blobDummies.size(); i++){
            BlobDummy blobDummy = blobDummies.get(i);
            byte[] data = (byte[]) blobDummy.findWithDecrypt(AES192.CTRMode(),blobDummy.getId(), aes192, encryptDataRepository);

            if(!blobDummy.getDummy().equals(data)){
                count++;
            }
        }

        Long afterTime = System.currentTimeMillis();
        System.out.println("ErrorCount = " + count);
        System.out.println("Time Taken :" + (afterTime - beforeTime)/(double)1000);

        encryptDataRepository.truncateMyTable();
    }
}
