package com.lab.aes192.entity.encryptdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface EncryptDataRepository extends JpaRepository<EncryptData, Long> {
    @Transactional
    @Modifying
    @Query(
            value = "truncate encrypttable",
            nativeQuery = true
    )
    void truncateMyTable();
}
