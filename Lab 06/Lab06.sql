
CREATE DATABASE IF NOT EXISTS university;
USE university;

-- Students
CREATE TABLE students (
    student_id INT PRIMARY KEY,
    name VARCHAR(100),
    dob DATE,
    email VARCHAR(100),
    gpa DECIMAL(3,2)
);

-- Courses
CREATE TABLE courses (
    course_id INT PRIMARY KEY,
    course_name VARCHAR(100),
    credits INT
);

-- Enrollments
CREATE TABLE enrollments (
    student_id INT,
    course_id INT,
    enroll_date DATE,
    grade CHAR(1),
    PRIMARY KEY (student_id, course_id),
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-- Instructors
CREATE TABLE instructors (
    instructor_id INT PRIMARY KEY,
    name VARCHAR(100),
    department VARCHAR(100)
);

-- Course-Instructors
CREATE TABLE course_instructors (
    course_id INT,
    instructor_id INT,
    PRIMARY KEY (course_id, instructor_id),
    FOREIGN KEY (course_id) REFERENCES courses(course_id),
    FOREIGN KEY (instructor_id) REFERENCES instructors(instructor_id)
);

-- Data

-- add your info and all your friends info here
INSERT INTO students VALUES 
(138, 'FIKRY', '2002-01-15', 'e21138@eng.pdn.ac.lk', 3.95),
(217, 'Ishan', '2002-06-21', 'e21217@eng.pdn.ac.lk', 3.35),
(49, 'Lahiru', '2002-09-01', 'e21049@eng.pdn.ac.lk', NULL);

-- add more courses if you like
INSERT INTO courses VALUES 
(224, 'Computer Architecture', 4),
(225, 'Software Construction', 3),
(226, 'Database Systems', 3);

-- add your enrollments here and your friends too
-- one course can obviously have more than one student 
-- soo add all your friends to all the courses
INSERT INTO enrollments VALUES 
(138, 224, '2024-02-01', 'A'),
(217, 225, '2024-02-01', 'B'),
(49, 226, '2024-02-15', NULL);

-- if you want you can add more instructors
INSERT INTO instructors VALUES 
(10, 'Saadia Jameel', 'Computer Engineering'),
(11, 'Shakthi Perera', 'Electrical Engineering');

-- and add newly assigned instructors to unassigned courses
INSERT INTO course_instructors VALUES 
(224, 10),
(225, 11);
