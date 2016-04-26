drop table PARTICIPER;

drop table REGATE;

drop table SKIPPER;

drop table VOILIER;

create table REGATE (
NUMREGATE            BIGINT                         not null,
NOMREGATE            VARCHAR(255)                   not null,
DISTANCE             DOUBLE PRECISION               not null,
"DATE"               DATE                           not null,
primary key (NUMREGATE)
);

create table VOILIER (
NUMVOIL              BIGINT                         not null,
NOMVOIL              VARCHAR(255)                   not null,
CLASSE               SMALLINT                       not null,
RATING               BIGINT                         not null,
primary key (NUMVOIL)
);

create table SKIPPER (
NUMSKIP              BIGINT                         not null,
NOMSKIP              VARCHAR(255)                   not null,
PRENOMSKIP           VARCHAR(255)                   not null,
primary key (NUMSKIP)
);

create table PARTICIPER (
TMPSREEL             DATE                           not null,
NUMREGATE            BIGINT                         not null,
NUMVOIL              BIGINT                         not null,
NUMSKIP              BIGINT                         not null,
primary key (TMPSREEL, NUMREGATE, NUMVOIL),
foreign key (NUMREGATE)
      references REGATE (NUMREGATE),
foreign key (NUMVOIL)
      references VOILIER (NUMVOIL),
foreign key (NUMSKIP)
      references SKIPPER (NUMSKIP)
);

