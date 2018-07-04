# jaxrs-reminder-app
_Fork by Sajid_

__Goal :__
   Transform a monolithic application into a horizontally scalable microservice-oriented application.



__Current Stage :__


![alt text](https://docs.google.com/drawings/d/e/2PACX-1vTHsD495ma1D0OgdzN7dZ_J5ugf804SF7MTyzPe5772rwvmjRva_A-3HFqiKf29NzFtwMbiek8JxKJL/pub?w=1440&h=1080 "ReminderApp Arch")

*** **_user_** class was added in order to illustrate in the best possible way how this became another set of microservices.



In the projects you will find a combination of Spring framework , JPA, OGM , spring data ,Mockito , Junit,  SLF4J ,Eureka, Zuul , etc  and in order to make it a turn-key solution I'm going to upload the images to docker hub https://hub.docker.com/u/darknautic/ along with the config files .



---



__Set Up & Start Services__
```
 kubectl create -f reminders-mysqldb-persistentVolume.yaml
 kubectl create -f reminders-mysqldb-persistentVolumeClaim.yaml
 kubectl create -f reminders-mysqldb-deployment.yaml
 kubectl create -f reminders-mysqldb-service.yaml
 kubectl create -f reminders-registry-deployment.yaml
 kubectl create -f reminders-registry-service.yaml
 kubectl create -f reminders-crud-deployment.yaml
 kubectl create -f users-crud-deployment.yaml
 kubectl create -f reminders-crud-service.yaml
 kubectl create -f users-crud-service.yaml
 kubectl create -f reminders-fetch-deployment.yaml
 kubectl create -f reminders-fetch-service.yaml
 kubectl create -f api-deployment.yaml 
 kubectl create -f api-service.yaml
 
```


---



__ENDPOINTS__

```

POST	http://localhost:9000/api/reminders-db-services/reminders
PUT	http://localhost:9000/api/reminders-db-services/reminders/{id}
DELETE	http://localhost:9000/api/reminders-db-services/reminders/{id}
GET	http://localhost:9000/api/reminders-db-services/reminders/{id}
GET	http://localhost:9000/api/reminders-db-services/reminders/userid/{userid} *a
GET	http://localhost:9000/api/reminders-db-services/reminders/count

POST	http://localhost:9000/api/users-db-services/users
PUT	http://localhost:9000/api/users-db-services/users/{id}
DELETE	http://localhost:9000/api/users-db-services/users/{id}
GET	http://localhost:9000/api/users-db-services/users/{id}
GET	http://localhost:9000/api/users-db-services/users/id/{username}  *b

GET	http://localhost:9000/api/reminders-fetch-services/rest/reminders/{username}  *c

```
*** _c_ uses _a_ and _b_  .


---


__Next Steps / Pending Tasks__


The initial aim was to wire all components and provide a working application, these next steps are all improvements and modifications needed to make the app better and fully featured.
```
> Add persistent volume to database
> Split CRUD operations into different services
> Add update/delete operations to front-end
> Change static references in yaml files hostAliases
> Connect reminder-ogm-mongo-service
> Improve authentication service ( redis back-end )
> Add registry client to API gateway
> Add DNS for service discovery 
> Possible change from eureka to kube-proxy
> Implement authentication service and remove it from users-service 
...
..  * updating *
.
```



