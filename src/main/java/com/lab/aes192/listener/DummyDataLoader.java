package com.lab.aes192.listener;


import com.lab.aes192.entity.Char.CharDummy;
import com.lab.aes192.entity.Char.CharDummyRepository;
import com.lab.aes192.entity.Decimal.DecimalDummy;
import com.lab.aes192.entity.Decimal.DecimalDummyRepository;
import com.lab.aes192.entity.Double.DoubleDummy;
import com.lab.aes192.entity.Double.DoubleDummyRepository;
import com.lab.aes192.entity.Float.FloatDummy;
import com.lab.aes192.entity.Float.FloatDummyRepository;
import com.lab.aes192.entity.Int.IntegerDummy;
import com.lab.aes192.entity.Int.IntegerDummyRepository;
import com.lab.aes192.entity.bigint.BigIntDummy;
import com.lab.aes192.entity.bigint.BigIntDummyRepository;
import com.lab.aes192.entity.binarydummy.BinaryDummy;
import com.lab.aes192.entity.binarydummy.BinaryDummyRepository;

import com.lab.aes192.entity.blob.BlobDummyRepository;
import com.lab.aes192.entity.date.DateDummy;
import com.lab.aes192.entity.date.DateDummyRepository;
import com.lab.aes192.entity.datetime.DateTimeDummy;
import com.lab.aes192.entity.datetime.DateTimeDummyRepository;
import com.lab.aes192.entity.json.JsonDummy;
import com.lab.aes192.entity.json.JsonDummyRepository;
import com.lab.aes192.entity.mediumint.MediumIntDummy;
import com.lab.aes192.entity.mediumint.MediumIntDummyRepository;
import com.lab.aes192.entity.mediumtext.MediumTextDummy;
import com.lab.aes192.entity.mediumtext.MediumTextDummyRepository;
import com.lab.aes192.entity.smallint.SmallIntDummy;
import com.lab.aes192.entity.smallint.SmallIntDummyRepository;
import com.lab.aes192.entity.text.TextDummy;
import com.lab.aes192.entity.text.TextDummyRepository;
import com.lab.aes192.entity.timestamp.TimeStampDummy;
import com.lab.aes192.entity.timestamp.TimeStampDummyRepository;

import com.lab.aes192.entity.tinyblob.TinyBlobDummyRepository;
import com.lab.aes192.entity.tinyint.TinyIntDummy;
import com.lab.aes192.entity.tinyint.TinyIntDummyRepository;
import com.lab.aes192.entity.tinytext.TinyTextDummy;
import com.lab.aes192.entity.tinytext.TinyTextDummyRepository;

import com.lab.aes192.entity.varchar.VarCharDummy;
import com.lab.aes192.entity.varchar.VarCharDummyRepository;
import com.lab.aes192.entity.year.YearDummy;
import com.lab.aes192.entity.year.YearDummyRepository;
import com.lab.aes192.service.*;
import com.lab.aes192.utils.crypto.AES192;
import com.lab.aes192.utils.crypto.CryptoUtils;
import com.lab.aes192.utils.dummyjsonrequest.DummyJsonRequest;
import com.lab.aes192.utils.jsonarraytomaplistconverter.JsonArrayToMapListConverter;
import com.lab.aes192.utils.random.Random;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Slf4j
@ConfigurationProperties(prefix = "property.test")
@Component
@AllArgsConstructor
public class DummyDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private BigIntDummyRepository bigIntDummyRepository;
    private BinaryDummyRepository binaryDummyRepository;
    private BlobDummyRepository blobDummyRepository;
    private CharDummyRepository charDummyRepository;
    private DateDummyRepository dateDummyRepository;
    private DateTimeDummyRepository dateTimeDummyRepository;
    private DecimalDummyRepository decimalDummyRepository;
    private DoubleDummyRepository doubleDummyRepository;
    private FloatDummyRepository floatDummyRepository;
    private IntegerDummyRepository integerDummyRepository;
    private JsonDummyRepository jsonDummyRepository;
    private MediumIntDummyRepository mediumIntDummyRepository;

    private MediumTextDummyRepository mediumTextDummyRepository;

    private TinyIntDummyRepository tinyIntDummyRepository;
    private SmallIntDummyRepository smallIntDummyRepository;
    private TextDummyRepository textDummyRepository;
    private TimeStampDummyRepository timeStampDummyRepository;
    private TinyBlobDummyRepository tinyBlobDummyRepository;
    private TinyTextDummyRepository tinyTextDummyRepository;
    private VarCharDummyRepository varCharDummyRepository;
    private YearDummyRepository yearDummyRepository;
    private DummyJsonRequest dummyJsonRequest;
    private Random random;
    private JsonArrayToMapListConverter jsonArrayToMapListConverter;

    private BigIntServiceImpl bigIntService;
    private BinaryServiceImpl binaryService;
    private BlobServiceImpl blobService;
    private CharServiceImpl charService;
    private DateServiceImpl dateService;
    private DateTimeServiceImpl dateTimeService;
    private DecimalServiceImpl decimalService;
    private DoubleServiceImpl doubleService;
    private FloatServiceImpl floatService;
    private IntegerServiceImpl integerService;
    private JsonServiceImpl jsonService;
    private MediumIntServiceImpl mediumIntService;
    private MediumTextServiceImpl mediumTextService;
    private SmallIntServiceImpl smallIntService;
    private TextServiceImpl textService;
    private TimeStampServiceImpl timeStampService;
    private TinyIntServiceImpl tinyIntService;
    private TinyTextServiceImpl tinyTextService;
    private VarCharServiceImpl varCharService;
    private YearServiceImpl yearService;

    private AES192 aes192;

    private final static int ROWSIZE = 1000;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        setBigIntData();
//        setBinaryData();
//        setCharData();
//        setBlobData();
//        setDateData();
//        setDateTimeData();
//        setDecimalData();
//        setDoubleData();
//        setFloatData();
//        setIntegerData();
//        setJsonData();
//        setSmallIntData();
//        setTextData();
//        setTimeStampData();
//        setTinyBlobData();
//        setTinyTextData();
//        setVarCharData();
//        setYearData();
//        setMediumIntData();
//        setMediumTextData();
//        setTinyIntData();

//         bigIntService.getTestingWithCBC();
//         bigIntService.getTestingWithCTR();
//        System.out.println("=====================================");
//         binaryService.getTestingWithCBC();
//         binaryService.getTestingWithCTR();
//        System.out.println("=====================================");
//        charService.getTestingWithCBC();
//        charService.getTestingWithCTR();
//        System.out.println("=====================================");
//        dateService.getTestingWithCBC();
//        dateService.getTestingWithCTR();
//        System.out.println("=====================================");
//        dateTimeService.getTestingWithCBC();
//        dateTimeService.getTestingWithCTR();
//        System.out.println("=====================================");
//        decimalService.getTestingWithCBC();
//        decimalService.getTestingWithCTR();
//        System.out.println("=====================================");
//        doubleService.getTestingWithCBC();
//        doubleService.getTestingWithCTR();
//        System.out.println("=====================================");
//        floatService.getTestingWithCBC();
//        floatService.getTestingWithCTR();
//        System.out.println("=====================================");
//        jsonService.getTestingWithCBC();
//        jsonService.getTestingWithCTR();
//        System.out.println("=====================================");
//        mediumIntService.getTestingWithCBC();
//        mediumIntService.getTestingWithCTR();
//        System.out.println("=====================================");
//        smallIntService.getTestingWithCBC();
//        smallIntService.getTestingWithCTR();
//        System.out.println("=====================================");
//        textService.getTestingWithCBC();
//        textService.getTestingWithCTR();
//        System.out.println("=====================================");
//        timeStampService.getTestingWithCBC();
//        timeStampService.getTestingWithCTR();
//        System.out.println("=====================================");
//        tinyIntService.getTestingWithCBC();
//        tinyIntService.getTestingWithCTR();
//        System.out.println("=====================================");
//        tinyTextService.getTestingWithCBC();
//        tinyTextService.getTestingWithCTR();
//        System.out.println("=====================================");
//        varCharService.getTestingWithCBC();
//        varCharService.getTestingWithCTR();
//        System.out.println("=====================================");
//        yearService.getTestingWithCBC();
//        yearService.getTestingWithCTR();
//        System.out.println("=====================================");

        try {
            FileEncryptor("C:\\Users\\jdh33\\IdeaProjects\\aes192\\dummyfile1");
            FileEncryptor("C:\\Users\\jdh33\\IdeaProjects\\aes192\\dummyfile2");
            FileEncryptor("C:\\Users\\jdh33\\IdeaProjects\\aes192\\dummyfile3");
            FileEncryptor("C:\\Users\\jdh33\\IdeaProjects\\aes192\\dummyfile4");
            FileEncryptor("C:\\Users\\jdh33\\IdeaProjects\\aes192\\dummyfile5");
            FileEncryptor("C:\\Users\\jdh33\\IdeaProjects\\aes192\\dummyfile6");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void setBigIntData() {
        System.out.println("BIGIntDataInsert...");
        for (int i = 0 ; i <ROWSIZE; i++){
            BigIntDummy dummy = BigIntDummy.builder().dummy(random.getRandom().nextLong()).build();
            bigIntDummyRepository.save(dummy);
            bigIntDummyRepository.flush();
        }
    }

    public void setBinaryData() {
        System.out.println("SetBinaryDataInsert...");
        for (int i = 0 ; i < ROWSIZE; i++){
            String generatedString = random.getRandomHexString(255);
            BinaryDummy binaryDummy = BinaryDummy.builder().dummy(generatedString).build();
            binaryDummyRepository.save(binaryDummy);
            binaryDummyRepository.flush();
        }
    }

//    public void setBlobData() {
//        System.out.println("SetBlobDataInsert...");
//        for (int i = 0 ; i < ROWSIZE; i++){
//            String generatedString = random.getRandomHexString(255);
//            BlobDummy blobDummy = BlobDummy.builder().dummy().build();
//            blobDummyRepository.save(blobDummy);
//            blobDummyRepository.flush();
//        }
//    }

    public void setCharData() {
        System.out.println("SetCharDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            String generatedString = random.getRandomString(11);
            CharDummy charDummy = CharDummy.builder().dummy(generatedString).build();
            charDummyRepository.save(charDummy);
            charDummyRepository.flush();
        }
    }

    public void setDateData() {
        System.out.println("SetDateDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            LocalDate dummyDate = random.getLocalDate();
            DateDummy dateDummy = DateDummy.builder().dummy(dummyDate).build();
            dateDummyRepository.save(dateDummy);
            dateDummyRepository.flush();
        }
    }

    public void setDateTimeData() {
        System.out.println("SetDateTimeDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            LocalDateTime randomLocalDate = LocalDateTime.of(random.getLocalDate(), random.getLocalTime());
            DateTimeDummy dummy = DateTimeDummy.builder().dummy(randomLocalDate).build();
            dateTimeDummyRepository.save(dummy);
            dateTimeDummyRepository.flush();
        }
    }

    public void setDecimalData() {
        System.out.println("SetDecimalDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            BigDecimal randDecimal = random.getBigDecimal(19,2);
            DecimalDummy dummy = DecimalDummy.builder().dummy(randDecimal).build();
            decimalDummyRepository.save(dummy);
            decimalDummyRepository.flush();
        }
    }

    public void setDoubleData() {
        System.out.println("SetDoubleDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            Double randDouble = random.getRandom().nextDouble();
            DoubleDummy dummy = DoubleDummy.builder().dummy(randDouble).build();
            doubleDummyRepository.save(dummy);
            doubleDummyRepository.flush();
        }
    }

    public void setFloatData() {
        System.out.println("SetFloatDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            Float randFloat = random.getRandom().nextFloat();
            FloatDummy dummy = FloatDummy.builder().dummy(randFloat).build();
            floatDummyRepository.save(dummy);
            floatDummyRepository.flush();
        }
    }

    public void setIntegerData() {
        System.out.println("SetIntegerDataInsert...");
        for(int i = 0 ; i < ROWSIZE; i++){
            Integer randInteger = random.getRandom().nextInt();
            IntegerDummy integerDummy = IntegerDummy.builder().dummy(randInteger).build();
            integerDummyRepository.save(integerDummy);
            integerDummyRepository.flush();
        }
    }

    public void setJsonData() {

        int postLength = 100;
        int photosLength = 400;

        for(int i = 1 ; i <= postLength; i++){
            Map<String,Object> dummyData = dummyJsonRequest.getPostWithId(i);
            JsonDummy dummy = JsonDummy.builder().dummy(dummyData).build();
            jsonDummyRepository.save(dummy);
            jsonDummyRepository.flush();
        }

        for(int i = 1 ; i <= postLength; i++){
            JSONArray dummyDataList =  dummyJsonRequest.getCommentWithPostId(i);
            List<Map<String, Object>> hashmapDummy = jsonArrayToMapListConverter.jsonArrayToMapListConverter(dummyDataList);
            for(int j = 0; j < hashmapDummy.size(); j++){
                System.out.print(j + " ");
                JsonDummy jsonDummy = JsonDummy.builder().dummy(hashmapDummy.get(j)).build();
                jsonDummyRepository.save(jsonDummy);
                jsonDummyRepository.flush();
            }
        }
        JSONArray dummyDataList = dummyJsonRequest.getPhotos();
        List<Map<String, Object>> hashmapDummy = jsonArrayToMapListConverter.jsonArrayToMapListConverter(dummyDataList);
        for(int i = 0 ; i < photosLength; i++){
            JsonDummy jsonDummy = JsonDummy.builder().dummy(hashmapDummy.get(i)).build();
            jsonDummyRepository.save(jsonDummy);
            jsonDummyRepository.flush();
        }

    }

    public void setSmallIntData() {
        for(int i = 0 ; i < ROWSIZE; i++){
            Short randShort = random.getShort();
            SmallIntDummy smallIntDummy = SmallIntDummy.builder().dummy(randShort).build();
            smallIntDummyRepository.save(smallIntDummy);
            smallIntDummyRepository.flush();
        }
    }

    public void setTextData() {
        for(int i = 0 ; i < ROWSIZE; i++){
            String randText = random.getRandomString(21844);
            System.out.println(i + " : " + randText);
            TextDummy textDummy = TextDummy.builder().dummy(randText).build();
            textDummyRepository.save(textDummy);
            textDummyRepository.flush();
        }
    }

    public void setTimeStampData() {
        for(int i = 0 ; i < ROWSIZE; i++){
            LocalDateTime randLocalDateTime = LocalDateTime.of(random.getLocalDate(), random.getLocalTime());
            TimeStampDummy timeStampDummy = TimeStampDummy.builder().dummy(randLocalDateTime).build();
            timeStampDummyRepository.save(timeStampDummy);
            timeStampDummyRepository.flush();
        }
    }

//
//    public void setTinyBlobData() {
//        for(int i = 0 ; i < ROWSIZE; i++){
//            String randString = random.getRandomHexString(255);
//            TinyBlobDummy tinyBlobDummy = TinyBlobDummy.builder().dummy(randString).build();
//            tinyBlobDummyRepository.save(tinyBlobDummy);
//            tinyBlobDummyRepository.flush();
//        }
//    }

    public void setTinyTextData() {
        for(int i = 0 ; i < ROWSIZE; i++){
            String randString = random.getRandomString(11);
            TinyTextDummy tinyTextDummy = TinyTextDummy.builder().dummy(randString).build();
            tinyTextDummyRepository.save(tinyTextDummy);
            tinyTextDummyRepository.flush();
        }
    }

    public void setVarCharData() {
        for(int i = 0; i < ROWSIZE; i++){
            String randString = random.getRandomString(255);
            VarCharDummy varCharDummy = VarCharDummy.builder().dummy(randString).build();
            varCharDummyRepository.save(varCharDummy);
            varCharDummyRepository.flush();
        }
    }

    public void setYearData() {
        for(int i = 0; i < ROWSIZE; i++){
            Integer dummyYear = random.getYear();
            YearDummy yearDummy = YearDummy.builder().dummy(dummyYear).build();
            yearDummyRepository.save(yearDummy);
            yearDummyRepository.flush();
        }
    }

    public void setMediumIntData(){
        for(int i = 0 ; i < ROWSIZE ; i++){
            Long dummyMediumInt = random.getMediumInt();
            MediumIntDummy mediumIntDummy = MediumIntDummy.builder().dummy(dummyMediumInt).build();
            mediumIntDummyRepository.save(mediumIntDummy);
            mediumIntDummyRepository.flush();
        }
    }

    public void setMediumTextData(){
        for(int i = 0 ; i < ROWSIZE; i++){
            String randMediumText = random.getRandomString(5592404);
            MediumTextDummy mediumTextDummy = MediumTextDummy.builder().dummy(randMediumText).build();
            mediumTextDummyRepository.save(mediumTextDummy);
            mediumTextDummyRepository.flush();
        }
    }

    public void setTinyIntData(){
        for(int i = 0 ; i < ROWSIZE; i++){
            Byte randTinyInt = (byte)(random.getRandom().nextInt(255+255)-256);
            TinyIntDummy tinyIntDummy = TinyIntDummy.builder().dummy(randTinyInt).build();
            tinyIntDummyRepository.save(tinyIntDummy);
            tinyIntDummyRepository.flush();
        }
    }

    public void FileEncryptor(String filePath) throws Exception {
        long beforeTime = System.currentTimeMillis();
        System.out.println(filePath + " : encryptStart");
        CryptoUtils.encrypt(aes192.getKey(), new File(filePath),new File(filePath+"encrypted"), "AES/CBC/PKCS5Padding");

        long encryptedTime = System.currentTimeMillis();
        System.out.printf("encrypt delay : %f \n", (encryptedTime - beforeTime)/(double)1000);

        System.out.println(filePath + " : decryptStart");
        CryptoUtils.decrypt(aes192.getKey(), new File(filePath+"encrypted"),new File(filePath+"decrypt"), "AES/CBC/PKCS5Padding");
        long afterTime = System.currentTimeMillis();
        System.out.printf("Decrypt delay : %f \n", (afterTime-encryptedTime)/(double)1000);
    }
}