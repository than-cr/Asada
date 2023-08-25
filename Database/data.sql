INSERT INTO Asada.type (id, name, `group`) VALUES
(1, 'Activo', 'user status'),
(2, 'Inactivo', 'user status'),
(3, 'Activo', 'lot status'),
(4, 'Inactivo', 'lot status'),
(5, 'Suspendido', 'lot status')
;

INSERT INTO Asada.type_seq (next_val) VALUES (6);

INSERT INTO Asada.role (id, name, status) VALUES
(1, 'Admin', 1),
(2, 'Staff', 1),
(3, 'User', 1);

INSERT INTO Asada.role_seq (next_val) VALUES (4);

INSERT INTO Asada.user (id, username, email, first_name, last_name, mother_last_name, password, phone_number, status) VALUES
(1, '116480417', 'than.cr@outlook.com', 'Jonathan', 'Castro', 'Ramírez', '$2a$11$c5SsTu/lHgsLJFMs4oqYouzqTWgNimYDuQbRmJq/lLNAg1Z33.S1.', '89945051', 1);

INSERT INTO Asada.user_seq (next_val) VALUES (2);

INSERT INTO Asada.users_roles (role_id, user_id) VALUES (1, 1);

INSERT INTO Asada.lot_seq (next_val) VALUES (1);
INSERT INTO Asada.lot_receipt_seq (next_val) VALUES (1);