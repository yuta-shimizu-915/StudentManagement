package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> searchCourse();

  @Insert(
      "INSERT INTO students (student_id,name,furigana,nickname,e_mail,region,age,gender,remark,isDeletes) "
          + "VALUES(#{studentId},#{name},#{furigana},#{nickName},#{email},#{region},#{age},#{gender},#{remark},false)")
  void registerStudent(Student student);
}
