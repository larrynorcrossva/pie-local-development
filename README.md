# NOTE: This is a stripped down version of the Docker Local Development (https://coderepo.mobilehealth.va.gov/projects/DEV/repos/docker-local-development/browse) code that ONLY stands up the services neceassry for VAR-Resouces, VMM, Facility Resources, and VAR-Web.


## Original README below.

# Docker Compose project to stand up API Gateway, Consul and VAMF Micro Services

The latest version of micro services docker images passing local integration tests are stored in the VA Innovations Gitlab registry https://mobileapps.vaftl.us:9250 The compose file in this project pulls the latest images and connects them together in the same manner as ece-dev for local testing purposes.

This project if for developer convenience, any issues identified with micro services should be filed against their respective JIRA projects and not against this project. 

## Prerequisites 
1. Docker Engine https://docs.docker.com/engine/installation/
2. Docker Compose https://docs.docker.com/compose/install/
3. WMSACT Innovations Gitlab Account https://wiki.mobilehealth.va.gov/display/WMSACT/Innovations
4. At least 8G of RAM

## To Run
1. `git clone https://coderepo.mobilehealth.va.gov/scm/dev/docker-local-development.git`
2. `cd docker-local-development`
1. `docker login https://mobileapps.vaftl.us:9250`
2. `sh run.sh`

The script will check for the latest version of each docker image on https://mobileapps.vaftl.us. It will then stand up Consul, API GW a Mock IDP and the following:

### Micro Services (offical Stash Repos):
- Clinical Data Warehouse (CDW) - https://coderepo.mobilehealth.va.gov/projects/CDWMS/repos/docker-cdw-service/browse
- Administrative Data Repository (ADR) - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-adr-services/browse
- Right of Access (ROA) - https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/docker-roa-service/browse
- User service and Mobile Veteran (Patient) Index - https://coderepo.mobilehealth.va.gov/projects/IUMS/repos/docker-mvi-user-service/browse
- Mobile Blue Button PDF generation (MBB-PDF) - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-mbb-pdfservice/browse
- MDWS-VIA-Adapter - https://coderepo.mobilehealth.va.gov/projects/VDSMS/repos/mdws-via-adapter/browse
- Vista Data Services (VDS) - https://coderepo.mobilehealth.va.gov/projects/VDSMS/repos/vista-data-services/browse
- Health Data Repository (HDR) Service - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/hdr-mock/browse
- Staff User Disclosure (SUD) Data Service - https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/sud-service/browse
- Notification Services - https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/vamf-notification-services/browse

### Helper Apps
- Where Are You From (WAYF) - https://coderepo.mobilehealth.va.gov/projects/IUMS/repos/wayf-app/browse
- Provider Context Selector (PCS) Web Application - https://coderepo.mobilehealth.va.gov/projects/IUMS/repos/pcs-app/browse

### Mock Datasource (official Stash Repos):
- ADR - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-adr-mock/browse
- CDW - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-cdw-mock/browse
- MVI - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-mvi-mock/browse
- MVI - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-roa-mock/browse
- VIA - https://coderepo.mobilehealth.va.gov/projects/VDSMS/repos/via-mock-service/browse
- HDR - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/hdr-mock/browse


### Sample Apps (offical Stash Repos):
- Mobile Blue Button Web application (MBB-Web) - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/docker-mbb-web/browse
- ROA-Web -https://coderepo.mobilehealth.va.gov/projects/VDMS/repos/docker-roa-web/browse

## Swagger Contracts

Swagger UI is available on http://localhost:8091

- CDW: http://localhost:8082/cdw/v1/
- ADR: http://localhost:8083/adr/v1/
- ROA: http://localhost:8086/roa/v1/
- User Service: http://localhost:8081/users/v1/
- VDS: http://localhost:8085/VistaDataServices
- HDR: http://localhost:8089/hdr/v1/
- SUD: http://localhost:8089/sud/v1/
- Notifications: http://localhost:8098/NotificationServices

## MBB Sample Application

### Login Flow
We are simulating the redirect triggered by MBB:
- In a Browser go to `http://localhost:8089/users/v1/login?redirect_uri=/mbb/v1/`
- This will redirect to `http://localhost:8089/wayf/v1/index.html?redirect_uri=/mbb/v1/`
- Select SSOE (set up to login in to http://localhost:9000/users/v1/landing)
- This will redirect t0 `http://localhost:9000/users/v1/landing?redirect_uri=/mbb/v1/`
- Login: `mockuser01 / pass`
- Final redirect to: `http://localhost:9000/mbb/v1/#eula`



## Working with the environment

### Notification Services
The notification REST service works with the Quartz scheduler container as well as the mongo mock (for streing notifications) and the ROA Mock. This is meant to be an internal service not exposed by the API Gateway. You can send a health check with a GET request to:

`http://localhost:8098/NotificationServices/healthcheck/ping`


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


## To Stop
1. run `sh stop.sh`
This will bring down all of the containers but not remove them. If you wish to remove them use:
`docker-compose rm`

Full docker compose [cli reference](https://docs.docker.com/compose/reference/overview/) 

## Deploying to AWS EC2
If you don't have enough resources on your local machine you can easily deploy to a Cloud provider with [Docker Machine](https://docs.docker.com/machine/get-started/). This example uses AWS but docker-machine also supports Azure, Digital Ocean, and many [others](https://docs.docker.com/machine/drivers/)   

This example requires you to have docker-machine installed, have an AWS account and API keys with permission to create instances. We recommend a minimum instance size of **t2.large**

### Create the machine with an ubuntu 16.04 base image
```
docker-machine create --driver amazonec2 --amazonec2-access-key $AWS_ACCESS_KEY --amazonec2-secret-key $AWS_SECRET_KEY --amazonec2-instance-type t2.xlarge --amazonec2-region us-east-1 --amazonec2-zone "b" vamf-worker-node
```

### Connect to newly created machine 
`docker-machine env vamf-swarm-node`

`eval $(docker-machine env vamf-swarm-node)`

### Forward the port for Consul
**If you don't do this the run script will not set endpoints in consul**
- `docker-machine ssh vamf-swarm-node -f -N -L 8500:localhost:8500` 

### Stand up containers
- `sh run.sh`

### Interacting with the services
Docker machine allows you to ssh in to the new machine. You can also set up port forwarding or modify you AWS ACLs to expose ports on the public IP of the machine you created.

- `docker-machine ssh vamf-swarm-node` To connect to the machine and interact with services on the host

To work with the services via localhost, create ssh tunnels as background process:
- `docker-machine ssh vamf-swarm-node  -f -N -L 8080:localhost:8080` Forwards the port For IDP
- `docker-machine ssh vamf-swarm-node -f -N -L 8089:localhost:8089` Forwards the port For API GW

To clean it all up you can use:

`docker-machine rm vamf-swarm-node` Will terminate the instance

`killall ssh` Will kill all the tunnels 





