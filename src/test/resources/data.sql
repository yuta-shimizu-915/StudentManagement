INSERT INTO students (name,furigana,nickname,e_mail,region,age,gender)
VALUES('Yamada Taro','Yamada Taro','Taro','yamada.taro@example.com','Aichi',18,'man'),
      ('Sato Mii','Sato Mii','Mii','sat.mii@example.com ','shizuoka',15,'woman'),
      ('Igarashi Kenta','Igarashi Kenta','Ken','igarashi.kenta@example.com','Tokyo',34,'man'),
      ('Tokui Hazime','Tokui Hazime','Hazime','tokui.hazime@example.com','Tokushim',30,'man'),
      ('Kobayashi Yui','Kobayashi Yui','Yui','kobayashi.yui@example.com','Kanagawa',23,'woman');

INSERT INTO students_courses(student_id,course_name,start_date,finish_date)
VALUES(2,'Java_beginner','2024-12-13','2025-02-13'),
      (3,'Java_standard','2024-09-30','2025-01-10'),
      (1,'Java_master','2025-02-15','2025-05-15'),
      (5,'Excel_beginner','2024-07-20','2024-12-20'),
      (4,'Excel_standard','2024-03-10','2024-09-10');