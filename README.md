# 🎵 OurBlindTest

**OurBlindTest** est une application Android de quiz musical (Blind Test) développée dans le cadre d'un défi technique étudiant. Testez vos connaissances musicales en devinant les titres diffusés en temps réel !

## ℹ️ Contexte du projet

Ce projet a été réalisé par une équipe de **3 étudiants** à l'**ESEO Angers**.
* **Contrainte de temps :** Développé lors d'un "Hackathon" de **12 heures**.
* **Objectif :** Produire un MVP (Minimum Viable Product) fonctionnel exploitant une API externe et la persistance de données.

## ✨ Fonctionnalités

* **Streaming Audio :** Utilisation de l'**API Deezer** pour diffuser des extraits musicaux de 30 secondes.
* **Système de Score :** Sauvegarde des meilleurs scores en local (stockage sur le téléphone) pour conserver l'historique des records.
* **Gestion de contenu flexible :** Architecture permettant l'ajout facile de nouvelles musiques et catégories sans toucher au code principal.

## 🛠️ Stack Technique

* **Langage :** Kotlin
* **Version Android :** Android 10 (API 29)
* **API :** Deezer API (Streaming)
* **Données :** JSON Parsing

## ⚙️ Comment ajouter des musiques ?

Le projet est conçu pour être facilement extensible. Voici la procédure pour ajouter du contenu :

### 1. Ajouter les données musicales (`songs.json`)
Toutes les musiques sont référencées dans un fichier JSON local.
* 📂 **Emplacement :** `res/raw/songs.json`
* **Action :** Ajoutez vos entrées en respectant la structure JSON existante (ID Deezer, titre, artiste, etc.).

### 2. Mettre à jour les Thèmes (`Theme.kt`)
Si vous avez ajouté de nouvelles catégories ou sous-catégories dans le JSON, vous devez les déclarer dans le code.
* 📂 **Emplacement :** `model/Theme.kt`
* **Action :** Ajoutez les nouveaux noms de thèmes et sous-thèmes dans l'**Enum** correspondant.

## 👥 Auteurs

* **Matthieu Lamidon**
* **Barthélémy Coutard**
* **Paul guettier**
