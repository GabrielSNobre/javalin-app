drop table if exists tb_user;

create table if not exists tb_user(
    id integer primary key autoincrement,
    nome text not null,
    email text not null
);


insert into tb_user(nome,email) values ('bruno', 'bruno@email.com');
insert into tb_user(nome,email) values ('gabriel', 'gabriel@email.com');
insert into tb_user(nome,email) values ('maria', 'maria@email.com');
insert into tb_user(nome,email) values ('ana', 'ana@email.com');
