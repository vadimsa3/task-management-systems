INSERT INTO public.users (user_id, first_name, last_name, email,
                          password, role, user_registration_date)
VALUES ('1cbfce9a-fa90-4598-a601-c327d793f3e5',
        'Иван',
        'Иванов',
        'ivanovTest@mail.ru',
        '$3a$10$B5XsEA3evlQpgX9z/Cg2JOMke5Pr/5XpCEIiQ4E1ZRTaNns1.LoNS',
        'USER',
        '2024-11-17 12:56:24.000000'
       );

INSERT INTO public.users (user_id, first_name, last_name, email,
                          password, role, user_registration_date)
VALUES ('d11d2c9e-698b-9873-ae96-8436d9ca215d',
        'Павел',
        'Павлов',
        'pavlovTest@mail.ru',
        '$2a$10$B5XsEA3evlQpgX9z/Cg2JOMke5Pr/5XpCEIiQ4E1ZRTaNns0.LoNS',
        'USER',
        '2024-10-02 18:01:11.000000'
       );

INSERT INTO public.users (user_id, first_name, last_name, email,
                          password, role, user_registration_date)
VALUES ('d13d2c9e-698b-9873-ae96-8436d9ca211d',
        'Елена',
        'Петрова',
        'lena@gmail.com',
        '$3r$12$B8XsEA3evlQpgX9z/Cg2JOMke5Pr/5XpCEIiQ4E1ZRTaNns0.LeNA',
        'ADMIN',
        '2024-11-21 11:04:15.000000'
       );

INSERT INTO public.tasks (task_id, title, description, status, priority, author_id, executor_id,
                          comment, task_create_date)
VALUES ('014366cb-c3d7-42bb-af0d-d5f9d6937bf7',
        'Разработка системы управления задачами',
        'Разработать простую систему управления задачами с использованием Java, Spring.',
        'PENDING',
        'HIGH',
        '1cbfce9a-fa90-4598-a601-c327d793f3e5',
        'd11d2c9e-698b-9873-ae96-8436d9ca215d',
        'Провести декомпозицию задачи',
        '2024-11-18 07:00:01.000000'
       );

INSERT INTO public.tasks (task_id, title, description, status, priority, author_id, executor_id,
                          comment, task_create_date)
VALUES ('012345cb-c3d7-42bb-af0d-d5f9d6937bf9',
        'Декомпозиция первоначальной задачи',
        'Провести декомпозицию полученной задачи - Разработка системы управления задачами',
        'PENDING',
        'HIGH',
        'd13d2c9e-698b-9873-ae96-8436d9ca211d',
        '1cbfce9a-fa90-4598-a601-c327d793f3e5',
        'Расппределить подзадачи',
        '2024-11-18 09:55:12.000000'
       );

INSERT INTO public.tasks (task_id, title, description, status, priority, author_id, executor_id,
                          comment, task_create_date)
VALUES ('013245cb-c3d7-42bb-af0d-d5f9d6937bf2',
        'Написание тестов',
        'Написать тесты',
        'PENDING',
        'HIGH',
        '1cbfce9a-fa90-4598-a601-c327d793f3e5',
        '1cbfce9a-fa90-4598-a601-c327d793f3e5',
        'Срочно выполнить',
        '2024-11-21 05:01:00.000000'
       );
