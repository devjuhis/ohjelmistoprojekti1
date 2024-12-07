# Uuden tapahtuman luominen

Luo uuden tapahtuman.

**URL** : `/api/tapahtumat`

**Method** : `POST`

**Auth required** : YES

**Permissions required** : Toiminnon tekevällä käyttäjällä on oltava ADMIN-tason oikeudet. 

**Data constraints**

Tapahtuman ID-generoituu automaattisesti. Bodyssa on lähetettävä tapahtuman nimi, aika, paikka, kuvaus, kokonaislippumäärä ja ennakkomyynnin loppumispäivä.

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

**Data example** Tapahtuman ID generoituu automaattisesti, mutta kaikki muut kentät on täytettävä.

```json
{
    "nimi": "Postman Tour 2025",
    "aika": "2025-08-02",
    "paikka": "Ratinan stadion",
    "kuvaus": "Vuoden odotetuin",
    "lippumaara": 1000,
    "ennakkomyynti": "2025-08-01"
}
```

## Success Response

**Condition** : Kaikki tapahtumalle tulevat tiedot on annettu ohjeistuksien mukaan.

**Code** : `200 OK`

**Content example**

```json
{
    "tapahtumaId": 2,
    "nimi": "Postman Tour 2025",
    "aika": "2025-08-02",
    "paikka": "Ratinan stadion",
    "kuvaus": "Vuoden odotetuin",
    "lippumaara": 1000,
    "ennakkomyynti": "2025-08-01"
}
```

## Error Responses

**Condition** : Jos tietoja puuttuu, tai annettu data on virheellisessä muodossa.

**Code** : `400 BAD REQUEST`

**Content example**: `{}`

### Or

**Condition** : Endpointia käyttävällä käyttäjällä ei ole ADMIN-tason oikeuksia.

**Code** : `403 FORBIDDEN`

**Content** : `{}`
