-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 13 nov. 2024 à 09:21
-- Version du serveur : 8.2.0
-- Version de PHP : 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `minis_creche`
--

-- --------------------------------------------------------

--
-- Structure de la table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
CREATE TABLE IF NOT EXISTS `candidate` (
  `id` bigint NOT NULL,
  `cv` text NOT NULL,
  `created_at` date NOT NULL,
  `email` varchar(70) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `motivation` text NOT NULL,
  `statut` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `candidate`
--

INSERT INTO `candidate` (`id`, `cv`, `created_at`, `email`, `first_name`, `last_name`, `mobile`, `motivation`, `statut`) VALUES
(34, 'chemin/vers/nouveau/cv.pdf', '2024-10-26', 'FAWZI.OUAHEB@GMAIL.COM', 'FAWZI', 'OUAHEB', '0123456789', 'Motivation pour le poste', 'Accepetée');

-- --------------------------------------------------------

--
-- Structure de la table `candidat_structure`
--

DROP TABLE IF EXISTS `candidat_structure`;
CREATE TABLE IF NOT EXISTS `candidat_structure` (
  `structure_id` bigint NOT NULL,
  `candidate_id` bigint NOT NULL,
  PRIMARY KEY (`structure_id`,`candidate_id`),
  KEY `FK2sluk5k7bpe94mriuumxmi5a5` (`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `candidat_structure`
--

INSERT INTO `candidat_structure` (`structure_id`, `candidate_id`) VALUES
(1, 34);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint NOT NULL,
  `access_level` int NOT NULL,
  `role_description` text NOT NULL,
  `role_name` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `access_level`, `role_description`, `role_name`) VALUES
(10, 0, 'Le compte administrateur aura tous les privilèges notamment pour la partie vitrine afin de modifier le contenu des sites vitrine', 'ADMINISTRATEUR'),
(11, 1, 'Le compte de la directrice lui donnera tous les privilèges sur la gestion administrative du personnel et des parents.', 'DIRECTRICE'),
(12, 2, 'Le compte de l\'employé lui permettra de gérer l\'administration des parents, à l\'exception de la validation des paiements et du traitement des dossiers de préinscription. Il aura également accès à son propre dossier.', 'EMPLOYE'),
(13, 3, 'Le compte parent lui permettra d\'accéder à son dossier dans le système d\'information, ainsi qu\'aux événements liés à la structure.', 'PARENT');

-- --------------------------------------------------------

--
-- Structure de la table `role_sequence`
--

DROP TABLE IF EXISTS `role_sequence`;
CREATE TABLE IF NOT EXISTS `role_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role_sequence`
--

INSERT INTO `role_sequence` (`next_val`) VALUES
(35);

-- --------------------------------------------------------

--
-- Structure de la table `structure`
--

DROP TABLE IF EXISTS `structure`;
CREATE TABLE IF NOT EXISTS `structure` (
  `id` bigint NOT NULL,
  `adresse` text NOT NULL,
  `capacity` int NOT NULL,
  `created_at` date NOT NULL,
  `lat` float NOT NULL,
  `log` float NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `statut` varchar(20) NOT NULL,
  `structure_name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `structure`
--

INSERT INTO `structure` (`id`, `adresse`, `capacity`, `created_at`, `lat`, `log`, `mobile`, `statut`, `structure_name`) VALUES
(1, '123 Rue Exemple', 20, '2024-10-26', 6.789, 1.2345, '0123456789', 'active', 'Nom de la crèche');

-- --------------------------------------------------------

--
-- Structure de la table `structure_sequence`
--

DROP TABLE IF EXISTS `structure_sequence`;
CREATE TABLE IF NOT EXISTS `structure_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `structure_sequence`
--

INSERT INTO `structure_sequence` (`next_val`) VALUES
(2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `candidat_structure`
--
ALTER TABLE `candidat_structure`
  ADD CONSTRAINT `FK2sluk5k7bpe94mriuumxmi5a5` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`id`),
  ADD CONSTRAINT `FKjks11byu2aofhjlqnmkgechyo` FOREIGN KEY (`structure_id`) REFERENCES `structure` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
