# Exercice 16 - Gestion d'erreurs dans une API de gestion d'inventaire

Le fichier schema.sql se trouve dans le dossier src/main/ressources.

## Lancer la base de données

Pour lancer la base de données :
```bash
docker compose up -d
```

## Endpoints

GET - /api/inventory/{id} : récupère un produit par id (404 si inexistant)

POST - /api/inventory : crée un produit (400 si nom manquant ou prix négatif)

GET - /api/inventory/price/{maxPrice} : retourne les produits dont le prix est inférieur ou égal à maxPrice (500 si maxPrice négatif)

## Tests

Toutes les routes ont été testées avec Postman.

POST /api/inventory :
```json
{
  "name": "Laptop",
  "quantity": 5,
  "price": 1200.50
}
```

POST /api/inventory avec données invalides :
```json
{
  "quantity": 5,
  "price": -10
}
```
