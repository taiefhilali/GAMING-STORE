-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 15 fév. 2022 à 20:02
-- Version du serveur : 10.4.22-MariaDB
-- Version de PHP : 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `forum`
--

-- --------------------------------------------------------

--
-- Structure de la table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `datep` date NOT NULL,
  `id_user` int(11) NOT NULL,
  `idc` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `post`
--

INSERT INTO `post` (`id`, `title`, `content`, `datep`, `id_user`, `idc`) VALUES
(1, 'hahaha', 'videogame', '1970-01-02', 2, 4),
(5, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '1970-01-03', 0, 0),
(6, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '1970-01-20', 0, 0),
(7, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '1970-01-03', 0, 0),
(8, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '1970-01-20', 0, 0),
(9, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-12', 0, 0),
(10, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-12', 0, 0),
(11, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(12, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(13, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(14, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(15, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(16, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(17, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(18, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(19, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(20, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(21, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(23, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-13', 0, 0),
(24, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(26, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-13', 0, 0),
(30, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-15', 3, 4),
(31, 'GAMING', 'ACCESOIRES ET JEUX VIDEOS', '2022-02-15', 1, 2),
(32, 'TAIEF', ' ETCETCETCJEUX VIDEOS', '2022-02-15', 3, 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
