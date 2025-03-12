package raisetech.StudentManagement.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;
import raisetech.StudentManagement.repository.StudentRepository;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository){
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    return repository.search().stream()
        .filter(Student -> Student.getAge()>=30&&Student.getAge()<=39)
        .collect(Collectors.toList());
  }

  public List<StudentCourses> searchStudentCoursesList(){
    return repository.search_course().stream()
        .filter(StudentCourses -> "Java_beginner".equals(StudentCourses.getCourseName())
        ||"Java_standard".equals(StudentCourses.getCourseName())
            ||"Java_master".equals(StudentCourses.getCourseName()))
        .collect(Collectors.toList());
  }
}
