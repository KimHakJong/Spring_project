create table project_participants (
P_NO NUMBER references project(p_no) on delete cascade,
P_ID varchar2(15) references members(id) on delete cascade,
P_NAME varchar2(15)
);
select * from project_participants;
drop table project_participants;