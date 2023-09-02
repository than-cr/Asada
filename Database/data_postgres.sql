INSERT INTO type (id, name, "group") VALUES
                                         (1, 'Activo', 'user status'),
                                         (2, 'Inactivo', 'user status'),
                                         (3, 'Activo', 'lot status'),
                                         (4, 'Inactivo', 'lot status'),
                                         (5, 'Suspendido', 'lot status')
;

INSERT INTO type_seq (next_val) VALUES (6);

INSERT INTO role (id, name, status) VALUES
                                        (1, 'Admin', 1),
                                        (2, 'Junta Directiva', 1),
                                        (3, 'Usuario', 1);

INSERT INTO role_seq (next_val) VALUES (4);

INSERT INTO privilege (id, name, "group") VALUES
(1, 'View home', 'Home')
                                               ,(2, 'View users', 'Users')
                                               ,(3, 'Add user', 'Users')
                                               ,(4, 'Edit user', 'Users')
                                               ,(5, 'Delete user', 'Users')
                                               ,(6, 'View lots', 'Lot')
                                               ,(7, 'Add lot', 'Lot')
                                               ,(8, 'Edit lot', 'Lot')
                                               ,(9, 'Delete lot', 'Lot')
                                               ,(10, 'View roles', 'Roles')
                                               ,(11, 'Add role', 'Roles')
                                               ,(12, 'Edit role', 'Roles')
                                               ,(13, 'Delete role', 'Roles')
                                               ,(14, 'View receipts', 'Receipts')
                                               ,(15, 'Add receipt', 'Receipts')
                                               ,(16, 'Edit receipt', 'Receipts')
                                               ,(17, 'Delete receipt', 'Receipts')
                                               ,(18, 'View all receipts', 'Receipts');


INSERT INTO user (id, username, email, first_name, last_name, mother_last_name, password, phone_number, status) VALUES
    (1, '116480417', 'than.cr@outlook.com', 'Jonathan', 'Castro', 'Ramírez', '$2a$11$c5SsTu/lHgsLJFMs4oqYouzqTWgNimYDuQbRmJq/lLNAg1Z33.S1.', '89945051', 1);

INSERT INTO user_seq (next_val) VALUES (2);

INSERT INTO users_roles (role_id, user_id) VALUES (1, 1);

INSERT INTO lot_seq (next_val) VALUES (1);
INSERT INTO lot_receipt_seq (next_val) VALUES (1);

INSERT INTO roles_privileges (role_id, privilege_id) VALUES
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

INSERT INTO roles_privileges (role_id, privilege_id) VALUES
(2, 1)
                                                          ,(2, 2)
                                                          ,(2, 3)
                                                          ,(2, 4)
                                                          ,(2, 5)
                                                          ,(2, 6)
                                                          ,(2, 7)
                                                          ,(2, 8)
                                                          ,(2, 9)
                                                          ,(2, 10)
                                                          ,(2, 11)
                                                          ,(2, 12)
                                                          ,(2, 13)
                                                          ,(2, 14)
                                                          ,(2, 15)
                                                          ,(2, 16)
                                                          ,(2, 17)
                                                          ,(2, 18);

INSERT INTO roles_privileges (role_id, privilege_id) VALUES
(3, 1)
                                                          ,(3, 14);