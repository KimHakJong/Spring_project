//예약추가,수정,조회
create table reserve(
num number primary key,
id varchar2(15) references members(id) delete cascade,
purpose varchar2(35),
names varchar2(100),
type varchar2(20),
resource varchar2(30),
day varchar2(10),
start_time varchar2(5) 
end_time varchar2(5)
);

//9시~7시 : 20개 (9시:1 , 9시30분:2, 10시:3 ~ 18시30분:20)
//자원,날짜별 예약 가능,불가 여부확인 테이블
create table reserve_available(
resource varchar2(30),
day varchar2(10),
reserved_time number
);

//종류별 자원 목록 테이블
create table reserve_item(
type varchar2(20),
resource varchar2(30)
);

//계정당 하루 6시간 예약가능(30분당 1개씩 차감)
//예약가능횟수 할당 테이블
create table reserve_give(
id varchar2(15)  references members(id) delete cascade,
possible number default 12
);

select * from reserve;
