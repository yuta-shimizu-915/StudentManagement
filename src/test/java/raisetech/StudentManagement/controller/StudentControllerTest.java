package raisetech.StudentManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.service.StudentService;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  @MockBean
  private StudentConverter studentConverter;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


  @Test
  void ichiranKensaku_returnList() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void Syousai_insert_check() {
    Student student = new Student();
    student.setStudentId("24");
    student.setName("Tadano Tameshi");
    student.setFurigana("Tadano tameshi");
    student.setNickName("Tame-shi");
    student.setEmail("Tadano.tameshi@example.com");
    student.setRegion("Hokkaido");
    student.setAge(35);
    student.setGender("man");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void Id_number_insert_check() {
    Student student = new Student();
    student.setStudentId("it is test.");
    student.setName("Tadano Tameshi");
    student.setFurigana("Tadano tameshi");
    student.setNickName("Tame-shi");
    student.setEmail("Tadano.tameshi@example.com");
    student.setRegion("Hokkaido");
    student.setAge(35);
    student.setGender("man");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message").containsOnly("");
  }

  @Test
  void getStudent_returnStudentDetail() throws Exception {

    Student student = new Student();
    student.setStudentId("24");
    student.setName("Tadano Tameshi");
    student.setFurigana("Tadano tameshi");
    student.setNickName("Tame-shi");
    student.setEmail("Tadano.tameshi@example.com");
    student.setRegion("Hokkaido");
    student.setAge(35);
    student.setGender("man");

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    when(service.searchStudent("24")).thenReturn(studentDetail);

    mockMvc.perform(get("/student/{id}", "24"))
        .andExpect(status().isOk())
        .andExpect(content().string(org.hamcrest.Matchers.containsString("Tadano Tameshi")));

    verify(service, times(1)).searchStudent("24");
  }
}