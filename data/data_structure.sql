CREATE SCHEMA `colewe` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `colewe`.`Chengyu` (
  `ID` INT NOT NULL,
  `Abbr` VARCHAR(45) NULL,
  `Chinese` VARCHAR(45) NULL,
  `ChineseExplanation` VARCHAR(45) NULL,
  `EnglishLiteral` VARCHAR(45) NULL,
  `EnglishFigurative` VARCHAR(45) NULL,
  `Pinyin` VARCHAR(45) NULL,
  `Example` VARCHAR(255) NULL,
  `ExampleTranslation` VARCHAR(255) NULL,
  `Origin` VARCHAR(255) NULL,
  `OriginTranslation` VARCHAR(255) NULL,
  `Frequency` INT NULL,
  PRIMARY KEY (`ID`));

CREATE TABLE `colewe`.`Tags` (
  `ID` INT NOT NULL,
  `Tag` VARCHAR(45) NULL,
  PRIMARY KEY (`ID`));


CREATE TABLE `colewe`.`ChengyuTag` (
  `ChengyuID` INT NOT NULL,
  `TagID` INT NOT NULL,
  PRIMARY KEY (`ChengyuID`, `TagID`),
  INDEX `fk_ChengyuTag_2_idx` (`TagID` ASC),
  CONSTRAINT `fk_ChengyuTag_1`
    FOREIGN KEY (`ChengyuID`)
    REFERENCES `colowe`.`Chengyu` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ChengyuTag_2`
    FOREIGN KEY (`TagID`)
    REFERENCES `colowe`.`Tags` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
