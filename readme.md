# Lancer le Projet

Cette section explique comment configurer et démarrer le projet localement, y compris les étapes pour démarrer Docker et lancer les conteneurs associés.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé les éléments suivants sur votre machine :

- **Docker** : [Télécharger et installer Docker](https://docs.docker.com/get-docker/)
- **Docker Compose** : Souvent inclus avec Docker, mais vérifiez que vous avez la dernière version.
- **Java JDK** : Assurez-vous d'avoir la version nécessaire de JDK installée.
- **Maven** : Pour la gestion des dépendances Java.

## Étapes pour Lancer le Projet

### 1. Cloner le Repository

Clonez le repository sur votre machine locale à l'aide de la commande suivante :

```bash
git clone https://github.com/mikael750/Medilabo_OCProjet9.git
cd Medilabo_OCProjet9
```
### 2. Construire les Images Docker
Avant de démarrer les services, construisez les images Docker. Cela permet de s'assurer que tous les services sont prêts à être lancés.
```bash
docker-compose build
```

### 3. Démarrer Docker Compose
Une fois les images construites, vous pouvez démarrer les conteneurs Docker avec la commande suivante :
```bash
docker-compose up
```
Cette commande lance tous les services définis dans votre fichier docker-compose.yml.

Optionnel : Démarrage en arrière-plan
Si vous souhaitez démarrer les conteneurs en arrière-plan, utilisez le flag -d :
```bash
docker-compose up -d
```
### 4. Accéder à l'Application
Une fois Docker Compose en cours d'exécution,
vous pouvez accéder à l'application
via votre navigateur Web.

Client UI : http://localhost:8082/login

### 5. Arrêter les Services
Pour arrêter les services Docker, utilisez la commande suivante :
```bash
docker-compose down
```


# Green Code Recommendations

Ce projet s'engage à adopter des pratiques de "Green Code" pour réduire son empreinte environnementale et améliorer son efficacité énergétique. Voici les recommandations que nous avons mises en place ou que nous envisageons d'intégrer :

## Optimisation du Code
- **Refactorisation du Code** : Réduire les redondances, éliminer le code inutile et optimiser les boucles pour minimiser la consommation de ressources.
- **Utilisation d'Algorithmes Efficaces** : Choisir des algorithmes ayant une complexité en temps et en espace réduite.

## Gestion des Ressources
- **Fermeture des Connexions** : S'assurer que toutes les connexions à des ressources externes sont correctement fermées après utilisation.
- **Utilisation de Pools de Connexions** : Réduire le coût de création de nouvelles connexions en utilisant des pools de connexions pour les bases de données et autres services.
- **Gestion Optimisée de la Mémoire** : Minimiser l'utilisation de la mémoire en optimisant les collections et en supprimant les références inutilisées.

## Optimisation des Infrastructures
- **Réduction du Nombre de Conteneurs** : Consolidation des services pour réduire le nombre de conteneurs Docker en fonctionnement, ce qui diminue l'utilisation des ressources.
- **Scalabilité Dynamique** : Mettre en place une scalabilité dynamique pour n'utiliser des ressources qu'en fonction des besoins réels.
- **Utilisation d'Images Docker Légères** : Adopter des images Docker plus légères pour réduire l'espace disque et le temps de démarrage des conteneurs.

## Optimisation des Requêtes Réseau
- **Minimisation des Appels API** : Réduire le nombre de requêtes réseau en regroupant les appels API ou en utilisant le cache là où c’est possible.
- **Compression des Réponses HTTP** : Activer la compression des réponses HTTP pour réduire la bande passante et accélérer les temps de réponse.

## Surveillance et Analyse
- **Monitoring de la Performance** : Mettre en place des outils de monitoring pour suivre la consommation des ressources et identifier les optimisations possibles.
- **Audit du Code** : Effectuer régulièrement des audits de code pour identifier et corriger les inefficacités.

## Pratiques Écologiques
- **Choisir un Hébergement Écologique** : Opter pour des fournisseurs d'hébergement utilisant des énergies renouvelables, lorsque cela est possible.
- **Sensibilisation de l'Équipe** : Former l'équipe de développement aux pratiques de Green Code pour les intégrer au quotidien.

## Amélioration des Tests
- **Optimisation des Tests** : Exécuter les tests en parallèle et utiliser des mocks pour éviter de charger des ressources inutiles.
- **Tests Automatisés de Performance** : Intégrer des tests de performance dans le pipeline CI/CD pour identifier en continu les parties du code nécessitant une optimisation.

