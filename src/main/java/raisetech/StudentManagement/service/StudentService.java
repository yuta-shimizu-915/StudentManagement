package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.controller.converter.StudentConverter;
import raisetech.StudentManagement.data.CourseStatus;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentData;
import raisetech.StudentManagement.data.StudentsCourse;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;


@Service
public class StudentService {

  @Autowired
  private StudentRepository repository;

  @Autowired
  private StudentConverter converter;

  /*@Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }*/

  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentsCourse> studentCourseList = repository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }


  public StudentDetail searchStudent(String studentId) {
    Student student = repository.searchStudent(studentId);
    List<StudentsCourse> studentCourseList = repository.searchStudentCourse(
        student.getStudentId());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourseList);
    return studentDetail;
  }

  public List<StudentsCourse> searchStudentCoursesList() {
    return repository.searchStudentCourseList();
  }

  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentsCourse -> {
      initStudentsCourse(studentsCourse, student);
      repository.registerStudentCourse(studentsCourse);

      CourseStatus status = new CourseStatus();
      status.setStudentCourseId(studentsCourse.getId());
      status.setStatus("Kari");
      repository.registerCourseStatus(status);
    });
    return studentDetail;
  }

  @Transactional
  public void updateCourseStatus(CourseStatus courseStatus) {
    repository.updateCourseStatus(courseStatus);
  }

  void initStudentsCourse(StudentsCourse studentsCourse, Student student) {
    LocalDate now = LocalDate.now();

    studentsCourse.setStudentId(student.getStudentId());
    studentsCourse.setStartDate(now);
    studentsCourse.setFinishDate(now.plusYears(1));
  }


  public void updateStudentDetail(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());

    for (StudentsCourse course : studentDetail.getStudentCourseList()) {
      repository.updateStudentsCourses(course);
    }
  }

  public StudentDetail getStudentDetailById(String id) {
    Student student = repository.findStudentById(id);
    List<StudentsCourse> courses = repository.findStudentCoursesById(id);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(courses);

    return studentDetail;
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentsCourse -> repository.updateStudentsCourses(studentsCourse));
  }

  @Transactional(readOnly = true)
  public List<StudentData> searchStudents(String name, String courseName, String status) {
    return repository.searchStudents(name, courseName, status);
  }


}
