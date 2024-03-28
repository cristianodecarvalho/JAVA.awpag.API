CREATE TABLE public.t_cliente (
    id serial PRIMARY KEY,
    nome varchar(60) NOT NULL,
    email varchar(255) UNIQUE  NOT NULL,
    telefone varchar(20) NOT NULL
);