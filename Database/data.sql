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

INSERT INTO Asada.privilege (id, name, `group`) VALUES
(1, 'View home', 'Home')
,(2, 'View payments', 'Payments')
,(3, 'View users', 'Users')
,(4, 'Add user', 'Users')
,(5, 'Edit user', 'Users')
,(6, 'Delete user', 'Users')
,(7, 'View lots', 'Lot')
,(8, 'Add lot', 'Lot')
,(9, 'Edit lot', 'Lot')
,(10, 'Delete lot', 'Lot')
,(11, 'View roles', 'Roles')
,(12, 'Add role', 'Roles')
,(13, 'Edit role', 'Roles')
,(14, 'Delete role', 'Roles')
,(15, 'View receipts', 'Receipts')
,(16, 'Add receipt', 'Receipts')
,(17, 'Edit receipt', 'Receipts')
,(18, 'Delete receipt', 'Receipts');

INSERT INTO Asada.user (id, username, email, first_name, last_name, mother_last_name, password, phone_number, status) VALUES
(1, '116480417', 'than.cr@outlook.com', 'Jonathan', 'Castro', 'Ramírez', '$2a$11$c5SsTu/lHgsLJFMs4oqYouzqTWgNimYDuQbRmJq/lLNAg1Z33.S1.', '89945051', 1);

INSERT INTO Asada.user_seq (next_val) VALUES (2);

INSERT INTO Asada.users_roles (role_id, user_id) VALUES (1, 1);

INSERT INTO Asada.lot_seq (next_val) VALUES (1);
INSERT INTO Asada.lot_receipt_seq (next_val) VALUES (1);

INSERT INTO Asada.roles_privileges (role_id, privilege_id) VALUES
(1, 1)
,(1, 2)
,(1, 3)
,(1, 4)
,(1, 5)
,(1, 6)
,(1, 7)
,(1, 8)
,(1, 9)
,(1, 10)
,(1, 11)
,(1, 12)
,(1, 13)
,(1, 14)
,(1, 15)
,(1, 16)
,(1, 17)
,(1, 18);

