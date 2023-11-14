package com.encryption.encryption.config;

import com.encryption.encryption.config.EncryptionKeyConfig;
import com.encryption.exception.EncryptionException;

import com.encryption.exception.ExceptionCode;
import jakarta.annotation.PostConstruct;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Ref : https://adangel.org/2016/08/29/openssl-rsa-java/
/**
 * The type Encryption key manager.
 * to crate a new key-pair please follow below commands
 * 1. openssl genrsa -out privatekey.pem 2048
 * 2. openssl rsa -in privatekey.pem -out publickey.pem -pubout
 * 3. openssl pkcs8 -in privatekey.pem -topk8 -nocrypt -out privatekey-pkcs8.pem
 *
 * key should never be committed in the code.
 *
 */
@Component
@Slf4j
@Getter
@Setter
public class EncryptionKeyManager {

    private String latestKeyId = null;
    private PublicKey latestPublicKey = null;
    private PrivateKey latestPrivateKey = null;

    @Autowired
    EncryptionKeyConfig encryptionKeyConfig;


    public static final String BEGIN_PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----";
    public static final String END_PUBLIC_KEY = "-----END PUBLIC KEY-----";
    public static final String SPACE = "\\s";
    public static final String RSA = "RSA";
    public static final String BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----";
    public static final String END_PRIVATE_KEY = "-----END PRIVATE KEY-----";
    public static final String EMPTY = "";

    @PostConstruct
    public void postConstruct() throws EncryptionException {
        this.latestKeyId = getLatestKeyId();
        this.latestPrivateKey = loadPrivateKey(latestKeyId);
        this.latestPublicKey = loadPublicKey(latestKeyId);
    }


    public String getLatestKeyId() throws EncryptionException {
        if (encryptionKeyConfig.getPublicKeys().isEmpty() || encryptionKeyConfig.getPrivateKeys()
            .isEmpty()) {
            throw new EncryptionException(ExceptionCode.ERROR, "No Encryption key found!");
        }
        Map<String, String> privateKeyMap = encryptionKeyConfig.getPrivateKeys();
        return "key" + privateKeyMap.keySet().stream().map(s -> s.replace("key", "")).sorted()
            .reduce((first, second) -> second).orElse(null);
    }

    /**
     * Load public key.
     *
     * @param keyId the public key pem
     * @return the public key
     * @throws EncryptionException the vb app exception
     */
    public PublicKey loadPublicKey(String keyId) throws EncryptionException {
        try {
            if (latestKeyId.equals(keyId) && latestPublicKey != null) {
                return latestPublicKey;
            }
            String publicKeyPEM = encryptionKeyConfig.getPublicKeys().get(keyId);
            if (StringUtils.isAllBlank(publicKeyPEM)) {
                throw new EncryptionException(ExceptionCode.BAD_REQUEST,
                    "Public key is null/blank.");
            }
            // strip of header, footer, newlines, whitespaces
            publicKeyPEM = publicKeyPEM
                .replace(BEGIN_PUBLIC_KEY, EMPTY)
                .replace(END_PUBLIC_KEY, EMPTY)
                .replaceAll(SPACE, EMPTY);

            // decode to get the binary DER representation
            byte[] publicKeyDER = Base64.getDecoder().decode(publicKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyDER));
        } catch (Exception e) {
            log.error("EncryptionKeyManager:loadPublicKey::", e);
            throw new EncryptionException(ExceptionCode.ERROR, e.getMessage());
        }
    }


    /**
     * Load private key.
     *
     * @param keyId the private key pem
     * @return the private key
     * @throws EncryptionException the vb app exception
     */
    public PrivateKey loadPrivateKey(String keyId) throws EncryptionException {
        try {
            if (latestKeyId.equals(keyId) && latestPrivateKey != null) {
                return latestPrivateKey;
            }
            String privateKeyPEM = encryptionKeyConfig.getPrivateKeys().get(keyId);
            if (StringUtils.isAllBlank(privateKeyPEM)) {
                throw new EncryptionException(ExceptionCode.BAD_REQUEST,
                    "Private key is null/blank.");
            }
            // strip of header, footer, newlines, whitespaces
            privateKeyPEM = privateKeyPEM
                .replace(BEGIN_PRIVATE_KEY, EMPTY)
                .replace(END_PRIVATE_KEY, EMPTY)
                .replaceAll(SPACE, EMPTY);

            // decode to get the binary DER representation
            byte[] privateKeyDER = Base64.getDecoder().decode(privateKeyPEM);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyDER));
        } catch (Exception e) {
            log.error("EncryptionKeyManager:loadPrivateKey::", e);
            throw new EncryptionException(ExceptionCode.ERROR, e.getMessage());
        }
    }


    public String getPublicKeyStr(String keyId) {
        return encryptionKeyConfig.getPublicKeys().get(keyId);
    }

}
