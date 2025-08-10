package raisetech.StudentManagement.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;
import raisetech.StudentManagement.domain.StudentDetail;

public class StudentConverterTest {

  @Test
  void studentcourse_mapping() {
    //複数の学生と、それぞれに対応した受講コース情報が正しく紐づいているかを確認するテスト。
    Student student1 = new Student();
    student1.setStudentId("1");
    student1.setName("YamadaTaro");

    Student student2 = new Student();
    student2.setStudentId("12");
    student2.setName("TadanoTameshi");

    StudentsCourse course1 = new StudentsCourse();
    course1.setId(1);
    course1.setStudentId("1");
    course1.setCourseName("Java_beginner");

    StudentsCourse course2 = new StudentsCourse();
    course2.setId(13);
    course2.setStudentId("12");
    course2.setCourseName("Java_start");

    List<Student> studentList = List.of(student1, student2);
    List<StudentsCourse> studentsCourseList = List.of(course1, course2);

    StudentConverter converter = new StudentConverter();
    List<StudentDetail> result = converter.convertStudentDetails(studentList, studentsCourseList);

    assertThat(result).hasSize(2);

    StudentDetail detail1 = result.get(0);
    assertThat(detail1.getStudent().getStudentId()).isEqualTo("1");
    assertThat(detail1.getStudentsCourseList()).hasSize(1);
    assertThat(detail1.getStudentsCourseList().get(0).getCourseName()).isEqualTo("Java_beginner");

    StudentDetail detail2 = result.get(1);
    assertThat(detail2.getStudent().getStudentId()).isEqualTo("12");
    assertThat(detail2.getStudentsCourseList()).hasSize(1);
    assertThat(detail2.getStudentsCourseList().get(0).getCourseName()).isEqualTo("Java_start");
  }

  @Test
  void emptyLists_returnEmptyResult() {
    StudentConverter converter = new StudentConverter();

    List<StudentDetail> result = converter.convertStudentDetails(List.of(), List.of());

    assertThat(result).isEmpty();
  }

  @Test
  void unmatchedStudentId_noCoursesLinked() {

    Student student = new Student();
    student.setStudentId("100");

    StudentsCourse course = new StudentsCourse();
    course.setId(1);
    course.setStudentId("999");

    StudentConverter converter = new StudentConverter();
    List<StudentDetail> result = converter.convertStudentDetails(List.of(student), List.of(course));

    assertThat(result).hasSize(1);
    StudentDetail detail = result.get(0);
    assertThat(detail.getStudent().getStudentId()).isEqualTo("100");
    assertThat(detail.getStudentsCourseList()).isEmpty();
  }
}