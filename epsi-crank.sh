#!/usr/bin/env bash

export GITHUB_ACCOUNT="larrynorcrossva"
export GITHUB_PERSONAL_ACCESS_TOKEN="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"
cd ../projects
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/canscore-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/ctb-api-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/ctb-feature-flag-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/ctb-hsrm-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/ctb-web.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-content-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-cprs-api-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-cprs-web.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-ehrm-api-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-ehrm-web.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-eligibility-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-lighthouse-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-lighthouse-mock.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-local-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-mvi-data-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-orchestration-service.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-performance-tests.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-postgres-db.git
git clone https://$GITHUB_ACCOUNT:$GITHUB_PERSONAL_ACCESS_TOKEN@github.com/department-of-veterans-affairs/dst-vista-api-service.git