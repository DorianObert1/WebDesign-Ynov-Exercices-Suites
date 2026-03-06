# Exercice 11 - Gestion des utilisateurs

Le fichier schema.sql se trouve dans le dossier src/main/ressources.

## Lancer la base de données

Pour lancer la base de données :
```bash
docker compose up -d
```

## Endpoints

GET - /api/users : liste tous les utilisateurs

GET - /api/users/{id} : récupère un utilisateur par id

POST - /api/users : crée un utilisateur

PUT - /api/users/{id} : met à jour un utilisateur

DELETE - /api/users/{id} : supprime un utilisateur

## Tests

Toutes les routes ont été testées avec Postman.

POST /api/users :
```json
{
  "name": "Alice",
  "email": "alice@mail.com",
  "active": true
}
```

PUT /api/users/{id} :
```json
{
  "name": "Alice Updated",
  "email": "alice.updated@mail.com",
  "active": false
}
```
