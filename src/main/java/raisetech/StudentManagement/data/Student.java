package raisetech.StudentManagement.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  @NotBlank
  private String studentId;

  @NotBlank
  private String name;

  @NotBlank
  private String furigana;

  @NotBlank
  private String nickName;

  @NotBlank
  private String email;

  @NotBlank
  private String region;

  @NotBlank
  private int age;

  @NotBlank
  private String gender;

  private String remark;
  private boolean isDeleted;//論理削除


}


