package raisetech.StudentManagement.data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  @NotBlank
  @Pattern(regexp = "^\\d+$", message = "suuzi nomi insert")
  private String studentId;

  @NotBlank(message = "name wo insert")
  private String name;

  @NotBlank
  private String furigana;

  @NotBlank
  private String nickName;

  @NotBlank
  private String email;

  @NotBlank
  private String region;

  @NotNull
  @Min(0)
  @Max(150)
  private Integer age;

  @NotBlank
  private String gender;

  private String remark;
  private boolean isDeleted;//論理削除


}


