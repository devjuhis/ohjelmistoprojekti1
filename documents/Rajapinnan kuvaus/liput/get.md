# Yksittäisen lipun tiedot

Näyttää tiedon yksittäisestä lipusta.

**URL** : `/api/liput/{id}`

**URL Parameters** : `{id}`, jossa ID on käyttäjän lippuId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Lippu on olemassa, ja pyynnön tekevällä käyttäjällä on ADMIN-tason oikeudet.

**Code** : `200 OK`

**Content example**

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
  "kaytetty": 0,
  "maara": 1
}
```

## Error Responses

**Condition** : Käyttäjää ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
