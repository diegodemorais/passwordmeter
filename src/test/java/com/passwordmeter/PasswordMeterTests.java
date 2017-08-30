package com.passwordmeter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.passwordmeter.controller.PasswordMeterController;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author DM
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@WebMvcTest(PasswordMeterController.class)
public class PasswordMeterTests{

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void passDiego123() throws Exception {
        this.mockMvc.perform(post("/pass=Diego123").accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"nota\":65,\"complexidade\":\"Forte\",\"chars\":32,\"upperLetters\":14,\"lowLetters\":8,\"numbers\":12,\"symbols\":0,\"midNumsSyms\":4,\"requirements\":8,\"lettersOnly\":0,\"numbersOnly\":0,\"repeatChars\":0,\"upperConsec\":0,\"lowConsec\":6,\"numbersConsec\":4,\"lettersSeq\":0,\"numbersSeq\":3,\"symbolsSeq\":0}"));}    
    
    @Test
    public void passaaa() throws Exception {
        this.mockMvc.perform(post("/pass=aaa").accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("{\"nota\":0,\"complexidade\":\"Muito fraca\",\"chars\":12,\"upperLetters\":0,\"lowLetters\":0,\"numbers\":0,\"symbols\":0,\"midNumsSyms\":0,\"requirements\":0,\"lettersOnly\":3,\"numbersOnly\":0,\"repeatChars\":6,\"upperConsec\":0,\"lowConsec\":4,\"numbersConsec\":0,\"lettersSeq\":0,\"numbersSeq\":0,\"symbolsSeq\":0}"));}    

}