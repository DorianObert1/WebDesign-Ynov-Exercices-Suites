# Exercice 14 - Sécurisation d'une API de réservation de salles

Le fichier schema.sql se trouve dans le dossier src/main/ressources.

## Lancer la base de données

Pour lancer la base de données :
```bash
docker compose up -d
```

## Utilisateurs

user / user → rôle USER

admin / admin → rôle ADMIN

## Endpoints

GET - /api/rooms : liste toutes les salles (USER et ADMIN)

POST - /api/rooms : crée une salle (ADMIN uniquement)

DELETE - /api/rooms/{id} : supprime une salle (ADMIN uniquement)

## Tests

Toutes les routes ont été testées avec Postman.

POST /api/rooms (en tant qu'admin) :
```json
{
  "name": "Salle A"
}
```

Erreur 401 (sans authentification) :
```json
{
  "error": "UNAUTHORIZED",
  "message": "Vous devez vous connecter pour accéder à cette ressource"
}
```

Erreur 403 (user tente un POST) :
```json
{
  "error": "FORBIDDEN",
  "message": "Vous n'avez pas les droits nécessaires pour accéder à cette ressource"
}
```
