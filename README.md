# CodeSharingPlatform #
***
This project was developed with "JetBrains Academy" https://hyperskill.org/.

Programming is full of little secrets. Sometimes, even within
the same company, there is some secret code that should be 
hidden from most of the colleagues. This piece of code should 
only be available to certain people, and it may be deleted in the 
future to hide the traces.

In this project, we made such functionality possible. You will need
to add two limitations on the code snippet's visibility:

A limit on the number of views will allow viewing the snippet only a certain
number of times, after which the snippet is deleted from the database.
A limit on the viewing time will allow viewing a code snippet for a certain 
period of time, and after its expiration, the code snippet is deleted from the database.
If both restrictions are applied to a certain code snippet, it has to be deleted when at
least one of these limits is reached. They only are available through a link.

If you are a true spy, you might object: if all the links have 
numeric identifiers, can't we find the secret snippets just by 
looking through the different numbers? Indeed, it is quite easy to 
access secret links this way. To avoid this, we are generate links 
not with consecutive numbers but with UUID's (Universally Unique
IDentifiers).

### Examples

###### Example 1

Request `POST /api/code/new` with the following body:
```JSON
{
"code": "class Code { ...",
"time": 0,
"views": 0
}
```
Response: `{ "id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f" }`.

Request `POST /api/code/new` with the following body:
```JSON
{
"code": "public static void ...",
"time": 0,
"views": 0
}
```
Response: `{ "id" : "e6780274-c41c-4ab4-bde6-b32c18b4c489" }`.

Request POST /api/code/new with the following body:
```JSON
{
"code": "Secret code",
"time": 5000,
"views": 5
}
```
Response: `{ "id" : "2187c46e-03ba-4b3a-828b-963466ea348c" }`.

###### Example 2

Request: `GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c`

Response:
```JSON
{
"code": "Secret code",
"date": "2020/05/05 12:01:45",
"time": 4995,
"views": 4
}
```
Another request `GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c`

Response:
```JSON
{
"code": "Secret code",
"date": "2020/05/05 12:01:45",
"time": 4991,
"views": 3
}
```

###### Example 3

Request: `GET /api/code/latest`

Response:
```JSON
[
{
"code": "public static void ...",
"date": "2020/05/05 12:00:43",
"time": 0,
"views": 0
},
{
"code": "class Code { ...",
"date": "2020/05/05 11:59:12",
"time": 0,
"views": 0
}
]
```

##### There is also some features in web-browser:
* possible to add snippets via fill out the form by web at `/code/new`;
* to get snippet by the direct link `/code/uuid`;
* get latest snippets at `/code/latest`.
