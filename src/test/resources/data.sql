---------------------ACCOMMODATION TYPES---------------------
INSERT INTO accommodation_types (name, deleted) VALUES ('test', false);
---------------------TAGS---------------------
INSERT INTO tags (name, deleted) VALUES ('tag test', false);
---------------------ACCOMMODATION PUBLISHER---------------------
INSERT INTO accommodation_publishers (id, first_name, last_name, email, confirmed_email, password, phone_number, blocked) VALUES
                                     (nextval('users_id_seq'),'Mika','Mikic','mika2@gmail.com', true, 'password123','0664413445598', false);



INSERT INTO locations (address_name, longitude, latitude) VALUES ('test address', 1.1, 1.1);
INSERT INTO locations (address_name, longitude, latitude) VALUES ('test address 2', 1.1, 1.1);
INSERT INTO accommodations (name, description, bed_number, room_number, deleted, default_price, publisher_id, type_id, location_id) VALUES
                            ('test','opis test', 3, 3, false, 550, 1, 1, 1);

INSERT INTO accommodations (name, description, bed_number, room_number, deleted, default_price, publisher_id, type_id, location_id) VALUES
                            ('test 2','opis test 2', 4, 1, false, 1550, 1, 1, 2);


INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1000, '2024-01-01', '2024-02-02', 1, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1100, '2024-04-13', '2024-07-02', 1, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1200, '2024-08-01', '2024-08-02', 1, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1200, '2024-09-01', '2024-10-02', 1, true);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1300, '2024-09-01', '2024-09-22', 1, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1400, '2024-10-01', '2024-11-25', 1, false);

INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1000, '2024-01-01', '2024-02-02', 2, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1100, '2024-04-13', '2024-07-02', 2, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1200, '2024-08-01', '2024-08-02', 2, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1300, '2024-09-01', '2024-09-22', 2, false);
INSERT INTO prices (price, start_date, end_date, accommodation_id, deleted) VALUES (1400, '2024-10-01', '2024-12-25', 2, false);
