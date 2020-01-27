-- Alterado/removido: auto_increment, longtext, datetime, longblob, double, procedure, year

create table testando (id integer not null, primary key (id));

create table produto_loja (id integer not null, nome varchar(100), descricao varchar(500), preco decimal(19, 2), data_criacao timestamp(6), data_ultima_atualizacao timestamp(6), foto bytea, primary key (id));

create table ecm_produto (prd_id integer not null, prd_nome varchar(100), prd_descricao varchar(500), prd_preco decimal(19, 2), prd_data_criacao timestamp(6), prd_data_ultima_atualizacao timestamp(6), prd_foto bytea, primary key (prd_id));

create table erp_produto (id integer not null, nome varchar(100), descricao varchar(500), preco decimal(19, 2), primary key (id));

create table ecm_categoria (cat_id integer not null, cat_nome varchar(100), cat_categoria_pai_id integer, primary key (cat_id));

create or replace function acima_media_faturamento(valor double precision) returns integer as $$ begin return (case (valor > (select avg(total) from pedido)) when true then 1 else 0 end); end; $$ language plpgsql;

create or replace function buscar_nome_produto(in produto_id int, out produto_nome varchar(255)) language plpgsql as $$ begin select nome into produto_nome from produto where id = produto_id; end; $$

create or replace function compraram_acima_media(in ano integer) returns table(id int, versao int, nome varchar, cpf varchar, sexo varchar, data_nascimento timestamp) as $$ begin return query select cli.id, cli.versao, cli.nome, cli.cpf, clid.sexo, clid.data_nascimento::timestamp without time zone from cliente cli join cliente_detalhe clid on clid.cliente_id = cli.id join pedido ped on ped.cliente_id = cli.id where ped.status = 'PAGO' and extract(year from ped.data_criacao) = ano group by ped.cliente_id, cli.id, clid.cliente_id having sum(ped.total) >= (select avg(total_por_cliente.sum_total) from (select sum(ped2.total) sum_total from pedido ped2 where ped2.status = 'PAGO' and extract(year from ped2.data_criacao) = ano group by ped2.cliente_id) as total_por_cliente); end; $$ language plpgsql;

create or replace function ajustar_preco_produto(in produto_id int, in percentual_ajuste double precision) returns numeric as $$ declare produto_preco numeric; preco_ajustado numeric; begin select preco from produto where id = produto_id into produto_preco; preco_ajustado := (produto_preco + (produto_preco * percentual_ajuste)); update produto set preco = preco_ajustado where id = produto_id; return preco_ajustado; end; $$ language plpgsql;

create view view_clientes_acima_media as select cli.*, clid.* from cliente cli join cliente_detalhe clid on clid.cliente_id = cli.id join pedido ped on ped.cliente_id = cli.id where ped.status = 'PAGO' and extract(year from ped.data_criacao) = extract(year from current_date) group by ped.cliente_id, cli.id, clid.cliente_id having sum(ped.total) >= (select avg(total_por_cliente.sum_total) from (select sum(ped2.total) sum_total from pedido ped2 where ped2.status = 'PAGO' and extract(year from ped2.data_criacao) = extract(year from current_date) group by ped2.cliente_id) as total_por_cliente);

alter sequence hibernate_sequence restart with 50;