package raisetech.StudentManagement;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentManagementApplication {

	private String name = "hasegawa";
	private String age = "37";

	private Map<String,String> student;

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@GetMapping("/studentInfo")
	public String studentInfo() {
		return name+" "+age+"sai";
	}

	@PostMapping("/studentInfo")
	public void setStudentInfo(String name,String age){
		this.name = name;
		this.age=age;
	}

	@PostMapping("/studentName")
	public void updateStudentName(String name){
		this.name = name;
	}

	@PostMapping("/studentage")
	public void updateStudentAge(String age){
		this.age = age;
	}

	//GET POST
	//GETは取得する、リクエストの結果を受け取る
	//POSTは情報を与える、渡す
}
