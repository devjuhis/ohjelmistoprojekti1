# Lipun päivittäminen

Päivitä lipun määrä ja / tai kaytetty kenttää.

**URL** : `/api/liput/{id}`

**URL Parameters** : `{id}`, jossa ID on päivitettävän lipun lippuId.

**Method** : `PUT`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

```json
{
    "kaytetty": [ int ],
    "maara": [ int ]
}
```

**Data example** Kaikkia kenttiä ei tarvitse täyttää, vain ainoastaan ne kentät päivittyvät, jotka annetaan bodyssä mukana. Käyttäjän ID:tä ei saa päivittää. Kyseisessä esimerkissä päivitetään käyttäjän etu- ja sukunimi.

```json
{
    "kaytetty": 1,
    "maara": -1
}
```

## Success Responses

**Condition** : Päivitys tulee tehdä ADMIN-tason käyttäjällä olemassaolevalle käyttäjälle.

**Code** : `200 OK`

**Content example** : Tässä esimerkissä päivitetään lippua jonka id on 2. URL:issa `/api/liput/2`...

```json
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
        "hinta": 12.0,
        "hinnastoid": 1
    },
    "maksutapahtuma": {
        "maksutapahtumaId": 1,
        "hintayhteensa": 55.0,
        "aikaleima": "2024-09-29T19:00:07.862604",
        "kayttaja": {
            "etunimi": "matti",
            "sukunimi": "esimerkki",
            "salasana": "salasana",
            "kayttajatunnus": "matti123",
            "oikeus": "ADMIN",
            "kayttajaid": 1
        }
    },
    "kaytetty": 1,
    "maara": -1
}
```

## Error Response

**Condition** : Annetulla ID-parametrilla olevaa käyttäjää ei ole olemassa.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
