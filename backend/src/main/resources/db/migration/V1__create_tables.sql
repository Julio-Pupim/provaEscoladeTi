create table if not exists viagem(
id serial primary key,
nome varchar(50),
data_saida Date,
data_chegada Date,
valor numeric
);
create table if not exists destino(
  id serial primary key,
  nome varchar(50),
  viagem_id int8,
  constraint fk_viagem foreign key (viagem_id) references viagem(id)
);
