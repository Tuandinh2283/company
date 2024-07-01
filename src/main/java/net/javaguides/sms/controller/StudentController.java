package net.javaguides.sms.controller;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/new")
    public String createStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "create_student";
    }

    @PostMapping
    public String saveStudent(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_student";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/details/{id}")
    public String showStudentDetails(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "student-details";
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_student";
        }
        student.setId(id);
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }

    

    @PostMapping("/save-scores/{id}")
    public String saveScores(@PathVariable Long id,
                             @RequestParam("processScore") Double processScore,
                             @RequestParam("componentScore") Double componentScore) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }

        student.setProcessScore(processScore);
        student.setComponentScore(componentScore);

        studentService.updateStudent(student);

        return "redirect:/students/details/" + id;
    }
    @GetMapping("/input-scores/{id}")
    public String inputScoresForm(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "input-scores";
    }

   

    @GetMapping("/view-result/{id}")
    public String viewResult(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "view-result";
    }
    @GetMapping("/view-scores/{id}")
    public String viewScores(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id).orElse(null);
        if (student == null) {
            return "redirect:/students";
        }

        // Calculate overall score
        double overallScore = student.getProcessScore() + student.getComponentScore();

        model.addAttribute("student", student);
        model.addAttribute("overallScore", overallScore);

        return "view-scores";
    }

}
