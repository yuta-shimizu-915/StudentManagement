package raisetech.StudentManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  @InjectMocks
  private StudentService sut;

  @Test
  void searchStudentList_shouldReturnConvertedList() {
    List<Student> students = new ArrayList<>();
    List<StudentsCourse> courses = new ArrayList<>();
    List<StudentDetail> expected = new ArrayList<>();

    when(repository.search()).thenReturn(students);
    when(repository.searchStudentCourseList()).thenReturn(courses);
    when(converter.convertStudentDetails(students, courses)).thenReturn(expected);

    List<StudentDetail> actual = sut.searchStudentList();

    assertEquals(expected, actual);
    verify(repository).search();
    verify(repository).searchStudentCourseList();
    verify(converter).convertStudentDetails(students, courses);
  }


  @Test
  void searchStudent_shouldReturnStudentDetail() {
    String studentId = "S001";
    Student student = new Student();
    student.setStudentId(studentId);
    List<StudentsCourse> courseList = new ArrayList<>();

    when(repository.searchStudent(studentId)).thenReturn(student);
    when(repository.searchStudentCourse(studentId)).thenReturn(courseList);

    StudentDetail result = sut.searchStudent(studentId);

    assertEquals(student, result.getStudent());
    assertEquals(courseList, result.getStudentsCourseList());
    verify(repository).searchStudent(studentId);
    verify(repository).searchStudentCourse(studentId);
  }


  @Test
  void registerStudent_shouldCallRegisterMethods() {
    Student student = new Student();
    student.setStudentId("S123");

    StudentsCourse course = new StudentsCourse();
    List<StudentsCourse> courseList = List.of(course);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentsCourseList(courseList);

    StudentDetail result = sut.registerStudent(detail);

    verify(repository).registerStudent(student);
    verify(repository).registerStudentCourse(course);

    assertEquals(LocalDate.now(), course.getStartDate());
    assertEquals(LocalDate.now().plusYears(1), course.getFinishDate());
    assertEquals("S123", course.getStudentId());
    assertEquals(detail, result);
  }

  @Test
  void updateStudent_shouldCallUpdateMethods() {
    Student student = new Student();
    student.setStudentId("S456");

    StudentsCourse course = new StudentsCourse();
    List<StudentsCourse> courseList = List.of(course);

    StudentDetail detail = new StudentDetail();
    detail.setStudent(student);
    detail.setStudentsCourseList(courseList);

    sut.updateStudent(detail);

    verify(repository).updateStudent(student);
    verify(repository).updateStudentsCourses(course);
  }

  @Test
  void initStudent() {
    String studentId = "S001";
    Student student = new Student();
    student.setStudentId(studentId);

    StudentsCourse studentCourse = new StudentsCourse();

    LocalDate now = LocalDate.now();

    sut.initStudentsCourse(studentCourse, student);

    assertEquals(studentId, studentCourse.getStudentId());
    assertEquals(LocalDateTime.now().getHour(), studentCourse.getStartDate());
    assertEquals(LocalDateTime.now().plusYears(1).getYear(),
        studentCourse.getFinishDate());


  }
}

