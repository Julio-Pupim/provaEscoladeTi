create table if not exists viagem(
id int8 primary key,
nome varchar(50),
data_saida Date,
data_chegada Date,
valor numeric
);
create table if not exists destino(
  id int8 primary key,
  nome varchar(50),
  id_viagem int8,
  constraint fk_viagem foreign key (id_viagem) references viagem(id)
);
