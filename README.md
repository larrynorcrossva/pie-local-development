# NOTE: This is a stripped down version of the Docker Local Development (https://coderepo.mobilehealth.va.gov/projects/DEV/repos/docker-local-development/browse) code that ONLY stands up the services required for VAR-related development

##VAR Development Environment Setup
The stack is broken down into five main components

- NextGen Core Infrastructure (long-running)
- Fixtures (Mock Databases) (long-running)
- Shared Services (long-running)
- Data (Seeding mock databases) (short-running)
- Applications (VAR, SM, VATS, etc) (development-centric)

The stack should be stood up in this order to guarantee all dependencies are met at each segment of the infrastructure.
Be sure to run the ECR login command such as
`$(aws ecr get-login --no-include-email)` so the scripts are are able to properly pull prior to standing up each segment
of the stack.

1. Stand up NextGen Core Infrastructure:
   
   `./run-nextgen-infrastructure.sh`
   
2. Stand up mock fixtures:

   `./run-fixtures.sh`
   
3. Stand up shared services:

   `./run-shared-services.sh`
   
4. Seed mock fixtures

   `./run-data-seed.sh`
   
5. Stand up necessary applications

   `./run-var.sh`
   
   `./run-sm.sh`

- (Experimental) To stand up the entire stack all in one go:

   `./run-all.sh`

###Stopping containers
To stop containers, run the stop script, adding additional parameters where necessary.  Note that running ./stop.sh
will delete the Oracle container cache, and next startup will do a full rebuild

- Stop all containers:
   
   `./stop.sh all`

- Stop NextGen Core containers:
   
   `./stop.sh`

- Stop fixtures containers:
   
   `./stop.sh fixtures`

- Stop shared services containers:
   
   `./stop.sh shared-services`

- Stop var containers:
   
   `./stop.sh var`


## Original README below.

# Docker Compose project to stand up API Gateway, Consul and VAMF Micro Services

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
- Health Data Repository (HDR) Service - https://coderepo.mobilehealth.va.gov/projects/VAMFC/repos/hdr-mock/browse
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
- VDS: http://localhost:8085/VistaDataServices/v1/
- HDR: http://localhost:8089/hdr/v1/
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

