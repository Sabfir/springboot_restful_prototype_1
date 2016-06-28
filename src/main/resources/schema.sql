-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema userdb1
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema userdb1
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS userdb DEFAULT CHARACTER SET utf8 ;
USE userdb ;

-- -----------------------------------------------------
-- Table `userdb1`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS userdb.`role` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `userdb1`.`user_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS userdb.`user_detail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `is_active` BIT(1) NULL DEFAULT b'1',
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `userdb1`.`user_deatail_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS userdb.user_detail_role (
  `user_detail_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_detail_id`, `role_id`),
  INDEX `fk_userdetailrole_userdetail_idx` (`role_id` ASC),
  CONSTRAINT `fk_userdetailrole_role`
  FOREIGN KEY (`role_id`)
  REFERENCES userdb.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userdetailrole_userdetail`
  FOREIGN KEY (`user_detail_id`)
  REFERENCES userdb.`user_detail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


#
# CREATE SCHEMA  IF NOT EXISTS `userdb`;
# USE `userdb`;
#
# --
# -- Table structure for table `user_detail`
# --
#
# DROP TABLE IF EXISTS `user_detail`;
# CREATE TABLE `user_detail` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `username` varchar(45) NULL DEFAULT NULL,
#   `password` varchar(45) NULL DEFAULT NULL,
#   `is_active` bit(1) DEFAULT b'1',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
#
# DROP TABLE IF EXISTS `role`;
# CREATE TABLE `role` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `name` varchar(45) NULL DEFAULT NULL,
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
#
# CREATE TABLE IF NOT EXISTS `userdb`.`user_detail_role` (
#   `user_detail_id` INT(11) UNSIGNED NOT NULL,
#   `role_id` INT(11) UNSIGNED NOT NULL,
#   PRIMARY KEY (`user_detail_id`, `role_id`),
#   INDEX `fk_user_detailrole_role_idx` (`role_id` ASC),
#   CONSTRAINT `fk_user_detailrole_user_detail`
#   FOREIGN KEY (`user_detail_id`)
#   REFERENCES `userdb`.`user_detail` (`id`)
#     ON DELETE CASCADE
#     ON UPDATE CASCADE,
#   CONSTRAINT `fk_user_detailrole_role`
#   FOREIGN KEY (`role_id`)
#   REFERENCES `userdb`.`role` (`id`)
#     ON DELETE CASCADE
#     ON UPDATE CASCADE)
#   ENGINE = InnoDB
#   DEFAULT CHARACTER SET = utf8;
