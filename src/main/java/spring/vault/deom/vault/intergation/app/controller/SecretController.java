package spring.vault.deom.vault.intergation.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vault")
public class SecretController {

   /* @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @GetMapping("/secret")
    public String getSecret() {
        return "Username: " + username + ", Password: " + password;
    }*/

    @GetMapping("/mtls/endpoint")
    public String getData() {
        return "mTls successfully..";
    }
}
