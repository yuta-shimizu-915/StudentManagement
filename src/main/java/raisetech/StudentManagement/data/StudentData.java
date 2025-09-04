package raisetech.StudentManagement.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentData {

  private String studentId;
  private String name;
  private String furigana;
  private String nickName;
  private String email;
  private String region;
  private Integer age;
  private String gender;
  private String remark;

  private int studentCourseId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate finishDate;

  private int courseStatusId;
  private String status;
}
