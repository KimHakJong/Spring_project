drop table comments3 cascade constraints purge;

CREATE TABLE comments3(
num               NUMBER primary key,           
id                VARCHAR2(30) references member3(id),     
content           VARCHAR2 (200), 
reg_date          date,
board_num         NUMBER  references board3(board_num) on delete cascade --comments ���̺��� �����ϴ� ���� �۹�ȣ
);

-- �Խ��� ���� �����Ǹ� �����ϴ� ��۵� �����˴ϴ�.


drop sequence com_seq3; --����������

create sequence com_seq3;--����������

delete comments3;

select * from comments3;


  