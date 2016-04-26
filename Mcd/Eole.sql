/*==============================================================*/
/* Nom de SGBD :  Microsoft SQL Server 2012                     */
/* Date de création :  09/11/2015 09:32:12                      */
/*==============================================================*/


if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PARTICIPER') and o.name = 'FK_PARTICIP_DF_SKIPPER')
alter table PARTICIPER
   drop constraint FK_PARTICIP_DF_SKIPPER
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PARTICIPER') and o.name = 'FK_PARTICIP_REGPART_REGATE')
alter table PARTICIPER
   drop constraint FK_PARTICIP_REGPART_REGATE
go

if exists (select 1
   from sys.sysreferences r join sys.sysobjects o on (o.id = r.constid and o.type = 'F')
   where r.fkeyid = object_id('PARTICIPER') and o.name = 'FK_PARTICIP_VOILPART_VOILIER')
alter table PARTICIPER
   drop constraint FK_PARTICIP_VOILPART_VOILIER
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PARTICIPER')
            and   name  = 'DF_FK'
            and   indid > 0
            and   indid < 255)
   drop index PARTICIPER.DF_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PARTICIPER')
            and   name  = 'VOILPART_FK'
            and   indid > 0
            and   indid < 255)
   drop index PARTICIPER.VOILPART_FK
go

if exists (select 1
            from  sysindexes
           where  id    = object_id('PARTICIPER')
            and   name  = 'REGPART_FK'
            and   indid > 0
            and   indid < 255)
   drop index PARTICIPER.REGPART_FK
go

if exists (select 1
            from  sysobjects
           where  id = object_id('PARTICIPER')
            and   type = 'U')
   drop table PARTICIPER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('REGATE')
            and   type = 'U')
   drop table REGATE
go

if exists (select 1
            from  sysobjects
           where  id = object_id('SKIPPER')
            and   type = 'U')
   drop table SKIPPER
go

if exists (select 1
            from  sysobjects
           where  id = object_id('VOILIER')
            and   type = 'U')
   drop table VOILIER
go

/*==============================================================*/
/* Table : PARTICIPER                                           */
/*==============================================================*/
create table PARTICIPER (
   TMPSREEL             datetime             not null,
   NUMREGATE            bigint               not null,
   NUMVOIL              bigint               not null,
   NUMSKIP              bigint               not null,
   constraint PK_PARTICIPER primary key nonclustered (TMPSREEL, NUMREGATE, NUMVOIL)
)
go

/*==============================================================*/
/* Index : REGPART_FK                                           */
/*==============================================================*/
create index REGPART_FK on PARTICIPER (
NUMREGATE ASC
)
go

/*==============================================================*/
/* Index : VOILPART_FK                                          */
/*==============================================================*/
create index VOILPART_FK on PARTICIPER (
NUMVOIL ASC
)
go

/*==============================================================*/
/* Index : DF_FK                                                */
/*==============================================================*/
create index DF_FK on PARTICIPER (
NUMSKIP ASC
)
go

/*==============================================================*/
/* Table : REGATE                                               */
/*==============================================================*/
create table REGATE (
   NUMREGATE            bigint               not null,
   NOMREGATE            text                 not null,
   DISTANCE             double precision     not null,
   DATE                 datetime             not null,
   constraint PK_REGATE primary key nonclustered (NUMREGATE)
)
go

/*==============================================================*/
/* Table : SKIPPER                                              */
/*==============================================================*/
create table SKIPPER (
   NUMSKIP              bigint               not null,
   NOMSKIP              text                 not null,
   PRENOMSKIP           text                 not null,
   constraint PK_SKIPPER primary key nonclustered (NUMSKIP)
)
go

/*==============================================================*/
/* Table : VOILIER                                              */
/*==============================================================*/
create table VOILIER (
   NUMVOIL              bigint               not null,
   NOMVOIL              text                 not null,
   CLASSE               smallint             not null,
   RATING               bigint               not null,
   constraint PK_VOILIER primary key nonclustered (NUMVOIL)
)
go

alter table PARTICIPER
   add constraint FK_PARTICIP_DF_SKIPPER foreign key (NUMSKIP)
      references SKIPPER (NUMSKIP)
go

alter table PARTICIPER
   add constraint FK_PARTICIP_REGPART_REGATE foreign key (NUMREGATE)
      references REGATE (NUMREGATE)
go

alter table PARTICIPER
   add constraint FK_PARTICIP_VOILPART_VOILIER foreign key (NUMVOIL)
      references VOILIER (NUMVOIL)
go

