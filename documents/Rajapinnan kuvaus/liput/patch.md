# Lipun päivittäminen

Päivitä lipun määrä, kaytetty ja removed kenttää.

**URL** : `/api/liput/{id}`

**URL Parameters** : `{id}`, jossa ID on päivitettävän lipun lippuId.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava USER-tason oikeudet. 

**Data constraints**

```json
{
    "kaytetty": [ Boolean ],
    "maara": [ int ] (-1 -> käytetty, 1 -> ei käytetty)
    "removed": [ Boolean ]
}
```

**Data example** Kaikkia kenttiä ei tarvitse täyttää, vain ainoastaan ne kentät päivittyvät, jotka annetaan bodyssä mukana.

```json
{
    "kaytetty": true,
    "maara": 1,
    "removed": false
}
```

## Success Responses

**Condition** : Päivitys tulee tehdä USER-tason käyttäjänä.

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
    "maksutapahtuma": null,
    "kaytetty": false,
    "maara": 1,
    "removed": false
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
