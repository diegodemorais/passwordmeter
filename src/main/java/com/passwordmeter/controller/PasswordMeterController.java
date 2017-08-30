package com.passwordmeter.controller;

import com.passwordmeter.entity.PasswordMeter;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DM
 */
@RestController
public class PasswordMeterController {
    PasswordMeter meter;
    public PasswordMeterController(){
        meter = new PasswordMeter();
    }
    
    @RequestMapping(value = "/pass={password}", produces = "application/json") //For both methods: POST and GET
    public @ResponseBody PasswordMeter command(@PathVariable("password") String password) {
        meter.setNotaComplexidade(password); //Verifying grade and complexity
        //String response = "{\"nota\":\""+ meter.getNota() + "\",\"complexidade\":\""+ meter.getComplexidade()+"\"}";
        //return response;
        return meter;
    }    
    
    
//    public ResponseEntity<String> okRequest() {
//        String content = ("Nota: " + meter.getNota() + "   Complexidade: "+ meter.getComplexidade());
//        HttpHeaders responseHeaders = new HttpHeaders();
//        return new ResponseEntity<>(content,responseHeaders,HttpStatus.OK);
//    }    
//    
//    public ResponseEntity<String> badRequest(){
//        String content = "400 Bad Request";
//        HttpHeaders responseHeaders = new HttpHeaders();
//        return new ResponseEntity<>(content,responseHeaders,HttpStatus.BAD_REQUEST);
//    } 
}