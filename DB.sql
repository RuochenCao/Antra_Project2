CREATE TABLE Student (
    student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(255)
);

CREATE TABLE Teacher (
    teacher_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    teacher_name VARCHAR(255)
);

CREATE TABLE student_teacher (
    student_id BIGINT,
    teacher_id BIGINT,
    PRIMARY KEY (student_id, teacher_id),
    FOREIGN KEY (student_id) REFERENCES Student(student_id),
    FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id)
);
