package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/picture")
public class PictureController {
    @GetMapping("")
    public ModelAndView showList(){
        ModelAndView modelAndView=new ModelAndView("list");
        return modelAndView;
    }
}
