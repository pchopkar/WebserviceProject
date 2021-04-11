-- 50 JPA - add this insert query and check the console
insert into user values(101, sysdate() , 'Jack');
insert into user values(102, sysdate() , 'Jill');
insert into user values(103, sysdate() , 'Adam');

--53 Relationsship between two table -
--4) add insert queries on data.sql and 5) check on console of h2 Post table might have created with data
insert into post values(201,'My first post',101);
insert into post values(202,'My second post',101);
insert into post values(203,'My third post',101);
