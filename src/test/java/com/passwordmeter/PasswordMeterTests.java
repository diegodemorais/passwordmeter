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
    public void testReset() throws Exception {
        this.mockMvc.perform(post("/").accept(MediaType.parseMediaType("application/json")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string("(0, 0, N)"));
    }
    

//    @Test
//    public void testReset() throws Exception {
//        this.mockMvc.perform(post("/").accept(MediaType.parseMediaType("application/json")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(content().string("(0, 0, N)"));
//    }
//   
//    @Test
//    public void testCenario3() throws Exception {
//        this.mockMvc.perform(post("/position").accept(MediaType.parseMediaType("application/json")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(content().string("(0, 2, W)"));
//    }       
//
//    @Test
//    public void testCenario4() throws Exception {
//        this.mockMvc.perform(post("/AAA").accept(MediaType.parseMediaType("application/json")))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(content().string("400 Bad Request"));
//    }    
}