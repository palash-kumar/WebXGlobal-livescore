### Livescores
The following program calls an URL http://static.cricinfo.com/rss/livescores.xml and parse the **XML** response to retrieve the required data and save it to DB which can be later used for other operations.
The project has been developed using *Spring Boot*, *Thymeleaf*, Spring Security, *MySQL*. 

#### File Structure

```shell
webXGlobal
├───.idea
│   └───libraries
├───.mvn
│   └───wrapper
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───palash
│   │   │           └───webXGlobal
│   │   │               ├───config
│   │   │               ├───controllers
│   │   │               ├───entities
│   │   │               ├───repositories
│   │   │               ├───scheduler
│   │   │               ├───securityConfig
│   │   │               ├───services
│   │   │               ├───specifications
│   │   │               └───utils
│   │   └───resources
│   │       ├───static
│   │       │   ├───bootstrap-513
│   │       │   └───plugins
│   │       │       ├───DataTables
│   │       │       ├───jQuery
│   │       │       └───select2
│   │       └───templates
│   │           ├───error
│   │           └───fragments
└───target
    ├───classes
    │   ├───com
    │   │   └───palash
    │   │       └───webXGlobal
    │   │           ├───config
    │   │           ├───controllers
    │   │           ├───entities
    │   │           ├───repositories
    │   │           ├───scheduler
    │   │           ├───securityConfig
    │   │           ├───services
    │   │           └───utils
    │   ├───static
    │   │   ├───bootstrap-513
    │   │   └───plugins
    │   │       ├───DataTables
    │   │       ├───jQuery
    │   │       └───select2
    │   └───templates
    │       ├───error
    │       └───fragments
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    └───test-classes
        └───com
            └───palash
                └───webXGlobal
```

#### Description
The core idea of the project revolved around using a *Scheduler* to call the *API URL* every 5 mins. Here the task scheduler is defined as **ParserTaskScheduler** under *com.palash.webXFlobal.scheduler*.
The scheduler calls *parseChannel()* function every 5 min. interval where the *URL* http://static.cricinfo.com/rss/livescores.xml is invoked, and the response is received and processed. The secondary URL's are collected from the response and sent to *iterateOverElements(channels)*, Where channel is an object of Channel class built to accommodate primary data, function for further processing and collection of data.
All the collected data are mapped to two objects *Matches* and *ScoreTimeLine* are sent to *validateSaveOrUpdate()* function to insert or update data if existed.

>NOTE: **Matches** contains information about all the matches which are taking place and **ScoreTimeLine** contains all the scores related to each of the match over the period of the match.

#### Frontend
* **Login** page is used to login to the program and see the dashboard, a user can also register by clicking on *create user* link provided.
* **create-user** simply takes 3 parameters *name, username and password* and creates a user, than the user can login using the username and password and see the dashboard.
* **welcome** page contains details of all the ongoing or coming up matches, list of previous matches, search box to search matches which has been recorded.
* **matchDetails** page contains specific details of a match which user has clicked on in welcome page or searched for, or selected from previous match list. This page also displays all the scores for that specific match. the scores and details are loaded using *DataTables, Serverside processing*.
 
