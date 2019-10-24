
CREATE TABLE uf(
  id bigserial NOT NULL,
  nome varchar(255) NULL,
  sigla varchar(255) NULL,
  data_hora timestamptz NULL,
  constraint uf_pk primary key (id)
);
