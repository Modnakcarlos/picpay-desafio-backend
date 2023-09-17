create schema if not exists public;

-- PESSOA
create table if not exists public.pessoa (
    id varchar(255) not null constraint pessoa_pkey primary key,
    name varchar(255) not null,
    usuario varchar(255) not null constraint fk_user_id references public.usuario
);

-- PESSOA FISICA
create table if not exists public.pessoa_fisica (
    id varchar(255) not null constraint pessoa_fisica_pkey primary key constraint fk_pessoa_id references public.pessoa,
    cpf varchar(20) not null
);

-- PESSOA JURIDICA
create table if not exists public.pessoa_juridica (
    id varchar(255) not null constraint pessoa_juridica_pkey primary key constraint fk_pessoa_id references public.pessoa,
    cnpj varchar(255) not null
);

--USUARIO
create table if not exists public.usuario (
    id varchar(255) not null constraint usuario_pkey primary key,
    username varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    tipo_pessoa char(2) not null,
    dt_ultimo_login timestamp
);

-- CARTEIRA
create table if not exists public.carteira (
    id serial not null constraint carteira_pkey primary key,
    usuario_id varchar(255) not null,
    saldo integer default 0
);