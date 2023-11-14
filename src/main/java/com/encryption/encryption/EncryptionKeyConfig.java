package com.encryption.encryption;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.encryption")
@Getter
@Setter
public class EncryptionKeyConfig {

    private Map<String, String> publicKeys = new HashMap<>();
    private Map<String, String> privateKeys = new HashMap<>();
}
