CREATE TABLE client (
     client_id serial NOT NULL,
     first_name varchar(100) NOT NULL,
     last_name  varchar(200) NOT NULL,
     CONSTRAINT client_pkey PRIMARY KEY (client_id)
);