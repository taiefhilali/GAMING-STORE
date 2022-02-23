-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 23 fév. 2022 à 19:53
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `levelup`
--

-- --------------------------------------------------------

--
-- Structure de la table `administrateur`
--

DROP TABLE IF EXISTS `administrateur`;
CREATE TABLE IF NOT EXISTS `administrateur` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `cin` varchar(8) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `administrateur`
--

INSERT INTO `administrateur` (`id_user`, `cin`) VALUES
(4, '07227308');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `nom_categorie` varchar(254) NOT NULL,
  PRIMARY KEY (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_categorie`, `nom_categorie`) VALUES
(1, 'Souris'),
(2, 'Bureaux'),
(35, 'Accessoires PC');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `sexe` varchar(254) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_user`, `sexe`) VALUES
(1, 'femme'),
(2, 'femme'),
(9, 'femme');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `prix_livraison` double NOT NULL,
  `date_commande` date NOT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `fk_user1` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `id_user`, `prix_livraison`, `date_commande`) VALUES
(8, 1, 25, '2017-03-31'),
(9, 9, 35, '2006-12-11'),
(11, 2, 15, '2017-03-31');

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `idc` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` text NOT NULL,
  `label` varchar(255) NOT NULL,
  `resp` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_post` int(11) NOT NULL,
  PRIMARY KEY (`idc`),
  KEY `fk_comment_post` (`id_post`),
  KEY `fk_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`idc`, `contenu`, `label`, `resp`, `id_user`, `id_post`) VALUES
(63, 'secondcomment', 'commtwo', 10, 1, 70);

-- --------------------------------------------------------

--
-- Structure de la table `detail_commande`
--

DROP TABLE IF EXISTS `detail_commande`;
CREATE TABLE IF NOT EXISTS `detail_commande` (
  `id_elemC` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `id_commande` int(11) NOT NULL,
  `Quantite` int(11) NOT NULL,
  PRIMARY KEY (`id_elemC`),
  UNIQUE KEY `id` (`id`,`id_commande`),
  KEY `fk_commande1` (`id_commande`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `id_facture` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `prix_total` varchar(255) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_facture`),
  KEY `fk_facture` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`id_facture`, `date`, `prix_total`, `id_user`) VALUES
(26, '2015-03-31', '90.990dt', 2);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `cin` varchar(8) NOT NULL,
  `nom_marque` varchar(254) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id_user`, `cin`, `nom_marque`) VALUES
(2, '07227308', 'Arvea');

-- --------------------------------------------------------

--
-- Structure de la table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
CREATE TABLE IF NOT EXISTS `livraison` (
  `id_livraison` int(11) NOT NULL AUTO_INCREMENT,
  `id_commande` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `date` date NOT NULL,
  `etat_livraison` varchar(254) NOT NULL,
  PRIMARY KEY (`id_livraison`),
  KEY `fk_liv_user` (`id_user`),
  KEY `fk_commande` (`id_commande`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livraison`
--

INSERT INTO `livraison` (`id_livraison`, `id_commande`, `id_user`, `date`, `etat_livraison`) VALUES
(8, 9, 3, '2015-03-31', 'livré'),
(9, 8, 3, '2017-03-31', 'en cours');

-- --------------------------------------------------------

--
-- Structure de la table `livreur`
--

DROP TABLE IF EXISTS `livreur`;
CREATE TABLE IF NOT EXISTS `livreur` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `cin` varchar(8) NOT NULL,
  `vehicule` varchar(254) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livreur`
--

INSERT INTO `livreur` (`id_user`, `cin`, `vehicule`) VALUES
(3, '07227308', '00000');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `id_panier` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_panier`),
  UNIQUE KEY `id_panier` (`id_panier`,`id_user`),
  UNIQUE KEY `id_user` (`id_user`),
  KEY `fk_cl` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id_panier`, `id_user`) VALUES
(1, 1),
(8, 2);

-- --------------------------------------------------------

--
-- Structure de la table `panier_elem`
--

DROP TABLE IF EXISTS `panier_elem`;
CREATE TABLE IF NOT EXISTS `panier_elem` (
  `id_elem` int(11) NOT NULL AUTO_INCREMENT,
  `id_panier` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id_elem`),
  UNIQUE KEY `id_panier` (`id_panier`,`id`),
  UNIQUE KEY `id_panier_2` (`id_panier`,`id`),
  KEY `fk_produit1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `datep` date NOT NULL,
  `id_user` int(11) NOT NULL,
  `nblike` int(11) NOT NULL,
  `nbdislike` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkpost_user` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `post`
--

INSERT INTO `post` (`id`, `title`, `content`, `datep`, `id_user`, `nblike`, `nbdislike`) VALUES
(69, 'sujet7', 'JEUX VIDEOS', '2017-03-31', 1, 30, 12),
(70, 'Sujet2', ' fournisseur', '2017-03-31', 1, 4, 1);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(254) NOT NULL,
  `reference` varchar(254) NOT NULL,
  `id_categorie` int(11) NOT NULL,
  `prix` double NOT NULL,
  `description` text NOT NULL,
  `id_user` int(11) NOT NULL,
  `promotion` double NOT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `fk_CategorieProduit` (`id_categorie`),
  KEY `fk_idFournisseur` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `nom`, `reference`, `id_categorie`, `prix`, `description`, `id_user`, `promotion`) VALUES
(47, 'HyperX Cloud II', '0x001', 1, 350, 'Casque que pour les gamers', 1, 50),
(49, 'Razer Kraken Pro v7', '0x005', 1, 650, 'Casque que pour les gamers', 1, 20);

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `id_reclamation` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `id_livraison` int(11) NOT NULL,
  `description` varchar(254) NOT NULL,
  PRIMARY KEY (`id_reclamation`),
  KEY `fk_rec_user` (`id_user`),
  KEY `fk_rec_livraison` (`id_livraison`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_reclamation`, `id_user`, `id_livraison`, `description`) VALUES
(5, 1, 8, 'nkcnkdsnckdsncdk'),
(6, 1, 8, 'lk niodsjcpoepckdvnv');

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `quantite` int(11) NOT NULL,
  `etat` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `stock`
--

INSERT INTO `stock` (`id`, `nom`, `quantite`, `etat`) VALUES
(56, 'accessoire', 50, 'en stock');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(254) NOT NULL,
  `password` varchar(254) NOT NULL,
  `role` varchar(254) NOT NULL,
  `nom` varchar(254) NOT NULL,
  `prenom` varchar(254) NOT NULL,
  `adresse` varchar(254) NOT NULL,
  `tel` varchar(254) NOT NULL,
  `dns` date NOT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `tentative` int(11) NOT NULL DEFAULT '0',
  `limite` datetime DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id_user`, `email`, `password`, `role`, `nom`, `prenom`, `adresse`, `tel`, `dns`, `locked`, `tentative`, `limite`) VALUES
(1, 'bayoudh.hazem@gmail.com', '){==[(>~', 'client', 'Bayoudh', 'Hazem', 'Ben arouse/Mourouj 1/Rue 234/2074', '26386558', '2015-03-31', 0, 0, '0000-00-00 00:00:00'),
(2, 'bargaoui.iskander@gmail.com', '){==[(>~', 'fournisseur', 'Bargaoui', 'Iskander', 'Ben arouse/Mourouj 1/Rue 234/2074', '26386558', '2015-03-31', 1, 0, NULL),
(3, 'nouira.amal@gmail.com', '){==[(>~', 'livreur', 'Nouira', 'Amal', 'Ben arouse/Mourouj 1/Rue 234/2074', '26386558', '2015-03-31', 0, 0, '2022-02-23 08:00:00'),
(4, 'beldi.mariem@gmail.com', '){==[(>~', 'administrateur', 'Beldi', 'Mariem', 'Ben arouse/Mourouj 1/Rue 234/2074', '26386558', '2015-03-31', 0, 0, '2022-02-23 06:30:11'),
(9, 'siwar.tabbena@gmail.com', '){==[(>~', 'client', 'tabbena', 'siwar', 'Ben arouse/Mourouj 1/Rue 234/2074', '26386558', '2017-03-31', 1, 0, '2022-02-23 09:00:00'),
(12, 'labyedh.slimen@gmail.com', ';+=;', 'client', 'labyedh', 'slimen', 'Ben arouse/Mourouj 1/Rue 234/2074', '26386558', '2017-03-31', 0, 0, '2022-02-23 10:00:00');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `administrateur`
--
ALTER TABLE `administrateur`
  ADD CONSTRAINT `FK_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_u` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fk_user1` FOREIGN KEY (`id_user`) REFERENCES `client` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_comment_post` FOREIGN KEY (`id_post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_comment_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `detail_commande`
--
ALTER TABLE `detail_commande`
  ADD CONSTRAINT `fk_commande1` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_commande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pr` FOREIGN KEY (`id`) REFERENCES `produit` (`id_produit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `fk_facture` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD CONSTRAINT `u` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `livraison`
--
ALTER TABLE `livraison`
  ADD CONSTRAINT `fk_commande` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`id_commande`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_liv_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `livreur`
--
ALTER TABLE `livreur`
  ADD CONSTRAINT `ul` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE;

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `fk_panier_user` FOREIGN KEY (`id_user`) REFERENCES `client` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `panier_elem`
--
ALTER TABLE `panier_elem`
  ADD CONSTRAINT `fk_panier` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_pr1` FOREIGN KEY (`id`) REFERENCES `produit` (`id_produit`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_CategorieProduit` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_idFournisseur` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fk_rec_livraison` FOREIGN KEY (`id_livraison`) REFERENCES `livraison` (`id_livraison`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_rec_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
