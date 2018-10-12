package org.mobula.mobula_bigdata.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zw on 18-10-10.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() throws Exception {
       return "hello";
    }
}
