package com.encryption.encryption;


import com.encryption.exception.EncryptionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Utility API(s)", description = "Utility API")
public class EncryptionController {

    @Autowired
    EncryptionService encryptionService;

    @GetMapping("/v1/cert")
    @Operation(summary = "Get latest encryption cert")
    public PublicCert getPublicKey() throws EncryptionException {
        return encryptionService.getCert();
    }

}
