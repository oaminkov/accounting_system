insert into related_project (id, name, abbreviation, type)
    values (1, 'Проект1', 'Проект1', 'Проект'),
           (2, 'Программа1', 'Программа1', 'Программа');

insert into language (id, name)
    values (1, 'Английский'), (2, 'Греческий'), (3, 'Испанский'), (4, 'Китайский'), (5, 'Немецкий'),
           (6, 'Русский'), (7, 'Французский'), (8, 'Чешский'), (9, 'Японский');

insert into country (id, name)
    values (1, 'Австралия'), (2, 'Австрия'), (3, 'Азербайджан'), (4, 'Албания'), (5, 'Алжир'),
           (6, 'Ангола'), (7, 'Антигуа И Барбуда'), (8, 'Аомынь (Макао)'), (9, 'Арабская Республика Египет'), (10, 'Аргентина'),
           (11, 'Армения'), (12, 'Афганистан'), (13, 'Багамские Острова'), (14, 'Бангладеш'), (15, 'Барбадос'),
           (16, 'Бахрейн'), (17, 'Беларусь'), (18, 'Белиз'), (19, 'Бельгия'), (20, 'Бенин (Дагомея)'),
           (21, 'Берег Слоновой Кости (Кабо-Верде)'), (22, 'Бирма'), (23, 'Болгария'), (24, 'Боливия'), (25, 'Ботсвана'),
           (26, 'Бразилия'), (27, 'Бруней'), (28, 'Буркина-Фасо'), (29, 'Бурунди'), (30, 'Вануату, Республика'),
           (31, 'Великобритания'), (32, 'Венгрия'), (33, 'Венесуэла'), (34, 'Вьетнам'), (35, 'Габон'),
           (36, 'Гаити'), (37, 'Гайна'), (38, 'Гамбия'), (39, 'Гана'), (40, 'Гватемала'),
           (41, 'Гвиана, Французская'), (42, 'Гвинея'), (43, 'Гвинея, Экваториальная'),
           (0, '');

insert into organization (id, abbreviation, name_rus, name_eng, id_country)
    values (1, 'Моск.ГМБ', 'АНО "Московское ГМБ"', '', 44),
           (2, 'AORI', 'Институт исследования атмосферы и океана Токио', 'Atmosphere and Ocean Research Institute of the University of Tokyo', 44);

insert into observation_method (id, name)
    values (1, 'Замеры'),
           (2, 'Наблюдение');

insert into observation_discipline (id, name)
    values (1, 'Дисциплина 1'),
           (2, 'Дисциплина 2');

insert into observation_type (id, name, id_observation_discipline)
    values (1, 'Тип11', 1),
           (2, 'Тип12', 1),
           (3, 'Тип21', 2),
           (4, 'Тип22', 2);

insert into observation_parameter (id, name, id_observation_type)
    values (1, 'Параметр111', 1),
           (2, 'Параметр112', 1),
           (3, 'Параметр121', 2),
           (4, 'Параметр122', 2),
           (5, 'Параметр211', 3),
           (6, 'Параметр212', 3),
           (7, 'Параметр221', 4),
           (8, 'Параметр222', 4);

insert into observation_scope (id, name)
    values (1, 'Сфера1'),
           (2, 'Сфера2');

insert into observation_territory (id, name)
    values (1, 'Территория1'),
           (2, 'Территория2');

select setval('related_project_seq', (select max(id) FROM related_project));
select setval('language_seq', (select max(id) FROM language));
select setval('country_seq', (select max(id) FROM country));
select setval('organization_seq', (select max(id) FROM organization));
select setval('observation_method_seq', (select max(id) FROM observation_method));
select setval('observation_discipline_seq', (select max(id) FROM observation_discipline));
select setval('observation_type_seq', (select max(id) FROM observation_type));
select setval('observation_parameter_seq', (select max(id) FROM observation_parameter));
select setval('observation_scope_seq', (select max(id) FROM observation_scope));
select setval('observation_territory_seq', (select max(id) FROM observation_territory));