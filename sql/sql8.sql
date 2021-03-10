CREATE TABLE myshower(
myid  INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
parentid INTEGER,
mytype VARCHAR(32),
username VARCHAR(32),
path VARCHAR(32),
sname VARCHAR(32),
ischecked VARCHAR(32),
mydate DATE
);

CREATE TABLE users(
 username VARCHAR(32),
 userpsw VARCHAR(32),
 usertel VARCHAR(32),
 rootFolderId INTEGER
);