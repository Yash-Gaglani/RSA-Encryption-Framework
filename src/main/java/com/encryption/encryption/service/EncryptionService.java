package com.encryption.encryption.service;


import com.encryption.encryption.model.EncryptedObject;
import com.encryption.encryption.model.PublicCert;
import com.encryption.exception.EncryptionException;

public interface EncryptionService {

    byte[] encrypt(byte[] plainObject) throws EncryptionException;

    byte[] encrypt(byte[] plainObject, String keyId) throws EncryptionException;

    byte[] decrypt(byte[] encryptedObject) throws EncryptionException;

    byte[] decrypt(byte[] plainObject, String keyId) throws EncryptionException;
    byte[] decrypt(EncryptedObject encryptedObject) throws EncryptionException;

    PublicCert getCert() throws EncryptionException;
}
