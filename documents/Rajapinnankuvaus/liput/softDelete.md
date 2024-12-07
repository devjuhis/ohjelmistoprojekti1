# Lipun soft delete

Päivitä lipun removed kenttää.

**URL** : `/api/liput/softdelete/{id}`

**URL Parameters** : `{id}`, jossa ID on päivitettävän lipun lippuId.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 


## Success Responses

**Condition** : Päivitys tulee tehdä USER-tason käyttäjänä.

**Code** : `200 OK`

**Content example** : Tässä esimerkissä päivitetään lippua jonka id on 1. URL:issa `/api/liput/softdelete/1`...

```json
{
    "lippuId": 1,
    "tapahtuma": {
        "tapahtumaId": 1,
        "nimi": "Uusi tapahtuma",
        "aika": "2024-12-14",
        "paikka": "Olympiastadion",
        "kuvaus": "Hyvä tapahtuma :D",
        "lippumaara": 600,
        "ennakkomyynti": "2024-12-13"
    },
    "hinnasto": {
        "tapahtuma": {
            "tapahtumaId": 1,
            "nimi": "Uusi tapahtuma",
            "aika": "2024-12-14",
            "paikka": "Olympiastadion",
            "kuvaus": "Hyvä tapahtuma :D",
            "lippumaara": 600,
            "ennakkomyynti": "2024-12-13"
        },
        "hintaluokka": "opiskelija",
        "hinta": 12.0,
        "hinnastoid": 1
    },
    "maksutapahtuma": {
        "maksutapahtumaId": 1,
        "hintayhteensa": 24.0,
        "aikaleima": "2024-10-15T13:39:26.044925",
        "kayttaja": {
            "etunimi": "matti",
            "sukunimi": "esimerkki",
            "salasana": "salasana",
            "kayttajatunnus": "matti123",
            "oikeus": "ADMIN",
            "kayttajaid": 1
        }
    },
    "kaytetty": false,
    "maara": 1,
    "removed": true
}
```

## Error Response

**Condition** : Annetulla ID-parametrilla olevaa käyttäjää ei ole olemassa.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### OR

**Condition** : Käyttäjällä ei ole USER-tason oikeuksia

**Code** : `403 FORBIDDEN`

**Content** : `{}
