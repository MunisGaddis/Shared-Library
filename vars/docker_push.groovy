def call(String credId, String imageName, String imageTag) {
    withCredentials([usernamePassword(
        credentialsId: credId,
        usernameVariable: 'dockerHubUser',
        passwordVariable: 'dockerHubPass'
    )]) {
        sh 'echo $dockerHubPass | docker login -u $dockerHubUser --password-stdin'
        sh "docker image tag ${imageName}:${imageTag} \$dockerHubUser/${imageName}:${imageTag}"
        sh "docker push \$dockerHubUser/${imageName}:${imageTag}"
    }
}


