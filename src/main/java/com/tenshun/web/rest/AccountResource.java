package com.tenshun.web.rest;

import com.tenshun.model.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/account")
public class AccountResource {


    @GetMapping("/info")
    public ResponseEntity<?> getAccountInfo(){
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
}
