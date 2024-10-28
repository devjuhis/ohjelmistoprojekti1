# Login

Käyttäjän kirjautuminen, autentikaatio ja autentikointi.

Käyttäjän on kirjauduttava järjestelmään ja saatava JSON Web Token (JWT) jokaisen istunnon alussa.
Tämä JWT-token sisältää tiedot käyttäjän tunnistamisesta, oikeustasosta ja tokenin voimassaoloajasta. 

JWT tokeni on sisällytettävä kaikkiin pyyntöihin mukaan `Authorization`-otsikkoon "`Bearer <token>`" muodossa.
Token tarkistetaan jokaisen pyynnön yhteydessä ja sen voimassaolo, oikeellisuus, sekä käyttäjän oikeustason varmistetaan.

**URL** : `/api/login/`

**Method** : `POST`

**Auth required** : NO

**Data constraints** : Käyttäjätunnus ja salasana on lähetettävä __JSON__-muodossa

**Data example**

```json
{
	"kayttajatunnus":"iloveauth",
	"salasana": "abcd1234!yXz?"
}
```


## Success Response

**Code** : `200 OK`

**Content example**

```json
{
    "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxMjM0NTY3ODkwLCJleHAiOjE2MDA4ODgwMDB9.qZxvv9zJkTbhfR6KZDuvU0tyR8qk_YkJqZ0Iu9qXZ1U"
}
```

## Error Response

**Condition** : Jos käyttäjätunnus tai salasana on väärin.

**Code** : `401 Unauthorized`

**Content** :

```json
{
    "message": "Virheelliset tunnukset",
    "statusCode": 401,
    "timestamp": "2024-10-28T12:28:58.8050539"
}
```
