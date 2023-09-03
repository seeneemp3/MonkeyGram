--user insertions
INSERT INTO public.monkey_user (id, username, nickname)
VALUES ('tom', 'Thomas C. Andersun', 'neonrider');
INSERT INTO public.monkey_user (id, username, nickname)
VALUES ('grumpy', 'Oogha Boogha', 'sca_a_a_ry');
INSERT INTO public.monkey_user (id, username, nickname)
VALUES ('harambe', 'Basileus Felis F.', 'under_wood');

--post insertions
INSERT INTO public.monkey_post (author_id, description, photo_url, creation_date) VALUES ('tom', 'я ', 'file:///storage/monkeygram/tom/1/image.png', CURRENT_DATE);
INSERT INTO public.monkey_post (author_id, description, photo_url, creation_date) VALUES ('tom', 'первый пост)', 'file:///storage/monkeygram/tom/2/image.png', CURRENT_DATE);
INSERT INTO public.monkey_post (author_id, description, photo_url, creation_date) VALUES ('grumpy', 'ВсЕм ПрИвЕт!!!111', 'file:///storage/monkeygram/grumpy/1/image.png', CURRENT_DATE);

--follower insertion
INSERT INTO monkey_follow (author_id, follower_id) VALUES('grumpy', 'tom');
INSERT INTO public.monkey_post (author_id, description, photo_url, creation_date) VALUES ('grumpy', 'Мой новый пост!!111', 'file:///storage/monkeygram/grumpy/2/image.png', CURRENT_DATE);