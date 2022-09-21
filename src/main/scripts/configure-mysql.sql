## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE dev_fruit_shop;
CREATE DATABASE prod_fruit_shop;

#Create database service accounts
CREATE USER `dev_user` @ `localhost` IDENTIFIED BY `p@55w0rd`;
CREATE USER `prod_user` @ `localhost` IDENTIFIED BY `p@55w0rd`;
CREATE USER `dev_user` @ `%` IDENTIFIED BY `p@55w0rd`;
CREATE USER `prod_user` @ `%` IDENTIFIED BY `p@55w0rd`;

#Database grants for dev_user
GRANT SELECT ON `dev_fruit_shop`.* TO `dev_user`@`localhost`;
GRANT INSERT ON `dev_fruit_shop`.* TO `dev_user`@`localhost`;
GRANT UPDATE ON `dev_fruit_shop`.* TO `dev_user`@`localhost`;
GRANT DELETE ON `dev_fruit_shop`.* TO `dev_user`@`localhost`;

GRANT SELECT ON `dev_fruit_shop`.* TO `dev_user`@`%`;
GRANT INSERT ON `dev_fruit_shop`.* TO `dev_user`@`%`;
GRANT UPDATE ON `dev_fruit_shop`.* TO `dev_user`@`%`;
GRANT DELETE ON `dev_fruit_shop`.* TO `dev_user`@`%`;

#Database grants for prod_user
GRANT SELECT ON `prod_fruit_shop`.* TO `prod_user`@`localhost`;
GRANT INSERT ON `prod_fruit_shop`.* TO `prod_user`@`localhost`;
GRANT UPDATE ON `prod_fruit_shop`.* TO `prod_user`@`localhost`;
GRANT DELETE ON `prod_fruit_shop`.* TO `prod_user`@`localhost`;

GRANT SELECT ON `prod_fruit_shop`.* TO `prod_user`@`%`;
GRANT INSERT ON `prod_fruit_shop`.* TO `prod_user`@`%`;
GRANT UPDATE ON `prod_fruit_shop`.* TO `prod_user`@`%`;
GRANT DELETE ON `prod_fruit_shop`.* TO `prod_user`@`%`;
