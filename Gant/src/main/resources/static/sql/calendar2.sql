drop table calendar cascade constraints purge;

create table calendar(
id  varchar2(15), 
admin varchar2(15),
allday varchar2(15) default 'true',
startday varchar2(30),
endday varchar2(30),
name varchar2(30) references members(id) on delete cascade,
title varchar2(200)
);

create table pcalendar(
id  varchar2(15), 
p_no number, --references members(id) on delete cascade,
hostid varchar2(15),
allday varchar2(15) default 'true',
startday varchar2(30),
endday varchar2(30),
name varchar2(30) references members(id) on delete cascade,
title varchar2(200)
);

insert into calendar values('admin', 'true', 'true', '2023-03-20', '2023-03-21', '프로젝트', '프로젝트 기간');  
insert into members values('true','asd12345','12345678','박길동','811111-1111111','010-1111-1111','1@1.com','22222','서울시 종로구','인사부','대리',null,'20230201');