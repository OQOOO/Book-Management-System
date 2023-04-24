
---------------------------------------------------------------------------

-- ������������ �����뿩�������� ����
-- ���� ����
CREATE TABLE USER_INFO (
     ID                       VARCHAR2(30)       PRIMARY KEY
    ,PASSWORD        VARCHAR2(30)       NOT NULL
    ,NAME                 VARCHAR2(50)        NOT NULL
    ,BIRTH_DATE         DATE                       NOT NULL 
    ,EMAIL                 VARCHAR2(50)       NOT NULL
    ,PHONE_NUM        VARCHAR2(11)            NOT NULL
    , ADMIN_RIGHT             NUMBER(1)
);

SELECT *
FROM USER_INFO;

SELECT ID, PASSWORD
FROM USER_INFO
WHERE ID = 'id' AND PASSWORD = 'pw';

INSERT INTO USER_INFO(ID, PASSWORD, NAME, BIRTH_DATE, EMAIL, PHONE_NUM, ADMIN_RIGHT)
VALUES('admin', 'ad', 'imAdmin', to_date(19990730, 'YYYYMMDD'), 'admin@adm', '01011113333', 1);

--DROP TABLE USER_INFO;

-- ��Ʈ ���̺�
CREATE TABLE USER_RENTED (
      ISBN                          VARCHAR2(13)         PRIMARY KEY
    , ID                             VARCHAR2(30)       NOT NULL
    , BOOK_NAME              VARCHAR2(200)    NOT NULL
    , RENTAL_DATE            DATE                    NOT NULL
    , RETURN_DATE            DATE                    NOT NULL
);

INSERT INTO USER_RENTED(ISBN, ID, BOOK_NAME, RENTAL_DATE, RETURN_DATE)
VALUES('1234567890123', 'id', '���ǿ���'
, TO_DATE(20230730, 'YYYYMMDD')
, TO_DATE(20230830, 'YYYYMMDD'));

SELECT *
FROM USER_RENTED; ----������

delete user_rented where book_name = '�ѱռ�';

--DROP TABLE USER_RENTED;
 
-- ������ ���̺�
CREATE TABLE LIBRARY_BOOKS(
     ISBN                       VARCHAR2(13)               PRIMARY KEY
     , BOOK_NAME         VARCHAR2(30)            NOT NULL
     , WRITER                VARCHAR2(50)              NOT NULL
     , PUBLISHER            VARCHAR(50)                NOT NULL
     , RENT_USER_ID      VARCHAR2(30)
);


SELECT * FROM LIBRARY_BOOKS
WHERE BOOK_NAME = '' OR WRITER = '���� ����' or publisher = 'AA���ǻ�';

INSERT INTO LIBRARY_BOOKS(ISBN, BOOK_NAME, WRITER, PUBLISHER, RENT_USER_ID)
VALUES('1234567890123', '���ǿ���', '���� ����', 'AA���ǻ�', NULL);

INSERT INTO LIBRARY_BOOKS(ISBN, BOOK_NAME, WRITER, PUBLISHER, RENT_USER_ID)
VALUES('1234567890124', 'ȣ�� ���콺', '���� ����', 'AA���ǻ�', null);

INSERT INTO LIBRARY_BOOKS(ISBN, BOOK_NAME, WRITER, PUBLISHER, RENT_USER_ID)
VALUES('1111111111234', '�ѱռ�', '������ ���̾Ƹ��', 'AA���ǻ�',  NULL);

INSERT INTO LIBRARY_BOOKS(ISBN, BOOK_NAME, WRITER, PUBLISHER, RENT_USER_ID)
VALUES('1234561011456', '�̱��� ������', '��ó�� ��Ų��', 'BB���ǻ�',  NULL);

--DROP TABLE LIBRARY_BOOKS;

SELECT *
FROM USER_INFO;

SELECT *
FROM USER_RENTED;

SELECT * 
FROM LIBRARY_BOOKS;
