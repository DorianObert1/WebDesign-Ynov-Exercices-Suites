# Exercice 12 - API de gestion des commandes

Le fichier schema.sql se trouve dans le dossier src/main/ressources.

## Lancer la base de données

Pour lancer la base de données :
```bash
docker compose up -d
```

## Endpoints

GET - /api/orders : liste toutes les commandes

GET - /api/orders/{id} : récupère une commande par id

POST - /api/orders : crée une commande (statut PENDING par défaut)

PUT - /api/orders/{id} : met à jour le statut d'une commande

DELETE - /api/orders/{id} : supprime une commande

GET - /api/orders/search?status=SHIPPED : recherche les commandes par statut

GET - /api/orders/paged?page=0&size=5 : retourne les commandes paginées

GET - /api/orders/customer/{customerName} : retourne les commandes d'un client

## Tests

Toutes les routes ont été testées avec Postman.

POST /api/orders :
```json
{
  "customerName": "Alice",
  "totalAmount": 149.99
}
```

PUT /api/orders/1 :
```json
{
  "status": "SHIPPED"
}
```

