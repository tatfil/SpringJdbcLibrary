INSERT INTO authors (id, name)  VALUES (1, 'Mark Twain');
INSERT INTO authors (id, name)  VALUES (2, 'Rudyard Kipling');
INSERT INTO authors (id, name)  VALUES (3, 'Michael Bond');

INSERT INTO books (id, isbn, title, barcode, borrowed, status)  VALUES (1, 12234568, 'The Adventures of Tom Sawyer', 11111, '20220110', 'NOT AVAILABLE');
INSERT INTO books (id, isbn, title, barcode, borrowed, status)  VALUES (2, 45678412, 'Jungle Book', 22222, '20220110', 'AVAILABLE');
INSERT INTO books (id, isbn, title, barcode, borrowed, status)  VALUES (3, 56485123, 'Paddington''s Day Off', 33333, '20220110', 'NOT AVAILABLE');

INSERT INTO authors_books (author_id, book_id)  VALUES (1, 1);
INSERT INTO authors_books (author_id, book_id)  VALUES (2, 2);
INSERT INTO authors_books (author_id, book_id)  VALUES (3, 3);

INSERT INTO patrons (id, name, address)  VALUES (1, 'Aaaa Aaa', 'NY 10007, New York, Broadway 195');
INSERT INTO patrons (id, name, address)  VALUES (2, 'Bbbb Bbb', 'NY 10007, New York, Broadway 197');
INSERT INTO patrons (id, name, address)  VALUES (3, 'Cccc Ccc', 'NY 10007, New York, Broadway 199');
INSERT INTO patrons (id, name, address)  VALUES (4, 'Dddd Ddd', 'NY 10007, New York, Broadway 201');

INSERT INTO accounts (id, patron_id, state)  VALUES (1, 1, 'ACTIVE');
INSERT INTO accounts (id, patron_id, state)  VALUES (2, 2, 'ACTIVE');
INSERT INTO accounts (id, patron_id, state)  VALUES (3, 3, 'FROZEN');
INSERT INTO accounts (id, patron_id, state)  VALUES (4, 4, 'ACTIVE');

INSERT INTO accounts_books (account_id, book_id)  VALUES (1, 1);
INSERT INTO accounts_books (account_id, book_id)  VALUES (2, 2);
INSERT INTO accounts_books (account_id, book_id)  VALUES (3, 3);
INSERT INTO accounts_books (account_id, book_id)  VALUES (4, 3);