package com.passwordmeter.controller;

import com.passwordmeter.entity.PasswordMeter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody String command(@PathVariable("password") String password) {
        meter.setNotaComplexidade(password); //Verifying grade and complexity
        String response = "{\"nota\":\""+ meter.getNota() + "\",\"complexidade\":\""+ meter.getComplexidade()+"\"}";
        return response;
    }    
    
//    @RequestMapping(value = "/pass={password}", produces = "application/json") //For both methods: POST and GET
//    public ResponseEntity<String> command(@PathVariable("password") String password) {
//        meter.setNotaComplexidade(password); //Verifying grade and complexity
//        return okRequest();
//    }
    
    public ResponseEntity<String> okRequest() {
        String content = ("Nota: " + meter.getNota() + "   Complexidade: "+ meter.getComplexidade());
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(content,responseHeaders,HttpStatus.OK);
    }    
    
    public ResponseEntity<String> badRequest(){
        String content = "400 Bad Request";
        HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(content,responseHeaders,HttpStatus.BAD_REQUEST);
    } 
    

//    @RequestMapping(value = "/", produces = "application/json") //For both methods: POST and GET
//    public ResponseEntity<String> index(HttpServletRequest request, HttpServletResponse response) {
//        //meter.resetRobot(); //Setting initial okRequest
//        return okRequest();
//    }
//    
//    @RequestMapping(value = "/position", produces = "application/json") //For both methods: POST and GET
//    public ResponseEntity<String> okRequest() {
//        String content = ("(" + meter.getX()+ ", " + meter.getY() + ", " + meter.getVista() + ")");
//        HttpHeaders responseHeaders = new HttpHeaders();
//        return new ResponseEntity<>(content,responseHeaders,HttpStatus.OK);
//    }    
//    
//    @RequestMapping(value = "/error", produces = "application/json")
//    public ResponseEntity<String> badRequest(){
//        String content = "400 Bad Request";
//        HttpHeaders responseHeaders = new HttpHeaders();
//        return new ResponseEntity<>(content,responseHeaders,HttpStatus.BAD_REQUEST);
//    }    
//       
//    @RequestMapping(value = "/{command}", produces = "application/json", method = RequestMethod.POST)
//    public ResponseEntity<String> command(@PathVariable("command") String command) {
//        boolean validPosition = meter.resetRobot(); //Setting initial okRequest
//        //boolean validPosition = true;
//        CharacterIterator it = new StringCharacterIterator(command);
//        for (Character ch = it.first() ; ch != CharacterIterator.DONE && validPosition; ch = it.next()) {
//            switch(Character.toUpperCase(ch)) {
//                case 'L': {
//                    meter.turnLeft();
//                    break;
//                }
//                case 'R': {
//                    meter.turnRight();
//                    break;
//                }
//                case 'M': {
//                    validPosition = meter.move();
//                    break;
//                }
//                default:
//                    return badRequest();
//            }
//        }
//        if (validPosition)
//            return okRequest();
//        else
//            return badRequest();
//    }
}