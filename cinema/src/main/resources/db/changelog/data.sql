INSERT INTO room(id, room_name)
VALUES
    (1, 'Sala 1'),
    (2, 'Sala 2'),
    (3, 'IMAX');
INSERT INTO movie(id, movie_name)
VALUES
    (1, 'Openheimer'),
    (2, 'Barbie');
INSERT INTO seat(id, seat_row, number, room_id)
VALUES
    (1, 'A', 1, 1),
    (2, 'A', 2, 1),
    (3, 'A', 3, 1),
    (4, 'A', 4, 1),
    (5, 'B', 1, 1);
INSERT INTO seance(id, movie_id, room_id, date_of_seance)
VALUES
    (1, 1, 1, '2023-04-26 15:00:00');
INSERT INTO available_seats(id, seance_id, seat_id, reserved, ticket, price)
VALUES
    (1, 1, 1, false, '', 20.5),
    (2, 1, 2, false, '', 20.5),
    (3, 1, 3, false, '', 20.5),
    (4, 1, 4, false, '', 20.5),
    (5, 1, 5, false, '', 20.5);

