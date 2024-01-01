package com.encryption.encryption.controller;


import com.encryption.encryption.model.DecryptRequest;
import com.encryption.encryption.model.EncryptionRequest;
import com.encryption.encryption.model.PublicCert;
import com.encryption.encryption.service.EncryptionService;
import com.encryption.exception.EncryptionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptionController {

    @Autowired
    EncryptionService encryptionService;

    @GetMapping("/v1/cert")
    @Operation(summary = "Get latest encryption cert")
    public PublicCert getPublicKey() throws EncryptionException {
        return encryptionService.getCert();
    }

    @PostMapping("/v1/encrypt")
    @Operation(summary = "Encrypt")
    public String encrypt(@RequestBody EncryptionRequest request) throws EncryptionException{
        return encryptionService.encryptPlainText(request.getPlainText());
    }

    @PostMapping("/v1/decrypt")
    @Operation(summary = "Decrypt")
    public String encrypt(@RequestBody DecryptRequest request) throws EncryptionException{
        return encryptionService.decryptAsPlainText(request.getEncryptedText());
    }


}
