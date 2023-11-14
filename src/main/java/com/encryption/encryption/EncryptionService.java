package com.encryption.encryption;


import com.encryption.exception.EncryptionException;

public interface EncryptionService {

    byte[] encrypt(byte[] plainObject) throws EncryptionException;

    byte[] encrypt(byte[] plainObject, String keyId) throws EncryptionException;

    byte[] decrypt(byte[] encryptedObject) throws EncryptionException;

    byte[] decrypt(byte[] plainObject, String keyId) throws EncryptionException;
    byte[] decrypt(EncryptedObject encryptedObject) throws EncryptionException;

    PublicCert getCert() throws EncryptionException;
}
