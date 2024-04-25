insert into users
(create_date, update_date, email, name, password, role, status)
values (now(), now(), '테스트1@email.com', '테스트 관리자1', '테스트 비밀번호1', 'ADMIN', true);

insert into users
(create_date, update_date, email, name, password, role, status)
values (now(), now(), '테스트2@email.com', '테스트 관리자2', '테스트 비밀번호2', 'ADMIN', true);

insert into users
(create_date, update_date, email, name, password, role, status)
values (now(), now(), '테스트3@email.com', '테스트 관리자3', '테스트 비밀번호3', 'ADMIN', true);

insert into users
(create_date, update_date, email, name, password, role, status)
values (now(), now(), '테스트4@email.com', '테스트 관리자4', '테스트 비밀번호4', 'ADMIN', false);

insert into users
(create_date, update_date, email, name, password, role, status)
values (now(), now(), '테스트5@email.com', '테스트 관리자5', '테스트 비밀번호5', 'GUEST', false);
