DROP DATABASE IF EXISTS petka;
CREATE DATABASE IF NOT EXISTS petka CHARACTER SET utf8 COLLATE utf8_general_ci;;
USE petka;

--
-- Table structure for table `oauth_access_token`
--
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token`
(
    `token_id`          varchar(255)    DEFAULT NULL,
    `token`             varbinary(4096) DEFAULT NULL,
    `authentication_id` varchar(255)    DEFAULT NULL,
    `user_name`         varchar(255)    DEFAULT NULL,
    `client_id`         varchar(255)    DEFAULT NULL,
    `authentication`    varbinary(4096) DEFAULT NULL,
    `refresh_token`     varchar(255)    DEFAULT NULL
) ENGINE = InnoDB;


--
-- Table structure for table `oauth_client_details`
--
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`
(
    `client_id`               varchar(256) NOT NULL,
    `resource_ids`            varchar(256)  DEFAULT NULL,
    `client_secret`           varchar(256)  DEFAULT NULL,
    `scope`                   varchar(256)  DEFAULT NULL,
    `authorized_grant_types`  varchar(256)  DEFAULT NULL,
    `web_server_redirect_uri` varchar(256)  DEFAULT NULL,
    `authorities`             varchar(256)  DEFAULT NULL,
    `access_token_validity`   int(11)       DEFAULT NULL,
    `refresh_token_validity`  int(11)       DEFAULT NULL,
    `additional_information`  varchar(4096) DEFAULT NULL,
    `autoapprove`             varchar(256)  DEFAULT NULL,
    PRIMARY KEY (`client_id`)
) ENGINE = InnoDB;



--
-- Table structure for table `oauth_client_token`
--
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token`
(
    `token_id`          varchar(255)    DEFAULT NULL,
    `token`             varbinary(1024) DEFAULT NULL,
    `authentication_id` varchar(255)    DEFAULT NULL,
    `user_name`         varchar(255)    DEFAULT NULL,
    `client_id`         varchar(255)    DEFAULT NULL
) ENGINE = InnoDB;


--
-- Table structure for table `oauth_code`
--
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code`
(
    `code`           varchar(255)    DEFAULT NULL,
    `authentication` varbinary(1024) DEFAULT NULL
) ENGINE = InnoDB;


--
-- Table structure for table `oauth_refresh_token`
--
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token`
(
    `token_id`       varchar(255)    DEFAULT NULL,
    `token`          varbinary(4096) DEFAULT NULL,
    `authentication` varbinary(4096) DEFAULT NULL
) ENGINE = InnoDB;



--
-- Table structure for table `role`
--
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `description` varchar(255) DEFAULT NULL,
    `role_name`   varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;


--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `email`    varchar(255) DEFAULT NULL,
    `password` varchar(255) DEFAULT NULL,
    `username` varchar(255) DEFAULT NULL,
    `created`  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    `updated`  TIMESTAMP    NULL ON UPDATE CURRENT_TIMESTAMP,
    `lastLogin` TIMESTAMP NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;


--
-- Table structure for table `user_role`
--
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    KEY `uidx_user_role` (`user_id`, `role_id`),
    FOREIGN KEY fk_user_role_user (user_id) REFERENCES user (id),
    FOREIGN KEY fk_user_role_role (role_id) REFERENCES role (id)
) ENGINE = InnoDB;


-- #################### Loading data ####################
-- $2a$10$Ed6BsruRFx3to7bTIBOT4.u.Aune52A7cpsSnIpzr9G1Wjkm6Agqe bcrypted petka
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`,
                                    `web_server_redirect_uri`, `authorities`, `access_token_validity`,
                                    `refresh_token_validity`, `additional_information`, `autoapprove`)
VALUES ('petkaclient', NULL, '$2a$10$Ed6BsruRFx3to7bTIBOT4.u.Aune52A7cpsSnIpzr9G1Wjkm6Agqe', 'read,write',
        'password,refresh_token,client_credentials,authorization_code', NULL, 'ROLE_CLIENT, ROLE_TRUSTED_CLIENT', 900,
        2592000, NULL, NULL);


INSERT INTO `role` (`description`, `role_name`)
VALUES ('Admin User - Has permission to perform admin tasks', 'ADMIN_USER'),
       ('Standard User - Has no admin rights', 'STANDARD_USER');



INSERT INTO `user` (`email`, `password`, `username`)
VALUES ('petka@petka.com', '$2a$10$Ed6BsruRFx3to7bTIBOT4.u.Aune52A7cpsSnIpzr9G1Wjkm6Agqe', 'admin'),
       ('petka@petka.com', '$2a$10$Ed6BsruRFx3to7bTIBOT4.u.Aune52A7cpsSnIpzr9G1Wjkm6Agqe', 'petka');


INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ((select u.id from user u where u.username = 'admin'),
        (select r.id from role r where r.role_name = 'ADMIN_USER'));
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ((select u.id from user u where u.username = 'admin'),
        (select r.id from role r where r.role_name = 'STANDARD_USER'));
INSERT INTO `user_role` (`user_id`, `role_id`)
VALUES ((select u.id from user u where u.username = 'petka'),
        (select r.id from role r where r.role_name = 'STANDARD_USER'));
