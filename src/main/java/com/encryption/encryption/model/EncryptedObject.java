package com.encryption.encryption.model;

import lombok.Data;

@Data
public class EncryptedObject {

    private String keyId;
    private byte[] content;

}
