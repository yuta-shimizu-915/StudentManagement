package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentCourses;


@Mapper
public interface StudentRepository {

  @Select("SELECT * FROM students")
  List<Student> searchStudent();

  @Select("SELECT * FROM students_courses")
  List<StudentCourses> searchCourse();

  @Insert(
      "INSERT INTO students (name,furigana,nickname,e_mail,region,age,gender,remark,isDeletes) "
          + "VALUES(#{name},#{furigana},#{nickName},#{email},#{region},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(student_id,course_name,start_date,finish_date)"
      + "VALUE(#{studentId},#{courseName},#{startDate},#{finishDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentsCourses(StudentCourses studentCourses);

  @Update("UPDATE students SET name=#{name},furigana=#{furigana}, nickname=#{nickName}, e_mail=#{email}, region=#{region}, age=#{age}, gender=#{gender}, remark=#{remark} WHERE student_id=#{studentId}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course_name=#{courseName}, start_date=#{startDate}, finish_date=#{finishDate} WHERE id=#{id}")
  void updateStudentCourse(StudentCourses studentCourses);

  @Select("SELECT student_id, name, furigana, nickname, e_mail, region, age, gender, remark  "
      + "FROM students WHERE student_id = #{id}")
  Student findStudentById(@Param("id") String id);

  @Select("SELECT student_id, course_name, start_date, finish_date " +
      "FROM students_courses WHERE student_id = #{id}")
  List<StudentCourses> findStudentCoursesById(@Param("id") String id);

}
