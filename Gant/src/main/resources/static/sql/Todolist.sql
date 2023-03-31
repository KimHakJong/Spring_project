drop table todolist cascade constraints purge;

create table todolist(
board_num number,
board_id varchar2(30), --현재 로그인아이디
board_name varchar2(30), --references members(id) on delete cascade  --보내는사람
board_r_id varchar2(30), --받는사람 아이디 
board_r_name varchar2(30), --받는사람 
board_pass varchar2(30),
board_subject varchar2(300),
board_content varchar2(4000),
deadline varchar2(20),
state varchar2(10) --현재 상태
);


    
   





    
   