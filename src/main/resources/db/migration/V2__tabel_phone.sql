CREATE TABLE phone (
    phone_id serial NOT NULL,
    client_id  integer NOT NULL,
    phone_number varchar(15) NOT NULL,
    CONSTRAINT phone_pkey PRIMARY KEY (phone_id),
    CONSTRAINT phone_fkey FOREIGN KEY (client_id)
        REFERENCES client (client_id) MATCH SIMPLE,
    CONSTRAINT phone_unique UNIQUE (client_id, phone_number)
);