DROP TABLE SHORTENED_URL;
CREATE TABLE SHORTENED_URL 
(
	ID VARCHAR2(8) NOT NULL,
	URL VARCHAR2(1000) NOT NULL,
	REDIR_COUNT NUMBER NOT NULL
);
