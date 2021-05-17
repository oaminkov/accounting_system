create sequence hibernate_sequence start 1 increment 1;

create sequence usr_seq start 1 increment 10;
create sequence resource_type_seq start 1 increment 10;
create sequence language_seq start 1 increment 10;
create sequence project_type_seq start 1 increment 10;
create sequence related_project_seq start 1 increment 10;
create sequence observation_method_seq start 1 increment 10;
create sequence country_seq start 1 increment 10;
create sequence organization_seq start 1 increment 10;
create sequence observation_discipline_seq start 1 increment 10;
create sequence observation_type_seq start 1 increment 10;
create sequence observation_parameter_seq start 1 increment 10;
create sequence observation_scope_seq start 1 increment 10;
create sequence observation_territory_seq start 1 increment 10;

create sequence information_resource_seq start 1 increment 50;
create sequence uploaded_file_seq start 1 increment 50;

create table usr (
    id int8 not null,
    username varchar(255) not null,
    password varchar(255) not null,
    active boolean not null,
    primary key (id)
);

create table user_role (
    id_usr int8 not null,
    roles varchar(255)
);

create table resource_type (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table language (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table project_type (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table related_project (
    id int8 not null,
    abbreviation varchar(255) not null,
    abbreviation_rus varchar(255) not null,
    name varchar(255) not null,
    name_rus varchar(255) not null,
    id_project_type int8 not null,
    primary key (id)
);

create table observation_method (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table country (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table organization (
    id int8 not null,
    name varchar(255),
    name_rus varchar(255),
    abbreviation varchar(255) not null,
    id_country int8,
    primary key (id)
);

create table observation_discipline (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table observation_type (
    id int8 not null,
    name varchar(255) not null,
    id_observation_discipline int8 not null,
    primary key (id)
);

create table observation_parameter (
    id int8 not null,
    name varchar(255) not null,
    id_observation_type int8,
    primary key (id)
);

create table observation_scope (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table observation_territory (
    id int8 not null,
    name varchar(255) not null,
    primary key (id)
);

create table information_resource (
    id int8 not null,
    inventory_number varchar(255) not null,
    fullname_cdrom varchar(255) not null,
    abbreviation_cdrom varchar(255) not null,
    date_observation_start varchar(255) not null,
    date_observation_end varchar(255) not null,
    brief_content varchar(2048) not null,
    volume varchar(255) not null,
    received_date varchar(255) not null,
    duplicate boolean not null,
    id_resource_type int8 not null,
    id_language int8 not null,
    id_related_project int8 not null,
    id_observation_method int8 not null,
    id_country int8 not null,
    id_main_organization int8 not null,
    date_of_entering varchar(255) not null,
    id_operator int8 not null,
    date_of_edit varchar(255),
    id_editor int8,
    primary key (id)
);

create table uploaded_file (
    id int8 not null,
    name varchar(255) not null,
    path varchar(255) not null,
    id_information_resource int8,
    primary key (id)
);

--Many2Many tables
create table infres_organization (
    id_information_resource int8 not null,
    id_organization int8 not null,
    primary key (id_information_resource, id_organization)
);

create table infres_observdiscipl (
    id_information_resource int8 not null,
    id_observation_discipline int8 not null,
    primary key (id_information_resource, id_observation_discipline)
);

create table infres_observtype (
    id_information_resource int8 not null,
    id_observation_type int8 not null,
    primary key (id_information_resource, id_observation_type)
);

create table infres_observparam (
    id_information_resource int8 not null,
    id_observation_parameter int8 not null,
    primary key (id_information_resource, id_observation_parameter)
);

create table infres_observscope (
    id_information_resource int8 not null,
    id_observation_scope int8 not null,
    primary key (id_information_resource, id_observation_scope)
);

create table infres_observterritory (
    id_information_resource int8 not null,
    id_observation_territory int8 not null,
    primary key (id_information_resource, id_observation_territory)
);

--Unique keys
alter table usr add constraint usr__username_uk unique (username);
alter table resource_type add constraint resource_type__name_uk unique (name);
alter table language add constraint language__name_uk unique (name);
alter table observation_method add constraint observ_method__name_uk unique (name);
alter table country add constraint country__name_uk unique (name);
alter table organization add constraint organization__abbreviation_uk unique (abbreviation);
alter table observation_discipline add constraint observ_discipline__name_uk unique (name);
alter table observation_type add constraint observ_type__name_uk unique (name);
alter table observation_parameter add constraint observ_parameter__name_uk unique (name);
alter table observation_scope add constraint observ_scope__name_uk unique (name);
alter table observation_territory add constraint observ_territory__name_uk unique (name);
alter table information_resource add constraint inf_res__inventory_number_uk unique (inventory_number);
alter table uploaded_file add constraint uploaded_file__path_uk unique (path);

--Foreign keys
alter table information_resource add constraint inf_res__resource_type_fk foreign key (id_resource_type) references resource_type;
alter table information_resource add constraint inf_res__language_fk foreign key (id_language) references language;
alter table information_resource add constraint inf_res__related_project_fk foreign key (id_related_project) references related_project;
alter table information_resource add constraint inf_res__observ_method_fk foreign key (id_observation_method) references observation_method;
alter table information_resource add constraint inf_res__country_fk foreign key (id_country) references country;
alter table information_resource add constraint inf_res__organization_fk foreign key (id_main_organization) references organization;
alter table information_resource add constraint inf_res__operator_fk foreign key (id_operator) references usr;
alter table information_resource add constraint inf_res__editor_fk foreign key (id_editor) references usr;

alter table user_role add constraint user_role__usr_fk foreign key (id_usr) references usr
    ON DELETE CASCADE ON UPDATE CASCADE;
alter table related_project add constraint related_project__project_type_fk foreign key (id_project_type) references project_type
    ON DELETE CASCADE ON UPDATE CASCADE;
alter table organization add constraint organization__country_fk foreign key (id_country) references country
    ON DELETE CASCADE ON UPDATE CASCADE;
alter table observation_type add constraint observ_type__observ_discipline_fk foreign key (id_observation_discipline) references observation_discipline
    ON DELETE CASCADE ON UPDATE CASCADE;
alter table observation_parameter add constraint observ_parameter__observ_type_fk foreign key (id_observation_type) references observation_type
    ON DELETE CASCADE ON UPDATE CASCADE;
alter table uploaded_file add constraint uploaded_file__inf_res_fk foreign key (id_information_resource) references information_resource
    ON DELETE CASCADE ON UPDATE CASCADE;

--Many2Many foreign keys
alter table infres_organization add constraint infres_organization__inf_res_fk foreign key (id_information_resource) references information_resource;
alter table infres_organization add constraint infres_organization__organization_fk foreign key (id_organization) references organization;

alter table infres_observdiscipl add constraint infres_observdiscipl__inf_res_fk foreign key (id_information_resource) references information_resource;
alter table infres_observdiscipl add constraint infres_observdiscipl__observ_discipline_fk foreign key (id_observation_discipline) references observation_discipline;

alter table infres_observtype add constraint infres_observtype__inf_res_fk foreign key (id_information_resource) references information_resource;
alter table infres_observtype add constraint infres_observtype__observ_type_fk foreign key (id_observation_type) references observation_type;

alter table infres_observparam add constraint infres_observparam__inf_res_fk foreign key (id_information_resource) references information_resource;
alter table infres_observparam add constraint infres_observparam__observ_parameter_fk foreign key (id_observation_parameter) references observation_parameter;

alter table infres_observscope add constraint infres_observscope__inf_res_fk foreign key (id_information_resource) references information_resource;
alter table infres_observscope add constraint infres_observscope__observ_scope_fk foreign key (id_observation_scope) references observation_scope;

alter table infres_observterritory add constraint infres_observterritory__inf_res_fk foreign key (id_information_resource) references information_resource;
alter table infres_observterritory add constraint infres_observterritory__observ_territory_fk foreign key (id_observation_territory) references observation_territory;