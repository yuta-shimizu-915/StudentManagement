package raisetech.StudentManagement.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import raisetech.StudentManagement.data.Student;
import raisetech.StudentManagement.data.StudentsCourse;

@Schema(description = "zyukousei")
@Getter
@Setter
public class StudentDetail {

  private Student student;
  private List<StudentsCourse> studentsCourseList;
}
