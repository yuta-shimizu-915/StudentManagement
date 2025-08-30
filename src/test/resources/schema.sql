CREATE TABLE IF NOT EXISTS students
(
  student_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  furigana VARCHAR(100) NOT NULL,
  nickname VARCHAR(100),
  e_mail VARCHAR(100) NOT NULL,
  region VARCHAR(100) NOT NULL,
  age INT,
  gender VARCHAR(100),
  remark TEXT,
  isDeletes INT
);

CREATE TABLE IF NOT EXISTS students_courses
(
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT NOT NULL,
  course_name VARCHAR(100)NOT NULL,
  start_date DATE,
  finish_date DATE
);