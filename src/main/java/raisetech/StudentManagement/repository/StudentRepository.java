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
 * ��u���e�[�u���Ǝ�u���R�[�X���e�[�u���ƕR�Â���Repository
 */

@Mapper
public interface StudentRepository {

  /**
   * ��u���̑S������
   *
   * @return�@��u���ꗗ(�S��)
   */
  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * ��u���̌���
   *
   * @param studentId �@��u��ID
   * @return ��u��
   */
  @Select("SELECT * FROM students WHERE student_id = #{studentId}")
  Student searchStudent(String studentId);

  /**
   * ��u���̃R�[�X���̑S������
   *
   * @return�@��u���̃R�[�X���(�S��)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourse> searchStudentCourseList();

  /**
   * ��u��ID�ɕR�Â���u���̃R�[�X���̌���
   *
   * @param studentId �@��u��ID
   * @return ��u��ID�ɕR�Â��R�[�X���
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentsCourse> searchStudentCourse(String studentId);

  /**
   * ��u���̐V�K�o�^
   *
   * @param student ��u��
   */
  @Insert(
      "INSERT INTO students (name,furigana,nickname,e_mail,region,age,gender,remark,isDeletes) "
          + "VALUES(#{name},#{furigana},#{nickName},#{email},#{region},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "studentId")
  void registerStudent(Student student);

  /**
   * ��u���R�[�X����V�K�o�^�@ID�͎����̔�
   *
   * @param studentCourse ��u���R�[�X���
   */
  @Insert("INSERT INTO students_courses(student_id,course_name,start_date,finish_date)"
      + "VALUE(#{studentId},#{courseName},#{startDate},#{finishDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourse(StudentsCourse studentCourse);

  /**
   * ��u���̍X�V
   *
   * @param student �@��u��
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
   * ��u���R�[�X���̃R�[�X���X�V
   *
   * @param studentsCourse ��u���R�[�X���
   */
  @Update(
      "UPDATE students_courses SET course_name = #{courseName} WHERE id =#{id}")
  void updateStudentsCourses(StudentsCourse studentsCourse);


}
