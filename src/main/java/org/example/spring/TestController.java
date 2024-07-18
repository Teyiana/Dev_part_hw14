package org.example.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



@RestController
public class TestController {

    @RequestMapping("/test")
       public ModelAndView requestTest() {
            ModelAndView result = new ModelAndView("test");
            return result;
        }



}
