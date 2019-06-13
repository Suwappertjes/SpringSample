# MySQL Manual

For our project we need to run MySQL on the server and locally.
This manual details how to set it up and explains all the fixes I used to make it work.

1. Install MySQL Server (you don't need client to set this up).
    - $`sudo apt-get install mysql-server`
    - Restart your computer.
2. The default root-password is broken in version 5.7.26, so you need to stop the service and reset the password (in this example to 'Password').
    - $`echo "ALTER USER 'root'@'localhost' IDENTIFIED BY 'Password'" > /$HOME/mysql-init` *make a init-file*
    - $`sudo /etc/init.d/mysql stop` *stop the server*
    - $`sudo mysqld --init-file=/$HOME/mysql-init` *reset the password with your script*
    - $`sudo /etc/init.d/mysql start` *start the server again*
    - $`sudo rm /$HOME/mysql-init` *remove the script*
    - $`sudo mysql --password` *enter new password to start the mysql interpreter as 'root'@'localhost'*
3. We now have to create the database for our project...
    - mysql>`CREATE DATABASE db_example;`
    - mysql>`USE db_example;` *switch to db_example*
4. And create a user account for spring (you can not use root because of security reasons).
    - mysql>`CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'Password';` *I used the same root and user password for convenience, but don't do that in a production environment*
    - mysql>`GRANT ALL PRIVILEGES ON db_example.* TO 'springuser'@'localhost';` *Give the new user all privileges to the database*

sudo /etc/init.d/mysql start
sudo mysql --password