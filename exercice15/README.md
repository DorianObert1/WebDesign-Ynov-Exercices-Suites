# Exercice 15 - Authentification JWT pour un service de gestion de projets

## Utilisateurs

alice / password123

bob / secret456

## Endpoints

POST - /api/auth/login : retourne un token JWT

GET - /api/projects : retourne les projets de l'utilisateur connecté (JWT requis)

## Tests

Toutes les routes ont été testées avec Postman.

POST /api/auth/login :
```json
{
  "username": "alice",
  "password": "password123"
}
```

Réponse :
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

GET /api/projects avec header Authorization: Bearer {token} :
```json
{
  "projects": ["Projet A", "Projet B"]
}
```

Erreur 401 (token absent ou invalide) :
```json
{
  "error": "UNAUTHORIZED"
}
```
