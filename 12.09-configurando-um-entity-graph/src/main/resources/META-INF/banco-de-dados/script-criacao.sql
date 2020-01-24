create table testando (id integer not null auto_increment, primary key (id)) engine=InnoDB;

create table produto_loja (id integer not null auto_increment, nome varchar(100), descricao longtext, preco decimal(19, 2), data_criacao datetime(6), data_ultima_atualizacao datetime(6), foto longblob, primary key (id)) engine=InnoDB;

create table ecm_produto (prd_id integer not null auto_increment, prd_nome varchar(100), prd_descricao longtext, prd_preco decimal(19, 2), prd_data_criacao datetime(6), prd_data_ultima_atualizacao datetime(6), prd_foto longblob, primary key (prd_id)) engine=InnoDB;

create table erp_produto (id integer not null auto_increment, nome varchar(100), descricao longtext, preco decimal(19, 2), primary key (id)) engine=InnoDB;

create table ecm_categoria (cat_id integer not null auto_increment, cat_nome varchar(100), cat_categoria_pai_id integer, primary key (cat_id)) engine=InnoDB;

create function acima_media_faturamento(valor double) returns boolean reads sql data return valor > (select avg(total) from pedido);

create procedure buscar_nome_produto(in produto_id int, out produto_nome varchar(255)) begin select nome into produto_nome from produto where id = produto_id; end

create procedure compraram_acima_media(in ano integer) begin select cli.*, clid.* from cliente cli join cliente_detalhe clid on clid.cliente_id = cli.id join pedido ped on ped.cliente_id = cli.id where ped.status = 'PAGO' and year(ped.data_criacao) = ano group by ped.cliente_id having sum(ped.total) >= (select avg(total_por_cliente.sum_total) from (select sum(ped2.total) sum_total from pedido ped2 where ped2.status = 'PAGO' and year(ped2.data_criacao) = ano group by ped2.cliente_id) as total_por_cliente); end

create procedure ajustar_preco_produto(in produto_id int, in percentual_ajuste double, out preco_ajustado double) begin declare produto_preco double; select preco into produto_preco from produto where id = produto_id; set preco_ajustado = produto_preco + (produto_preco * percentual_ajuste); update produto set preco = preco_ajustado where id = produto_id; end

create view view_clientes_acima_media as select cli.*, clid.* from cliente cli join cliente_detalhe clid on clid.cliente_id = cli.id join pedido ped on ped.cliente_id = cli.id where ped.status = 'PAGO' and year(ped.data_criacao) = year(current_date) group by ped.cliente_id having sum(ped.total) >= (select avg(total_por_cliente.sum_total) from (select sum(ped2.total) sum_total from pedido ped2 where ped2.status = 'PAGO' and year(ped2.data_criacao) = year(current_date) group by ped2.cliente_id) as total_por_cliente);
