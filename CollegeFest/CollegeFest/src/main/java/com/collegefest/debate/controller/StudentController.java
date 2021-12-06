package com.collegefest.debate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.collegefest.debate.entity.Student;
import com.collegefest.debate.service.StudentService;

@Controller
@RequestMapping("/debate")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/participantslist")
	public String listStudents(Model model) {
		List<Student> students = studentService.findAll();
		model.addAttribute("Student", students);
		return "liststudents";
	}

	@RequestMapping("/addParticipantDetails")
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);
		return "studentform";
	}

	@RequestMapping("/updateParticipantDetails")
	public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = new Student();
		student = studentService.findById(id);
		model.addAttribute("Student", student);
		return "studentform";
	}

	// This post method will be used to both save new participant details
	// and update existing participants details
	@PostMapping("/saveParticipantsDetails")
	public String saveStudentInformation(@RequestParam("id") int id, @RequestParam("firstName") String fname,
			@RequestParam("lastName") String lname, @RequestParam("course") String course,
			@RequestParam("country") String country) {
		Student student;
		System.out.println(id);
		if (id != 0) {
			student = studentService.findById(id);
			student.setFirstName(fname);
			student.setLastName(lname);
			student.setCourse(course);
			student.setCountry(country);

		} else
			student = new Student(fname, lname, course, country);
		studentService.save(student);
		return "redirect:/debate/participantslist";
	}

	@RequestMapping("/deleteParticipantsDetails")
	public String deleteStudentInformation(@RequestParam("studentId") int id) {
		studentService.deleteById(id);
		return "redirect:/debate/participantslist";
	}
}
