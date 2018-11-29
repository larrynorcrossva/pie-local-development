#!/bin/bash

#generate master token
MASTER_TKN=7BE784A4-7498-4469-BE2F-9C3B9444DFEF

#generate json for master.json file
echo -e "{\n  \"acl_datacenter\": \"dc1\",\n  \"acl_default_policy\": \"deny\",\n  \"acl_down_policy\": \"extend-cache\",\n  \"acl_master_token\": \""$MASTER_TKN"\" \n}"

