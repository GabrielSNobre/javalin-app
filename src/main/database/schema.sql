create table if not exists tb_user(
    id integer primary key autoincrement,
    nome text not null,
    email text not null,
    unique(email)
);

insert into tb_user(nome,email) values ('bruno', 'bruno@email.com') on conflict(email) do nothing;
insert into tb_user(nome,email) values ('gabriel', 'gabriel@email.com')on conflict(email) do nothing;
insert into tb_user(nome,email) values ('maria', 'maria@email.com')on conflict(email) do nothing;
insert into tb_user(nome,email) values ('ana', 'ana@email.com')on conflict(email) do nothing;
