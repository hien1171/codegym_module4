package com.codegym.spring_data_jpa.controller;

import com.codegym.spring_data_jpa.model.Student;
import com.codegym.spring_data_jpa.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @ModelAttribute("classList")
    public String[] getListStudent() {
        System.out.println("get list always run");
        return new String[]{"A02", "A03", "A04"};
    }

//    @GetMapping("")
//    public String showList(@PageableDefault(page = 0,size = 2,sort = "name",direction = Sort.Direction.ASC) Pageable pageable,
//                           @RequestParam(defaultValue = "",required = false) String searchName,
//                           Model model) {
//        Page<Student> studentPage = studentService.findAll(pageable,searchName);
//        model.addAttribute("studentPage",studentPage);
//        model.addAttribute("searchName",searchName);
//        return "list";
//    }
    @GetMapping("")
    public String showList(@RequestParam(defaultValue = "0",required = false) int page,
                           @RequestParam(defaultValue = "",required = false) String searchName,
                           Model model) {
        Pageable pageable = PageRequest.of(page,2,Sort.by("name").ascending().and(Sort.by("gender").ascending()));
        Page<Student> studentPage = studentService.findAll(pageable,searchName);
        model.addAttribute("studentPage",studentPage);
        model.addAttribute("searchName",searchName);
        return "list";
    }


    @GetMapping("/search")
    public ModelAndView search(@RequestParam String searchName) {
        List<Student> studentList = studentService.search(searchName);

        ModelAndView modelAndView =
                new ModelAndView("list", "studentList", studentList);
        return modelAndView;
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("languages", new String[]{"JS", "Java", "PHP"});
        return "create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute Student student,
                       RedirectAttributes redirectAttributes) {
        studentService.add(student);
        redirectAttributes.addFlashAttribute("mess", "Created Success");
        return "redirect:/student";
    }

    @GetMapping("/detail")
    public String detail1(@RequestParam(name = "id",
            required = false,
            defaultValue = "-1") int detailId,
                          Model model) {
        Student student = studentService.findById(detailId);
        model.addAttribute("student", student);
        return "detail";
    }

    @GetMapping("/detail/{id}")
    public String detail2(@PathVariable int id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "detail";
    }

}