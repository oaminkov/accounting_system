insert into language (id, name)
    values (1, 'Русский'),
           (2, 'Украинский'),
           (3, 'Английский');

insert into related_project (id, name, abbreviation, type)
    values (1, 'Проект1', 'Проект1', 'Проект'),
           (2, 'Программа1', 'Программа1', 'Программа');

insert into country (id, name)
    values (1, 'Россия'),
           (2, 'Украина'),
           (3, 'Великобритания');

insert into organization (id, name, abbreviation, id_country)
    values (1, 'Организация1', 'О1', 1),
           (2, 'Организация2', 'О2', 2),
           (3, 'Организация3', 'О3', 3);

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