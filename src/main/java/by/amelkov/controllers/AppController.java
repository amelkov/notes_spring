package by.amelkov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @RequestMapping(value = {"/", "/index**"}, method = {RequestMethod.GET})
    public ModelAndView welcomePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Tutorial");
        model.addObject("message", "Welcome Page !");
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = "/protected**", method = RequestMethod.GET)
    public ModelAndView protectedPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security 3.2.4 Hello World Tutorial");
        model.addObject("message", "This is protected page - Only for Admin Users!");
        model.setViewName("protected");
        return model;

    }

    @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security 3.2.4 Hello World Tutorial");
        model.addObject("message", "This is confidential page - Need Super Admin Role!");
        model.setViewName("protected");

        return model;

    }
}
