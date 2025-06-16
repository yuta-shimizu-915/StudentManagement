package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;


@Mapper
public interface StudentRepository {


  List<Student> search();


  Student searchStudent(String studentId);


  List<StudentsCourse> searchStudentCourseList();


  List<StudentsCourse> searchStudentCourse(String studentId);


  void registerStudent(Student student);


  void registerStudentCourse(StudentsCourse studentCourse);


  void updateStudent(Student student);


  void updateStudentCourse(StudentsCourse studentsCourse);


  Student findStudentById(@Param("studentId") String studentId);


  List<StudentsCourse> findStudentCoursesById(@Param("studentId") String studentId);


  void updateStudentsCourses(StudentsCourse studentsCourse);


}
