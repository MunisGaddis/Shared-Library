def call() {
    withCredentials([usernamePassword(
        credentialsId: 'dockerHubCreds',
        usernameVariable: 'dockerHubUser',
        passwordVariable: 'dockerHubPass'
    )]) {
        sh 'echo $dockerHubPass | docker login -u $dockerHubUser --password-stdin'
        sh "docker image tag node-app:latest ${dockerHubUser}/node-app:latest"
        sh "docker push ${dockerHubUser}/node-app:latest"
    }
}

