drop table cust_account;
CREATE table cust_account
(
    accountNO varchar2(20) primary key,
    custName  Varchar2(50),
    balance   number(20, 4)
);

insert into cust_account
values ('112', '홍길동', 100000);
insert into cust_account
values ('113', '김길동', 100000);
commit;

select *
from cust_account;