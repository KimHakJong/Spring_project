drop sequence note_delete_seq; --쪽지휴지통 시퀀스삭제
create sequence note_delete_seq; --쪽지휴지통 시퀀스생성	


drop table note_delete CASCADE CONSTRAINTS;


CREATE TABLE note_delete(
	delete_num      number PRIMARY KEY,
	kind          varchar2(4) check (kind in ('from','to')), --보낸쪽지테이블이면"from" 받은 쪽지 테이블이면 "to"
	delete_table  varchar2(3) check (delete_table in ('yes','no')), -- 휴지통 버리기를 선택했다면"yes" 선택하지 않았다면 "no"
	delete_date   varchar2(8) -- 만약 휴지버리기를 시작했다면 "20230203" 형태로 날짜가 삽입된다. 만약 버리기를 취소했다면 다시 공백을 넣는다. 버리기 날짜를 기준으로 일주일 뒤에 테이블은 삭제된다.
	);	
	
	
drop sequence notefile_seq; --쪽지파일 시퀀스삭제
create sequence notefile_seq; --쪽지파일 시퀀스생성

drop table notefile CASCADE CONSTRAINTS;

CREATE TABLE notefile(
	file_num      number PRIMARY KEY,
	original_filename	varchar2(50), -- 오리지널 파일이름
	extension			varchar2(50), -- 확장자
	save_folder			varchar2(1000) -- 저장경로 (파일경로 + 중복방지파일명)
);	
	
		
drop sequence note_seq; --쪽지 시퀀스삭제
create sequence note_seq; --쪽지 시퀀스생성	
	

DROP TABLE note CASCADE CONSTRAINTS;


CREATE TABLE note(
    id	            varchar2(15) references members(id) on delete cascade, -- id (작성자)
	to_id	        varchar2(300), --id을 "," 을 기준으로 나눈다.
    note_num	    number PRIMARY KEY,
	subject	    	varchar2(300),
	content	    	varchar2(4000),
	write_date		varchar2(10)  default to_char(SYSDATE, 'YYYY/MM/DD'), --작성일
	delete_num      number references note_delete(delete_num) on delete cascade,
	file_num        number 
);



drop table note_to CASCADE CONSTRAINTS;

CREATE TABLE note_to(
	note_num	    number,
	id	            varchar2(15) references members(id), -- 보낸사람 id
	to_id	        varchar2(15) references members(id) on delete cascade, --보내는사람 id
	read_check		varchar2(5)  check (read_check in ('true','false')), -- true 쪽지 읽음 , false 쪽지 안읽음
    subject	    	varchar2(300),
	content	    	varchar2(4000),
	write_date		varchar2(10)  default to_char(SYSDATE, 'YYYY/MM/DD'),
	delete_num      number references note_delete(delete_num) on delete cascade,
	file_num        number 
	);
	
	