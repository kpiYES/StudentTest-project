CREATE SCHEMA IF NOT EXISTS `studentTest_app`
  DEFAULT CHARACTER SET utf8;

USE studentTest_app;

CREATE TABLE IF NOT EXISTS subject (
  subject_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS question (
  question_id    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  subject_id     INT UNSIGNED  NOT NULL,
  query          VARCHAR(500) NOT NULL,
  answer_1       VARCHAR(100)  NOT NULL,
  answer_2       VARCHAR(100)  NOT NULL,
  answer_3       VARCHAR(100),
  answer_4       VARCHAR(100),
  correct_answer VARCHAR(100) NOT NULL,
  FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS test (
  test_id    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  subject_id INT UNSIGNED NOT NULL,
  name VARCHAR(50) UNIQUE NOT NULL,
  time_limit TINYINT(3) NOT NULL,
  FOREIGN KEY (subject_id) REFERENCES subject (subject_id)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS test_question (
  test_id     INT UNSIGNED,
  question_id INT UNSIGNED,
  PRIMARY KEY (test_id, question_id),
  FOREIGN KEY (test_id) REFERENCES test (test_id),
  FOREIGN KEY (question_id) REFERENCES question (question_id)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS role (
  role_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  name    VARCHAR(30)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS user (
  user_id    INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  role_id   INT UNSIGNED,
  first_name VARCHAR(30),
  last_name  VARCHAR(30),
  mail       VARCHAR(50) UNIQUE NOT NULL ,
  salt       VARCHAR(128) NOT NULL,
  hash       VARCHAR(256) NOT NULL,
  FOREIGN KEY (role_id) REFERENCES role (role_id)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS passed_test (
  passed_test_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  user_id        INT UNSIGNED,
  test_id        INT UNSIGNED,
  mark           TINYINT(3) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES user (user_id),
  FOREIGN KEY (test_id) REFERENCES test (test_id)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;

CREATE TABLE IF NOT EXISTS passed_question (
  passed_question_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  passed_test_id INT UNSIGNED,
  question_id INT UNSIGNED,
  user_answer VARCHAR(100) NULL,
  FOREIGN KEY (passed_test_id) REFERENCES passed_test(passed_test_id),
  FOREIGN KEY (question_id) REFERENCES question(question_id)
)
  ENGINE = InnoDB
  CHARACTER SET = UTF8;
