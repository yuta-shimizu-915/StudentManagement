package raisetech.StudentManagement.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;

@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;

  @Test
  void Zyukousei_ZennkennKennsaku() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void Zyukousei_Touroku() {
    Student student = new Student();
    student.setName("Tadano Tameshi");
    student.setFurigana("Tadano Tameshi");
    student.setNickName("Tame");
    student.setEmail("tadano.tameshi@example.com");
    student.setRegion("Aichi");
    student.setAge(36);
    student.setGender("man");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();

    assertThat(actual.size()).isEqualTo(6);
  }

  @Test
  void Zyukousei_IdDe_Kensaku() {
    Student actual = sut.searchStudent("1");
    assertThat(actual.getName()).isEqualTo("Yamada Taro");
  }

  @Test
  void ZyukouKouza_ZennkennKensaku() {
    List<StudentsCourse> actual = sut.searchStudentCourseList();
    assertThat(actual).isNotEmpty();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void ZyukouKouza_IdDe_Kensaku() {
    List<StudentsCourse> actual = sut.searchStudentCourse("1");
    assertThat(actual).allMatch(c -> c.getStudentId().equals("1"));
    assertThat(actual).extracting(StudentsCourse::getCourseName).contains("Java_master");
  }

  @Test
  void ZyukouKouza_Touroku() {
    StudentsCourse course = new StudentsCourse();
    course.setStudentId("1");
    course.setCourseName("SQL_beginner");
    course.setStartDate(LocalDate.of(2025, 3, 1));
    course.setFinishDate(LocalDate.of(2025, 6, 1));
    sut.registerStudentCourse(course);

    List<StudentsCourse> actual = sut.searchStudentCourse("1");
    assertThat(actual).extracting(StudentsCourse::getCourseName)
        .contains("SQL_beginner");
  }

  @Test
  void Zyukousei_Koushin() {
    Student student = sut.searchStudent("1");
    student.setRegion("Osaka");
    sut.updateStudent(student);

    Student updated = sut.searchStudent("1");
    assertThat(updated.getRegion()).isEqualTo("Osaka");
  }

  @Test
  void ZyukouKouza_Koushin() {
    StudentsCourse course = sut.searchStudentCourse("1").get(0);
    course.setCourseName("Python_standard");
    sut.updateStudentCourse(course);

    StudentsCourse updated = sut.searchStudentCourse("1").get(0);
    assertThat(updated.getCourseName()).isEqualTo("Python_standard");
  }

  @Test
  void IdDe_Zyukousei_Kensaku() {
    Student student = sut.findStudentById("1");
    assertThat(student.getName()).isEqualTo("Yamada Taro");
  }

  @Test
  void IdDe_ZyukouKouza_Kensaku() {
    List<StudentsCourse> courses = sut.findStudentCoursesById("1");
    assertThat(courses).allMatch(c -> c.getStudentId().equals("1"));
  }

  @Test
  void ZyukouKouza_IkkatsuKoushin() {
    StudentsCourse course = sut.findStudentCoursesById("1").get(0);
    course.setCourseName("CSharp_master");
    sut.updateStudentsCourses(course);

    StudentsCourse updated = sut.findStudentCoursesById("1").get(0);
    assertThat(updated.getCourseName()).isEqualTo("CSharp_master");
  }

}