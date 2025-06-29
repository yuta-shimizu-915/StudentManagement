package raisetech.StudentManagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;

@Validated
@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;


  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @Operation(summary = "ichirannkensaku", description = "ichirann no kennsaku")
  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.searchStudentList();
  }

  @Operation(summary = "id-kensaku", description = "id wo kensaku")
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @Size(min = 1, max = 3) String id) {
    return service.searchStudent(id);
  }

  private List<StudentDetail> convertStudentDetails(List<Student> students,
      List<StudentsCourse> studentsCourses) {
    List<StudentDetail> studentDetails = new ArrayList<>();
    students.forEach(student -> {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);
      List<StudentsCourse> convertStudentCours = studentsCourses.stream()
          .filter(studentCourse -> student.getStudentId().equals(studentCourse.getStudentId()))
          .collect(Collectors.toList());
      studentDetail.setStudentsCourseList(convertStudentCours);
      studentDetails.add(studentDetail);
    });
    return studentDetails;
  }

  @Operation(summary = "course-kensaku", description = "course wo kensaku")
  @GetMapping("/studentcourses")
  public List<StudentsCourse> getStudentCoursesList() {
    return service.searchStudentCoursesList();
  }

  @Operation(summary = "zyukouseitouroku", description = "zukousei wo touroku", responses = {
      @ApiResponse(responseCode = "400", content = @Content())})
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  @Operation(summary = "hensyugamenn-hyouzi", description = "hensyuugamenn wo hyouzi")
  @GetMapping("/editStudent/{id}")
  public String editStudent(@PathVariable String id, Model model) {
    StudentDetail studentDetail = service.getStudentDetailById(id);
    model.addAttribute("studentDetail", studentDetail);
    return "updateStudent";
  }

  @Operation(summary = "info update", description = "zyouhou wo kousinn")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("update is success.");
  }

  @Operation(summary = "update cancel", description = "kousinn wo tyuusi")
  @GetMapping("/cancelUpdate")
  public String cancelUpdate() {
    return "redirect:/studentList";
  }

}
