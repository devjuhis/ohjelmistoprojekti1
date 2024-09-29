# Käyttäjän päivittäminen

Päivitä käyttäjän yhtä tai useampaa tietoa.

**URL** : `/api/kayttajat/{id}`

**URL Parameters** : `{id}`, jossa ID on päivitettävän käyttäjän kayttajaId.

**Method** : `PATCH`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

```json
{
    "etunimi": "[varchar 30]",
    "sukunimi": "[varchar 30]",
    "salasana": "[varchar 256]",
    "kayttajatunnus": "[varchar 30]",
    "oikeus": "[varchar 30, ADMIN tai USER]"
}
```

**Data example** Kaikkia kenttiä ei tarvitse täyttää, vain ainoastaan ne kentät päivittyvät, jotka annetaan bodyssä mukana. Käyttäjän ID:tä ei saa päivittää. Kyseisessä esimerkissä päivitetään käyttäjän etu- ja sukunimi.

```json
{
    "etunimi": "uusimatti",
    "sukunimi": "uusiesimerkki"
}
```

## Success Responses

**Condition** : Päivitys tulee tehdä ADMIN-tason käyttäjällä olemassaolevalle käyttäjälle.

**Code** : `200 OK`

**Content example** : Tässä esimerkissä päivitetään vanhalta nimeltään Matti Esimerkki-nimistä käyttäjää URL:issa `/api/kayttajat/1`...

```json
{
    "etunimi": "uusimatti",
    "sukunimi": "uusiesimerkki",
    "salasana": "salasana",
    "kayttajatunnus": "matti123",
    "oikeus": "ADMIN",
    "kayttajaid": 1
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