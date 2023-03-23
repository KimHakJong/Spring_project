create table memo (
num 	number primary key,
id      	varchar2(15) references members(id) on delete cascade,
subject 	varchar2(50),
content 	varchar2(3000),
background varchar2(100),
color 		varchar2(20),
update_date 	varchar2(14) default to_char(SYSDATE, 'YYYYMMDDHH24MISS')
);
select * from memo;
drop table memo;