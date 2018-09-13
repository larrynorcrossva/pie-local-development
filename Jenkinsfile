node {
    stage('clone repo') {
        checkout(scm)
    }
    
    stage('preconditions') {
        fileExists 'app.env'
        sh 'which docker-compose'
    }

    stage('verify') {
        withEnv([
                "BASH_ENV=${env.WORKSPACE}/app.env"
        ]) {
            sh "docker-compose config --resolve-image-digests"
        }
    }
}

