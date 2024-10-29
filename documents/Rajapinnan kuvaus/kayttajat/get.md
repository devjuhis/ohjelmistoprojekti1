# Yksittäisen käyttäjän tiedot

Näyttää tiedon yksittäisestä käyttäjästä.

**URL** : `/api/kayttajat/{id}`

**URL Parameters** : `{id}`, jossa ID on käyttäjän kayttajaId.

**Method** : `GET`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data**: `{}`

## Success Response

**Condition** : Käyttäjä on olemassa, ja pyynnön tekevällä käyttäjällä on ADMIN-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
{
    "etunimi": "matti",
    "sukunimi": "esimerkki",
    "salasana": "salasana",
    "kayttajatunnus": "matti123",
    "oikeus": "ADMIN",
    "kayttajaid": 1
}
```

## Error Responses

**Condition** : Käyttäjää ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Pyynnössä ei ole mukana toimivaa JWT-tokenia tai endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`