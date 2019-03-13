/* 
	* New Account Delete&Create *

drop user team3 cascade;

create user team3 identified by tiger;
grant connect, resource, create view, create procedure to team3;

*/

/* Drop Tables */

DROP TABLE Boards_Recommend CASCADE CONSTRAINTS;
DROP TABLE Comments CASCADE CONSTRAINTS;
DROP TABLE file_db CASCADE CONSTRAINTS;
DROP TABLE Boards CASCADE CONSTRAINTS;
DROP TABLE Category CASCADE CONSTRAINTS;
DROP TABLE members CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE Boards
(
	mem_num number NOT NULL,
	board_num number NOT NULL,
	board_subject varchar2(150),
	board_name varchar2(20),
	board_content clob,
	board_regdate date,
	board_like number,
	board_hate number,
	board_viewcount number,
	category_id varchar2(10) NOT NULL,
	PRIMARY KEY (board_num)
);


CREATE TABLE Boards_Recommend
(
	board_num number NOT NULL,
	mem_num number NOT NULL,
	like_count number DEFAULT 0,
	hate_count number DEFAULT 0
);


CREATE TABLE Category
(
	category_id varchar2(10) NOT NULL,
	PRIMARY KEY (category_id)
);


CREATE TABLE Comments
(
	mem_num number NOT NULL,
	board_num number NOT NULL,
	comment_num number NOT NULL,
	comment_content clob NOT NULL,
	comment_regdate date,
	comment_name varchar2(20) NOT NULL,
	PRIMARY KEY (comment_num)
);


CREATE TABLE file_db
(
	board_num number NOT NULL,
	file_id number NOT NULL,
	original_file varchar2(50) DEFAULT 'none',
	convert_file varchar2(50) DEFAULT 'none',
	PRIMARY KEY (file_id)
);


CREATE TABLE members
(
	mem_num number NOT NULL,
	mem_alive number DEFAULT 0 NOT NULL,
	mem_super number DEFAULT 0 NOT NULL,
	mem_id varchar2(30) NOT NULL,
	mem_pw varchar2(30) NOT NULL,
	mem_name varchar2(20) NOT NULL,
	mem_birth date NOT NULL,
	mem_email varchar2(40) NOT NULL,
	mem_gender number NOT NULL,
	mem_phone varchar2(15) NOT NULL,
	mem_school varchar2(50),
	mem_part varchar2(50),
	mem_job varchar2(50),
	mem_create date NOT NULL,
	PRIMARY KEY (mem_num)
);



/* Create Foreign Keys */

ALTER TABLE Boards_Recommend
	ADD FOREIGN KEY (board_num)
	REFERENCES Boards (board_num)
;


ALTER TABLE Comments
	ADD FOREIGN KEY (board_num)
	REFERENCES Boards (board_num)
;


ALTER TABLE file_db
	ADD FOREIGN KEY (board_num)
	REFERENCES Boards (board_num)
;


ALTER TABLE Boards
	ADD FOREIGN KEY (category_id)
	REFERENCES Category (category_id)
;


ALTER TABLE Boards
	ADD FOREIGN KEY (mem_num)
	REFERENCES members (mem_num)
;


ALTER TABLE Boards_Recommend
	ADD FOREIGN KEY (mem_num)
	REFERENCES members (mem_num)
;


ALTER TABLE Comments
	ADD FOREIGN KEY (mem_num)
	REFERENCES members (mem_num)
;
/* 기존 시퀀스 삭제 및 생성*/
drop sequence board_num_seq;
drop sequence comment_num_seq;
drop sequence file_id_seq;
drop sequence mem_num_seq;
drop sequence test_write_seq;

create sequence board_num_seq;
create sequence comment_num_seq;
create sequence file_id_seq;
create sequence mem_num_seq;
create sequence test_write_seq;

/* 카테고리 생성 */
INSERT INTO CATEGORY VALUES('free');
INSERT INTO CATEGORY VALUES('study');
INSERT INTO CATEGORY VALUES('notice');
INSERT INTO CATEGORY VALUES('job');
INSERT INTO CATEGORY VALUES('part');

/* 관리자 및 유저 생성*/
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 1, 1, 'admin', 'admin123!@#', '관리자', '1900-01-01', 'admin@test.com', 1, '01000000000', 'None', 'None', 'None', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 1, 0, 'test100', '123qwe!@#', '홍길동', '1978-02-10', 'test100@test.com', 1, '01054963215', '서울대학교', '컴퓨터과', 'IT', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test101', '123qwe!@#', '고길동', '2000-03-20', 'test101@test.com', 1, '01054632154', '경기대학교', '화학과', '사무직', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test102', '123qwe!@#', '둘리야', '1988-04-05', 'test102@test.com', 1, '01012349564', '인하대학교', 'None', '조교', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test103', '123qwe!@#', '또치야', '1988-05-15', 'test103@test.com', 1, '01056654878', '인하대학교', '화학과', '사무직', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 1, 0, 'test104', '123qwe!@#', '호랑이', '1999-06-25', 'test104@test.com', 1, '01062154896', '경기대학교', '의학과', '조교', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test105', '123qwe!@#', '여울이', '1999-07-30', 'test105@test.com', 1, '01025789622', '고려대학교', 'None', 'IT', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test106', '123qwe!@#', '작명힘', '2001-08-19', 'test106@test.com', 1, '01077777777', '경희대학교', 'None', 'None', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 1, 0, 'test107', '123qwe!@#', '아무거', '1977-09-29', 'test107@test.com', 1, '01024633547', '연세대학교', '컴퓨터과', '사무직', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test108', '123qwe!@#', '나만들', '1980-10-09', 'test108@test.com', 1, '01061874232', 'None', 'None', 'IT', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test109', '123qwe!@#', '어야해', '1987-11-07', 'test109@test.com', 1, '01014654951', '경희대학교', '의학과', '조교', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 1, 0, 'test110', '123qwe!@#', '서너무', '1978-12-17', 'test110@test.com', 1, '01012349545', '서울대학교', '컴퓨터과', 'None', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test111', '123qwe!@#', '힘들지', '1978-01-27', 'test111@test.com', 1, '01012341953', '경희대학교', '의학과', 'None', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test112', '123qwe!@#', '만그래', '1966-02-04', 'test112@test.com', 1, '01068723215', 'None', '화학과', 'IT', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test113', '123qwe!@#', '도역시', '1997-03-14', 'test113@test.com', 1, '01078532988', '연세대학교', '컴퓨터과', '사무직', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test114', '123qwe!@#', '해야해', '1974-04-24', 'test114@test.com', 1, '01083216854', '서울대학교', 'None', '조교', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test115', '123qwe!@#', '힘내자', '1973-05-03', 'test115@test.com', 1, '01098542532', '연세대학교', '의학과', 'IT', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test116', '123qwe!@#', '다왔다', '1978-06-13', 'test116@test.com', 1, '01045689878', '경기대학교', '컴퓨터과', 'None', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test117', '123qwe!@#', '조금더', '2007-07-23', 'test117@test.com', 1, '01032158979', 'None', '화학과', 'IT', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test118', '123qwe!@#', '다했다', '2004-08-21', 'test118@test.com', 1, '01032168795', '인하대학교', '화학과', 'None', SYSDATE);
INSERT INTO MEMBERS VALUES(MEM_NUM_SEQ.NEXTVAL, 0, 0, 'test200', '123qwe!@#', 'Error', '2004-08-21', 'test118@test.com', 1, '01011111111', '인하대학교', '화학과', 'None', SYSDATE);

/* 게시글 생성 id 1 ~ 18 */
INSERT INTO BOARDS VALUES(1, BOARD_NUM_SEQ.NEXTVAL, '1번글테스트', '관리자', '테스트 글을 작성합니다 관리자 입니다.', SYSDATE, 0, 0, 0, 'notice');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '4번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '23번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '8번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '14번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '9번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '10번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '18번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '17번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '5번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '42번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'part');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '19번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '7번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '16번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '37번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '21번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '35번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '36번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '20번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '25번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '38번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '22번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '43번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '24번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '6번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '26번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '30번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '11번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '31번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '15번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(1, BOARD_NUM_SEQ.NEXTVAL, '2번글테스트', '관리자', '테스트 글을 작성합니다 관리자 입니다.', SYSDATE, 0, 0, 0, 'notice');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '12번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '13번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '29번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '32번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'job');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '33번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(1, BOARD_NUM_SEQ.NEXTVAL, '3번글테스트', '관리자', '테스트 글을 작성합니다 관리자 입니다.', SYSDATE, 0, 0, 0, 'notice');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '39번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '40번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '41번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '27번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '49번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '28번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '44번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '45번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '46번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'study');
INSERT INTO BOARDS VALUES(6, BOARD_NUM_SEQ.NEXTVAL, '34번글테스트', '호랑이', '테스트 글을 작성합니다 호랑이 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '47번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(2, BOARD_NUM_SEQ.NEXTVAL, '48번글테스트', '홍길동', '테스트 글을 작성합니다 홍길동 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(9, BOARD_NUM_SEQ.NEXTVAL, '50번글테스트', '아무거', '테스트 글을 작성합니다 아무거 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(12, BOARD_NUM_SEQ.NEXTVAL, '51번글테스트', '서너무', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'part');
INSERT INTO BOARDS VALUES(22, BOARD_NUM_SEQ.NEXTVAL, 'edyjdrytjdr6tyjd', 'Error', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'free');
INSERT INTO BOARDS VALUES(22, BOARD_NUM_SEQ.NEXTVAL, 'djdjdyr6j', 'Error', '테스트 글을 작성합니다 서너무 입니다.', SYSDATE, 0, 0, 0, 'part');

