create table testando (id integer not null auto_increment, primary key (id)) engine=InnoDB;

create function acima_media_faturamento(valor double) returns boolean reads sql data return valor > (select avg(total) from pedido);
