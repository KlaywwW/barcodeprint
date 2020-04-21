/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.5.28 : Database - infogathering
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`infogathering` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `infogathering`;

/*Table structure for table `education` */

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `eduid` int(11) DEFAULT NULL,
  `edu` varchar(50) DEFAULT NULL,
  `schoolname` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `studytime` datetime DEFAULT NULL,
  `isend` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `education` */

insert  into `education`(`eduid`,`edu`,`schoolname`,`department`,`studytime`,`isend`) values (1,'到家说吧','堵得慌','回电话','2020-04-06 00:00:00','毕业');

/*Table structure for table `experience` */

DROP TABLE IF EXISTS `experience`;

CREATE TABLE `experience` (
  `exid` int(11) DEFAULT NULL,
  `comname` varchar(50) DEFAULT NULL,
  `workpost` varchar(50) DEFAULT NULL,
  `workcontent` varchar(100) DEFAULT NULL,
  `treatment` varchar(50) DEFAULT NULL,
  `workstart` datetime DEFAULT NULL,
  `workend` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `experience` */

/*Table structure for table `family` */

DROP TABLE IF EXISTS `family`;

CREATE TABLE `family` (
  `fmid` int(11) DEFAULT NULL,
  `appellation` varchar(100) DEFAULT NULL,
  `fmname` varchar(50) DEFAULT NULL,
  `fmage` int(11) DEFAULT NULL,
  `fmphone` varchar(100) DEFAULT NULL,
  `fmcom` varchar(50) DEFAULT NULL,
  `fmpost` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `family` */

insert  into `family`(`fmid`,`appellation`,`fmname`,`fmage`,`fmphone`,`fmcom`,`fmpost`) values (1,'拿到','地方',80,'12345678944','对对对今年','放假多久');

/*Table structure for table `infomation` */

DROP TABLE IF EXISTS `infomation`;

CREATE TABLE `infomation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `marriage` varchar(50) DEFAULT NULL,
  `nativePlace` varchar(100) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `houseRegister` varchar(100) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `addressdesc` varchar(100) DEFAULT NULL,
  `post` varchar(50) DEFAULT NULL,
  `militaryService` varchar(100) DEFAULT NULL,
  `sortms` varchar(50) DEFAULT NULL,
  `startms` varchar(50) DEFAULT NULL,
  `endms` varchar(50) DEFAULT NULL,
  `reasonms` varchar(100) DEFAULT NULL,
  `urgname` varchar(50) DEFAULT NULL,
  `urgphone` varchar(100) DEFAULT NULL,
  `expertise` varchar(100) DEFAULT NULL,
  `pressure` varchar(100) DEFAULT NULL,
  `presInfo` varchar(100) DEFAULT NULL,
  `software` varchar(100) DEFAULT NULL,
  `certificate` varchar(100) DEFAULT NULL,
  `cername` varchar(100) DEFAULT NULL,
  `worktime` varchar(50) DEFAULT NULL,
  `choosetime` varchar(50) DEFAULT NULL,
  `shift` varchar(100) DEFAULT NULL,
  `workplace` varchar(100) DEFAULT NULL,
  `field` varchar(100) DEFAULT NULL,
  `mna` varchar(100) DEFAULT NULL,
  `wages` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `infomation` */

insert  into `infomation`(`id`,`name`,`sex`,`birthday`,`userId`,`marriage`,`nativePlace`,`age`,`email`,`phone`,`houseRegister`,`address`,`addressdesc`,`post`,`militaryService`,`sortms`,`startms`,`endms`,`reasonms`,`urgname`,`urgphone`,`expertise`,`pressure`,`presInfo`,`software`,`certificate`,`cername`,`worktime`,`choosetime`,`shift`,`workplace`,`field`,`mna`,`wages`,`date`) values (1,'测试1','男','2020-04-09 00:00:00','123456789456123789','未婚','不大好动不动就',0,'','79456123775','绝大部分九分裤排好队','租屋','不大不小喝点酒.','机械助理工程师','毕役','不到好多','2020-04','2020-06','','都会变得','78945612546','人资','有','房贷,养育子女,卡债,其他,','Word,Excel,Office,其他,','是','把电话费v是','即刻','','日班,大夜班,可配合轮班,','回电话大V','on','百度八点半','800000','2020-04-06 00:00:00');

/*Table structure for table `infoquesnaire` */

DROP TABLE IF EXISTS `infoquesnaire`;

CREATE TABLE `infoquesnaire` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isfill` varchar(50) DEFAULT NULL,
  `fillname` varchar(50) DEFAULT NULL,
  `filltime` datetime DEFAULT NULL,
  `healthgrade` varchar(50) DEFAULT NULL,
  `chronicillness` varchar(50) DEFAULT NULL,
  `illnessname` varchar(50) DEFAULT NULL,
  `occupationdisaster` varchar(100) DEFAULT NULL,
  `accident` varchar(50) DEFAULT NULL,
  `accidentinfo` varchar(100) DEFAULT NULL,
  `certificate` varchar(50) DEFAULT NULL,
  `certificateinfo` varchar(100) DEFAULT NULL,
  `certificategrade` varchar(100) DEFAULT NULL,
  `contract` varchar(100) DEFAULT NULL,
  `dispute` varchar(100) DEFAULT NULL,
  `pregnant` varchar(100) DEFAULT NULL,
  `insomnia` varchar(100) DEFAULT NULL,
  `medicine` varchar(100) DEFAULT NULL,
  `willing` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `infoquesnaire` */

insert  into `infoquesnaire`(`id`,`isfill`,`fillname`,`filltime`,`healthgrade`,`chronicillness`,`illnessname`,`occupationdisaster`,`accident`,`accidentinfo`,`certificate`,`certificateinfo`,`certificategrade`,`contract`,`dispute`,`pregnant`,`insomnia`,`medicine`,`willing`,`name`,`userId`) values (1,'是','多喝点VB的','2020-04-08 00:00:00','没有差别','否','','是','是','好的好的别的','否,但具有辅会鉴定字号','','','否','是','否','是','是','否','好的吧都不会','123456789456123789');

/*Table structure for table `promise` */

DROP TABLE IF EXISTS `promise`;

CREATE TABLE `promise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `promise` */

insert  into `promise`(`id`,`name`,`sex`,`age`,`userId`,`phone`,`date`) values (1,'测试1','♀',45,'123456789456123789','12345678945','2020-04-06 07:49:17');

/*Table structure for table `warrant` */

DROP TABLE IF EXISTS `warrant`;

CREATE TABLE `warrant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `warname` varchar(50) DEFAULT NULL,
  `waruserId` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `userId` varchar(100) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `warrant` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
