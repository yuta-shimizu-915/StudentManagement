package raisetech.StudentManagement.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  List<Student> search();
  @Select("SELECT * FROM student WHERE name = #{name}")
  Student searchByName(String name);


  Student searchStudent(String studentId);
  @Insert("INSERT student values(#{name},#{age})")
  void registerStudent(String name,int age);


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
