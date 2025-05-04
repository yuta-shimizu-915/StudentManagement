package raisetech.StudentManagement;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Studentcourses {
  private int id;
  private String studentid;
  private String coursename;
  private LocalDate startdate;
  private LocalDate finishdate;
}
