---------------------ACCOMMODATION TYPES---------------------
INSERT INTO accommodation_types (name, deleted) VALUES ('test', false);
---------------------TAGS---------------------
INSERT INTO tags (name, deleted) VALUES ('tag test', false);
---------------------ACCOMMODATION PUBLISHER---------------------
INSERT INTO accommodation_publishers (id, first_name, last_name, email, confirmed_email, password, phone_number, blocked) VALUES
                                     (nextval('users_id_seq'),'Mika','Mikic','mika@gmail.com', true, 'password123','066441345598', false);