create table day
(
    id           serial           not null
        constraint day_pk
            primary key,
    date         date             not null,
    min_temp     double precision not null,
    max_temp     double precision not null,
    min_hum      double precision not null,
    max_hum      double precision not null,
    min_pressure double precision not null,
    max_pressure double precision not null
);

alter table day
    owner to postgres;

create unique index day_date_uindex
    on day (date);

create unique index day_id_uindex
    on day (id);

create table weather_data
(
    id          serial           not null
        constraint weather_data_pk
            primary key,
    date        timestamp        not null,
    temperature double precision not null,
    humidity    double precision not null,
    pressure    double precision not null,
    day         integer
        constraint weather_data_day_id_fk
            references day
            on delete cascade
);

alter table weather_data
    owner to postgres;

create unique index weather_data_id_uindex
    on weather_data (id);
