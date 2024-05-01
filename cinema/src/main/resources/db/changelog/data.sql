INSERT INTO room(id, room_name)
VALUES
    (1, 'Sala 1'),
    (2, 'Sala 2'),
    (3, 'IMAX');
INSERT INTO movie(id, movie_name, movie_description, movie_running_time, movie_thumbnail_url)
VALUES
    (1, 'Oppenheimer', 'The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.', '180 min', 'null'),
    (2, 'Barbie', 'Barbie and Ken are having the time of their lives in the colorful and seemingly perfect world of Barbie Land. However, when they get a chance to go to the real world, they soon discover the joys and perils of living among humans.', '114 min', 'null');
INSERT INTO seat(id, seat_row, number, room_id)
VALUES
    (1, 'A', 1, 1),
    (2, 'A', 2, 1),
    (3, 'A', 3, 1),
    (4, 'A', 4, 1),
    (5, 'B', 1, 1);
INSERT INTO seance(id, movie_id, room_id, date_of_seance)
VALUES
    (1, 1, 1, '2023-04-26 15:00:00'),
    (2, 1, 2, '2023-04-26 17:00:00'),
    (3, 2, 1, '2023-04-26 20:00:00'),
    (4, 2, 2, '2023-04-26 21:00:00');
INSERT INTO available_seats(id, seance_id, seat_id, reserved, ticket, price)
VALUES
    (1, 1, 1, false, '', 20.5),
    (2, 1, 2, false, '', 20.5),
    (3, 1, 3, false, '', 20.5),
    (4, 1, 4, false, '', 20.5),
    (5, 1, 5, false, '', 20.5);

