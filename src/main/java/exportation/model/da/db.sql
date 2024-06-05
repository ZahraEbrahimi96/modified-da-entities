-- PERSON
create table PERSON_TABLE
(
    PERSON_ID           number primary key,
    PERSON_NAME         nvarchar2(30),
    PERSON_FAMILY       nvarchar2(30),
    PERSON_GENDER       nvarchar2(10) CHECK ( PERSON_GENDER IN ('male', 'female')),
    NATIONAL_ID         nvarchar2(10),
    PERSON_PHONE_NUMBER nvarchar2(11),
    PERSON_EMAIL        nvarchar2(300),
    PERSON_ADDRESS      nvarchar2(300),
    PERSON_POSITION     nvarchar2(30)
);

--COUNTRY
create table COUNTRY_TABLE
(
    COUNTRY_ID            number primary key,
    COUNTRY_NAME          nvarchar2(30),
    COUNTRY_PHONE_CODE    nvarchar2(4),
    COUNTRY_RELATEDMARKET nvarchar2(30)
);

--COMPANY
create table COMPANY_TABLE
(
    COMPANY_ID           number primary key,
    COMPANY_NAME         nvarchar2(30),
    COMPANY_PRODUCT      nvarchar2(30),
    COMPANY_ADDRESS      nvarchar2(250),
    COMPANY_EMAIL        nvarchar2(30),
    COMPANY_PHONE_NUMBER nvarchar2(11)
);

--TRADE
create table TRADE_TABLE
(
    TRADE_ID              number primary key,
    TRADE_STATUS          nvarchar2(30),
    TRADE_CORRESPONDENCES nvarchar2(500),
    TRADE_CONTRACT        nvarchar2(500),
    TRADE_AGREEMENT       nvarchar2(500),
    TRADE_INVOICE         nvarchar2(500)
);

--PAYMENT
create table PAYMENT_TABLE
(
    PAYMENT_ID         number primary key,
    PAYMENT_TOTAL_COST number,
    PAYMENT_TAX        number,
    PAYMENT_INSURANCE  number,
    PAYMENT_ITEM references ITEM_TABLE,
    PAYMENT_TRANSPORTATION references TRANSPORTATION_TABLE,
    PAYMENT_INFO references INFO_TABLE
);

--ITEM
create table ITEM_TABLE
(
    ITEM_ID             number primary key,
    PALLET_CAPACITY     number,
    ITEM_NAME           nvarchar2(40),
    ITEM_BRAND          nvarchar2(40)
        CHECK ( ITEM_BRAND IN ('hipile', 'carpile', 'handle', 'tino')),
    ITEM_MODEL          nvarchar2(40),
    DIMENSION_OF_UNIT   nvarchar2(40),
    DIMENSION_OF_PALLET nvarchar2(40),
    ITEM_HS_CODE        number,
    WEIGHT_OF_UNIT      number,
    WEIGHT_OF_PALLET    number,
    ITEM_COST           number
);

--TRANSPORTATION
create table TRANSPORTATION_TABLE
(
    TRANSPORTATION_ID        number primary key,
    TRANSPORTATION_DIRECTION nvarchar2(30),
    TRANSPORTATION_ORIGIN    nvarchar2(30),
    TRANSPORTATION_FREIGHT   number
);

--IMPORTS
create table IMPORTS_TABLE
(
    IMPORTS_ID        number primary key,
    IMPORTS_HS_CODE   number,
    IMPORTS_QUANTITY  number,
    IMPORTS_USD_VALUE number
);

--EXPORTS
create table EXPORTS_TABLE
(
    EXPORTS_ID        number primary key,
    EXPORTS_HS_CODE   number,
    EXPORTS_QUANTITY  number,
    EXPORTS_USD_VALUE number
);

--EXPORT_TRACING
create table EXPORT_TRACING_TABLE
(
    TRACING_ID             number primary key,
    TRACING_LOADING_STATUS char check (TRACING_LOADING_STATUS in (0, 1)),
    TRACING_INVOICE        nvarchar2(100),
    TRACING_WAYBILL        nvarchar2(100),
    TRACING_PREPAYMENT     char check (TRACING_PREPAYMENT in (0, 1)),
    TRACING_CHECKOUT       char check (TRACING_CHECKOUT in (0, 1))
);

--INFO
create table INFO_TABLE
(
    INFO_ID         number primary key,
    INFO_POPULATION number,
    INFO_CAR_RATE   number,
    INFO_CLIMATE    nvarchar2(30),
    INFO_DEMAND     nvarchar2(30),
    INFO_TARIFF     nvarchar2(4)
);

--ACCESSPATH
CREATE TABLE ACCESS_PATH_TABLE
(
    ACCESS_PATH_ID       number primary key,
    ACCESS_PATH_CITY     nvarchar2(20),
    ACCESS_PATH_TYPE     nvarchar2(20),
    CHECK ( ACCESS_PATH_TYPE IN ('air', 'rail', 'road', 'sea')),
    ACCESS_PATH_DISTANCE number,
    ACCESS_NAVIGATION    nvarchar2(20) CHECK ( ACCESS_NAVIGATION IN
                                               ('north', 'northeast', 'northwest', 'south', 'southeast', 'southwest',
                                                'east', 'west'))
);

--MANUFACTURE
create table MANUFACTURER_TABLE
(
    MANUFACTURER_ID      number primary key,
    MANUFACTURER_NAME    nvarchar2(30),
    MANUFACTURER_PRODUCT nvarchar2(30),
    MANUFACTURER_ADDRESS nvarchar2(300),
    MANUFACTURER_EMAIL   nvarchar2(250),
    MANUFACTURER_PHONE   nvarchar2(30),
    PRODUCTION_RATE      number
);

--SUPPLIER
create table SUPPLIER_TABLE
(
    SUPPLIER_ID      number primary key,
    SUPPLIER_NAME    nvarchar2(30),
    SUPPLIER_PRODUCT nvarchar2(30),
    SUPPLIER_ADDRESS nvarchar2(300),
    SUPPLIER_EMAIL   nvarchar2(250),
    SUPPLIER_PHONE   nvarchar2(30),
    ONLINE_SALE      char check (ONLINE_SALE in (0, 1))
);

-- COUNTRY_MANUFACTURER
create table COUNTRY_MANUFACTURER
(
    int number primary key,
    C_COUNTRY_ID references COUNTRY_TABLE,
    C_MANUFACTURER_ID references MANUFACTURER_TABLE
);
create view COUNTRY_MANUFACTURER_REPORT as
select *
from country_table
         join COUNTRY_MANUFACTURER on country_table.country_id = COUNTRY_MANUFACTURER.C_COUNTRY_ID
         join COUNTRY_MANUFACTURER on COUNTRY_MANUFACTURER.C_MANUFACTURER_ID = COUNTRY_MANUFACTURER.C_MANUFACTURER_ID;

-- PERSON_MANUFACTURER
create table PERSON_MANUFACTURER
(
    int number primary key,
    C_PERSON_ID references PERSON_table,
    C_MANUFACTURER_ID references MANUFACTURER_TABLE
);
create view PERSON_MANUFACTURER_REPORT as
select *
from PERSON_table
         join PERSON_MANUFACTURER on PERSON_table.PERSON_id = PERSON_MANUFACTURER.C_PERSON_ID
         join PERSON_MANUFACTURER on PERSON_MANUFACTURER.C_MANUFACTURER_ID = PERSON_MANUFACTURER.C_MANUFACTURER_ID;

-- COUNTRY_SUPPLIE
create table COUNTRY_SUPPLIER
(
    int number primary key,
    C_COUNTRY_ID references country_table,
    C_SUPPLIER_ID references SUPPLIER_TABLE
);
create view COUNTRY_SUPPLIER_REPORT as
select *
from country_table
         join COUNTRY_SUPPLIER on country_table.country_id = COUNTRY_SUPPLIER.C_COUNTRY_ID
         join COUNTRY_SUPPLIER on COUNTRY_SUPPLIER.C_SUPPLIER_ID = COUNTRY_SUPPLIER.C_SUPPLIER_ID;

-- PERSON_SUPPLIER
create table PERSON_SUPPLIER
(
    int number primary key,
    C_PERSON_ID references PERSON_table,
    C_SUPPLIER_ID references SUPPLIER_TABLE
);
create view PERSON_SUPPLIER_REPORT as
select *
from PERSON_table
         join PERSON_SUPPLIER on PERSON_table.PERSON_id = PERSON_SUPPLIER.C_PERSON_ID
         join PERSON_SUPPLIER on PERSON_SUPPLIER.C_SUPPLIER_ID = PERSON_SUPPLIER.C_SUPPLIER_ID;

-- COUNTRY_EXPORTS
create table COUNTRY_EXPORTS
(
    int number primary key,
    C_COUNTRY_ID references country_table,
    C_EXPORTS_ID references EXPORTS_TABLE
);
create view COUNTRY_EXPORTS_REPORT as
select *
from country_table
         join COUNTRY_EXPORTS on country_table.country_id = COUNTRY_EXPORTS.C_COUNTRY_ID
         join COUNTRY_EXPORTS on COUNTRY_EXPORTS.C_EXPORTS_ID = COUNTRY_EXPORTS.C_EXPORTS_ID;

-- COUNTRY_IMPORTS
create table COUNTRY_IMPORTS
(
    int number primary key,
    C_COUNTRY_ID references country_table,
    C_IMPORTS_ID references IMPORTS_TABLE
);
create view COUNTRY_IMPORTS_REPORT as
select *
from country_table
         join COUNTRY_IMPORTS on country_table.country_id = COUNTRY_IMPORTS.C_COUNTRY_ID
         join COUNTRY_IMPORTS on COUNTRY_IMPORTS.C_IMPORTS_ID = COUNTRY_IMPORTS.C_IMPORTS_ID;

-- ACCESS_PATH_INFO
create table ACCESS_PATH_INFO
(
    int number primary key,
    C_ACCESS_PATH_ID references ACCESS_PATH_TABLE,
    C_INFO_ID references INFO_TABLE
);
create view ACCESS_PATH_INFO_REPORT as
select *
from ACCESS_PATH_TABLE
         join ACCESS_PATH_INFO on ACCESS_PATH_TABLE.ACCESS_PATH_ID = ACCESS_PATH_INFO.C_ACCESS_PATH_ID
         join ACCESS_PATH_INFO on ACCESS_PATH_INFO.C_INFO_ID = ACCESS_PATH_INFO.C_INFO_ID;

-- SUPPLIER_INFO
create table SUPPLIER_INFO
(
    int number primary key,
    C_SUPPLIER_ID references SUPPLIER_TABLE,
    C_INFO_ID references INFO_TABLE
);
create view SUPPLIER_INFO_REPORT as
select *
from SUPPLIER_TABLE
         join SUPPLIER_INFO on SUPPLIER_TABLE.SUPPLIER_ID = SUPPLIER_INFO.C_SUPPLIER_ID
         join SUPPLIER_INFO on SUPPLIER_INFO.C_INFO_ID = SUPPLIER_INFO.C_INFO_ID;

-- MANUFACTURER_INFO
create table MANUFACTURER_INFO
(
    int number primary key,
    C_MANUFACTURER_ID references MANUFACTURER_TABLE,
    C_INFO_ID references INFO_TABLE
);
create view MANUFACTURER_INFO_REPORT as
select *
from MANUFACTURER_TABLE
         join MANUFACTURER_INFO on MANUFACTURER_TABLE.MANUFACTURER_ID = MANUFACTURER_INFO.C_MANUFACTURER_ID
         join MANUFACTURER_INFO on MANUFACTURER_INFO.C_INFO_ID = MANUFACTURER_INFO.C_INFO_ID;

-- IMPORTS_INFO
create table IMPORTS_INFO
(
    int number primary key,
    C_IMPORTS_ID references IMPORTS_TABLE,
    C_INFO_ID references INFO_TABLE
);
create view IMPORTS_INFO_REPORT as
select *
from IMPORTS_TABLE
         join IMPORTS_INFO on IMPORTS_TABLE.IMPORTS_ID = IMPORTS_INFO.C_IMPORTS_ID
         join IMPORTS_INFO on IMPORTS_INFO.C_INFO_ID = IMPORTS_INFO.C_INFO_ID;

-- EXPORTS_INFO
create table EXPORTS_INFO
(
    int number primary key,
    C_EXPORTS_ID references EXPORTS_TABLE,
    C_INFO_ID references INFO_TABLE
);
create view EXPORTS_INFO_REPORT as
select *
from EXPORTS_TABLE
         join EXPORTS_INFO on EXPORTS_TABLE.EXPORTS_ID = EXPORTS_INFO.C_EXPORTS_ID
         join EXPORTS_INFO on EXPORTS_INFO.C_INFO_ID = EXPORTS_INFO.C_INFO_ID;

-- ITEM_PAYMENT
create table ITEM_PAYMENT
(
    int number primary key,
    C_ITEM_ID references ITEM_TABLE,
    C_PAYMENT_ID references PAYMENT_TABLE
);
create view ITEM_PAYMENT_REPORT as
select *
from ITEM_TABLE
         join ITEM_PAYMENT on ITEM_TABLE.ITEM_ID = ITEM_PAYMENT.C_ITEM_ID
         join ITEM_PAYMENT on ITEM_PAYMENT.C_PAYMENT_ID = ITEM_PAYMENT.C_PAYMENT_ID;

-- TRANSPORTATION_PAYMENT
create table TRANSPORTATION_PAYMENT
(
    int number primary key,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE,
    C_PAYMENT_ID references PAYMENT_TABLE
);
create view TRANSPORTATION_PAYMENT_REPORT as
select *
from TRANSPORTATION_TABLE
         join TRANSPORTATION_PAYMENT
              on TRANSPORTATION_TABLE.TRANSPORTATION_ID = TRANSPORTATION_PAYMENT.C_TRANSPORTATION_ID
         join TRANSPORTATION_PAYMENT on TRANSPORTATION_PAYMENT.C_PAYMENT_ID = TRANSPORTATION_PAYMENT.C_PAYMENT_ID;

-- INFO_PAYMENT
create table INFO_PAYMENT
(
    int number primary key,
    C_INFO_ID references INFO_TABLE,
    C_PAYMENT_ID references PAYMENT_TABLE
);
create view INFO_PAYMENT_REPORT as
select *
from INFO_TABLE
         join INFO_PAYMENT on INFO_TABLE.INFO_ID = INFO_PAYMENT.C_INFO_ID
         join INFO_PAYMENT on INFO_PAYMENT.C_PAYMENT_ID = INFO_PAYMENT.C_PAYMENT_ID;

-- ACCESS_PATH_TRANSPORTATION
create table ACCESS_PATH_TRANSPORTATION
(
    int number primary key,
    C_ACCESS_PATH_ID references ACCESS_PATH_TABLE,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE
);
create view ACCESS_PATH_TRANSPORTATION_REPORT as
select *
from ACCESS_PATH_TABLE
         join ACCESS_PATH_TRANSPORTATION
              on ACCESS_PATH_TABLE.ACCESS_PATH_ID = ACCESS_PATH_TRANSPORTATION.C_ACCESS_PATH_ID
         join ACCESS_PATH_TRANSPORTATION
              on ACCESS_PATH_TRANSPORTATION.C_TRANSPORTATION_ID = ACCESS_PATH_TRANSPORTATION.C_TRANSPORTATION_ID;

-- COUNTRY_TRANSPORTATION
create table COUNTRY_TRANSPORTATION
(
    int number primary key,
    C_COUNTRY_ID references COUNTRY_TABLE,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE
);
create view COUNTRY_TRANSPORTATION_REPORT as
select *
from COUNTRY_TABLE
         join COUNTRY_TRANSPORTATION on COUNTRY_TABLE.COUNTRY_ID = COUNTRY_TRANSPORTATION.C_COUNTRY_ID
         join COUNTRY_TRANSPORTATION
              on COUNTRY_TRANSPORTATION.C_TRANSPORTATION_ID = COUNTRY_TRANSPORTATION.C_TRANSPORTATION_ID;

-- ITEM_TRANSPORTATION
create table ITEM_TRANSPORTATION
(
    int number primary key,
    C_ITEM_ID references ITEM_TABLE,
    C_TRANSPORTATION_ID references TRANSPORTATION_TABLE
);
create view ITEM_TRANSPORTATION_REPORT as
select *
from ITEM_TABLE
         join ITEM_TRANSPORTATION on ITEM_TABLE.ITEM_ID = ITEM_TRANSPORTATION.C_ITEM_ID
         join ITEM_TRANSPORTATION on ITEM_TRANSPORTATION.C_TRANSPORTATION_ID = ITEM_TRANSPORTATION.C_TRANSPORTATION_ID;


-- todo : create sequences