DROP TABLE IF EXISTS `tm_resource`;
CREATE TABLE `tm_resource`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `code`       varchar(64) NOT NULL,
    `path`       varchar(256) DEFAULT NULL,
    `name`       varchar(32)  DEFAULT NULL,
    `creater`    int(11)      DEFAULT NULL,
    `created_at` datetime     DEFAULT NULL,
    `modifier`   int(11)      DEFAULT NULL,
    `updated_at` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

DROP TABLE IF EXISTS `tm_account`;
CREATE TABLE `tm_account`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `email`      varchar(64) NOT NULL,
    `passwd`     varchar(64) NOT NULL,
    `name`       varchar(32) NOT NULL,
    `creater`    int(11)  DEFAULT NULL,
    `created_at` datetime DEFAULT NULL,
    `modifier`   int(11)  DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;