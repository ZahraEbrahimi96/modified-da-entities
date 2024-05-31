create table PERSON_TABLE
(
    PERSON_ID           number primary key,
    PERSON_NAME         nvarchar2(30),
    PERSON_FAMILY       nvarchar2(30),
    PERSON_GENDER       number(0),
    NATIONAL_ID         nvarchar2(10),
    PERSON_PHONE_NUMPER nvarchar2(11),
    PERSON_EMAIL        nvarchar2(300),
    PERSON_ADDRESS      nvarchar2(300),
    PERSON_POSITION     nvarchar2(30)
);

create table MANUFACTURER_TABLE
(
    MANUFACTURER_ID           number primary key,
    MANUFACTURER_NAME         nvarchar2(30),
    MANUFACTURER_PRODUCT      nvarchar2(30),
    MANUFACTURER_ADDRESS      nvarchar2(300),
    MANUFACTURER_EMAIL        nvarchar2(250),
    MANUFACTURER_PHONE        nvarchar2(30),
    PRODUCTION_RATE           number
);

create table SUPPLIER_TABLE
(
    SUPPLIER_ID           number primary key,
    SUPPLIER_NAME         nvarchar2(30),
    SUPPLIER_PRODUCT      nvarchar2(30),
    SUPPLIER_ADDRESS      nvarchar2(300),
    SUPPLIER_EMAIL        nvarchar2(250),
    SUPPLIER_PHONE        nvarchar2(30),
    SUPPLIER_RATE         number,
    ONLINE_SALE           number(0)
);

create table country_table
(
    country_id             number primary key,
    country_name           nvarchar2(30),
    country_phone_code     nvarchar2(4)
);

create table EXPORTS_TABLE(
    EXPORTS_ID           number primary key,
    EXPORTS_HS_CODE      number,
    EXPORTS_QUANTITY     number,
    EXPORTS_USD_VALUE    number
);


create table IMPORTS_TABLE(
    IMPORTS_ID           number primary key,
    IMPORTS_HS_CODE      number,
    IMPORTS_QUANTITY     number,
    IMPORTS_USD_VALUE    number
);

create table EXPORT_TRACING_TABLE(
    EXPORT_TRACING_ID    number primary key,
    LOADING_STATUS       number(0),
    INVOICE              nvarchar2(100),
    WAYBILL              nvarchar2(100),
    PREPAYMENT           number(0),
    CHECKOUT             number(0)
);
create table INFO_TABLE(
    INFO_ID              number primary key,
    POPULATION           number,
    CAR_RATE             number,
    CLIMATE              nvarchar2(30),
    DEMAND               nvarchar2(30),
    TARIFF               nvarchar2(4)
);


CREATE TABLE ACCESS_PATH_TABLE(
    ACCESS_PATH_ID       number primary key,
    ACCESS_PATH_CITY     nvarchar2(20),
    ACCESS_PATH_TYPE     nvarchar2(20),
    ACCESS_PATH_DISTANCE number,
    ACCESS_NAVIGATION    nvarchar2(20)
);

create table trade_table
(
    trade_id              number primary key,
    trade_status          nvarchar2(30),
    trade_correspondences nvarchar2(500),
    trade_contract        nvarchar2(500),
    trade_agreement       nvarchar2(500),
    trade_invoice         nvarchar2(500)
);

create table TRANSPORTATION_TABLE
(
    TRANSPORTATION_ID        number primary key,
    transportation_origin    nvarchar2(30),
    transportation_freight   number
);

create table payment_table
(
    payment_id         number primary key,
    payment_total_cost number,
    payment_tax        number,
    payment_insurance  number
);

create table ITEM_TABLE
(
    ITEM_ID      number primary key,
    ITEM_HS_CODE number,
    ITEM_NAME    nvarchar2(40),
    ITEM_MODEL   nvarchar2(40),
    ITEM_TYPE    nvarchar2(40),
    ITEM_BRAND   nvarchar2(40),
    DIMENSION_OF_UNIT   nvarchar2(40),
    DIMENSION_OF_PALLET nvarchar2(40),
    WEIGHT_OF_UNIT      number,
    WEIGHT_OF_PALLET    number,
    PALLET_CAPACITY     number,
    ITEM_COST           number
);




create table COUNTRY_MANUFACTURER
(
    int          number primary key,
    C_COUNTRY_ID references country_table,
    C_MANUFACTURER_ID references MANUFACTURER_TABLE
);

create view COUNTRY_MANUFACTURER_REPORT as
select *
from country_table
         join COUNTRY_MANUFACTURER on country_table.country_id = COUNTRY_MANUFACTURER.C_COUNTRY_ID
         join COUNTRY_MANUFACTURER on COUNTRY_MANUFACTURER.C_MANUFACTURER_ID = COUNTRY_MANUFACTURER.C_MANUFACTURER_ID

create table PERSON_MANUFACTURER
(
    int          number primary key,
    C_PERSON_ID references PERSON_table,
    C_MANUFACTURER_ID references MANUFACTURER_TABLE
);

create view PERSON_MANUFACTURER_REPORT as
select *
from PERSON_table
         join PERSON_MANUFACTURER on PERSON_table.PERSON_id = PERSON_MANUFACTURER.C_PERSON_ID
         join PERSON_MANUFACTURER on PERSON_MANUFACTURER.C_MANUFACTURER_ID = PERSON_MANUFACTURER.C_MANUFACTURER_ID





create table COUNTRY_SUPPLIER
(
    int          number primary key,
    C_COUNTRY_ID references country_table,
    C_SUPPLIER_ID references SUPPLIER_TABLE
);

create view COUNTRY_SUPPLIER_REPORT as
select *
from country_table
         join COUNTRY_SUPPLIER on country_table.country_id = COUNTRY_SUPPLIER.C_COUNTRY_ID
         join COUNTRY_SUPPLIER on COUNTRY_SUPPLIER.C_SUPPLIER_ID = COUNTRY_SUPPLIER.C_SUPPLIER_ID

create table PERSON_SUPPLIER
(
    int          number primary key,
    C_PERSON_ID references PERSON_table,
    C_SUPPLIER_ID references SUPPLIER_TABLE
);

create view PERSON_SUPPLIER_REPORT as
select *
from PERSON_table
         join PERSON_SUPPLIER on PERSON_table.PERSON_id = PERSON_SUPPLIER.C_PERSON_ID
         join PERSON_SUPPLIER on PERSON_SUPPLIER.C_SUPPLIER_ID = PERSON_SUPPLIER.C_SUPPLIER_ID





create table COUNTRY_EXPORTS
(
    int          number primary key,
    C_COUNTRY_ID references country_table,
    C_EXPORTS_ID references EXPORTS_TABLE
);

create view COUNTRY_EXPORTS_REPORT as
select *
from country_table
         join COUNTRY_EXPORTS on country_table.country_id = COUNTRY_EXPORTS.C_COUNTRY_ID
         join COUNTRY_EXPORTS on COUNTRY_EXPORTS.C_EXPORTS_ID = COUNTRY_EXPORTS.C_EXPORTS_ID



create table COUNTRY_IMPORTS
(
    int          number primary key,
    C_COUNTRY_ID references country_table,
    C_IMPORTS_ID references IMPORTS_TABLE
);

create view COUNTRY_IMPORTS_REPORT as
select *
from country_table
         join COUNTRY_IMPORTS on country_table.country_id = COUNTRY_IMPORTS.C_COUNTRY_ID
         join COUNTRY_IMPORTS on COUNTRY_IMPORTS.C_IMPORTS_ID = COUNTRY_IMPORTS.C_IMPORTS_ID




create table ACCESS_PATH_INFO
(
    int          number primary key,
    C_ACCESS_PATH_ID references ACCESS_PATH_TABLE,
    C_INFO_ID references INFO_TABLE
);

create view ACCESS_PATH_INFO_REPORT as
select *
from ACCESS_PATH_TABLE
         join ACCESS_PATH_INFO on ACCESS_PATH_TABLE.ACCESS_PATH_ID = ACCESS_PATH_INFO.C_ACCESS_PATH_ID
         join ACCESS_PATH_INFO on ACCESS_PATH_INFO.C_INFO_ID = ACCESS_PATH_INFO.C_INFO_ID


create table SUPPLIER_INFO
(
    int          number primary key,
    C_SUPPLIER_ID references SUPPLIER_TABLE,
    C_INFO_ID references INFO_TABLE
);

create view SUPPLIER_INFO_REPORT as
select *
from SUPPLIER_TABLE
         join SUPPLIER_INFO on SUPPLIER_TABLE.SUPPLIER_ID = SUPPLIER_INFO.C_SUPPLIER_ID
         join SUPPLIER_INFO on SUPPLIER_INFO.C_INFO_ID = SUPPLIER_INFO.C_INFO_ID




create table MANUFACTURER_INFO
(
    int          number primary key,
    C_MANUFACTURER_ID references MANUFACTURER_TABLE,
    C_INFO_ID references INFO_TABLE
);

create view MANUFACTURER_INFO_REPORT as
select *
from MANUFACTURER_TABLE
         join MANUFACTURER_INFO on MANUFACTURER_TABLE.MANUFACTURER_ID = MANUFACTURER_INFO.C_MANUFACTURER_ID
         join MANUFACTURER_INFO on MANUFACTURER_INFO.C_INFO_ID = MANUFACTURER_INFO.C_INFO_ID



create table IMPORTS_INFO
(
    int          number primary key,
    C_IMPORTS_ID references IMPORTS_TABLE,
    C_INFO_ID references INFO_TABLE
);

create view IMPORTS_INFO_REPORT as
select *
from IMPORTS_TABLE
         join IMPORTS_INFO on IMPORTS_TABLE.IMPORTS_ID = IMPORTS_INFO.C_IMPORTS_ID
         join IMPORTS_INFO on IMPORTS_INFO.C_INFO_ID = IMPORTS_INFO.C_INFO_ID



create table EXPORTS_INFO
(
    int          number primary key,
    C_EXPORTS_ID references EXPORTS_TABLE,
    C_INFO_ID references INFO_TABLE
);

create view EXPORTS_INFO_REPORT as
select *
from EXPORTS_TABLE
         join EXPORTS_INFO on EXPORTS_TABLE.EXPORTS_ID = EXPORTS_INFO.C_EXPORTS_ID
         join EXPORTS_INFO on EXPORTS_INFO.C_INFO_ID = EXPORTS_INFO.C_INFO_ID



create table ITEM_PAYMENT
(
    int          number primary key,
    C_ITEM_ID references ITEM_TABLE,
    C_PAYMENT_ID references PAYMENT_TABLE
);

create view ITEM_PAYMENT_REPORT as
select *
from ITEM_TABLE
         join ITEM_PAYMENT on ITEM_TABLE.ITEM_ID = ITEM_PAYMENT.C_ITEM_ID
         join ITEM_PAYMENT on ITEM_PAYMENT.C_PAYMENT_ID = ITEM_PAYMENT.C_PAYMENT_ID





create table TRANSPORTATION_PAYMENT
(
    int          number primary key,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE,
    C_PAYMENT_ID references PAYMENT_TABLE
);

create view TRANSPORTATION_PAYMENT_REPORT as
select *
from TRANSPORTATION_TABLE
         join TRANSPORTATION_PAYMENT on TRANSPORTATION_TABLE.TRANSPORTATION_ID = TRANSPORTATION_PAYMENT.C_TRANSPORTATION_ID
         join TRANSPORTATION_PAYMENT on TRANSPORTATION_PAYMENT.C_PAYMENT_ID = TRANSPORTATION_PAYMENT.C_PAYMENT_ID





create table INFO_PAYMENT
(
    int          number primary key,
    C_INFO_ID references INFO_TABLE,
    C_PAYMENT_ID references PAYMENT_TABLE
);

create view INFO_PAYMENT_REPORT as
select *
from INFO_TABLE
         join INFO_PAYMENT on INFO_TABLE.INFO_ID = INFO_PAYMENT.C_INFO_ID
         join INFO_PAYMENT on INFO_PAYMENT.C_PAYMENT_ID = INFO_PAYMENT.C_PAYMENT_ID





create table ACCESS_PATH_TRANSPORTATION
(
    int          number primary key,
    C_ACCESS_PATH_ID references ACCESS_PATH_TABLE,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE
);

create view ACCESS_PATH_TRANSPORTATION_REPORT as
select *
from ACCESS_PATH_TABLE
         join ACCESS_PATH_TRANSPORTATION on ACCESS_PATH_TABLE.ACCESS_PATH_ID = ACCESS_PATH_TRANSPORTATION.C_ACCESS_PATH_ID
         join ACCESS_PATH_TRANSPORTATION on ACCESS_PATH_TRANSPORTATION.C_TRANSPORTATION_ID = ACCESS_PATH_TRANSPORTATION.C_TRANSPORTATION_ID




create table COUNTRY_TRANSPORTATION
(
    int          number primary key,
    C_COUNTRY_ID references COUNTRY_TABLE,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE
);

create view COUNTRY_TRANSPORTATION_REPORT as
select *
from COUNTRY_TABLE
         join COUNTRY_TRANSPORTATION on COUNTRY_TABLE.COUNTRY_ID = COUNTRY_TRANSPORTATION.C_COUNTRY_ID
         join COUNTRY_TRANSPORTATION on COUNTRY_TRANSPORTATION.C_TRANSPORTATION_ID = COUNTRY_TRANSPORTATION.C_TRANSPORTATION_ID





create table ITEM_TRANSPORTATION
(
    int          number primary key,
    C_ITEM_ID references ITEM_TABLE,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE
);

create view ITEM_TRANSPORTATION_REPORT as
select *
from ITEM_TABLE
         join ITEM_TRANSPORTATION on ITEM_TABLE.ITEM_ID = ITEM_TRANSPORTATION.C_ITEM_ID
         join ITEM_TRANSPORTATION on ITEM_TRANSPORTATION.C_TRANSPORTATION_ID = ITEM_TRANSPORTATION.C_TRANSPORTATION_ID
-- todo : create sequences