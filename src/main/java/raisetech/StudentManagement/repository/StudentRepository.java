package raisetech.StudentManagement.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;


@Mapper//データベースそのもの
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> search();
  @Select("SELECT * FROM student WHERE name = #{name}")
  Student searchByName(String name);

  @Insert("INSERT student values(#{name},#{age})")
  void registerStudent(String name,int age);

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> search_course();
}
