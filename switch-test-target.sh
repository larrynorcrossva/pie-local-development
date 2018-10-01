#!/usr/bin/env bash
network_interface=`route -n get 0.0.0.0 2>/dev/null | awk '/interface: / {print $2}'`
printf 'Mac network_interface: %s\n' $network_interface

ios_host=`ipconfig getifaddr $network_interface`
ie_host='10.0.2.2'

source app.env

# Mac host domain/adress, for proxying to iOS or IE  VM (localhost will not work for them)
if [ -z "$1" ]; then
	echo 'No testing target supplied. Defaulting to HOST_DOMAIN=localhost\n'
	export HOST_DOMAIN=localhost
fi

# either ios or ie - otherwise HOST_DOMAIN defaults to localhost for local Mac/Android testing
test_target=$1

if [[ $test_target == 'ios' && ! -z "$ios_host" ]]; then
	printf 'Testing target is iOS, using HOST_DOMAIN=%s\n' $ios_host
	export HOST_DOMAIN=$ios_host
elif [[ $test_target == 'ie' ]]; then
	printf 'Testing target is IE, using HOST_DOMAIN=%s\n' $ie_host
	export HOST_DOMAIN=$ie_host
fi

echo 'Restarting user-services...'

docker-compose stop user-services
docker-compose up -d user-services

echo 'HOST_DOMAIN is '
echo ${HOST_DOMAIN}
echo -e '\nDone, exiting'
