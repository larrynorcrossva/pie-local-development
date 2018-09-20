#!/bin/bash

ping -c 1 hostlocal.io > /dev/null;
status=$?;

if [ $status -ne 0 ]; then
	echo "Hostlocal.io does not appear to be mapped to this host. Registering now… (this may require admin password to authorize)"
	if [ $(uname) == "Darwin" ]; then
		sudo /sbin/ifconfig lo0 add 169.254.255.254/24;
	else
		docker run --rm --privileged --net=host gliderlabs/hostlocal;
	fi
else
	echo "It appears that hostlocal.io is already mapped to a network interface."
fi