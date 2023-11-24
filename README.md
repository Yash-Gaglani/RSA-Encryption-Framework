# RSA Encryption Framework

## Introduction

Welcome to the RSA Encryption Framework! This framework provides a simple API for RSA encryption and decryption, allowing users to easily integrate RSA cryptography into their applications. The framework includes three main APIs: one to retrieve the public certificate, one to encrypt plaintext, and one to decrypt an encrypted Base64 string.

## Getting Started

To use the RSA Encryption Framework, follow these steps:

1. **Clone the repository to your local machine:**

    ```bash
    git clone https://github.com/your-username/rsa-encryption-framework.git
    ```

2. **Open the `application.properties` file in the project and configure the public and private keys:**

    ```properties
    # RSA Keys Configuration
    rsa.publicKey=YOUR_PUBLIC_KEY_HERE
    rsa.privateKey=YOUR_PRIVATE_KEY_HERE
    ```

    Replace `YOUR_PUBLIC_KEY_HERE` and `YOUR_PRIVATE_KEY_HERE` with your actual public and private RSA keys.

3. **Build and run the application:**

    ```bash
    ./mvnw spring-boot:run
    ```

    The application will start, and you can access the APIs described below.

## API Endpoints

### 1. Get Public Certificate

   - **Endpoint:** `/v1/cert`
   - **Method:** `GET`
   - **Description:** Retrieves the public certificate used for RSA encryption.
   - **Example Request:**
     ```bash
     curl http://localhost:8080/v1/cert
     ```

### 2. Encrypt Plaintext

   - **Endpoint:** `/v1/encrypt`
   - **Method:** `POST`
   - **Description:** Encrypts plaintext and returns the encrypted Base64 string.
   - **Request Body:**
     ```json
     {
       "plaintext": "Hello, RSA!"
     }
     ```
   - **Example Request:**
     ```bash
     curl -X POST -H "Content-Type: application/json" -d '{"plaintext": "Hello, RSA!"}' http://localhost:8080/v1/encrypt
     ```

### 3. Decrypt Encrypted Base64 String

   - **Endpoint:** `/v1/decrypt`
   - **Method:** `POST`
   - **Description:** Decrypts an encrypted Base64 string and returns the plaintext.
   - **Request Body:**
     ```json
     {
       "encryptedBase64String": "ENCRYPTED_BASE64_STRING_HERE"
     }
     ```
   - **Example Request:**
     ```bash
     curl -X POST -H "Content-Type: application/json" -d '{"encryptedBase64String": "ENCRYPTED_BASE64_STRING_HERE"}' http://localhost:8080/v1/decrypt
     ```

## Note

Make sure to handle and store your private key securely. Do not expose it in the source code or share it publicly.

Feel free to explore and integrate the RSA Encryption Framework into your projects! If you encounter any issues or have suggestions for improvement, please open an issue on the GitHub repository.

Happy encrypting!
