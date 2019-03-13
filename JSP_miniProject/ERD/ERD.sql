SELECT ROWNUM, m.* FROM (SELECT b.* FROM Boards b where b.CATEGORY_id like 'free' ORDER BY b.board_num ASC) m where rownum order by ROWNUM DESC



"select ROWNUM, m.* FROM (select b.mem_num, b.mem_alive, b.mem_super, b.mem_id, b.mem_pw, b.mem_name, to_char(b.mem_birth, 'yyyy-mm-dd') mem_birth, b.mem_email, b.mem_gender, b.mem_phone, b.mem_school, b.mem_part, b.mem_job, to_char(b.mem_create, 'yyyy-mm-dd') mem_create from members b ORDER BY b.mem_num ASC) m"
	+ "order by ROWNUM DESC";
	
	
select ROWNUM, m.* FROM 
(select b.mem_num, b.mem_alive, b.mem_super, b.mem_id, b.mem_pw, b.mem_name, to_char(b.mem_birth, 'yyyy-mm-dd') mem_birth, b.mem_email, b.mem_gender, b.mem_phone, b.mem_school, b.mem_part, b.mem_job, to_char(b.mem_create, 'yyyy-mm-dd') mem_create from members b ORDER BY b.mem_num ASC) m
where mem_alive=0 AND ROWNUM <= ?-(?*?)+? order by ROWNUM DESC;
select * from members where mem_alive=0
select * from members where mem_alive=1



select B.board_num, B.board_name, B.board_content, FD.file_id, FD.original_file, FD.convert_file from boards B JOIN file_db FD on B.board_num = FD.board_num

select * from boards where mem_num =2;
select * from file_db;
SELECT M.*, B.board_subject from members M JOIN boards B on M.mem_num = B.mem_num where M.mem_num = 2



select B.*, BR.* from boards B, BOARDS_RECOMMEND BR where B.mem_num = BR.mem_num;


select B.* from
(select boards.* from BOARDS boards where boards.mem_num = 2) B

select M.mem_num, B.board_num from MEMBERS M JOIN BOARDS B ON M.mem_num = B.mem_num;

/* 파일 디비 셀렉트 및 삭제 */
select t1.*, t2.* from file_db t1 join boards t2 on t1.board_num = t2.board_num AND t2.mem_num = 2
delete from file_db t1 where EXISTS (select 1 from BOARDS t2 where t1.board_num = t2.board_num AND t2.mem_num = 21)
/* 댓글 셀렉트 및 삭제 */
select * from comments where mem_num = 2
delete from comments where mem_num = 2
select t1.*, t2.* from comments t1 join boards t2 on t1.board_num = t2.board_num AND t2.mem_num = 2
delete from comments t1 where EXISTS (select 1 from BOARDS t2 where t1.board_num = t2.board_num AND t2.mem_num = 21)
/* 좋아요 싫어요 */
select t1.*, t2.* from BOARDS_RECOMMEND t1 join boards t2 on t1.board_num = t2.board_num AND t2.mem_num = 2
delete from BOARDS_RECOMMEND t1 where EXISTS (select 1 from BOARDS t2 where t1.board_num = t2.board_num AND t2.mem_num = 21)
select * from BOARDS_RECOMMEND where mem_num = 2
delete from BOARDS_RECOMMEND where mem_num = 2
/*  작성글 삭제 */
select * from BOARDS where mem_num = 2;
delete from BOARDS where mem_num = 2;
/* 유저 삭제 */
select * from MEMBERS where mem_num = 2;
delete from MEMBERS where mem_num = 2;

