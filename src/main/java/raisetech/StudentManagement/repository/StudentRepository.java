package raisetech.StudentManagement.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;

/**
 * óu¶ƒe[ƒuƒ‹‚Æóu¶ƒR[ƒXî•ñƒe[ƒuƒ‹‚Æ•R‚Ã‚¯‚éRepository
 */

@Mapper
public interface StudentRepository {

  /**
   * óu¶‚Ì‘SŒŒŸõ
   *
   * @return@óu¶ˆê——(‘S‘Ì)
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * óu¶‚ÌŒŸõ
   *
   * @param studentId @óu¶ID
   * @return óu¶
   */
  @Select("SELECT * FROM students WHERE student_id = #{studentId}")
  Student searchStudent(String studentId);

  /**
   * óu¶‚ÌƒR[ƒXî•ñ‚Ì‘SŒŒŸõ
   *
   * @return@óu¶‚ÌƒR[ƒXî•ñ(‘SŒ)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourse> searchStudentCourseList();

  /**
   * óu¶ID‚É•R‚Ã‚­óu¶‚ÌƒR[ƒXî•ñ‚ÌŒŸõ
   *
   * @param studentId @óu¶ID
   * @return óu¶ID‚É•R‚Ã‚­ƒR[ƒXî•ñ
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourse> searchStudentCourse(String studentId);

  /**
   * óu¶‚ÌV‹K“o˜^
   *
   * @param student óu¶
   */
  @Insert(
      "INSERT INTO students (name,furigana,nickname,e_mail,region,age,gender,remark,isDeletes) "
          + "VALUES(#{name},#{furigana},#{nickName},#{email},#{region},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  /**
   * óu¶ƒR[ƒXî•ñ‚ğV‹K“o˜^@ID‚Í©“®Ì”Ô
   *
   * @param studentCourse óu¶ƒR[ƒXî•ñ
   */
  @Insert("INSERT INTO students_courses(student_id,course_name,start_date,finish_date)"
      + "VALUE(#{studentId},#{courseName},#{startDate},#{finishDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourse(StudentsCourse studentCourse);

  /**
   * óu¶‚ÌXV
   *
   * @param student @óu¶
   */
  @Update("UPDATE students SET name=#{name},furigana=#{furigana}, nickname=#{nickName}, e_mail=#{email}, region=#{region}, age=#{age}, gender=#{gender}, remark=#{remark} WHERE student_id=#{studentId}")
  void updateStudent(Student student);
  
  @Update("UPDATE students_courses SET course_name=#{courseName}, start_date=#{startDate}, finish_date=#{finishDate} WHERE id=#{id}")
  void updateStudentCourse(StudentsCourse studentsCourse);

  @Select("SELECT student_id, name, furigana, nickname, e_mail, region, age, gender, remark  "
      + "FROM students WHERE student_id = #{studentId}")
  Student findStudentById(@Param("studentId") String studentId);

  @Select("SELECT student_id, course_name, start_date, finish_date " +
      "FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourse> findStudentCoursesById(@Param("studentId") String studentId);

  /**
   * óu¶ƒR[ƒXî•ñ‚ÌƒR[ƒX–¼XV
   *
   * @param studentsCourse óu¶ƒR[ƒXî•ñ
   */
  @Update(
      "UPDATE students_courses SET course_name = #{courseName} WHERE id =#{id}")
  void updateStudentsCourses(StudentsCourse studentsCourse);


}
