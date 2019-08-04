DROP  DATABASE  IF  EXISTS  `githubtracker`;
CREATE  DATABASE  IF  NOT  EXISTS  `githubtracker`;
USE  `githubtracker`;

DROP TABLE IF EXISTS `githuber`;
CREATE TABLE `githuber` (
    id INT PRIMARY  KEY AUTO_INCREMENT,
    github_id INT PRIMARY KEY,
    `name` VARCHAR(64), 
    `login` VARCHAR(64) UNIQUE,
    `url` VARCHAR(512),
    `email` VARCHAR(64),
    `bio` VARCHAR(2048),
    `location` VARCHAR (64),
    `avatar_url` VARCHAR (512)
    );
    
insert into `githuber` (`github_id`,`name`,`login`, `url`,`email`,`bio`,`location`,`avatar_url`) 
values ('007','James','goodsavethekeen','www.github/secret','007@agency','classified','classified','www.avatar.com&id=33');
