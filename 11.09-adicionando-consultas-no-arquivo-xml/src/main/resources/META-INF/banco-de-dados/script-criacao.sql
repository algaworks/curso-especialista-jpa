create table testando (id integer not null auto_increment, primary key (id)) engine=InnoDB;

create table produto_loja (id integer not null auto_increment, nome varchar(100), descricao longtext, preco decimal(19, 2), data_criacao datetime(6), data_ultima_atualizacao datetime(6), foto longblob, primary key (id)) engine=InnoDB;

create table ecm_produto (prd_id integer not null auto_increment, prd_nome varchar(100), prd_descricao longtext, prd_preco decimal(19, 2), prd_data_criacao datetime(6), prd_data_ultima_atualizacao datetime(6), prd_foto longblob, primary key (prd_id)) engine=InnoDB;

create table erp_produto (id integer not null auto_increment, nome varchar(100), descricao longtext, preco decimal(19, 2), primary key (id)) engine=InnoDB;

create table ecm_categoria (cat_id integer not null auto_increment, cat_nome varchar(100), cat_categoria_pai_id integer, primary key (cat_id)) engine=InnoDB;

create function acima_media_faturamento(valor double) returns boolean reads sql data return valor > (select avg(total) from pedido);
