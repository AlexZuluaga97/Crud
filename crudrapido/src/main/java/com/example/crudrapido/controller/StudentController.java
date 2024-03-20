package com.example.crudrapido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.example.crudrapido.entity.Student;
import com.example.crudrapido.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    // listar estudiantes
    @RequestMapping({ "/students", "/" })
    public String students(Model model) {
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }

    // agregar estudiante
    @GetMapping("/students/nuevo")
    public String crearStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "crear_student";
    }

    // Registrar
    @PostMapping("/students")
    public String guardarStudent(@ModelAttribute("student") Student student) {
        studentService.saveOrUpdate(student);
        return "redirect:/students";
    }

    // consultar estudiante por id
    //@GetMapping("/{studentId}")
    //public Optional<Student> getBId(@PathVariable("studentId") Long studentId) {
        //return studentService.getStudent(studentId);
    //}

    // crear o actualizar estudiante
    @PostMapping
    public void saveUpdate(@RequestBody Student student) {
        studentService.saveOrUpdate(student);
    }

    // eliminar estudiante
    @GetMapping("/{studentId}")
    public String delete(@PathVariable("studentId") Long studentId) {
        studentService.delete(studentId);
        return "redirect:/students";
    }
}
