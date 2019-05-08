# Users photos loader 
Simple app providing REST API for loading and showing users' photos.

### Used technologies
 -[Spring Boot](https://spring.io/projects/spring-boot)
 -[RestFb](https://restfb.com/)
 -[ModelMapper](http://modelmapper.org/)
 -[Flyway](https://flywaydb.org/)
 -[PostgreSQL](http://www.postgresql.org/)
 
### Development setup

Create a PostgreSql database. The data source has to be configured in `user-photos-loeader/src/main/resources/application.properties`

### Usage

Run:
 ```sh
 mvn spring-boot:run
 ``` 
## API endpoints

* Creates the user and his photos - Data is loaded from FB and stored in application DB.
	* POST `/users`
* Deletes the user and his photos from application DB.
	* DELETE `/users/{user_fb_id}`
* Responds with the user details(FB ID, name, gender, prfile picture URL).
	* GET `/users/{user_fb_id}`
* Responds with list of photos (each with: URL on FB, URL of image file, album name (if any), numbers of reactions grouped by type.
	* GET `/users/{user_fb_id}/photos`

## Request & Response examples

### POST /users

Example: Create - POST http://example.cz/users

Request body:

```sh
	{
		"userFbId": "100227021210251",
		"accessToken": "EAAgQ1dDxFiMBAD8CQGxAuZCMPd3MWZB3Vixji3AM8AdfDkZAh83SEFZCaqpzEu0MsOpmqhixJuJ9lL13p1eOI8d8jKvlNIUvMcV63ZBmN8e2p5yRyyE2PzJS3jYumPZAN0Re79ZAvZAHeBjd4aBxHqbBoLUpn0MfADVX6TkVzvsKE2b0fede3wHZBktZAbtSj2nx7fGGtoLZAYQGxs9ZA27XmUpa"
	}
```
Status codes:
 * 201 Created
 * 400 Bad Request
 * 409 Conflict
 * 503 Service unavaible - FB message error in the response.
	
### DELETE /users/{user_fb_id}

Example: Delete - DELETE http://example.cz/users/100227021210251

Status codes:
 * 204 No Content
 * 404 Not found

### GET /users/{user_fb_id}

Example: Get - GET http://example.cz/users/100227021210251

Response body:
```sh
	{
    	"userFbId": "100227021210251",
    	"name": "Will Alcffhheijefb Bharambeman",
    	"gender": "male",
    	"profilePictureUrl": "https://platform-lookaside.fbsbx.com/platform/profilepic/?asid=100227021210251&height=50&width=50&ext=1559837006&hash=AeRPWV2DuqrSEd7O"
	}
```
Status codes:
 * 200 OK
 * 404 Not found
    				
### GET /users/{user_fb_id}/photos

Example: Get - GET http://example.cz/users/100227021210251/photos

Response body:
```sh
	[
    	{
        	"fbLink": "https://www.facebook.com/photo.php?fbid=100220171210936&set=a.100219177877702&type=3",
        	"imageFileUrl": "https://scontent.xx.fbcdn.net/v/t1.0-9/59765361_100220177877602_3148039826276614144_n.jpg?_nc_cat=109&_nc_ht=scontent.xx&oh=e9e074f7f264506797cf51752e461c99&oe=5D592D2C",
        	"albumName": "Untitled Album",
        	"reactions": [
            	{
                	"type": "haha",
                	"numOfReactions": 1
            	},
            	{
                	"type": "like",
                	"numOfReactions": 1
            	}
        ]
    	}
    ]
```
   
Status codes:
 * 200 OK
 * 204 No Content
 * 206 Partial Content

### Todos
 - Write some tests
 - Refactor
 - Try to use rest repositories for GET, DELETE methods
 - Docker
