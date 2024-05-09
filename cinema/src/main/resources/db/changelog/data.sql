INSERT INTO room(id, room_name)
VALUES
    (1, 'IMAX');
INSERT INTO movie(id, movie_name, movie_description, movie_running_time, movie_thumbnail_url)
VALUES
    (1, 'Oppenheimer', 'The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.', '180 min', 'assets/images/oppenheimer.jpg'),
    (2, 'Barbie', 'Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.', '114 min', 'assets/images/barbie.jpg');
INSERT INTO seat(id, seat_row, number, room_id)
VALUES
    (1, 'A', 1, 1),
    (2, 'A', 2, 1),
    (3, 'A', 3, 1),
    (4, 'A', 4, 1),
    (5, 'B', 1, 1),
    (6, 'B', 2, 1),
    (7, 'B', 3, 1),
    (8, 'B', 4, 1);

INSERT INTO seance(id, movie_id, room_id, date_of_seance)
VALUES
    (1, 1, 1, '2023-04-26 15:00:00'),
    (2, 1, 1, '2023-04-26 17:00:00'),
    (3, 2, 1, '2023-04-26 20:00:00'),
    (4, 2, 1, '2023-04-26 21:00:00');

INSERT INTO available_seats(id, seance_id, seat_id, seat_status, price)
VALUES
    (1, 1, 1, 'RESERVED', 20.5),
    (2, 1, 2, 'EMPTY', 20.5),
    (3, 1, 3, 'EMPTY', 20.5),
    (4, 1, 4, 'VALIDATED', 20.5),
    (5, 1, 5, 'RESERVED', 24),
    (6, 1, 6, 'EMPTY', 24),
    (7, 1, 7, 'EMPTY', 24),
    (8, 1, 8, 'EMPTY', 24),

    (9, 2, 1, 'EMPTY', 20.5),
    (10, 2, 2, 'EMPTY', 20.5),
    (11, 2, 3, 'EMPTY', 20.5),
    (12, 2, 4, 'EMPTY', 20.5),
    (13, 2, 5, 'EMPTY', 24),
    (14, 2, 6, 'EMPTY', 24),
    (15, 2, 7, 'EMPTY', 24),
    (16, 2, 8, 'EMPTY', 24);

INSERT INTO reservation(id, total_price, person_data, ticket)
VALUES
    (1, 20.5, 'WeronikaZ', ''),
    (2, 41.0, 'PatrycjaK', '');

INSERT INTO user_cinema_attendant(id, username, password)
VALUES
    (1, 'marek', '123');

