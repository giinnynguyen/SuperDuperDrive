CREATE TABLE IF NOT EXISTS USERS (
                                     userid INT PRIMARY KEY auto_increment,
                                     username VARCHAR(20) UNIQUE,
                                     salt VARCHAR,
                                     password VARCHAR,
                                     firstname VARCHAR(20),
                                     lastname VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS NOTES (
                                     noteid INT PRIMARY KEY auto_increment,
                                     notetitle VARCHAR(20),
                                     notedescription VARCHAR (1000),
                                     userid INT,
                                     foreign key (userid) references USERS(userid)
);

CREATE TABLE IF NOT EXISTS FILES (
                                     fileId INT PRIMARY KEY auto_increment,
                                     filename VARCHAR,
                                     contenttype VARCHAR,
                                     filesize VARCHAR,
                                     userid INT,
                                     filedata BLOB,
                                     FOREIGN KEY (userid) REFERENCES USERS(userid)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
                                           credentialid INT PRIMARY KEY AUTO_INCREMENT,
                                           url VARCHAR(100),
                                           username VARCHAR(30),
                                           credential_key VARCHAR,
                                           credential_password VARCHAR,
                                           userid INT,
                                           FOREIGN KEY (userid) REFERENCES USERS(userid)
);

INSERT INTO USERS (username, salt, password, firstname, lastname)
VALUES ('ginnynguyen', '4F7jF31Kk+yPX/nfY/a5tA==', '2WaNIrk1OLNkMNLCLLDqy0a1RR3QcHJnD45gp3/gSbnPbFlhBfSigeCkR5AAUL7MjxbLISg+eA++yqwZk1ujc89Bc39ZVVHeECtJCpBnVT2VGE3zA9XNznbgiixULH2PiPjHJP2wljLvjYW35NqYWM5QfSKcKITZ8v3e+qCzDe7sfxMo+G5Ddzq1BvDl4R2QIphQVpLOrhM2gHPrzr/j1iI6CQv5O5LsSf7Xkml6FZQGZzGUm29OjR/VPqQrVDsL3w0qNGu0VksAckIqXsvsn7GngmXGYcjZgRy0XmvPVhLA6tQ3mbXgAImvb8jIv6nTKQwqnkmE63ruQdH/25ULiQ==', 'Ginny', 'Nguyen');

