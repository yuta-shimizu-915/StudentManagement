package raisetech.StudentManagement.data;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseStatus {

  private int id;
  private int studentCourseId;
  private String status;
  private LocalDateTime updatedAt;

}