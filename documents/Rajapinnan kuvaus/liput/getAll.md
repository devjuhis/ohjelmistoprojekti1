# Kaikkien lippujen tiedot

Näyttää kaikki tiedot kaikista lipuista.

**URL** : `/api/liput`

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data constraints** : `{}`

## Success Responses

**Condition** : USER-tason käyttäjä näkee kaikki ohjelman liput.

**Code** : `200 OK`

**Content** : Tässä esimerkissä ADMIN-tason käyttäjä näkee kaksi lippua.

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
      "hinta": 12,
      "hinnastoid": 1
    },
    "maksutapahtuma": {
      "maksutapahtumaId": 1,
      "hintayhteensa": 55,
      "aikaleima": "2024-09-29T17:08:25.062653",
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
    "removed": false
  },
  {
    "lippuId": 2,
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
      "hinta": 12,
      "hinnastoid": 1
    },
    "maksutapahtuma": {
      "maksutapahtumaId": 1,
      "hintayhteensa": 55,
      "aikaleima": "2024-09-29T17:08:25.062653",
      "kayttaja": {
        "etunimi": "matti",
        "sukunimi": "esimerkki",
        "salasana": "salasana",
        "kayttajatunnus": "matti123",
        "oikeus": "ADMIN",
        "kayttajaid": 1
      }
    },
    "kaytetty": 0,
    "maara": 1,
    "removed": false
  }
]
```

