#!/bin/bash
export PROJECTS_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )"/.. && pwd )"
echo "Using PROJECTS_DIR = $PROJECTS_DIR"

gem list bundler | grep bundler
if [[ $? -ne 0 ]]; then
    echo "Bundler not installed.  Run gem install bundler"
fi

# Common steps for building all applets
function setup_project() {


    if [ -f package.json ]; then
        npm install 
        export GEM_BUILD_ONLY=true
        bundle install
    fi
}

function checkout_project() {

    local repo=$1

    if [[ -z "${repo}" ]]; then

        echo "Usage: $FUNCTION <repo>"
        return -1
    fi

    cd $PROJECTS_DIR || { echo "Failed to change to directory $PROJECTS_DIR" ; exit 1 ; }

    local project="${repo/%.git/}"

    OIFS=$IFS
    IFS='/'
    project=( ${project[@]} )
    len=${#project[@]}
    IFS=$OIFS
    (( len=len - 1 ))
    project=${project[$len]}

    echo "Checking $project"

    if [[ ! -d $project ]] ; then
        echo "Project: $project does not exist..."
        git clone $repo
        cd $project
        git_checkout_latest_release_branch
		setup_project
    else
        echo "Project: $project already downloaded, stashing working changes before checking out changes..."
        cd $project
        git status -b
        git stash save "Automated backup before checkout."
        git fetch origin
        git_checkout_latest_release_branch
        git submodule update --init --recursive --remote
		setup_project
    fi
}

function git_checkout_latest_release_branch() {
	if [ ! -d .git ]; then
		echo "Assert Failed; current working dir is not a git repository"
		return 1
	fi

	if ! git fetch origin ; then
		echo "Failed to fetch from origin"
		return 2
	fi

	for branch in `git branch -r | grep -v HEAD`; do 
		echo -e `git show --format="%ci %cr" $branch | head -n 1` \\t$branch; 
	done | sort -r | grep release\/

	local branch
    branch=$(git branch -r | cut -d \/ -f 2- | sed -n -e '/^release\/[0-9].[0-9][0-9]$/p' | sort -n  | tail -n 1)

    if [[ -z "$branch" ]]; then
        branch=$(git branch -r | cut -d \/ -f 2- | sed -n -e '/^release\/[0-9].[0-9]$/p' | sort -n  | tail -n 1)
    fi

    echo "Using latest release branch: $branch"

    git checkout $branch || { echo " Failed to checkout branch. " ; exit 3 ; }
    git pull || { echo " Failed to pull branch $branch. " ; exit 3 ; }
	return 0
}

function checkout_all_projects() {
	checkout_project https://coderepo.mobilehealth.va.gov/scm/var/var-utility-resources.git
	checkout_project https://coderepo.mobilehealth.va.gov/scm/var/var-utility-web.git
	checkout_project https://coderepo.mobilehealth.va.gov/scm/var/var-web.git
	checkout_project https://coderepo.mobilehealth.va.gov/scm/var/var-resources.git
	checkout_project https://coderepo.mobilehealth.va.gov/scm/var/scheduling-manager-web.git
	checkout_project https://coderepo.mobilehealth.va.gov/scm/var/scheduling-manager-resources.git
    checkout_project https://coderepo.mobilehealth.va.gov/scm/var/facility-resources.git
    checkout_project https://coderepo.mobilehealth.va.gov/scm/var/var-messaging-microservice.git
}

checkout_all_projects
