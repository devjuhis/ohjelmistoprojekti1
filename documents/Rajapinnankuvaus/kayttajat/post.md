# Uuden käyttäjän luominen

Luo uuden käyttäjän.

**URL** : `/api/kayttajat`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

Käyttäjän ID-generoituu automaattisesti. Bodyssa on lähetettävä etunimi, sukunimi, salasana, käyttäjätunnus ja oikeudet.

```json
{
    "etunimi": "[varchar 30]",
    "sukunimi": "[varchar 30]",
    "salasana": "[varchar 256]",
    "kayttajatunnus": "[varchar 30]",
    "oikeus": "[varchar 30, ADMIN tai USER]"
}
```

**Data example** Kaikki kentät on täytettävä.

```json
{
    "etunimi": "minna",
    "sukunimi": "esimerkki",
    "salasana": "salasana",
    "kayttajatunnus": "minna123",
    "oikeus": "USER"
}
```

## Success Response

**Condition** : Kaikki käyttäjälle tulevat tiedot on annettu ohjeistuksien mukaan.

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
    "aktiivisuus": true
}
```

## Error Responses

**Condition** : Jos tietoja puuttuu, tai annettu data on virheellisessä muodossa.

**Code** : `400 BAD REQUEST`

**Content example**: `{}`

### Or

**Condition** : Pyynnössä ei ole mukana toimivaa JWT-tokenia tai endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`