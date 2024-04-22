DROP DATABASE IF EXISTS scheduleProject;
CREATE DATABASE scheduleProject;
USE scheduleProject;

-- 사용자 테이블 생성
CREATE TABLE users (
    loginName VARCHAR(50) UNIQUE,
    loginPw VARCHAR(50)
);

SELECT * FROM users;

-- 일정 테이블 생성
CREATE TABLE schedules (
    DATE DATE,
    todo VARCHAR(255)
);

SELECT * FROM schedules;

-- 사용자 데이터 삽입


INSERT INTO users (loginName, loginPw)
VALUES ('애송이', '111'),
       ('송현지', '222'),
       ('박재민', '333');


INSERT INTO users (loginName, loginPw)
VALUES ('새 사용자', '새로운 비밀번호');  -- 회원가입 쿼리문


-- 일정 데이터 삽입
INSERT INTO schedules (DATE, todo)
VALUES ('2024-04-13', '재민생일'),
       ('2024-04-20', '오늘'),
       ('2024-04-23', '재민돼지');

-- 모든 사용자 검색
SELECT * FROM users;

-- 모든 일정 검색
SELECT * FROM schedules;

-- 특정 날짜의 일정 검색
SELECT * FROM schedules WHERE DATE = '2024-04-13';

-- 특정 키워드로 일정 검색
SELECT * FROM schedules WHERE todo LIKE '%돼지%';

-- 특정 연도와 월의 일정 검색
SELECT * FROM schedules WHERE YEAR(DATE) = 2024 AND MONTH(DATE) = 4;


UPDATE schedules
SET todo = 'Updated Content'
WHERE DATE = '2024-04-13';

-- 사용자 비밀번호 변경
UPDATE users
SET loginPw = '444'
WHERE loginName = '애송이';
