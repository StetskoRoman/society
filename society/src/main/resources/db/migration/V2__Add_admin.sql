insert into usr (id, username, password, active)
values (1,'admin', '1', true);

insert into user_role (user_id, roles)
values (1,'USER'), (1, 'ADMIN');

insert into usr (id, username, password, active)
values (2,'A', '1', true);

insert into user_role (user_id, roles)
values (2,'USER');

insert into usr (id, username, password, active)
values (3,'Q', '1', true);

insert into user_role (user_id, roles)
values (3,'USER');

