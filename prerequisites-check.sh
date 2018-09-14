echo -e "\n\nVerifying prerequisites / required tools…\n\n"

docker --version &> /dev/null || { echo "***FATAL: Could not run docker - is it installed?"; exit 1; }
docker-compose --version &> /dev/null || { echo "***FATAL: Could not run docker-compose - is it installed?"; exit 1; }
java -version &> /dev/null || { echo "***FATAL: Could not run java- is it installed?"; exit 1; }
mvn --version &> /dev/null || { echo "***FATAL: Could not run maven - is it installed?"; exit 1; }
perl -v &> /dev/null || { echo "***FATAL: Could not run perl - is it installed?"; exit 1; }

JVM_VERSION=$(java -version 2>&1  | awk -F '"' '/version/ {print $2}')
echo "Java version: $JVM_VERSION"
if [[ "$JVM_VERSION" > "1.9" ]]; then
    echo -e "Java version must be 1.8+ to build!"
    exit 1
fi

# Make sure hostlocal.io is set up before continuing…
echo -e "***ENVIRONMENT VALIDATION***"
echo -e "Checking if hostlocal.io is set up properly…"
ping -c 1 hostlocal.io > /dev/null;

if [ $? -ne 0 ]; then
	echo -e "\n\nThe hostname 'hostlocal.io' is not set up properly and is required to support local development environments. Please run ./add-hostlocal.sh and try again." >&2
	exit 1
fi
echo -e "hostlocal.io is responding. Now starting the docker-compose services…"
