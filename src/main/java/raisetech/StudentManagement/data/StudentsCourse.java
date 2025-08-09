package raisetech.StudentManagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "course info")
@Getter
@Setter
public class StudentsCourse {

  private int id;
  private String studentId;
  private String courseName;
  private LocalDate startDate;
  private LocalDate finishDate;

}
