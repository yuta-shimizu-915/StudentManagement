package raisetech.StudentManagement.data;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentCourses {
  private int id;
  private String Studentid;
  private String CourseName;
  private LocalDate StartDate;
  private LocalDate FinishDate;
}
