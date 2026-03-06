# Exercice 13 - API de gestion des produits avec stock

Le fichier schema.sql se trouve dans le dossier src/main/ressources.

## Lancer la base de données

Pour lancer la base de données :
```bash
docker compose up -d
```

## Endpoints

GET - /api/products : liste tous les produits

GET - /api/products/{id} : récupère un produit par id

POST - /api/products : crée un produit

PUT - /api/products/{id} : met à jour un produit

DELETE - /api/products/{id} : supprime un produit

GET - /api/products/search?name=phone : recherche des produits par nom

PUT - /api/products/{id}/buy?quantity=5 : réduit le stock d'un produit

## Tests

Toutes les routes ont été testées avec Postman.

POST /api/products :
```json
{
  "name": "iPhone",
  "price": 999.99,
  "stock": 50
}
```

PUT /api/products/1 :
```json
{
  "name": "iPhone Updated",
  "price": 899.99,
  "stock": 45
}
```

