# Hinnaston päivittäminen

Päivitä hinnaston yhtä tai useampaa tietoa.

**URL** : `/api/hinnastot/{id}`

**URL Parameters** : `{id}`, jossa ID on päivitettävän hinnaston hinnastoId.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

```json
{
    "tapahtumaId": "[long]",
    "hintaluokka": "[varchar 30]",
    "hinta": "[dobule]"
}
```

**Data example** Kaikkia kenttiä ei tarvitse täyttää, vain ainoastaan ne kentät päivittyvät, jotka annetaan bodyssä mukana. Hinnasto ID:tä ei saa päivittää. Kyseisessä esimerkissä päivitetään hinnaston hintaluokka ja hinta.

```json
{
    "hintaluokka": "nuori",
    "hinta": 8
}
```

## Success Responses

**Condition** : Päivitys tulee tehdä ADMIN-tason käyttäjällä olemassaolevalle käyttäjälle.

**Code** : `200 OK`

**Content example** : Tässä esimerkissä päivitetään vanhalta hintaluokalta "opiskelija" ja hinta "12.0" URL:issa `/api/hinnastot/1`...

```json
{
    "tapahtuma": {
        "tapahtumaId": 1,
        "nimi": "Uusi tapahtuma",
        "aika": "2024-12-14",
        "paikka": "Olympiastadion",
        "kuvaus": "Hyvä tapahtuma :D",
        "lippumaara": 600,
        "ennakkomyynti": "2024-12-13"
    },
    "hintaluokka": "nuori",
    "hinta": 8.0,
    "hinnastoid": 1
}
```

## Error Response

**Condition** : Annetulla ID-parametrilla olevaa hinnastoa ei ole olemassa.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

!!Autentikointia ei ole vielä tehty!!

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
