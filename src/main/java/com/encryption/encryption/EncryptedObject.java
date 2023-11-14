package com.encryption.encryption;

import lombok.Data;

@Data
public class EncryptedObject {

    private String keyId;
    private byte[] content;

}
