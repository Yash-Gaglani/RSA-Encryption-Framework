package com.encryption.encryption.impl;

import com.encryption.encryption.EncryptedObject;
import com.encryption.encryption.EncryptionKeyManager;
import com.encryption.encryption.EncryptionService;
import com.encryption.encryption.PublicCert;
import com.encryption.exception.EncryptionException;
import com.encryption.exception.ExceptionCode;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EncryptionServiceImpl implements EncryptionService {

    @Autowired
    EncryptionKeyManager encryptionKeyManager;

    Cipher cipher;

    public EncryptionServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA-256ANDMGF1PADDING");
    }

    @Override
    public byte[] encrypt(byte[] plainObject) throws EncryptionException {
        return encrypt(plainObject, encryptionKeyManager.getLatestKeyId());
    }

    @Override
    public byte[] encrypt(byte[] plainObject, String keyId) throws EncryptionException {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, encryptionKeyManager.loadPublicKey(keyId));
            return cipher.doFinal(plainObject);
        } catch (IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            log.error("EncryptionService:encrypt::", e);
            throw new EncryptionException(ExceptionCode.ERROR, "Error encrypting");
        }
    }

    @Override
    public byte[] decrypt(byte[] encryptedObject) throws EncryptionException {
        return decrypt(encryptedObject, encryptionKeyManager.getLatestKeyId());
    }

    @Override
    public byte[] decrypt(byte[] plainObject, String keyId) throws EncryptionException {
        try {
            PrivateKey key = encryptionKeyManager.loadPrivateKey(keyId);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(plainObject);
        } catch (IllegalBlockSizeException |
                 BadPaddingException | InvalidKeyException e) {
            log.error("EncryptionService:encrypt::", e);
            throw new EncryptionException(ExceptionCode.ERROR, "Error decrypting");
        }
    }

    @Override
    public byte[] decrypt(EncryptedObject encryptedObject) throws EncryptionException {
        return decrypt(encryptedObject.getContent(), encryptedObject.getKeyId());
    }

    @Override
    public PublicCert getCert() throws EncryptionException {
        PublicCert publicCert = new PublicCert();
        publicCert.setKeyId(encryptionKeyManager.getLatestKeyId());
        publicCert.setKey(encryptionKeyManager.getPublicKeyStr(publicCert.getKeyId()));
        return publicCert;
    }

}
