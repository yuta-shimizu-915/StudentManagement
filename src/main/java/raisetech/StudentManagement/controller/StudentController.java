package raisetech.StudentManagement.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentCourses> studentsCourses = service.searchStudentCoursesList();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));
    return "studentList";
  }

  @GetMapping("/Student/{id}")
  public String getStudent(@PathVariable String id, Model model) {
    StudentDetail studentDetail = service.searchStudent(id);
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  private List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentCourses> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);
      List<StudentCourses> convertStudentCourses = studentsCourses.stream()
          .filter(studentCourse -> student.getStudentId().equals(studentCourse.getStudentId()))
          .collect(Collectors.toList());
      studentDetail.setStudentsCourses(convertStudentCourses);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }

  @GetMapping("/studentcourses")
  public List<StudentCourses> getStudentCoursesList() {
    return service.searchStudentCoursesList();
  }

  @GetMapping("/newStudent")
  public String newStudent(Model model) {
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudentsCourses(Arrays.asList(new StudentCourses()));
    model.addAttribute("studentDetail", studentDetail);
    return "registerStudent";
  }

  @PostMapping("/registerStudent")
  public String registerStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "registerStudent";
    }
    service.registerStudent(studentDetail);
    return "redirect:/studentList";
  }

  @GetMapping("/editStudent/{id}")
  public String editStudent(@PathVariable String id, Model model) {
    StudentDetail studentDetail = service.getStudentDetailById(id);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }


  /*@PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail) {
    service.updateStudentDetail(studentDetail);
    return "redirect:/studentList";
  }*/


  /*@PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result) {
    if (result.hasErrors()) {
      return "updateStudent";
    }
    service.updateStudent(studentDetail);
    return "redirect:/studentList";
  }*/

  @PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute StudentDetail studentDetail, BindingResult result,
      @RequestParam("student.age") String ageStr) {
    if (result.hasErrors()) {
      return "updateStudent";
    }

//    String replaced = ageStr.replace(',', '.');
//    int ageInt = 0;
//    try {
//      double d = Double.parseDouble(replaced);
//      ageInt = (int) d;
//    } catch (NumberFormatException e) {
//
//    }
//
//    studentDetail.getStudent().setAge(ageInt);

    service.updateStudent(studentDetail);
    return "redirect:/studentList";
  }

  @GetMapping("/cancelUpdate")
  public String cancelUpdate() {
    return "redirect:/studentList";
  }

}
