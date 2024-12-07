# Tapahtuman päivittäminen

Päivitä tapahtuman yhtä tai useampaa tietoa.

**URL** : `/api/tapahtumat/{id}`

**URL Parameters** : `{id}`, jossa ID on päivitettävän tapahtuman tapahtumaId.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

```json
{
    "nimi": "[varchar 60]",
    "aika": "[date]",
    "paikka": "[varchar 60]",
    "kuvaus": "[varchar 500]",
    "lippumaara": "[int]",
    "ennakkomyynti": "[date]"
}
```

**Data example** Kaikkia kenttiä ei tarvitse täyttää, vain ainoastaan ne kentät päivittyvät, jotka annetaan bodyssä mukana. Tapahtuman ID:tä ei saa päivittää. Esimerkissä päivitetään tapahtuman nimeä.

```json
{
    "nimi": "Uusiiii tapahtuma"
}
```

## Success Responses

**Condition** : Päivitys tulee tehdä ADMIN-tason käyttäjällä olemassaolevalle tapahtumalle.

**Code** : `200 OK`

**Content example** : Tässä esimerkissä päivitetään vanhalta nimeltään "Uusi tapahtuma" -nimistä tapahtumaa URL:issa `/api/tapahtumat/1`...

```json
{
    "tapahtumaId": 1,
    "nimi": "Uusiiii tapahtuma",
    "aika": "2024-12-14",
    "paikka": "Olympiastadion",
    "kuvaus": "Hyvä tapahtuma :D",
    "lippumaara": 600,
    "ennakkomyynti": "2024-12-13"
}
```

## Error Response

**Condition** : Annetulla ID-parametrilla olevaa tapahtumaa ei ole olemassa.

**Code** : `404 NOT FOUND`

**Content** : `{}`

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
