create ProjectBoard(
p_no number,
pbo_num number,
writer varchar2(15) varchar2(15) references members(id) on delete cascade,

);