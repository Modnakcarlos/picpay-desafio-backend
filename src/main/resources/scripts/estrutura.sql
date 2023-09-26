create schema if not exists public;

-- PESSOA
create table if not exists public.pessoa (
    id varchar(255) not null constraint pessoa_pkey primary key,
    name varchar(255) not null,
    usuario varchar(255) not null
);

-- PESSOA FISICA
create table if not exists public.pessoa_fisica (
    id varchar(255) not null constraint pessoa_fisica_pkey primary key constraint fk_pessoa_id references public.pessoa,
    cpf varchar(11) -- null
);

-- PESSOA JURIDICA
create table if not exists public.pessoa_juridica (
    id varchar(255) not null constraint pessoa_juridica_pkey primary key constraint fk_pessoa_id references public.pessoa,
    cnpj varchar(255) not null
);

--CONTATOS
create table if not exists public.contato (
    id serial not null constraint contato_pkey primary key,
    tipo_contato varchar(30) not null,
    value varchar(100) not null,
    pessoa_id varchar(255) not null constraint fk_pessoa_id references public.pessoa
);


create table if not exists public.social_links (
    id          serial not null constraint social_links_pkey primary key,
    pessoa_id   varchar(255) not null constraint fk_pessoa_id references public.pessoa,
    nome        varchar(255),
    link        varchar(255)
);

create table if not exists public.endereco (
    id serial not null constraint endereco_pkey primary key,
    pessoa_id varchar(255) not null constraint fk_pessoa_id references public.pessoa,
    bairro          varchar(255),
    complemento     varchar(255),
    logradouro      varchar(255),
    numero          varchar(255),
    cep             varchar(255),
    cidade          varchar(255),
    uf              varchar(2),
    pais             varchar(100)
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
    saldo double precision default 0
);

-- CARTAO
create table if not exists public.cartao (
  id serial not null constraint pkey_cartao primary key,
  bandeira varchar(30) not null,
  nome_titular varchar(100) not null,
  numero char(19) not null,
  validade timestamp not null,
  codigo_seguranca char(3) not null,
  carteira_id integer not null
);

--TRANSACAO
create table if not exists public.transacao (
  id serial not null constraint pkey_transacao primary key,
  pagador varchar(100) not null,
  recebedor varchar(100) not null,
  valor double precision not null
);