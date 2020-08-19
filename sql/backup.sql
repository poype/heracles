CREATE TABLE `heracles`.`application` (
`application_id` CHAR(30) NOT NULL,
`domain_id` CHAR(30) NOT NULL,
`application_name` VARCHAR(50) NOT NULL,
`application_type` INT NOT NULL,
`description` VARCHAR(200) NOT NULL,
`code_repository` VARCHAR(200) NOT NULL,
`dev_owner` VARCHAR(50) NOT NULL,
`devs` VARCHAR(500) NULL,
`qa_owner` VARCHAR(50) NOT NULL,
`qas` VARCHAR(500) NULL,
`belong_system` VARCHAR(50) NOT NULL,
`belong_business` VARCHAR(50) NOT NULL,
PRIMARY KEY (`application_id`),
UNIQUE INDEX `application_name_UNIQUE` (`application_name` ASC));

CREATE TABLE `heracles`.`application_config` (
`config_id` CHAR(30) NOT NULL,
`application_id` CHAR(30) NULL,
`config_type` INT NULL,
`config_value` VARCHAR(500) NULL,
PRIMARY KEY (`config_id`),
INDEX `application_id_index` (`application_id` ASC));

CREATE TABLE `heracles`.`java_application` (
`application_id` CHAR(30) NOT NULL,
`base_code_branch` VARCHAR(100) NULL,
`config_file_path` VARCHAR(200) NULL,
`jar_path` VARCHAR(200) NULL,
`pom_path` VARCHAR(200) NULL,
`mvn_command` VARCHAR(200) NULL,
PRIMARY KEY (`application_id`));

CREATE TABLE `heracles`.`sprint` (
`sprint_id` CHAR(30) NOT NULL,
`sprint_name` VARCHAR(50) NOT NULL,
`description` VARCHAR(500) NULL,
`release_date` CHAR(8) NOT NULL,
`status` INT NULL,
`sit_env_name` VARCHAR(10) NULL,
`create_time` DATETIME NULL,
`update_time` DATETIME NULL,
PRIMARY KEY (`sprint_id`));
