create table salgados{
	id bigint auto_increment not null,
	valor decimal(10,2) not null,
	nome varchar(80) not null,
	quantidade bigint not null,
	
	primary key (id)
};