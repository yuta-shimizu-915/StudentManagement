package raisetech.StudentManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "zyukousei kanri System"))
@SpringBootApplication
public class StudentManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentManagementApplication.class, args);
  }
}
