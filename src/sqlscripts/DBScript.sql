create table EMPLOYEE
(
  EMPLOYEE_ID VARCHAR2(10) primary key,
  EMPLOYEE_NAME VARCHAR2(20),
  EMPLOYEE_SURNAME VARCHAR2(20),
  ADDRESS VARCHAR2(30)
);

drop table employee;
select * from EMPLOYEE where EMPLOYEE_ID = '1001';
delete from employee where employee_id = '1005';

INSERT INTO EMPLOYEE VALUES('1001', 'Vijay', 'Maringanti', '1/54 Prospect Street');
INSERT INTO EMPLOYEE VALUES('1002', 'Srirama', 'Maringanti', '1/54 Prospect Street');
INSERT INTO EMPLOYEE VALUES('1003', 'Ramesh', 'Babu', '33 Delhi Street');
INSERT INTO EMPLOYEE VALUES('1004', 'Devan', 'Mukerjee', '21 Gorakpur Street');

