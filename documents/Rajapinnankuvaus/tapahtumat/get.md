# Yksittäisen tapahtuman tiedot

Näyttää yksittäisen tapahtuman tiedot.

**URL** : `/api/tapahtumat/{id}`

**URL Parameters** : `{id}`, jossa ID on tapahtuman tapahtumaId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN- tai USER -tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Tapahtuma on olemassa, ja pyynnön tekevällä käyttäjällä on ADMIN- tai USER -tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
{
    "tapahtumaId": 1,
    "nimi": "Uusi tapahtuma",
    "aika": "2024-12-14",
    "paikka": "Olympiastadion",
    "kuvaus": "Hyvä tapahtuma :D",
    "lippumaara": 600,
    "ennakkomyynti": "2024-12-13"
}
```

## Error Responses

**Condition** : Tapahtumaa ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN- tai USER -tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
