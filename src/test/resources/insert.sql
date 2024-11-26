INSERT INTO public.user_profile (user_id, first_name, last_name, middle_name, birth_date, profile_reg_date)
VALUES ('4cbfce8a-fa90-4598-a601-c327d793f3e5', 'Victor', 'Ivanov', 'Aleksevich', '2000-06-13', '2024-06-13 12:56:24.000000');

INSERT INTO user_credentials (user_id, password, email, mobile_phone)
VALUES ('4cbfce8a-fa90-4598-a601-c327d793f3e5', '999999999', 'test@mail.ru', '9999999999');

INSERT INTO verification (verification_id, email, last_verification_code, last_code_expiration, next_attempt_time, wrong_attempts, sent_counter)
VALUES ('014366cb-c3d7-42bb-af0d-d5f9d6937bf7', 'test@mail.ru', '123456', '2024-06-13 13:00:00.000000', '2024-06-13 13:01:00.000000', '0', '0');

INSERT INTO public.identity_document (user_id, passport_number, issuer, issuer_code, issue_date, citizenship)
VALUES ('4cbfce8a-fa90-4598-a601-c327d793f3e5', 'passport_number', 'ГУ МВД России по Московской области', ' 300-200', '2015-01-01', 'Россия');

INSERT INTO public.registration_address (user_id, region, city, district, street, house, building, flat)
VALUES ('4cbfce8a-fa90-4598-a601-c327d793f3e5', 'Московская область', 'село Абрамцево', 'Сергиево-Посадский район','Ленина', '54','Б', '2');

INSERT INTO public.authorities (authority_id, authority)
VALUES ('d43da2c7-600b-4363-ae96-9436d3ca215d','Клиент банка');

INSERT INTO public.user_authorities (user_authority_id, authority_id, user_id)
VALUES ('d31d2c9e-698b-9873-ae96-8436d9ca215d','d43da2c7-600b-4363-ae96-9436d3ca215d','4cbfce8a-fa90-4598-a601-c327d793f3e5');

INSERT INTO public.customer_safety (user_id, security_question, security_answer)
VALUES ('4cbfce8a-fa90-4598-a601-c327d793f3e5',' Девичья фамилия матери?','Сидорова');

INSERT INTO public.status (user_id, status, update_date)
VALUES ('4cbfce8a-fa90-4598-a601-c327d793f3e5','ACTIVE', '2024-06-13 12:56:24.000000');

