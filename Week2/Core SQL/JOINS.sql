/* 7.0 JOINS */

/*7.1 INNER */

SELECT * FROM CUSTOMER;
SELECT * FROM INVOICE;

SELECT CUSTOMER.FIRSTNAME AS FIRSTNAME,
       CUSTOMER.LASTNAME AS LASTNAME,
       INVOICE.INVOICEID AS INVOICEID
       FROM CUSTOMER
       INNER JOIN INVOICE
       ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
       
/*7.2 OUTER */
SELECT CUSTOMER.CUSTOMERID AS CUSTOMERID,
       CUSTOMER.FIRSTNAME AS FIRSTNAME,
       CUSTOMER.LASTNAME AS LASTNAME,
       INVOICE.INVOICEID AS INVOICEID,
       INVOICE.TOTAL AS TOTAL
       FROM CUSTOMER
       FULL OUTER JOIN INVOICE
       ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;
       
/*7.3 RIGHT */
SELECT * FROM ARTIST;

SELECT ARTIST.NAME AS NAME,
       ALBUM.TITLE AS TITLE
       FROM ARTIST
       RIGHT OUTER JOIN ALBUM
       ON ARTIST.ARTISTID = ALBUM.ARTISTID;
       
/*7.4 CROSS */
SELECT * FROM ALBUM;

SELECT ALBUM.TITLE, ARTIST.NAME 
       FROM ALBUM,ARTIST
       ORDER BY ARTIST.NAME ASC;
    
/*7.5 SELF */
SELECT * FROM EMPLOYEE;
SELECT EMPLOYEE.FIRSTNAME AS FIRSTNAME,
       EMPLOYEE.LASTNAME AS LASTNAME,
       SELF.FIRSTNAME AS SUPERVISORFIRSTNAME,
       SELF.LASTNAME AS SUPERVISORLASTNAME
       FROM EMPLOYEE
       SELF JOIN EMPLOYEE
       ON EMPLOYEE.REPORTSTO = SELF.EMPLOYEEID;
       
/*8.0 INDEXES */
SELECT * FROM EMPLOYEE;

CREATE INDEX MYINDEX
    ON EMPLOYEE(FIRSTNAME);

/*9.0 ADMINISTRATION */