# Käyttäjän Soft Delete

Merkitään käyttäjä poistetuksi päivittämällä aktiivisuus-kenttää.

**URL** : `/api/kayttajat/softdelete/{id}`

**URL Parameters** : `{id}`, jossa ID on käyttäjän käyttäjäId.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 


## Success Response

**Condition** : Käyttäjä on olemassa sekä aktiivisuus kenttä on true. Lisäksi endpointtia käyttävällä käyttäjällä on ADMIN-tason oikeudet.

**Code** : `200 OK`

**Content example**

```json
{
    "kayttajaId": 3,
    "etunimi": "minna",
    "sukunimi": "esimerkki",
    "salasana": "$2a$10$/kE5X6t2EtjqQr745yxCbODxcKR9JcOWK6PflZ4yiMZAMxPeCJNoO",
    "kayttajatunnus": "minna123",
    "oikeus": "USER",
    "aktiivisuus": false
}
```

## Error Responses

**Condition** : Jos käyttäjää ei ole olemassa annetulla ID:llä.

**Code** : `404 NOT FOUND`

**Content example**: `{}`

### Or

**Condition** : Jos käyttäjä on jo merkitty epäaktiiviseksi.

**Code** : `410 GONE`

**Content example**: `{}`

### Or

**Condition** : Pyynnössä ei ole mukana toimivaa JWT-tokenia tai endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`