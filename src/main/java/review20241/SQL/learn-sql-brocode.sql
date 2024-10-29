CREATE DATABASE myDB;
USE myDB;

DROP DATABASE myDB;
-- ALTER DATABASE myDB READ ONLY = 0; 

CREATE TABLE employees (
	employee_id INT,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    hourly_pay DECIMAL(5, 2),
    hire_date DATE
);

-- modify table/working with column 
RENAME TABLE workers TO employees;

ALTER TABLE employees
ADD phone_number VARCHAR(15);

ALTER TABLE employees
rename column phone_number to email;

ALTER TABLE employees
MODIFY COLUMN email VARCHAR(100);

ALTER TABLE employees
modify email VARCHAR(100)
after last_name;

ALTER TABLE employees
modify email VARCHAR(100)
first;

alter table employees
drop column email;

INSERT INTO employees
value (
	1, "Eugene", "Krabs", 25.50, "2023-01-02"
);

INSERT INTO employees
value (2, "Squidward", "Tentacles", 15.00, "2023-01-03"),
	  (3, "Spongebob", "Squarepants", 12.50, "23-01-04"), 
      (4, "Patrick" , "Star", 12.50, "2023-01-05"), 
      (5, "Sandy", "Cheeks", 17.25, "2023-01-06");
      
-- insert into selected column (not full data)
 INSERT INTO employees (employee_id, first_name, last_name)
value (6, "Sheldon", "Plankton");

select first_name, last_name from employees;

select *
from employees
where employee_id = 1;

select * from employees
where first_name like "%bob%";

select * from employees
where hourly_pay >= 15.00;

select * from employees
where hire_date <= "2023-01-03";

select *
from employees
where employee_id != 1;

select *
from employees
where hire_date is null;

select *
from employees
where hire_date is not null;

-- update and delete data in table
update employees
set hourly_pay = 10.50,
	hire_date = "2023-01-07"
where employee_id = 6;

update employees
set hourly_pay = null,
	hire_date = null
where employee_id = 6;

-- update all rows 
update employees
set hourly_pay = 10.00;

-- delete all rows from a table
delete from employees;

-- delete selected rows
delete from employees
where employee_id = 6;


-- autocommit, commit, rollback
-- whenever we run a sql it call a transaction, sometimes we want to roll it back
-- autocommit is on by default/ its like a safe point to create by COMMIT at a point and rollback to it
set autocommit = off; -- it has to be off
commit; -- create safe point
delete from employees;
rollback;

SELECT * FROM employees;

-- current date and time
CREATE TABLE test (
	my_date DATE,
    my_time TIME,
    my_datetime DATETIME
);

insert into test
value (current_date(), current_time(), now()),
	  (current_date() + 1, null, null), -- tomorrow
	  (current_date() - 1, null, null); -- yesterday

select * from test;
drop table test;

-- UNIQUE CONSTRAINT 
-- (ensure all the values in the column is different)
create table products (
	product_id INT,
    product_name VARCHAR(25) unique,
    price DECIMAL(4, 2)
);

-- in case you forget to add unique constraint to a column
alter table products
add constraint
unique(products_name);

insert into products
values (100, "hamburger", 3.99),
	   (101, "fries", 1.89),
       (102, "soda", 1.00),
       (103, "ice cream", 1.49),
       (104, "fries", 1.79);


select * from products;

-- not null constraint

create table products (
	product_id INT,
    product_name VARCHAR(25),
    price DECIMAL(4, 2)
); 

-- add not null if you forget
alter table products
modify price DECIMAL(4, 2) not null;

insert into products
value (104, "cookie", null);

-- show every constraints in a table
SHOW CREATE TABLE products;

-- CHECK CONSTRAINT - check what value can be placed in a column
create table employees (
	employee_id int,
    first_name varchar(50),
    last_name varchar(50),
    hourly_pay decimal(5, 2),
    hire_date date,
    constraint chk_hourly_pay check (hourly_pay >= 10.00)
);

-- add check when table already created 
alter table employees
add constraint chk_hourly_pay check(hourly_pay >= 10.00);

insert into employees 
values (7, "Pearl", "Krabs", 9.75, "2023-01-08");

select * from employees;

-- show all constraints in a table
SHOW CREATE TABLE employees;

-- delete a check
alter table employees
drop check chk_hourly_pay;

-- DEFAULT CONSTRAINT
-- when inserting a new row, if you do not specified value for that column.
-- it will insert the value that we set

-- adding item to products that would be free
insert into products (product_id, product_name)
values (105, "straw"),
	  (106, "napkin"),
      (107, "fork"),
      (108, "spoon");

delete from products where product_id >= 105;

create table products(
	product_id int,
    product_name varchar(50),
    price decimal(5, 2) default 0
);

-- add default constraint after create a table 
alter table products
alter price set default 0;

select * from products;

-- Example default transaction table, after a transaction, add timestamp of that transaction
create table transactions (
	transaction_id int,
    transaction_amount decimal(5, 2),
    transaction_date datetime default now()
);

insert into transactions (transaction_id, transaction_amount)
values (3, 8.37);

select * from transactions;
drop table transactions;

-- PRIMARY KEY CONSTRAINT
-- apply to a column (values in column must be unique and not null)
-- typically use as an identifier

create table transactions (
	transaction_id INT PRIMARY KEY,
    amount decimal(5, 2)
);

alter table transactions
add constraint primary key(transaction_id);

insert into transactions 
values (1001, 3.38);

select * from transactions;

-- AUTO INCREMENT
drop table transactions;

create table transactions (
	transaction_id INT primary key auto_increment,
    amount decimal(5, 2)
);

insert into transactions (amount)
value (2.89), (3.31), (1.01), (5.00);

-- set primary key a different value
alter table transactions
auto_increment = 1000;

delete from transactions;

select * from transactions;

-- FOREIGN KEY
-- kinda like a primary key in one table can be found in a different table
-- using foreign key to establish a link between 2 tables
-- benefit (look up information about another table from the original table

create table customers (
	customer_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name VARCHAR(50)
);

insert into customers (first_name, last_name)
value ("Fred", "Fish"),
	  ("Larry", "Lobster"),
      ("Bubble", "Bass");

drop table transactions;
create table transactions (
	transaction_id int primary key auto_increment,
    amount decimal(5, 2),
    customer_id int,
    foreign key(customer_id) references customers(customer_id)
);

-- how to drop a foreign key
alter table transactions
drop foreign key transactions_ibfk_1;
-- drop constraint transactions_ibfk_1

-- give foreign key a unique name
alter table transactions
add constraint fk_customer_id
foreign key(customer_id) references customers(customer_id);

alter table transactions
auto_increment = 1000;

insert into transactions (amount, customer_id)
values (4.99, 3),
	   (2.89, 2),
       (3.38, 3),
       (4.99, 1);

delete from customers
where customer_id = 3;


SHOW CREATE TABLE transactions;
select * from customers;
select * from transactions;

-- JOIN clause - combine rows from 2 or more tables
-- based on a column between them (foreign key) 

-- not every customer have a transaction_id (they could pay in cash)
insert into transactions (amount, customer_id)
values (1.00, null);
-- not every customer have made a transaction
insert into customers (first_name, last_name)
values ("Poppy", "Puff");

-- inner join take data exist from both tables
select transaction_id, amount, first_name, last_name from transactions
inner join customers
on transactions.customer_id = customers.customer_id;

-- left join - display everything on the left, if a matching id, display other table's data on the right
select * from transactions
left join customers
on transactions.customer_id = customers.customer_id;

-- right join - reverse left join
select * from transactions
right join customers
on transactions.customer_id = customers.customer_id;

select * from customers;
select * from transactions;

-- wild card (% _)

select * from employees
where first_name like "s%";

select * from employees
where last_name like "%r";

alter table employees
add column job varchar(25) after hourly_pay;

update employees
set job = "janitor" where employee_id = 6;

select * from employees
where hire_date <= "2023-01-05"
and job = "cook";

select * from employees;

-- limit clause
-- limit number of records
-- useful if you're working with a lot of data
-- can be used to display a large data on pages (pagination)
select * from customers
order by last_name limit 1;

select * from customers
order by last_name desc limit 1;

-- union combines the results of two or more select statements
-- need the same number of column
CREATE TABLE income (
    income_name VARCHAR(50),
    hourly_pay DECIMAL(10, 2)
);

INSERT INTO income
value ("orders", 1000000),
	  ("merchandise", 50000),
      ("services", 125000);

CREATE TABLE expenses (
    expenses_name VARCHAR(50),
    amount DECIMAL(10, 2)
);
INSERT INTO expenses
value ("wages", -250000),
	  ("tax", -50000),
      ("repair", -15000);

select * from income
union
select * from expenses;

-- in case 2 tables have different column, select 2 of theirs
select first_name, last_name from employees
union
select first_name, last_name from customers;

-- union all -- include all the duplicated
select first_name, last_name from employees
union all
select first_name, last_name from customers;

-- views
-- a virtual table based on the result-set of an sql statement
--  the fields in a view a fields from one or more real tables in the database
-- they're not real tables, but can be interacted with as if they were

select * from employees;

CREATE VIEW employee_attendance as
select first_name, last_name
from employees;

drop view employee_attendance;

select * from employee_attendance;

-- self join
-- join another copy of a table to itself
-- used to compare rows of the same table
-- helps to display a heirarchy of data
alter table customers
add referral_id int;

update customers
set referral_id = 2
where customer_id = 3;

select a.customer_id, a.first_name, a.last_name,
		concat(b.first_name, " ", b.last_name) as "referred_by"
from customers as a
inner join customers as b
on a.referral_id = b.customer_id ;

select * from customers;

alter table employees
add supervisor_id int;

update employees
set supervisor_id = 
	case employee_id
		when 2 then 5
		when 3 then 5
        when 4 then 5
	end
where employee_id in (2, 3, 4);

update employees
set supervisor_id = 1 where employee_id = 5;

select a.employee_id, a.first_name, a.last_name,
	b.supervisor_id, concat(b.first_name, b.last_name) as "reports to"
from employees as a
inner join employees as b
on a.supervisor_id = b.employee_id;

select * from employees;




















