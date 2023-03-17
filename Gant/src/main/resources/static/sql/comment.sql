drop table comments3 cascade constraints purge;

CREATE TABLE comments3(
num               NUMBER primary key,           
id                VARCHAR2(30) references member3(id),     
content           VARCHAR2 (200), 
reg_date          date,
board_num         NUMBER  references board3(board_num) on delete cascade --comments 테이블이 참조하는 보드 글번호
);

-- 게시판 글이 삭제되면 참조하는 댓글도 삭제됩니다.


drop sequence com_seq3; --시퀀스삭제

create sequence com_seq3;--시퀀스생성

delete comments3;

select * from comments3;


  