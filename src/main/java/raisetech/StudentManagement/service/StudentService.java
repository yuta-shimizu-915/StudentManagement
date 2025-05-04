package raisetech.StudentManagement.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.domain.StudentDetail;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.searchStudent();
  }

  public List<StudentCourses> searchStudentCoursesList() {
    return repository.searchCourse();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    repository.registerStudent(studentDetail.getStudent());
    for (StudentCourses studentsCourse : studentDetail.getStudentsCourses()) {
      studentsCourse.setStudentId(studentDetail.getStudent().getStudentId());
      studentsCourse.setStartDate(LocalDate.now());
      studentsCourse.setFinishDate(LocalDate.now().plusYears(1));
      repository.registerStudentsCourses(studentsCourse);
    }
  }

  public void updateStudentDetail(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());

    for (StudentCourses course : studentDetail.getStudentsCourses()) {
      repository.updateStudentCourse(course);
    }
  }

  public StudentDetail getStudentDetailById(String id) {
    Student student = repository.findStudentById(id);
    List<StudentCourses> courses = repository.findStudentCoursesById(id);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentsCourses(courses);

    return studentDetail;
  }


}
