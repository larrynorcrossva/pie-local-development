# ** DRAFT ** #
## NOTE: This is a stripped down version of the [Docker Local Development](https://coderepo.mobilehealth.va.gov/projects/DEV/repos/docker-local-development/browse) code that ONLY stands up the services required for VAR-related development ##

### VAR Development Environment Setup ###
For best performance, it is recommended you allocate at least 10GB to Docker in a machine of a minimum 16GB available RAM (I've allocated 14GB, and increased swap size to 2GB). Also, check the Disk tab in your Docker Preferences dialog. You'll need to allocate at least 50GB of disk for your local Docker image repository.

Before you begin, if you currently have Docker images that were pulled from the AbleVets ECR, you'll want to start fresh, by doing a Docker system prune as follows:

```
$ docker system prune -a --volumes
WARNING! This will remove:
       - all stopped containers
       - all networks not used by at least one container
       - all volumes not used by at least one container`
       - all images without at least one container associated to them
       - all build cache
Are you sure you want to continue? [y/N] y
Deleted Networks:
vaos_default

Deleted Images:
untagged: dev/openjdk8-tomcat-appdynamics:1.0.1
untagged: mobileapps.vaftl.us:9250/md/map/openjdk8-tomcat-appdynamics:1.0.1
untagged: mobileapps.vaftl.us:9250/md/map/openjdk8-tomcat-appdynamics@sha256:11ef552a5e4eff5269fe0da0d762f5f9dc50a19a3fa0e896c2e6b989012ff75b
  .
  .
  .
deleted: sha256:676041b403df05d8cd9cf67de96df8a8836aea4a353c936b79204250af001c76
deleted: sha256:70942dd8c59734fc31e8f6820a3b22d4ace67f102d4c05e02a33033601e562c7
deleted: sha256:36018b5e978717a047892794aebab513ba6856dbe1bdfeb478ca1219df2c7e9c

Total reclaimed space: 45.74GB
$
 ```

***NOTE: Be sure that you've pulled the latest commits from the Bitbucket (Stash) repositories for the [var-web (release 4.17)](https://coderepo.mobilehealth.va.gov/projects/VAR/repos/var-web/browse?at=refs%2Fheads%2Frelease%2F4.17 "VAOS Web"), [var-resources (release 4.17)](https://coderepo.mobilehealth.va.gov/projects/VAR/repos/var-resources/browse "VAOS Resources"), [scheduling-manager-web (release 3.0)](https://coderepo.mobilehealth.va.gov/projects/VAR/repos/scheduling-manager-web/browse?at=refs%2Fheads%2Frelease%2F3.0 "SM Web"), [scheduling-manager-resources (release 3.0)](https://coderepo.mobilehealth.va.gov/projects/VAR/repos/scheduling-manager-resources/browse "SM Resources"), [var-utility-web (release 3.1)](https://coderepo.mobilehealth.va.gov/projects/VAR/repos/var-utility-web/browse?at=refs%2Fheads%2Frelease%2F3.1 "VATS Web"), [var-utility-resources (release 3.1)](https://coderepo.mobilehealth.va.gov/projects/VAR/repos/var-utility-resources/browse "VATS Resources").***

And finally, be sure that you've exported the VA_NEXUS_USER, VA_NEXUS_PWD, and VISTA_CACHE_KEY environment variables, set to the values that you were provided by encrypted email.

The stack is broken down into seven main components:

- NextGen Core Infrastructure (long-running)
- Fixtures (Mock Databases) (long-running)
- Shared Services (long-running)
- Vet/Staff Only Services (short-running)
- Data (Seeding mock databases) (short-running)
- Applications (VAR, SM, VATS, etc) (development-centric)
- VIA and VistA (backend services & Database) (long-running) - these are brought up last to avoid race conditions

### Menu Script ###
Prior to starting the stack, you may find it helpful to use the `./menu` script to log-in to the DTR, pull the images, and build the current project source. The locally built images will be tagged as `dev/` images, and will be brought up when you run the stack in `dev` mode, using the `--dev` command line argument for the `./run` script. The following options should prepare you to get up and running:

1. Run the menu script and log-in to the DTR (1) (note that you no longer need the AWS credentials for the AV ECR, and that the prerequisite check and hostlocal.io initialization will be automatic). Then choose the Setup menu (2) and Pull and Build All Images (4):

```
./menu

Verifying prerequisites / required tools…

Java version: 1.8.0_201
***ENVIRONMENT VALIDATION***
Checking if hostlocal.io is set up properly…

hostlocal.io is responding. Now starting the requested services…

1) Login DTR	      5) Build Menu	   9) Git Menu
2) Setup Menu	      6) Logs Menu	  10) Quit
3) Status Menu	      7) Validate Menu
4) Quickstart Menu    8) Java Force GC
Please enter your choice: 1
Authenticating with existing credentials...
Login Succeeded
Please enter your choice: 2
1) Stop Stack				10) Start staff
2) Reset Docker				11) Features Flags
3) Docker Pull Images			12) Start vista
4) Pull and Build All Images		13) Start via (after vista is healthy)
5) Build Menu				14) Start var (DEV)
6) Start fixtures			15) Start sm (DEV)
7) Check Database			16) Start vats (DEV)
8) Start data core ss			17) Start All (DEV)
9) Start vet				18) Quit
Please enter your choice: 4
Pulling latest images of the stack
Pulling consul                            ... 
Pulling registrator                       ... 
Pulling mock-mvi                          ...
.
.
.
Please enter your choice:
```
2. When all of the builds have successfully completed, you're ready to run the stack. Exit the menu and invoke the run script as shown below. A variety of other menu options are offered as alternatives to the command line invocations of the scripts. The Validation menu may be particularly helpful if you need to troubleshoot a problem.

### Run Script ###

If you haven't logged in to the DTR using either the menu script (as shown above) or from the command line, be sure to run the Docker login command as:

```
$ docker login mobileapps.vaftl.us:9250
Authenticating with existing credentials...
Login Succeeded
$
``` 

to assure that the scripts are able to properly pull the images prior to standing up each segment of the stack.

- The fastest way to get up and running is to stand up the entire stack all in one go as follows:

   `./run --dev all`

   (omitting the --dev argument will bring up the images pulled from the DTR, rather than the ones you build locally)

The stack should be stood up in this order to guarantee all dependencies are met at each segment of the infrastructure.

1. Stand up mock fixtures:

   `./run fixtures`
   
2. Seed mock fixtures

   `./run data`
   
3. Stand up NextGen Core Infrastructure:
   
   `./run core`
   
4. Stand up shared services (use optional --dev argument to run dev images for facility and vmm services):

   `./run [--dev] ss`
   
5. Stand up vet services (if running vet apps):

   `./run vet`
  
6. Stand up staff services (if running staff apps):

   `./run staff`

7. Stand up vaos-vista container:

   `./run vista`

8. Wait for the vaos-vista container to come up and show a healthy status (this may take two or three minutes)

8. Stand up VIA services:

   `./run via`

9. Stand up necessary applications (use optional --dev argument to run the dev images)

   `./run [--dev] var`
   
   `./run [--dev] sm`

   `./run [--dev] vats` [if testing/developing VATS or modifying config]

### Stopping containers ###
To stop containers, run the stop script, adding additional parameters where necessary.  Note that running ./stop.sh
will delete the Oracle container cache, and next startup will do a full rebuild

- Stop all containers:
   
   `./stop all`

- Stop NextGen Core containers:
   
   `./stop core`

- Stop fixtures containers:
   
   `./stop fixtures`

- Stop shared services containers:
   
   `./stop shared-services`

- Stop VAR containers:
   
   `./stop var`

- Stop SM containers:
   
   `./stop vm`

- Stop VIA services containers:
   
   `./stop via`

- Stop VistA container:
   
   `./stop vista`


### Building fixture and seed containers ###
By default, the var-related fixtures and data seeds are pulled from the latest in the ECR, however it may be necessary to build
and seed manually.  If this is the case, follow the steps to build the mocks out:

- Build VAR Mongo mock

   `docker build -t dev/var-mongo-db-mock ./var-mongo-db-mock`

- Build VAR Mongo data seed

   `docker build -t dev/var-mongo-data-seed ./var-mongo-data-seed`

From here, swap the commented image definitions in the fixtures and data seed compose file accordingly.  This will enable
the consumption of the locally built docker images.

# Original README below #

## Docker Compose project to stand up API Gateway, Consul and VAMF Micro Services ##

The latest version of micro services docker images passing local integration tests are stored in the VA Innovations Gitlab registry https://mobileapps.vaftl.us:9250 The compose file in this project pulls the latest images and connects them together in the same manner as ece-dev for local testing purposes.

This project if for developer convenience, any issues identified with micro services should be filed against their respective JIRA projects and not against this project. 

## Prerequisites 
1. Docker Engine https://docs.docker.com/engine/installation/
2. Docker Compose https://docs.docker.com/compose/install/
3. WMSACT Innovations Gitlab Account https://wiki.mobilehealth.va.gov/display/WMSACT/Innovations
4. At least 16G of RAM
5. 10G of RAM allocated to Docker

## To Run 
Run from Project Directory:
1. `docker login https://mobileapps.vaftl.us:9250`
2. **Note:** The script no longer pulls the latest images by default, to force compose to check for the latest images in the registry run `sh run.sh latest` otherwise run: `sh run.sh`


The script will check for the latest version of each docker image on https://mobileapps.vaftl.us. It will then stand up Consul, API GW a Mock IDP and the following:

### Micro Services (official Stash Repos):
- Clinical Data Warehouse (CDW) - https://coderepo.mobilehealth.va.gov/projects/CDWMS/repos/docker-cdw-service/browse
- Administrative Data Repository (ADR) - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-adr-services/browse
- Right of Access (ROA) - https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/docker-roa-service/browse
- User service and Mobile Veteran (Patient) Index - https://coderepo.mobilehealth.va.gov/projects/IUMS/repos/docker-mvi-user-service/browse
- Mobile Blue Button PDF generation (MBB-PDF) - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-mbb-pdfservice/browse
- MDWS-VIA-Adapter - https://coderepo.mobilehealth.va.gov/projects/VDSMS/repos/mdws-via-adapter/browse
- Vista Data Services (VDS) - https://coderepo.mobilehealth.va.gov/projects/VDSMS/repos/vista-data-services/browse
- Staff User Disclosure (SUD) Data Service - https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/sud-service/browse
- Patient Notification Services (PNS) - https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/vamf-notification-services/browse

### Helper Apps
- Where Are You From (WAYF) - https://coderepo.mobilehealth.va.gov/projects/IUMS/repos/wayf-app/browse
- Provider Context Selector (PCS) Web Application - https://coderepo.mobilehealth.va.gov/projects/IUMS/repos/pcs-app/browse

### Mock Datasource (official Stash Repos):
- ADR - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-adr-mock/browse
- CDW - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-cdw-mock/browse
- MVI - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-mvi-mock/browse
- MVI - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-roa-mock/browse
- VIA - https://coderepo.mobilehealth.va.gov/projects/VDSMS/repos/via-mock-service/browse


### Sample Apps (offical Stash Repos):
- Mobile Blue Button Web application (MBB-Web) - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-mbb-web/browse
- ROA-Web -https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/docker-roa-web/browse

## Swagger Contracts

Swagger UI is available on http://localhost:8091

- CDW: http://localhost:8082/cdw/v1/
- ADR: http://localhost:8083/adr/v1/
- ROA: http://localhost:8086/roa/v1/
- User Service: http://localhost:8081/users/v1/
- VDS: http://localhost:8085/VistaDataServices/v1/
- SUD: http://localhost:8089/sud/v1/
- PNS: http://localhost:8098/pns/v1/


## Working with the environment

### Vault
Vault is used to store secrets securely. The server is set up in dev mode and uses Consul for its storage backend. See the Vault [README](https://coderepo.mobilehealth.va.gov/projects/PPG/repos/vault-server/browse) for more detailed guides on working with Vault.

- Vault **Server Address**: http://localhost:8202
- Vault **Root Token**: 92389390-D796-490A-A91F-44CA582AA661 
- Vault is bootstrapped with the following **Role ID**: local-read

An application can login with this **Role ID** and receive a client token. This token will allow read access to the path `secret/local` in Vault

Login API:
```
curl -X POST -d '{"role_id":"local-read" }' http://localhost:8202/v1/auth/approle/login
```

Applications can use this token with ENVCONSUL, Consul-Template, or with the Vault API to read secrets at container run time.

### Notification Services
The notification REST service works with the Quartz scheduler container as well as the mongo mock (for streing notifications) and the ROA Mock. This is meant to be an internal service not exposed by the API Gateway. You can send a health check with a GET request to:

`http://localhost:8098/pns/v1/healthcheck/ping`

Check that PNS is talking to Quartz

`http://localhost:8098/pns/v1/healthcheck/ping-quartz-scheduler`


### Testing SUD Service
You will need to inject Staff headers for this service to work.

Headers:
`secid: 111111111`

http://localhost:8089/sud-web/v1/ Will bring up the web app where you can verify a 200 response from http://localhost:8089/sud/v1/staff/111111111/staff-user-disclaimer 

### Testing PCS
Inject the below headers via browser plugin and browse to:

http://localhost:8089/users/v1/session

This should trigger redirection to the PCS app. The redirection will happen when going to any endpoint behing the API Gateway with both the `adupn` and mulitple `vistaId` values in the header. 

`adupn` will trigger the SSOi handler  
`vistaId` with multiple IDs will trigger the PCS redirection

Headers:
```
vistaId: 508|22228439^PN^508^USVHA|A,590|11128439^PN^590^USVHA|A
adupn: 12345
first_name: test
last_name: test
```

### IDP Mock
The compose file stands up an IDP mock on `http://localhost:8080`. This mock injects the following headers:

```
va_eauth_dodedipnid:1113138327
va_eauth_firstname: Test
va_eauth_lastname: User
```

### API Gateway
If you want to test with your own headers, the API Gateway runs on `http://localhost:8089`. You can either use a browser plugin such as [Requestly](http://web.requestly.in) or use curl commands to inject headers in requests such as:

`curl http://localhost:8089/users/v1/session/mhpuser -H "va_eauth_dodedipnid:1113138327" -H "va_eauth_firstname: first" -H "va_eauth_lastname: last`

*Note depending on the resources available on you machine the return may be slow*

### Consul Server
- Consul Server is available on http://localhost:8500
- The Master Token is: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF

You can work with Consul via its Web UI or via it's REST endpoints. You will need to include the token in any calls to consul. For example:

`curl -H "X-Consul-Token: 7BE784A4-7498-4469-BE2F-9C3B9444DFEF" -s -X PUT localhost:8500/v1/kv/appconfig/local/myapp/v1/MY_ENV -d 'value'`

Would store a Key Value pair of MY_ENV=value in the `appconfig/local/myapp/v1/` folder of Consul's KV store.

